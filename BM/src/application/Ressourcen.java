package application;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.image.Image;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


public class Ressourcen {
	
	
	public static InputStreamReader file[]=new InputStreamReader[4] ,file_Server;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static String[] fields;
	
	public enum IMAGES{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM, MAP,EXPLOSION , BOMBERMANMATRIX ;
		
		public static Image[] playerUp = new Image[4];
		public static Image[] playerDown = new Image[4];
		public static Image[] playerRight = new Image[4];
		public static Image[] playerLeft = new Image[4];
		public static Image[] playerDead = new Image[4];
		
		static Image Map[] = new Image[4];
		
		 Image image;
		 
		  public Image getMap(int index) {
				return IMAGES.Map[index];
				
			}
    public Image getImage() {
			return this.image;
		}
	}
	public Ressourcen() {}
	
	public static Image teilImage(Image img, int stratX , int startY,int x , int y) throws IOException {	           
          PixelReader reader = img.getPixelReader();
          WritableImage newImage = new WritableImage(reader, stratX, startY, x, y);	         
          return newImage;
    }
	
	 public static void readFiles() throws IOException {
		 
	        IMAGES.PLAYER1.image = new Image(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
		    IMAGES.BOMBERMANMATRIX.image = new Image(Ressourcen.class.getResource("img/CharacterImages/bombermanmatrix.png").toString());
			IMAGES.HARDWALL.image= new Image(Ressourcen.class.getResource("img/hardWall.jpg").toString());
			IMAGES.SOFTWALL.image= new Image(Ressourcen.class.getResource("img/softWall.jpg").toString());
			IMAGES.BOMBE.image= new Image(Ressourcen.class.getResource("img/Bombe.gif").toString());
			IMAGES.FLAMMEITEM.image= new Image(Ressourcen.class.getResource("img/Bigflame.jpg").toString());
			IMAGES.HERZITEM.image= new Image(Ressourcen.class.getResource("img/herzplus.jpg").toString());
			IMAGES.SPEEDITEM.image= new Image(Ressourcen.class.getResource("img/laufschuhe.PNG").toString());
			IMAGES.BOMBITEM.image= new Image(Ressourcen.class.getResource("img/Bombentascheoriganl.jpg").toString());
			IMAGES.EXPLOSION.image= new Image(Ressourcen.class.getResource("img/danny.jpg").toString());
			IMAGES.Map[0] = new Image(Ressourcen.class.getResource("img/Frame 3.png").toString());
			IMAGES.Map[1] = new Image(Ressourcen.class.getResource("img/landscape-mountains-minimalist-o7.jpg").toString());
			IMAGES.Map[2] = new Image(Ressourcen.class.getResource("img/BG.png").toString());
			IMAGES.Map[3] = new Image(Ressourcen.class.getResource("img/BACKG.jpg").toString());
			IMAGES.BOT.image= new Image(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
			file[0] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map.csv"));
			file[1] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map.csv"));
			file_Server = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map_Server.csv"));
		 
		       for (int i = 0; i < 4; i++) {
		             IMAGES.playerRight[i]= teilImage(IMAGES.BOMBERMANMATRIX.getImage(), i*(GamePanel.SQUARE_SIZE-3), (4*GamePanel.SQUARE_SIZE)+4, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerLeft[i]= teilImage(IMAGES.BOMBERMANMATRIX.getImage(), i*(GamePanel.SQUARE_SIZE-3), (3*GamePanel.SQUARE_SIZE)-8, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerDown[i]= teilImage(IMAGES.BOMBERMANMATRIX.getImage(), i*( GamePanel.SQUARE_SIZE-3), GamePanel.SQUARE_SIZE+12, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerUp[i]= teilImage(IMAGES.BOMBERMANMATRIX.getImage(), i*(GamePanel.SQUARE_SIZE-3), 0, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
			     IMAGES.playerDead[i]= teilImage(IMAGES.BOMBERMANMATRIX.getImage(), i*(GamePanel.SQUARE_SIZE-3), (5*GamePanel.SQUARE_SIZE)+18, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		    }
			
	 }
	 }
