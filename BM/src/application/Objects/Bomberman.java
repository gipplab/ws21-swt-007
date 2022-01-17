package application.Objects;

import javafx.scene.image.Image;
import application.Client;
import application.Main;
import application.Ressourcen;

public class Bomberman extends Character {
String Name="";


public Bomberman(double x, double y,Image img, Boolean p) {
	super(x,y,img,p);
	this.speed=2.5;//2.5, 5, 7 ,8,75

		}



public String getName() {

	return Name;
}

public void setName(String name) {
	Name = name;
}







//wenn der Spieler die Explosion trifft, entweder muss sterben oder Health reduzieren.

public void update() {

	if(!isFreeExplosion(this.x,this.y)) {
		this.dontMove=true;
		if((System.currentTimeMillis()-time>=timeToExplosion && !dead))
		{
		time= System.currentTimeMillis();
		this.gethit();
		if(Main.online) {
			 
		}
		}
		this.img = Ressourcen.IMAGES.playerDead[this.PlayerFarbe][indexAnimPlayer()];
	}
	else if(!dontMove) { 
		if(!dead)
		time= System.currentTimeMillis();
	    if(this.dead&&(System.currentTimeMillis()-time>=timeToExplosion)) {
	           GameObjects.bomberObjects.remove(this);
	        }
	}
//verschiedene Items auftauchen können.
int v=isItem(this.x, this.y);
	 
        switch(v) {
	       case 0:{	// Hertz
	    	   HealthUp();
	       	break;
	       }
	       case 1:{ // Bomb
	    		BombanzahlUp();
	    
	       	break;
	       }
	       case 2:{	// Flamme
	    	   ExplosionUp();
	
	       	break;
	       }
	       case 3:{	// Speed
	    	   speedUp();
	       	break;
	       }
	
	       default:
	       	break;
	       
	       }
	
}

}







