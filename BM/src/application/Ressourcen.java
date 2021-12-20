package application;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class Ressourcen {

	public enum IMAGES{
		BACKGROUND, BOMBE, SOFTWALL, HARDWALL, PLAYER1, BOT, PLAYER2, BOMBITEM, HERZITEM, SPEEDITEM
		,FLAMMEITEM;
		
		
		 ImageView imageview;
		 
		public ImageView getImage() {
			return this.imageview;
		}
	}
	public Ressourcen() {}
		
		
		
	
		// TODO Auto-generated constructor stub
	
	
	 public static void readFiles() throws IOException {
	        IMAGES.PLAYER1.imageview = new ImageView(Ressourcen.class.getResource("img/CharacterImages/player.jpg").toString());
			IMAGES.HARDWALL.imageview= new ImageView(Ressourcen.class.getResource("img/hardWall.jpg").toString());
			IMAGES.SOFTWALL.imageview= new ImageView(Ressourcen.class.getResource("img/softWall.jpg").toString());
			IMAGES.BOMBE.imageview= new ImageView(Ressourcen.class.getResource("img/danny.jpg").toString());
			IMAGES.FLAMMEITEM.imageview= new ImageView(Ressourcen.class.getResource("img/Bigflame.jpg").toString());
			IMAGES.HERZITEM.imageview= new ImageView(Ressourcen.class.getResource("img/herzplus.jpg").toString());
			IMAGES.SPEEDITEM.imageview= new ImageView(Ressourcen.class.getResource("img/laufschuhe.PNG").toString());
			IMAGES.BOMBITEM.imageview= new ImageView(Ressourcen.class.getResource("img/Bombentascheoriganl.jpg").toString());
}
	 }
