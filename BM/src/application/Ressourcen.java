package application;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.image.Image;




public class Ressourcen {
	
	
	public static InputStreamReader file;
	public static ArrayList<ArrayList<String>> mapLayout;
	public static String[] fields;
	
	public enum IMAGES{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM;
		
		
		 Image image;
		 
    public Image getImage() {
			return this.image;
		}
	}
	public Ressourcen() {}
		
	
	
	 public static void readFiles() throws IOException {
	        IMAGES.PLAYER1.image = new Image(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
			IMAGES.HARDWALL.image= new Image(Ressourcen.class.getResource("img/hardWall.jpg").toString());
			IMAGES.SOFTWALL.image= new Image(Ressourcen.class.getResource("img/softWall.jpg").toString());
			IMAGES.BOMBE.image= new Image(Ressourcen.class.getResource("img/danny.jpg").toString());
			IMAGES.FLAMMEITEM.image= new Image(Ressourcen.class.getResource("img/Bigflame.jpg").toString());
			IMAGES.HERZITEM.image= new Image(Ressourcen.class.getResource("img/herzplus.jpg").toString());
			IMAGES.SPEEDITEM.image= new Image(Ressourcen.class.getResource("img/laufschuhe.PNG").toString());
			IMAGES.BOMBITEM.image= new Image(Ressourcen.class.getResource("img/Bombentascheoriganl.jpg").toString());
			file = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/level1.csv"));
			
	 }
	 
	 }
