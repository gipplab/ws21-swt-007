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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//Koordinaten und wichtige Attribute Spieles intialisieren. 
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
	public static Bomberman player;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static int mapIndex=0;
	Timeline timeline;
	long time;
	long timeToEnd=3000;
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
		loadMapFile();
		generateMap();//Map erstellen
		run();//Spiel starten
		
		  timeline = new Timeline(new KeyFrame(Duration.millis(1000.0/60), e -> 
		  {
			try {
				if(gameOver==0&& timeofDeath+2000 < System.currentTimeMillis()) 
				{
					update();
					time=System.currentTimeMillis();
				}
				else if(System.currentTimeMillis()-time>=timeToEnd)  
				{
					EndOfGame();
				
				}
				if(EndofGame) 
				{
					if(System.currentTimeMillis()>timeofDeath+5000) 
					{
						System.exit(0);
//						Stage primaryStage= new Stage();
//					 	root = FXMLLoader.load(getClass().getResource("Scenes/SinglePlayPanel.fxml"));
//				    	scene = new Scene(root);
//				    	primaryStage.setScene(scene);
//						primaryStage.setResizable(false);
//						primaryStage.show();
						
					}
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}));
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play(); 

}

void EndOfGame() throws InterruptedException {
		
		GameObjects.gameObjects.clear();
		update();
		if(gameOver == 1) {
			gc.drawImage(Ressourcen.IMAGES.GAMEOVER.getImage(),0,0, 16*SQUARE_SIZE, 17*SQUARE_SIZE);
		}
		else if (gameOver == 2) {
			gc.drawImage(Ressourcen.IMAGES.WIN.getImage(),0,0, 16*SQUARE_SIZE, 17*SQUARE_SIZE);
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
private void drawBackground(GraphicsContext gc) 
{
	// Meer  #b1e8fe
	//ORANGE
	if(mapIndex==0||mapIndex==4)
		gc.setFill(Color.WHITE);
	else if(mapIndex==1)
		gc.setFill(Color.valueOf("#FFFBD3"));
	else if (mapIndex==2||mapIndex==3)
		gc.setFill(Color.valueOf("#b1e8fe"));
	
	gc.fillRect(0,0 ,COLUMNS*SQUARE_SIZE, ROWS*SQUARE_SIZE);	
	//gc.drawImage(Ressourcen.IMAGES.BG5.getImage(),0,0, 16*SQUARE_SIZE, 17*SQUARE_SIZE);
}

private void getScore(GraphicsContext gc) {
		  gc.setFill(Color.BLACK); 
		  
		  gc.drawImage(Ressourcen.IMAGES.BOT.getImage(),0*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
		   gc.fillText("Welcome Back \n" + SinglePlayPanelController.name,40, 15	);
		  
		  gc.drawImage(Ressourcen.IMAGES.HERZITEM.getImage(),4*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	    	gc.fillText(Integer.toString(player.health),185, 21);
	    	
	     gc.drawImage(Ressourcen.IMAGES.BOMBITEM.getImage(),6*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	       gc.fillText(Integer.toString(player.bombanzahl),255, 21);
	    	
	     gc.drawImage(Ressourcen.IMAGES.SPEEDITEM.getImage(),8*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	 		gc.fillText(Double.toString(player.speed),325,21);
		
	 		 gc.drawImage(Ressourcen.IMAGES.FLAMMEITEM.getImage(),10*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
		 		gc.fillText(Integer.toString(player.explosion),395,21);
				
		
		gc.fillText("SCORE : " +  Integer.toString(Bot.killbot),440, 21);
	
		
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
	 
	//ausgehlte Map importieren.
private static void loadMapFile()  {
		
        bufferedReader = new BufferedReader(Ressourcen.file[mapIndex]);
	    mapLayout = new ArrayList<>();
	 
        try {
            String currentLine;

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
                     Wall soft= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL.getImage(),true);
                    if(soft!=null) 
                    GameObjects.spawn(soft);
                        break;

                    case ("H"):   //Hardwall.
                    	Wall hard= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL.getImage(),false);
                    if(hard!=null)
                    GameObjects.spawn(hard);                    
                        break;
                    case ("P"):     // Soft wall zerstoerbar
                        Wall soft1= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL1.getImage(),true);
                       if(soft1!=null) 
                       GameObjects.spawn(soft1);
                           break;
                           
                    case ("T"):   //Hardwall.
                       	Wall hard1= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL1.getImage(),false);
                       if(hard1!=null)
                       GameObjects.spawn(hard1);                    
                           break;
                           
                    case ("E"):     // Soft wall zerstoerbar
                        Wall soft2= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL2.getImage(),true);
                       if(soft2!=null) 
                       GameObjects.spawn(soft2);
                           break;
                           
                    case ("K"):   //Hardwall.
                       	Wall hard2= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL2.getImage(),false);
                       if(hard2!=null)
                       GameObjects.spawn(hard2);                    
                           break;
                    case ("A"):     // Soft wall zerstoerbar
                        Wall soft3= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL3.getImage(),true);
                       if(soft3!=null) 
                       GameObjects.spawn(soft3);
                           break;
                           
                    case ("L"):   //Hardwall.
                       	Wall hard3= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL3.getImage(),false);
                       if(hard3!=null)
                       GameObjects.spawn(hard3);                    
                           break;
                    case ("Q"):     // Soft wall zerstoerbar
                        Wall soft4= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL4.getImage(),true);
                       if(soft4!=null) 
                       GameObjects.spawn(soft4);
                           break;
                           
                    case ("U"):   //Hardwall.
                       	Wall hard4= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL4.getImage(),false);
                       if(hard4!=null)
                       GameObjects.spawn(hard4);                    
                           break;
                    case ("M"):     // Soft wall zerstoerbar
                        Wall soft5= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL5.getImage(),true);
                       if(soft5!=null) 
                       GameObjects.spawn(soft5);
                           break;
                           
                    case ("R"):   //Hardwall.
                       	Wall hard5= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL5.getImage(),false);
                       if(hard5!=null)
                       GameObjects.spawn(hard5);     
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


