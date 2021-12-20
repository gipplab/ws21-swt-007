package application;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import application.Objects.Bomb;
import application.Objects.Bomberman;
import application.Objects.Entities;
import application.Objects.GameObjects;
import application.Objects.Wall;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class GamePanel {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int ROWS = 20;
	public static final int COLUMNS = ROWS;
	public static final double SQUARE_SIZE = WIDTH * 1.0 / ROWS;

	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private boolean gameOver;
	private Scene scene;
	Canvas canvas;
	Group root;
	double Playerspeed;
	public static double imageX = 4, imageY = 4;
	public Bomberman player;
	public static ArrayList<ArrayList<String>> mapLayout;
	
	

	public GamePanel() {

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
		AnimationTimer timeline = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				update();
				 try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		timeline.start();

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

	private void update() {
		InputManager.handlePlayerMovements(this.player);
		drawBackground(gc);
		drawObjekte(gc);
		drawBomb(gc);

	}
	private void drawBackground(GraphicsContext gc) {
	  for(int i=0 ; i<ROWS; i++) 
		for(int j=0;j<COLUMNS;j++) {
			gc.setFill(Color.WHITE);
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);}
					
	}

	private void drawObjekte(GraphicsContext gc) {
		//hier werden alle Objekte gezeichnet 
		// die Objekte sind in einer Liste von Listen gespeichert(gameObjects)
		for (int i = 0; i < GameObjects.gameObjects.size(); i++) {
			for (int j = 0; j < GameObjects.gameObjects.get(i).size(); j++) {
				
				Entities obj= GameObjects.gameObjects.get(i).get(j);
				Bomb ob= new Bomb(5,5,Ressourcen.IMAGES.BOMBE.getImage());
				obj.drawImage(gc);

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
		
        bufferedReader = new BufferedReader(Ressourcen.file);
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
                    this.player= new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.PLAYER1.getImage());
                    GameObjects.spawn(player);                    
                    break;

                    default:
                    	
                        break;
                }
            }
        }
    }
// Methode isFree gibt true zuerueck ,wenn SQUARE kein Wall enthaelt.	
      public static boolean isFree(double nextX, double nextY) {
	     boolean  frei = true;
	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	      
	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {		 
		 if(GameObjects.tileObjects.get(i).getEntityImage().equals(Ressourcen.IMAGES.HARDWALL.getImage()))
			 if( (GameObjects.tileObjects.get(i).getEntityX()==nextX_1* SQUARE_SIZE  && GameObjects.tileObjects.get(i).getEntityY()==nextY_1* SQUARE_SIZE)||
				 (GameObjects.tileObjects.get(i).getEntityX()==nextX_2* SQUARE_SIZE && GameObjects.tileObjects.get(i).getEntityY()==nextY_2* SQUARE_SIZE)||
				 (GameObjects.tileObjects.get(i).getEntityX()==nextX_3* SQUARE_SIZE && GameObjects.tileObjects.get(i).getEntityY()==nextY_3* SQUARE_SIZE)||
				 (GameObjects.tileObjects.get(i).getEntityX()==nextX_4* SQUARE_SIZE && GameObjects.tileObjects.get(i).getEntityY()==nextY_4* SQUARE_SIZE) )
				 frei = false;
	    } 
	    return frei;
	/* 	    
	     return !(GamePanel.mapLayout.get(nextY_1).get(nextX_1).contentEquals("H")||GamePanel.mapLayout.get(nextY_1).get(nextX_1).contentEquals("S") ||
	    	  GamePanel.mapLayout.get(nextY_2).get(nextX_2).contentEquals("H")||GamePanel.mapLayout.get(nextY_2).get(nextX_2).contentEquals("S") ||
	    	  GamePanel.mapLayout.get(nextY_3).get(nextX_3).contentEquals("H")||GamePanel.mapLayout.get(nextY_3).get(nextX_3).contentEquals("S") ||
	    	  GamePanel.mapLayout.get(nextY_4).get(nextX_4).contentEquals("H")||GamePanel.mapLayout.get(nextY_4).get(nextX_4).contentEquals("S"));
       */
    }  

}


