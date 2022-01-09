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
import application.SceneControllers.SinglePlayPanelController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;



public class GamePanelOnline {
	public static final int WIDTH = 560;
	public static final int HEIGHT = 560;
	public static final int ROWS = 16;
	public static final int COLUMNS = ROWS;
	public static final int SQUARE_SIZE = WIDTH / ROWS;
	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private int gameOver=0;
	long timeofDeath;
	boolean EndofGame=false;
	private Scene scene;
	Canvas canvas;
	Group root;
	double Playerspeed;
	public static double imageX = 4, imageY = 4;
	
    public static Bomberman[] player=new Bomberman[4];
	public static int mainPlayerIndex=0;
	public static ArrayList<ArrayList<String>> mapLayout;

	

	public GamePanelOnline() {
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
				if(gameOver==0 && timeofDeath+3000 < System.currentTimeMillis())
					update();
				else  {
					EndOfGame();
				
				}
				if(EndofGame)
					if(System.currentTimeMillis()>timeofDeath+5000) {
						System.exit(0);
						}
								
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}));
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play();
	

	};
	
void EndOfGame() throws InterruptedException {
	GameObjects.gameObjects.clear();
	update();
	if(this.getPlayer().death()) {
		gc.drawImage(Ressourcen.IMAGES.GAMEOVER.getImage(),0,0, HEIGHT, COLUMNS);
	}
	else {
		gc.drawImage(Ressourcen.IMAGES.WIN.getImage(),0,0, HEIGHT, COLUMNS);
	}
	
	EndofGame=true;
	
	
} 

	public Bomberman getPlayer() {
		return player[mainPlayerIndex];
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
		String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-Updates-"+Client.updateString;
		String resp= "";
		resp=Client.accessServer(messageout);
		System.out.println(resp);
		onlineUpdates(resp);
		//druekeNachricht(resp);
		Client.updateString="";
		InputManager.handlePlayerMovements(player[mainPlayerIndex]);
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
				obj.update();
			if(this.getPlayer().death()) {
				gameOver=1;
				timeofDeath=System.currentTimeMillis();
			System.out.println("GameOber");
			
			}
			if(!obj.getDeath()) {
			
				obj.drawImage(gc);}

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
	
	void onlineUpdates(String resp) {
		String[] message = resp.split("-");
		for (int i=0;i< message.length;i+=2 ) {
			for (int j=0;j< Client.players.size();j++ )
			if(message[i].equals(player[j].getName())) {
				if(message[i+1].equals("Ónline"))
				{
					//System.out.println("IS ONLINE "+player[j].getName());
					if(message[i+1].equals("NoUpdates"))
					{
						//System.out.println("HAS NO UPDATES "+player[j].getName());
					}
				}
				String[] movesUpdates = message[i+2].split("/");
			
				for (int k=0;k< movesUpdates.length;k=k+3 ) {
				     switch (movesUpdates[k]) {
	                    case ("UP"):
	                   player[j].setEntityX(Double.parseDouble(movesUpdates[k+1]));
	                   player[j].setEntityY(Double.parseDouble(movesUpdates[k+2]));
	                   player[j].moveUp();
	                        break;

	                    case ("RIGHT"): 
	                    	player[j].setEntityX(Double.parseDouble(movesUpdates[k+1]));
		                    player[j].setEntityY(Double.parseDouble(movesUpdates[k+2]));
	                    	player[j].moveRight();                 
	                        break;

	                    case ("DOWN"): 
	                    	player[j].setEntityX(Double.parseDouble(movesUpdates[k+1]));
		                    player[j].setEntityY(Double.parseDouble(movesUpdates[k+2]));
	                    	player[j].moveDown();             
	                    break;
	                    case ("LEFT"): 
	                    	player[j].setEntityX(Double.parseDouble(movesUpdates[k+1]));
		                    player[j].setEntityY(Double.parseDouble(movesUpdates[k+2]));
	                    	player[j].moveLeft();              
	                        break;
	                    case ("BOMB"):  
	                    		Bomb b= new Bomb( Double.parseDouble(movesUpdates[k+1]) , Double.parseDouble(movesUpdates[k+2]),player[j].getExplosion() , Ressourcen.IMAGES.BOMBE.getImage(),player[j] );
	             	   			b.BombCollision(Double.parseDouble(movesUpdates[k+1]),Double.parseDouble(movesUpdates[k+2]));
	             	   			player[j].BombanzahlDown();
	             	   			//Client.updateString =Client.updateString+"BOMB/"+b.getX()+"/"+b.getY()+"/";
	             	   		System.out.println("HAS NO UPDATES "+player[j].getName());
	             	   			GameObjects.spawn(b);	             	   		       
	                        break;
	                        
	                    default:
	                    	
	                        break;
	                }
				}
			}
		}
}
	
	 private static void loadMapFile()  {
		
        bufferedReader = new BufferedReader(Ressourcen.file_Server);
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
		System.out.println( " nbr :"+Client.players.size());
		System.out.println( " nbr :"+GamePanelOnline.player.length);
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
                    	GamePanelOnline.player[0]= new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[0][0],true);
                    	GamePanelOnline.player[0].setName(Client.players.get(0));
                     	GamePanelOnline.player[0].setPlayerFarbe(0);
                    	GameObjects.spawn(GamePanelOnline.player[0]);                    
                    break;
                    case ("2"):     // Player 2
                    	GamePanelOnline.player[1] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[1][0],true);
                    	GamePanelOnline.player[1].setName(Client.players.get(1));
                    	GamePanelOnline.player[1].setPlayerFarbe(1);
                        GameObjects.spawn(GamePanelOnline.player[1]);   
                        
                        break;
                    case ("3"):     // Player 3
                    	if(Client.players.size()>2) {
                    		GamePanelOnline.player[2] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[2][0],true);
                    		GamePanelOnline.player[2].setName(Client.players.get(2));
                    		GameObjects.spawn(GamePanelOnline.player[2]);   
                    		GamePanelOnline.player[2].setPlayerFarbe(2);                    	
                    	}
                        break;
                    case ("4"):     // Player 3
                    	if(Client.players.size()>3) {
                    		GamePanelOnline.player[3] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[3][0],true);
                    		GamePanelOnline.player[3].setName(Client.players.get(3));
                    		GameObjects.spawn(GamePanelOnline.player[3]);   
                    		GamePanelOnline.player[3].setPlayerFarbe(3);                   	
                    	}                   
                        break;
<<<<<<< HEAD
=======
                    case ("3"):     // Player 3
                    	GamePanelOnline.player[2] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[1][0],true);
                        GamePanelOnline.player[2].setName(Client.players.get(2));
                        GameObjects.spawn(GamePanelOnline.player[2]);     
                        GamePanelOnline.player[2].setPlayerFarbe(2);
                        break;
                    case ("4"):     // Player 3
                    	GamePanelOnline.player[3] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[1][0],true);
                        GamePanelOnline.player[3].setName(Client.players.get(3));
                        GameObjects.spawn(GamePanelOnline.player[3]);     
                        GamePanelOnline.player[3].setPlayerFarbe(3);
                        break;
>>>>>>> main
                    default:
                        break;
                }
            }
        }
    }
	

}


