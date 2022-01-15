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



public class GamePanelOnline {
	public static final int HEIGHT = 595;
	public static final int WIDTH = 560;
	public static final int ROWS = 17;
	public static final int COLUMNS = 16;
	public static final int SQUARE_SIZE = WIDTH / COLUMNS;
	private static BufferedReader bufferedReader;
	public static ArrayList<Bomb> Objekte = new ArrayList<>();
	private GraphicsContext gc;
	private int gameOver=0;
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
	}

<<<<<<< HEAD
	private void update() throws InterruptedException {
		//System.out.println("114GamePanel: "+Client.updateString);
		String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-GetUpdates-"+System.currentTimeMillis()+"-"+player[mainPlayerIndex].getEntityX()+"-"+player[mainPlayerIndex].getEntityY();
		System.out.println(messageout);
		String resp= "";
		resp=Client.accessServer(messageout);
		//System.out.println(resp);
		
		//druekeNachricht(resp);
		Client.updateString="";
		InputManager.handlePlayerMovements(player[mainPlayerIndex]);
		onlineUpdates(resp);
		drawBackground(gc);
		drawObjekte(gc);
		drawBomb(gc);
		

	} 
	private void drawBackground(GraphicsContext gc) {
	  for(int i=0 ; i<ROWS; i++) 
		for(int j=0;j<COLUMNS;j++) {
=======
void update() throws InterruptedException {
			//System.out.println("114GamePanel: "+Client.updateString);
			String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-GetUpdates-"+System.currentTimeMillis()+"-"+player[mainPlayerIndex].getEntityX()+"-"+player[mainPlayerIndex].getEntityY();
			System.out.println(messageout);
			String resp= "";
			resp=Client.accessServer(messageout);
			//System.out.println(resp);
			//druekeNachricht(resp);
			Client.updateString="";
			InputManager.handlePlayerMovements(player[mainPlayerIndex]);
			onlineUpdates(resp);
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
>>>>>>> Yazan-Main
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
	    	gc.fillText(Integer.toString(player[mainPlayerIndex].health),185, 21);
	    	
	    gc.drawImage(Ressourcen.IMAGES.BOMBITEM.getImage(),6*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	    	gc.fillText(Integer.toString(player[mainPlayerIndex].bombanzahl),255, 21);
	    	
	    gc.drawImage(Ressourcen.IMAGES.SPEEDITEM.getImage(),8*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
	 		gc.fillText(Double.toString(player[mainPlayerIndex].speed),325,21);
		
	 	gc.drawImage(Ressourcen.IMAGES.FLAMMEITEM.getImage(),10*SQUARE_SIZE,0* SQUARE_SIZE,SQUARE_SIZE, SQUARE_SIZE);
			gc.fillText(Integer.toString(player[mainPlayerIndex].explosion),395,21);
				
		
		gc.fillText("SCORE : " +  Integer.toString(Bot.killbot),440, 21);
	
		
	}

	private void drawObjekte(GraphicsContext gc) {
		//hier werden alle Objekte gezeichnet 
		// die Objekte sind in einer Liste von Listen gespeichert(gameObjects)
		for (int i = 0; i < GameObjects.gameObjects.size(); i++) {
			for (int j = 0; j < GameObjects.gameObjects.get(i).size(); j++) {
				
				Entities obj= GameObjects.gameObjects.get(i).get(j);
				obj.update();
<<<<<<< HEAD
			if(obj.getDeath() && obj instanceof Bomberman) {
				gameOver=true;
			System.out.println("GameOver");
			System.exit(0);
		
=======
			if(this.getPlayer().getDeath()) {
				gameOver=1;
				timeofDeath=System.currentTimeMillis();
				System.out.println("GameOver");
			
>>>>>>> Yazan-Main
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
	
<<<<<<< HEAD
	void onlineUpdates(String resp) {
=======
void onlineUpdates(String resp) {
>>>>>>> Yazan-Main
		System.out.println(resp);
		String[] message = resp.split("-");
		int i =1;
		while(i < message.length){
			switch(message[i]) {
			case "PLAYER":
				for(int j=0; j< Client.players.size();j++) {
					if( message[i+2].equals(player[j].getName()) ){
						if(System.currentTimeMillis()-Double.parseDouble(message[i+1])>3000) {
							System.out.println("Player:"+player[j].getName()+" is disconnected");
						}else {
							switch(message[i+3]) {
							case "UP": 
								player[j].moveUp();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								i=i+5;
								break;
							case "DOWN": 
								player[j].moveDown();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								i=i+5;
								break;
							case "RIGHT": 
								player[j].moveRight();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								i=i+5;
								break;
							case "LEFT": 
								player[j].moveLeft();
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								i=i+5;
								break;
							case "STOP": 
								player[j].setEntityX(Double.parseDouble( message[i+4]));
								player[j].setEntityY(Double.parseDouble( message[i+5]));
								i=i+6;
								break;
							}
							System.out.println(message[i]+"/i"+i);
							if(message[i].equals("BOMB")) {
								System.out.println(message[i]+"/i"+i);
								if(player[j].getBombanzahl()>0) {
									Bomb bmb = new Bomb(Double.parseDouble(message[i+1]), Double.parseDouble(message[i+2]),1,Ressourcen.IMAGES.BOMBE.getImage(),player[j]);
									player[j].BombanzahlDown();
									GameObjects.spawn(bmb);							
								}
								//gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(), SQUARE_SIZE * Double.parseDouble( message[i+1]), SQUARE_SIZE * Double.parseDouble( message[i+2]),										SQUARE_SIZE, SQUARE_SIZE);								
								i=i+2;
							}else {							
								i=i+1;								
							}
<<<<<<< HEAD
							
							
=======
>>>>>>> Yazan-Main
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
		
        bufferedReader = new BufferedReader(Ressourcen.file_Server[0]);
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
            for (int x = 0; x < COLUMNS; x++) {
                switch (mapLayout.get(y).get(x)) {
                    case ("S"):     // Soft wall zerstoerbar
                    	Wall soft= new Wall(x* SQUARE_SIZE,y*SQUARE_SIZE,Ressourcen.IMAGES.SOFTWALL.getImage(),true);
                    	if(soft!=null) 
                    		GameObjects.spawn(soft);
                        break;
                    case ("H"):    
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


