package application.Objects;

import javafx.scene.image.Image;
import application.Ressourcen;

public class Bomberman extends Character {
String Name="";
int timeExplosion = 0 ;	

public Bomberman(double x, double y,Image img, Boolean p) {
	super(x,y,img,p);
	this.speed=2.5;//2.5, 5, 7 ,8,75

		}

//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
public void gethit() {
		this.health--;
		if(health<=0)
			dead=true;
		else dontMove=false;
		
	}
	




public String getName() {

	return Name;
}

public void setName(String name) {
	Name = name;
}


@Override
public boolean getDeath() {
	return this.dead;
}
//geprüft je nach Health, ob der Spieler sterben muss.
 public boolean death() {
	return this.dead;
			
}

//wenn der Spieler die Explosion trifft, entweder muss sterben oder Health reduzieren.
@Override
public void update() {

	if(!isFreeExplosion(this.x,this.y)||dontMove) {
		this.dontMove=true;
		timeExplosion++;
		if(timeExplosion == 50) {
		this.gethit();
	}
	
		this.img = Ressourcen.IMAGES.playerDead[this.PlayerFarbe][indexAnimPlayer()];
	}
	else if(!dontMove) { 
		timeExplosion=0;
	        if(this.dead) {
	           GameObjects.bomberObjects.remove(this);
	        }//verschiedene Items auftauchen können.
	}
	
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
	       
	       };
	
}



@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
}




	


}







