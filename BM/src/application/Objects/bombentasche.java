package application.Objects;
import java.util.Random;

import application.Client;
import application.GamePanel;
import application.Ressourcen;
import javafx.scene.image.Image;


public class bombentasche {
	
	int bombenanzahltasche=3;
	double itemX;
	double itemY;
	Image image;
	static int s;
	double rangeMin = 0;
	double rangeMax = 1;
	
	//Funktion für die Wahrscheinlichkeit dass eine bombentasche in einer Kiste auftaucht
	public void itemapear() {
		for(int x=0;x<16;x++) {
			for(int y=0;y<16;y++) {
				Random r = new Random();
				double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				if(x%2==0 && x>0 && x<16 && randomValue<0.21) {
						
			//Falls wir verschiedene Kistenanordunungen haben passt es sich dem Muster an 
				//else if(this.kisteX=true && this.kisteY=true) 
			
				
					bombentasche s = new bombentasche();
					
					s.bombenanzahltasche=3;
					
					s.itemX=x;
					//s.itemX=this.kisteX;
					s.itemY=y;	
					//s.itemY=this.kisteY;
				}
		}
	}	
}
	
	public Image getImage(){
		
		return image;
	}
}