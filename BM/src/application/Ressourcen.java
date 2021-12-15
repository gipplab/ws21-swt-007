package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.scene.image.Image;

public class Ressourcen {

	public enum IMAGES{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM;
		
		
		 Image image;
		 
		public Image getImage() {
			return this.image;
		}
	}
	public Ressourcen() {}
		
		
		
	
		// TODO Auto-generated constructor stub
	
	
	 public static void readFiles() throws IOException {
	        IMAGES.PLAYER1.image = new Image(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
			IMAGES.HARDWALL.image= new Image(Ressourcen.class.getResource("img/hardWall.jpg").toString());
			IMAGES.SOFTWALL.image= new Image(Ressourcen.class.getResource("img/softWall.jpg").toString());
			IMAGES.BOMBE.image= new Image(Ressourcen.class.getResource("img/danny.jpg").toString());
			IMAGES.FLAMMEITEM.image= new Image(Ressourcen.class.getResource("img/Bigflame.jpg").toString());
			IMAGES.HERZITEM.image= new Image(Ressourcen.class.getResource("img/herzplus.jpg").toString());
			IMAGES.SPEEDITEM.image= new Image(Ressourcen.class.getResource("img/laufschuhe.PNG").toString());
			IMAGES.BOMBITEM.image= new Image(Ressourcen.class.getResource("img/Bombentascheoriganl.jpg").toString());
}
	 
	 //Read CSV File
	 public static void readCSV() {
	
	InputStreamReader File = new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/level1.csv"));

     String Line = "";

     BufferedReader BUFFR;

     try {
     	BUFFR = new BufferedReader(File);

         
         while ((Line = BUFFR.readLine()) != null) {
             String[] fields = Line.split(",");
             
             
             System.out.println(fields[0] + fields[1] + fields[2] + fields[3] 
             				             + fields[4] + fields[5]);
            
             
             

         }

     } catch (FileNotFoundException ex) {
         ex.printStackTrace();
     } catch (IOException ex) {
         ex.printStackTrace();
     }

 }
	 }
