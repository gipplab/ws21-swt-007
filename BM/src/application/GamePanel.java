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



public class GamePanel {
	public static final int WIDTH = 560;
	public static final int HEIGHT = 560;
	public static final int ROWS = 16;
	public static final int COLUMNS = ROWS;
	public static final int SQUARE_SIZE = WIDTH / ROWS;

	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private boolean gameOver=false;
	private Scene scene;
	Canvas canvas;
	Group root;
	double Playerspeed;
	public static double imageX = 4, imageY = 4;
	public static Bomberman player;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static int mapIndex=0;
	

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
		generateMap();
		run();
		
		 Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0/60), e -> {
			try {
				update();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play();
		
//		AnimationTimer timeline = new AnimationTimer() {
//
//			
//			@Override
//			public void handle(long arg0) {
//				try {
//					if(!gameOver)
//					{
//
//					update();
//					Thread.sleep(100);
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		};
//		timeline.start();

	};

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

	private void update() throws InterruptedException {
		InputManager.handlePlayerMovements(player);
		drawBackground(gc);
		drawObjekte(gc);
		drawBomb(gc);

	}
	private void drawBackground(GraphicsContext gc) {
	  for(int i=0 ; i<ROWS; i++) 
		for(int j=0;j<COLUMNS;j++) {
			gc.setFill(Color.WHITE);
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);
			}
					
	}

	private void drawObjekte(GraphicsContext gc) {
		//hier werden alle Objekte gezeichnet 
		// die Objekte sind in einer Liste von Listen gespeichert(gameObjects)
		for (int i = 0; i < GameObjects.gameObjects.size(); i++) {
			for (int j = 0; j < GameObjects.gameObjects.get(i).size(); j++) {				
			     Entities obj= GameObjects.gameObjects.get(i).get(j);
			     obj.update();
			     if(obj.getDeath() && obj.isPlayer()) {
				gameOver=true;
			        System.out.println("GameOver");
		             // System.exit(0);
			     }
		      //     if(!obj.getDeath()) {
				 obj.drawImage(gc);
		      //     }
			}
		}
	}


	private void drawBomb(GraphicsContext gc) {
		// draw Player in Anfangscoordinate
		for (Bomb i : Objekte) {
			gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(), SQUARE_SIZE * i.getX(), SQUARE_SIZE * i.getY(),
					SQUARE_SIZE, SQUARE_SIZE);
		}
	}
	 
	
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
	 

	private void generateMap() {
	      
    
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < ROWS; x++) {
                switch (mapLayout.get(y).get(x)) {
                    case ("S"):     // Soft wall zerstoerbar
                     Wall soft= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL.getImage());
                    if(soft!=null) 
                    GameObjects.spawn(soft);
                        break;

                    case ("H"):    
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
                    case ("B"):     // Soft wall zerstoerbar
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


