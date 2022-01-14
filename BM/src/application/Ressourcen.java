package application;


import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.scene.image.Image;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;


public class Ressourcen {
	
	public static int farbe=3;
	public static InputStreamReader file[]=new InputStreamReader[5] ,file_Server;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static String[] fields;
	

public enum IMAGES
{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM, MAP,EXPLOSION , PLAYERWHITE, PLAYERBLACK,PLAYERROT,PLAYERBLUE,GAMEOVER,WIN;
		// Alle Player in einem Feld Speichern und Farben anhand zahlen als Farben intepretieren
		//das muss gemacht werden 
		
		
		// Matrix für Farbe und Richtung
		public static Image[] PlayerFarbe= new Image[4] ;
		public static Image[][] playerUp = new Image[4][4];
		public static Image[][] playerDown = new Image[4][4];
		public static Image[][] playerRight = new Image[4][4];
		public static Image[][] playerLeft = new Image[4][4];
		public static Image[][] playerDead = new Image[4][4];
		
		static Image Map[] = new Image[5];
		
		 Image image;
		 
		  public Image getMap(int index) 
		  {
				return IMAGES.Map[index];
				
		  }
		  public Image getImage() 
		  {
			return this.image;
		  }
}
	
	
	
	
public Ressourcen() {}
	
public static Image teilImage(Image img, int stratX , int startY,int x , int y) throws IOException 
{	           
          PixelReader reader = img.getPixelReader();
          WritableImage newImage = new WritableImage(reader, stratX, startY, x, y);	         
          return newImage;
}
	
public static void readFiles() throws IOException 
{
		 // 0  White , 1 Black , 2 Rot, 3 Blue 
	        IMAGES.PLAYER1.image = new Image(Ressourcen.class.getResource("img/CharacterImages/WhitePlayer.png").toString());
		    IMAGES.PlayerFarbe[0] = new Image(Ressourcen.class.getResource("img/CharacterImages/WhitePlayer.png").toString());
		    IMAGES.PlayerFarbe[1] = new Image(Ressourcen.class.getResource("img/CharacterImages/BlackPlayer.png").toString());
		    IMAGES.PlayerFarbe[2] = new Image(Ressourcen.class.getResource("img/CharacterImages/RotPlayer.png").toString());
		    IMAGES.PlayerFarbe[3] = new Image(Ressourcen.class.getResource("img/CharacterImages/BluePlayer.png").toString());
			IMAGES.HARDWALL.image= new Image(Ressourcen.class.getResource("img/hardWall.jpg").toString());
			IMAGES.SOFTWALL.image= new Image(Ressourcen.class.getResource("img/softWall.jpg").toString());
			IMAGES.BOMBE.image= new Image(Ressourcen.class.getResource("img/Bombe.gif").toString());
			IMAGES.FLAMMEITEM.image= new Image(Ressourcen.class.getResource("img/FlammeItem.jpeg").toString());
			IMAGES.HERZITEM.image= new Image(Ressourcen.class.getResource("img/herzplus.jpg").toString());
			IMAGES.SPEEDITEM.image= new Image(Ressourcen.class.getResource("img/SpeedItem.jpeg").toString());
			IMAGES.BOMBITEM.image= new Image(Ressourcen.class.getResource("img/BombItem.jpeg").toString());
			IMAGES.EXPLOSION.image= new Image(Ressourcen.class.getResource("img/pngegg.png").toString());
			IMAGES.Map[0] = new Image(Ressourcen.class.getResource("img/Frame 3.png").toString());
			IMAGES.Map[1] = new Image(Ressourcen.class.getResource("img/landscape-mountains-minimalist-o7.jpg").toString());
			IMAGES.Map[2] = new Image(Ressourcen.class.getResource("img/BG.png").toString());
			IMAGES.Map[3] = new Image(Ressourcen.class.getResource("img/BACKG.jpg").toString());
			IMAGES.BOT.image= new Image(Ressourcen.class.getResource("img/CharacterImages/RotPlayer.png").toString());
			IMAGES.GAMEOVER.image = new Image(Ressourcen.class.getResource("img/GameOver.png").toString());
		    IMAGES.WIN.image = new Image(Ressourcen.class.getResource("img/Win.png").toString());
			file[0] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map.csv"));
			file[1] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map1.csv"));
			file[2] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map2.csv"));
			file[3] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map3.csv"));
			file[4] = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map3.csv"));
			file_Server = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map_Server.csv"));

			// 0  White , 1 Black , 2 Rot, 3 Blue 
			// i ist die Farbe und j ist die richtung
			  for (int i = 0; i < 4; i++) 
			  for(int j=0;j<4;j++){
		             IMAGES.playerRight[i][j]= teilImage(IMAGES.PlayerFarbe[i], j*(GamePanel.SQUARE_SIZE-3), (4*GamePanel.SQUARE_SIZE)+4, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerLeft[i][j]= teilImage(IMAGES.PlayerFarbe[i], j*(GamePanel.SQUARE_SIZE-3), (3*GamePanel.SQUARE_SIZE)-8, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerDown[i][j]= teilImage(IMAGES.PlayerFarbe[i], j*( GamePanel.SQUARE_SIZE-3), GamePanel.SQUARE_SIZE+12, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerUp[i][j]= teilImage(IMAGES.PlayerFarbe[i], j*(GamePanel.SQUARE_SIZE-3), 0, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		             IMAGES.playerDead[i][j]= teilImage(IMAGES.PlayerFarbe[i], j*(GamePanel.SQUARE_SIZE-3), (5*GamePanel.SQUARE_SIZE)+18, GamePanel.SQUARE_SIZE-4, GamePanel.SQUARE_SIZE+12);
		    }
}
	 

}
