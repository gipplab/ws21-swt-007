package application;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import application.Objects.Bomb;
import application.Objects.Bomberman;
import application.Objects.Bot;
import application.Objects.Entities;
import application.Objects.GameObjects;
import application.Objects.Wall;
import application.SceneControllers.SinglePlayPanelController;
import javafx.animation.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;


//Koordinaten und wichtige Attribute des Spieles intialisieren. 
public class GamePanel {
	public static final int HEIGHT = 595;
	public static final int WIDTH = 560;
	public static final int ROWS = 17;
	public static final int COLUMNS = 16;
	public static final int SQUARE_SIZE = WIDTH / COLUMNS;
	long timeofDeath;
	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private int gameOver=0;
	private Scene scene;
	Canvas canvas;
	Group root;
	double Playerspeed;
	public static double imageX = 4, imageY = 4;
	public static Bomberman player;
	SinglePlayPanelController pname;
	public static Bot bot;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static int mapIndex=0;
	 Timeline timeline;
	 boolean EndofGame=false;
	public GamePanel() {
		System.out.println(SQUARE_SIZE);

		root = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		scene = new Scene(root, WIDTH, HEIGHT);
		KeysHandler.attachEventHandlers(scene);
		// scene.setOnKeyPressed(this);
		gc = canvas.getGraphicsContext2D();

	}

	public void init() throws IOException {
		
		Ressourcen.readFiles();
		GameObjects.init();
		Playerspeed = 0.15;
		loadMapFile();
		generateMap();//Map erstellen
		run();//Spiel starten
		
		  timeline = new Timeline(new KeyFrame(Duration.millis(1000.0/60), e -> {
			try {
				if(gameOver==0&& timeofDeath+2000 < System.currentTimeMillis())
					update();
				else  {
					EndOfGame();
				
				}
				if(EndofGame)
					if(System.currentTimeMillis()>timeofDeath+5000) {
						System.exit(0);}
					
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));
	        timeline.setCycleCount(Animation.INDEFINITE);
	     
	        timeline.play();
	      

	};

void 	EndOfGame() throws InterruptedException {
		
		GameObjects.gameObjects.clear();
		update();
		if(gameOver == 1) {
			gc.drawImage(Ressourcen.IMAGES.GAMEOVER.getImage(),0,0, HEIGHT, WIDTH);
		}
		else if (gameOver == 2) {
			gc.drawImage(Ressourcen.IMAGES.WIN.getImage(),0,0, HEIGHT, WIDTH);
		}

		EndofGame=true;
	}

public Bomberman getPlayer() {
		return player;
	}

	public Scene getScene() {
		return scene;
	}

	public double getSQUARE_SIZE() {
		return SQUARE_SIZE;
	}

	public double getWidth() {
		return WIDTH;
	}

	public double getHeight() {
		return HEIGHT;
	}

	public void run() {
		drawObjekte(gc);
	}
	//Aktualisierung der Objekte im Spiel.
	private void update() throws InterruptedException {
		InputManager.handlePlayerMovements(player);
		drawBackground(gc);
		drawObjekte(gc);
		drawBomb(gc);
		getScore(gc);

	}
	private void drawBackground(GraphicsContext gc) {
	  for(int i=0 ; i<ROWS; i++) 
		for(int j=0;j<COLUMNS;j++) {
			gc.setFill(Color.WHITE);
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);
			}
					
	}
	


	private void getScore(GraphicsContext gc) {
		  gc.setFill(Color.BLACK); 
		  
		  gc.drawImage(Ressourcen.IMAGES.BOT.getImage(),0*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
		   gc.fillText("Welcome Back \n" + pname.name,40, 15	);
		  
		  gc.drawImage(Ressourcen.IMAGES.HERZITEM.getImage(),4*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	    	gc.fillText(Integer.toString(player.health),185, 21);
	    	
	     gc.drawImage(Ressourcen.IMAGES.BOMBITEM.getImage(),6*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	       gc.fillText(Integer.toString(player.bombanzahl),255, 21);
	    	
	     gc.drawImage(Ressourcen.IMAGES.SPEEDITEM.getImage(),8*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	 		gc.fillText(Double.toString(player.speed),325,21);
		
	 		 gc.drawImage(Ressourcen.IMAGES.FLAMMEITEM.getImage(),10*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
		 		gc.fillText(Integer.toString(player.explosion),395,21);
				
		
		gc.fillText("SCORE : " +  Integer.toString(bot.killbot),440, 21);
	
		
	}
	 
	private void drawObjekte(GraphicsContext gc) {
		//hier werden alle Objekte gezeichnet 
		// die Objekte sind in einer Liste von Listen gespeichert(gameObjects)
		for (int i = 0; i < GameObjects.gameObjects.size(); i++) {
			for (int j = 0; j < GameObjects.gameObjects.get(i).size(); j++) {				
			     Entities obj= GameObjects.gameObjects.get(i).get(j);
			     obj.update();
			     if(obj.getDeath() && obj.isPlayer()) {
			    	 //

			    	 gameOver=1;
			    	 timeofDeath= System.currentTimeMillis();
			    	 System.out.println("GameOver");
			     }else if(obj instanceof Bot) {
			    	if( GameObjects.bomberObjects.size()==1 ) {
			    		 timeofDeath= System.currentTimeMillis();
			    		 gameOver=2;
			    	
			    	}
			    	 
			     }
			     
		       if(!obj.getDeath()) {
				 obj.drawImage(gc);
		          }
			}
		}
	}


	private void drawBomb(GraphicsContext gc) {
		// draw Bombe in der richtigen Koordinaten.
		for (Bomb i : Objekte) {
			gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(), SQUARE_SIZE * i.getX(), SQUARE_SIZE * i.getY(),
					SQUARE_SIZE, SQUARE_SIZE);
		}
	}
	 
	//ausgewählte Map importieren.
	 private static void loadMapFile()  {
		
        bufferedReader = new BufferedReader(Ressourcen.file[mapIndex]);
	    mapLayout = new ArrayList<>();
	 
        try {
            String currentLine;
         int i=1;
            while ((currentLine = bufferedReader.readLine()) != null) {
               if (currentLine.isEmpty()) {
                  continue;
               }

                mapLayout.add(new ArrayList<>(Arrays.asList(currentLine.split(","))));
             
            }   
          
        } catch (IOException | NullPointerException e) {
            System.out.println(e + "Error beim LoadMapFile()");
            e.printStackTrace();
        }
      
	 }
	 
	//Map mit allen Objekten erstellen.
	private void generateMap() {
	      
    
	    for (int y = 0; y < ROWS	; y++) {
            for (int x = 0; x < COLUMNS; x++) {
           switch (mapLayout.get(y).get(x)) {
                    case ("S"):     // Soft wall zerstoerbar
                     Wall soft= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL.getImage());
                    if(soft!=null) 
                    GameObjects.spawn(soft);
                        break;

                    case ("H"):   //Hardwall.
                    	Wall hard= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL.getImage());
                    if(hard!=null)
                    GameObjects.spawn(hard);                    
                        break;

                    case ("1"):     // Player 1
                //   GamePanel.player= new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.PLAYER1.getImage(),true);
                               
                    	GamePanel.player= new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[SinglePlayPanelController.playerFarbe][0],true);
                   		GamePanel.player.setPlayerFarbe(SinglePlayPanelController.playerFarbe);
                    	GameObjects.spawn(GamePanel.player);                    
                    break;
                    case ("B"):     // BOT
                        Bot bot= new Bot(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.BOT.getImage(),false);
                       if(bot!=null) 
                       GameObjects.spawn(bot);
                           break;
               
                    default:
                    	
                        break;
                }
            }
        }
    }
	
 

}


