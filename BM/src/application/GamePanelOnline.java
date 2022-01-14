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
	private boolean gameOver=false;
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
				
				update();				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}));
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play();
	

	};

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
		//System.out.println("114GamePanel: "+Client.updateString);
		String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-GetUpdates-"+player[mainPlayerIndex].getEntityX()+"-"+player[mainPlayerIndex].getEntityY();
		System.out.println(messageout);
		String resp= "";
		resp=Client.accessServer(messageout);
		//System.out.println(resp);
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
			if(obj.getDeath() && obj instanceof Bomberman) {
				gameOver=true;
			System.out.println("GameOber");
			System.exit(0);
		
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
		System.out.println(resp);
		String[] message = resp.split("-");
		int i =0;
		while(i < message.length){
			switch(message[i]) {
			case "PLAYER":
				for(int j=0; j< Client.players.size();j++) {
					if( message[i+1].equals(player[j].getName()) ){
						if(message[i+2].equals("NACTIF")) {
							i=i+3;
						}else {
							switch(message[i+2]) {
							case "UP": 
								player[j].moveUp();
								player[j].setEntityX(Double.parseDouble( message[i+3]));
								player[j].setEntityY(Double.parseDouble( message[i+4]));
								i=i+5;
								break;
							case "DOWN": 
								player[j].moveDown();
								player[j].setEntityX(Double.parseDouble( message[i+3]));
								player[j].setEntityY(Double.parseDouble( message[i+4]));
								i=i+5;
								break;
							case "RIGHT": 
								player[j].moveRight();
								player[j].setEntityX(Double.parseDouble( message[i+3]));
								player[j].setEntityY(Double.parseDouble( message[i+4]));
								i=i+5;
								break;
							case "LEFT": 
								player[j].moveLeft();
								player[j].setEntityX(Double.parseDouble( message[i+3]));
								player[j].setEntityY(Double.parseDouble( message[i+4]));
								i=i+5;
								break;
							case "STOP": 
								player[j].setEntityX(Double.parseDouble( message[i+3]));
								player[j].setEntityY(Double.parseDouble( message[i+4]));
								i=i+5;
								break;
							}						
							
							
						}
						
						break;
					}					
				}							
				break;
			}
			i++;
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
                    default:
                    	
                        break;
                }
            }
        }
    }

}


