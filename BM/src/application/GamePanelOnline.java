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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;



public class GamePanelOnline {
	public static final int HEIGHT = 595;
	public static final int WIDTH = 560;
	public static final int ROWS = 17;
	public static final int COLUMNS = 16;
	public static final int SQUARE_SIZE = WIDTH / COLUMNS;
	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private int gameOver=2;
	long timeToEnd=3000;
	long timeofDeath;
	long time;
	Timeline timeline;
	boolean EndofGame=false;
	private Scene scene;
	Canvas canvas;
	Group root;
	double Playerspeed;
	public static double imageX = 4, imageY = 4;
    public static Bomberman[] player=new Bomberman[4];
	public static int mainPlayerIndex=0;
	public static int mapIndex=0;
	public static int nbrOfPlayers=2;
	public static int nbrOfPlayersOffline=0;
	public static int offlineCounter=0;
	public static ArrayList<ArrayList<String>> mapLayout;
	String otherPlayersUpdates;

	

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
	loadMapFile();
	generateMap();//Map erstellen
	run();//Spiel starten
	
	  timeline = new Timeline(new KeyFrame(Duration.millis(1000.0/30), e -> 
	  {
		try {
			if(nbrOfPlayers > 1 && offlineCounter <150 ) 
			{
				if(nbrOfPlayers-nbrOfPlayersOffline == 1) {
					offlineCounter=offlineCounter+1;
				}else {
					offlineCounter=0;
				}
				System.out.println("condition   "+nbrOfPlayers +"///   "+ nbrOfPlayersOffline+"///"+offlineCounter);
				nbrOfPlayersOffline = 0;
				update();				
				time=System.currentTimeMillis();
				
				
			}
			else if(System.currentTimeMillis()-time>=timeToEnd)  
			{
				EndOfGame();
			
			}
			if(EndofGame) 
			{
				if(System.currentTimeMillis()-time>timeToEnd+4000) 
				{
					System.exit(0);
					
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
		Main.playmusic();
	}

void update() throws InterruptedException {
			//System.out.println("114GamePanel: "+Client.updateString);
			String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-GetUpdates-"+System.currentTimeMillis()+"-"+player[mainPlayerIndex].getEntityX()+"-"+player[mainPlayerIndex].getEntityY();
			System.out.println(messageout);
			String resp= "";
			resp=Client.accessServer(messageout);
			//System.out.println(resp);
			//druekeNachricht(resp);
			Client.updateString="";
			onlineUpdates(resp);
			messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-GetMap";
			System.out.println(messageout);
			resp= "";
			resp=Client.accessServer(messageout);
			//System.out.println(resp);
			//druekeNachricht(resp);
			Client.updateString="";
			InputManager.handlePlayerMovements(player[mainPlayerIndex]);
			onlineMapUpdates(resp);
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
		gc.setFont(new Font(12));
		gc.setFill(Color.BLACK); 
		  
		gc.drawImage(Ressourcen.IMAGES.BOT.getImage(),0*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
			gc.fillText("Room: "+Client.roomToJoin+"\n" + player[mainPlayerIndex].getName(),40, 15	);
	    	
	    gc.drawImage(Ressourcen.IMAGES.BOMBITEM.getImage(),4*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	    	gc.fillText(Integer.toString(player[mainPlayerIndex].bombanzahl),185, 21);
	    	gc.setFont(new Font(10));
	    	gc.fillText(otherPlayersUpdates,300,10);
	    	
	    	
				
	
		
	}

	private void drawObjekte(GraphicsContext gc) {
		//hier werden alle Objekte gezeichnet 
		// die Objekte sind in einer Liste von Listen gespeichert(gameObjects)
		for (int i = 0; i < GameObjects.gameObjects.size(); i++) {
			for (int j = 0; j < GameObjects.gameObjects.get(i).size(); j++) {
				
				Entities obj= GameObjects.gameObjects.get(i).get(j);
				obj.update();
			if(this.getPlayer().getDeath()) {
				if(gameOver==2) {
					gameOver=1;	
					Client.updateString =System.currentTimeMillis()+"-DEAD-0-0";
					Main.online= false;
					   String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
					   	String resp= "";
					   System.out.println(messageout);
						resp=Client.accessServer(messageout);
						System.out.println(resp);
					timeofDeath=System.currentTimeMillis();
					System.out.println("GameOver");
				}		
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
		otherPlayersUpdates ="";
		int i =1;
		while(i < message.length){
			System.out.println(message[i]+" :i "+i);
			switch(message[i]) {
			case "PLAYER":
				for(int j=0; j< Client.players.size();j++) {
					if( message[i+2].equals(player[j].getName()) ){
						System.out.println(player[j].getName()+":"+i);
						if(System.currentTimeMillis()-Double.parseDouble(message[i+1])>3000) {
							System.out.println("Player:"+player[j].getName()+" is disconnected");
							otherPlayersUpdates = otherPlayersUpdates + player[j].getName()+" is disconnected \n";
							nbrOfPlayersOffline=nbrOfPlayersOffline+1;
								
						}else {
							System.out.println(player[j].getName()+":"+message[i+3]);
							switch(message[i+3]) {
							case "UP": 
								player[j].moveUp();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is online \n";
								i=i+6;
								break;
							case "DOWN": 
								player[j].moveDown();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is online \n";
								i=i+6;
								break;
							case "RIGHT": 
								player[j].moveRight();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is online \n";
								i=i+6;
								break;
							case "LEFT": 
								player[j].moveLeft();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is online \n";
								i=i+6;
								break;
							case "STOP": 
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is online \n";
								i=i+6;
								break;
							case "DEAD": 
								otherPlayersUpdates=otherPlayersUpdates+player[j].getName()+" is dead \n";
								i=i+6;
							}
							if(message[i].equals("BOMB")) {
								System.out.println(message[i]+"/i"+i);
								if(player[j].getBombanzahl()>0) {
									Bomb bmb = new Bomb(Double.parseDouble(message[i+1]), Double.parseDouble(message[i+2]),1,Ressourcen.IMAGES.BOMBE.getImage(),player[j]);
									player[j].BombanzahlDown();
									GameObjects.spawn(bmb);	
									i=i+3;
								}
								//gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(), SQUARE_SIZE * Double.parseDouble( message[i+1]), SQUARE_SIZE * Double.parseDouble( message[i+2]),										SQUARE_SIZE, SQUARE_SIZE);								
								
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
void onlineMapUpdates(String resp) {
	System.out.println(resp);
	String[] message = resp.split("-");
		if(message[0].equals("ServerUpdates")) {
			for(int l=0;l<GameObjects.tileObjects.size();l++)
				if( GameObjects.tileObjects.get(l) instanceof Wall && GameObjects.tileObjects.get(l).isBreakable()) {
					GameObjects.tileObjects.remove(l);
					}
			nbrOfPlayers=Integer.parseInt( message[2]);
			String[] map = null;
			if(message.length>3) {
				map = message[3].split("/");
			int k=0;
			while(k < map.length) {
				
				 switch (mapIndex) {
                    case 0:     // Soft wall zerstoerbar
                    	Wall soft= new Wall(Double.parseDouble(map[k])* SQUARE_SIZE,Double.parseDouble(map[k+1])*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL1.getImage(),true);
                    	if(soft!=null) 
                    		GameObjects.spawn(soft);
                        break;
                    case 1:     // Soft wall zerstoerbar
                        Wall soft1= new Wall(Double.parseDouble(map[k])* SQUARE_SIZE,Double.parseDouble(map[k+1])*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL2.getImage(),true);
                       	if(soft1!=null) 
                       		GameObjects.spawn(soft1);
                           break;
                    case 2:     // Soft wall zerstoerbar
                        Wall soft2= new Wall(Double.parseDouble(map[k])* SQUARE_SIZE,Double.parseDouble(map[k+1])*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL3.getImage(),true);
                       	if(soft2!=null) 
                       		GameObjects.spawn(soft2);
                           break;
                    case 3:     // Soft wall zerstoerbar
                        Wall soft3= new Wall(Double.parseDouble(map[k])* SQUARE_SIZE,Double.parseDouble(map[k+1])*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL4.getImage(),true);
                       	if(soft3!=null) 
                       		System.out.println();
                       		GameObjects.spawn(soft3);
                           break;
                    case 4:     // Soft wall zerstoerbar
                        Wall soft4= new Wall(Double.parseDouble(map[k])* SQUARE_SIZE,Double.parseDouble(map[k+1])*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL5.getImage(),true);
                       	if(soft4!=null) 
                       		GameObjects.spawn(soft4);
                           break;

				 }
					
            		k=k+2;
			}				            	
	}
}
}
	 private static void loadMapFile()  {
		
        bufferedReader = new BufferedReader(Ressourcen.file_Server[mapIndex]);
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

	private void generateMap() {
		System.out.println( " nbr :"+Client.players.size());
		System.out.println( " nbr :"+GamePanelOnline.player.length);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                switch (mapLayout.get(y).get(x)) {
                                         
                    case ("T"):   //Hardwall.
                       	Wall hard1= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL1.getImage(),false);
                    	if(hard1!=null)
                    		GameObjects.spawn(hard1);                    
                           break;
     
                    case ("K"):   //Hardwall.
                       	Wall hard2= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL2.getImage(),false);
                       	if(hard2!=null)
                       		GameObjects.spawn(hard2);                    
                           break;
                    
                           
                    case ("A"):   //Hardwall.
                       	Wall hard3= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL3.getImage(),false);
                       if(hard3!=null)
                       GameObjects.spawn(hard3);                    
                           break;
                   
                    case ("U"):   //Hardwall.
                       	Wall hard4= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL4.getImage(),false);
                    	if(hard4!=null)
                    		GameObjects.spawn(hard4);                    
                           break;
                   
                           
                    case ("R"):   //Hardwall.
                       	Wall hard5= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.HARDWALL5.getImage(),false);
                       	if(hard5!=null)
                       		GameObjects.spawn(hard5);     
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
                    		nbrOfPlayers=3;
                    		GamePanelOnline.player[2] = new Bomberman(x*SQUARE_SIZE,y* SQUARE_SIZE,Ressourcen.IMAGES.playerDown[2][0],true);
                    		GamePanelOnline.player[2].setName(Client.players.get(2));
                    		GameObjects.spawn(GamePanelOnline.player[2]);   
                    		GamePanelOnline.player[2].setPlayerFarbe(2);                    	
                    	}
                        break;
                    case ("4"):     // Player 3
                    	if(Client.players.size()>3) {
                    		nbrOfPlayers=4;
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


