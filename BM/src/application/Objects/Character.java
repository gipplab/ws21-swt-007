package application.Objects;

import application.Client;
import application.GamePanel;
import application.Main;
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
	
public Character(double x, double y,Image img, Boolean isPlayer) {
	super(x,y,img);
	// TODO Auto-generated constructor stub
	this.Player=isPlayer;
	bombanzahl=1;
	 explosion=1;
	 health=1;
	 dead=false ;
	
}
	
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
	public void BombanzahlUp(){
		 this.bombanzahl++;
	}

	public void BombanzahlDown(){
		 this.bombanzahl--;
	}

public void ExplosionUp(){
	this.explosion++;
}
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
	
protected void speedUp() {
		if(speed ==2.5)
			speed=5;
		else if(speed ==5)
			speed=7;
		else if(speed==7)
			speed=7;
}
public void moveRight() {
	
	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )) {
	 	 if( isFree(this.x+  this.speed,this.y))
	 	  this.x= this.x+  this.speed;
	 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65) ) {
	 		  this.y= this.y+  (this.speed/(4*this.speed));	
	 		}
	 	 else if( (this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35) ) {
	 		  this.y= this.y-  (this.speed/(4*this.speed));	
	 		 
	 	 }
	 	  if(Main.online) {
	 		   Client.updateString =Client.updateString+"RIGHT/";
	 		   // Client.accessServer("Play-RIGHT");
	 	   }
		
	         this.img = Ressourcen.IMAGES.playerRight[indexAnimPlayer()];

    } 
	
}

public void moveLeft() {
	if((this.x >GamePanel.SQUARE_SIZE)) {
	 	 if(isFree(this.x- this.speed ,this.y))
	 		 this.x=this.x- this.speed;
		 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65) ) {
		 		  this.y= this.y+  (this.speed/(4*this.speed));	
		 		}
		 	 else if( (this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35) ) {
		 		  this.y= this.y-  (this.speed/(4*this.speed));	
		 		 
		 	 }
	 	   if(Main.online) {
	 		   	Client.updateString =Client.updateString+"LEFT/";
	 		   	//Client.accessServer("Play-LEFT");
	 	   }
		
	         this.img = Ressourcen.IMAGES.playerLeft[indexAnimPlayer()];
	 	  
	}
}

public void moveUp() {
	if((this.y >GamePanel.SQUARE_SIZE)) {
		if(isFree(this.x ,this.y - this.speed))
				   this.y=this.y - this.speed;
		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65) )
		 		  this.x= this.x+  (this.speed/4);	
		else if( (this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35) ) 
		 		  this.x= this.x-  (this.speed/(4*this.speed));	
		
		if(Main.online) {
	 		   	Client.updateString =Client.updateString+"UP/";
	 		   	//Client.accessServer("Play-UP");
	 	   		}
		
	        this.img = Ressourcen.IMAGES.playerUp[indexAnimPlayer()];
		 	 
		}
	
}

public void moveDown() {
	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)) {
		if(isFree(this.x ,this.y+ this.speed))
			 this.y=this.y+ this.speed;
		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65) )
	 		  this.x= this.x+  (this.speed/(4*this.speed));	
		else if( (this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35) ) 
	 		  this.x= this.x-  (this.speed/(4*this.speed));	
	 	   if(Main.online) {
	 		   Client.updateString =Client.updateString+"DOWN/";
	 		   // Client.accessServer("Play-DOWN");
	 		   }
		
	        this.img = Ressourcen.IMAGES.playerDown[indexAnimPlayer()];
	 	  
	}
	}

	
}
