package application;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.image.Image;




public class Ressourcen {
	
	
	public static InputStreamReader file,file_Server;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static String[] fields;
	public int yazan;
	
	public enum IMAGES{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM, MAP,EXPLOSION;
		
		
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
	
	
	 public static void readFiles() throws IOException {
	        IMAGES.PLAYER1.image = new Image(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
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
			file = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map.csv"));
			file_Server = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map_Server.csv"));
			
	 }
	 
	 }
