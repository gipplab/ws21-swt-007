package application.Objects;
import application.GamePanel;
import application.Ressourcen;
import javafx.scene.image.Image;

public abstract class Character extends Entities {

	int bombanzahl;
	double speed;
	int explosion;
	int health;
	boolean dead ;
	Boolean Player= false;
	int framePlayer = 0, intervalPlayer = 5, indexAnimPlayer = 0;
	public int PlayerFarbe;
public Character(double x, double y,Image img, Boolean isPlayer) {
	super(x,y,img);
	// TODO Auto-generated constructor stub
	this.Player=isPlayer;
	bombanzahl=1;
	 explosion=1;
	 health=1;
	 dead=false ;

}
public int getPlayerFarbe() {
	return PlayerFarbe;
}



public void setPlayerFarbe(int playerFarbe) {
	PlayerFarbe = playerFarbe;
}
//Hier wird geprüft, ob das Block frei(ein Weg) ist.
	public  boolean isFree(double nextX, double nextY) {
	     boolean  frei = true;
	     Entities obje;
	     
	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	   
	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
	    	   
	   	    obje = GameObjects.tileObjects.get(i);
	   	    
	 	        if((obje.getEntityImage().equals(Ressourcen.IMAGES.SOFTWALL.getImage()))
	 	          ||(obje.getEntityImage().equals(Ressourcen.IMAGES.HARDWALL.getImage()))) {
	 	        	
	 		      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
	 		            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
	 			        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
	 			        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
	 			           frei = false;
	 		    }             
	 	  }
	      	return frei;
	 }
	//Hier wird geprüft, ob es in diesem Block eine Bombe gibt.
	@SuppressWarnings("unused")
	private  boolean isFreeBomb(double nextX, double nextY) {
	     Entities obje;
	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	   
	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
	    	   
	   	    obje = GameObjects.tileObjects.get(i);
	   	    
	 	        if((obje.getEntityImage().equals(Ressourcen.IMAGES.BOMBE.getImage()))) {
	 		    	  		return false;
	 		    }             
	 	  }
	      	return true;
	 }
	//Hier wird geprüft, ob es in diesem Block eine Explosion gibt.
	public boolean isFreeExplosion(double nextX, double nextY) {
		// TODO Auto-generated method stub
		  Entities obje;
	
		     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
	
		     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
		    
		     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		 
		     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		  
		    for (int i = 0; i < GameObjects.explosionObjects.size(); i++) {	
		 	    obje = GameObjects.explosionObjects.get(i);
		 	    
			        if(obje.getEntityImage().equals(Ressourcen.IMAGES.EXPLOSION.getImage())) {
			        	
				      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
				            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
					           return false;
				    }             
			  }
		    	return true;
		} 


	public boolean isPlayer() {
		return Player;
		
	}
	public int getBombanzahl(){
		return this.bombanzahl;
	}

	public int getExplosion(){
		return this.explosion;
	}
	//Bombenanzahl erhöhen.
	public void BombanzahlUp(){
		 this.bombanzahl++;
	}
	//Bombenanzahl reduzieren.
	public void BombanzahlDown(){
		 this.bombanzahl--;
	}
//Explosion reichweite erhöhen.  
public void ExplosionUp(){
	this.explosion++;
}
//Health erhöhen.
public void HealthUp(){
	this.health++;
}
double getSpeed(){
	return this.speed;
}
public int indexAnimPlayer() {
		 framePlayer++;
	        if (framePlayer > intervalPlayer) {
	            framePlayer = 0;
	            indexAnimPlayer++;
	            if (indexAnimPlayer > 3) {
	                indexAnimPlayer = 0;
	            }
	        }
	        return indexAnimPlayer;
	 }
	 
//Hier wird geprüft, ob es in diesem Block ein Item gibt.
		public int isItem(double nextX, double nextY) {
		// TODO Auto-generated method stub
		  Entities obje;
	
		     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
	
		     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
		    
		     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		 
		     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		  
		    for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
		 	    obje = GameObjects.tileObjects.get(i);
		 	    
		 	   if(obje instanceof Items) {
				      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
				            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
				      	{  		GameObjects.tileObjects.remove(obje);
			    	  		return obje.getItemtype();
				     
				      	}
				    }             
			  }
		    	return -1;
		} 
//Geschwindigkeit erhöhen.		
protected void speedUp() {
		if(speed ==2.5)
			speed=5;
		else if(speed ==5)
			speed=7;
		else if(speed==7)
			speed=7;
}
//Rechts laufen.	
public void moveRight() {
	
	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )) {
	 	 if(isFree(this.x+  this.speed,this.y)){
	 	    this.x= this.x+  this.speed;
		 }
	 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65)) {
	 		 this.y= this.y+  (this.speed/4);	
	 	      }
	 	 else if((this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35)) {
	 		  this.y= this.y-  (this.speed/4);		 		 
	 	      }
        } //die Pixel verteilung könnte in der Calss Bopmberman implementiert werden und die Strucktur zu vereinfachen 
	// und nicht immer auf Ressourcen greifen 
	// die Player Matrix wird ein Teil des class  Charackter werden

	this.img = Ressourcen.IMAGES.playerRight[this.PlayerFarbe][indexAnimPlayer()];	
}
//Links laufen.
public void moveLeft() {
	
	if((this.x >GamePanel.SQUARE_SIZE)) {
	 	 if(isFree(this.x- this.speed ,this.y)){
	            this.x=this.x- this.speed;
		 }
		 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65)) {
		 	 this.y= this.y+  (this.speed/4);	
		      }
		      else if((this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35)) {
		 	       this.y= this.y-  (this.speed/4);			 		 
		           }			 	  
	}
	this.img = Ressourcen.IMAGES.playerLeft[this.PlayerFarbe][indexAnimPlayer()];
}
//nach Oben laufen.
public void moveUp() {
	
	if((this.y >GamePanel.SQUARE_SIZE)) {
		if(isFree(this.x ,this.y - this.speed)) {
		   this.y=this.y - this.speed;
		}
		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65)){
		 	this.x= this.x+  (this.speed/4);
		     }
		     else if((this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35)) {
		 	      this.x= this.x-  (this.speed/4);	
		          }
	}
	this.img = Ressourcen.IMAGES.playerUp[this.PlayerFarbe][indexAnimPlayer()];	
}
//nach Unten laufen.
public void moveDown() {
	
	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)) {
		if(isFree(this.x ,this.y+ this.speed)) {
		   this.y=this.y+ this.speed;
		}
		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65)) {
	 		this.x= this.x+  (this.speed/4);
		     }
		else if( (this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35)) {
	 		  this.x= this.x-  (this.speed/4);	
		     }	 	  
	}
	this.img = Ressourcen.IMAGES.playerDown[this.PlayerFarbe][indexAnimPlayer()];
}

	
}
