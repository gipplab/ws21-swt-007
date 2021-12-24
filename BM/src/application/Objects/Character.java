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
	String Name;
	Boolean Player= false;
	public Character(double x, double y,Image img, Boolean isPlayer) {
		super(x,y,img);
		// TODO Auto-generated constructor stub
		this.Player=isPlayer;
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

	public boolean isFreeExplosion() {
		// TODO Auto-generated method stub
		 Entities obje;
		    
		    int nextX_1 = (int) (this.x / GamePanel.SQUARE_SIZE);
		    int nextY_1 = (int) (this.y / GamePanel.SQUARE_SIZE);

		    int nextX_2 = (int) ((this.x + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		    int nextY_2 = (int) (this.y / GamePanel.SQUARE_SIZE);

		    int nextX_3 = (int) (this.x / GamePanel.SQUARE_SIZE);
		    int nextY_3 = (int) ((this.y + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

		    int nextX_4 = (int) ((this.x + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		    int nextY_4 = (int) ((this.y + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		  
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
	public int getHealth(){
		return this.health;
	}
	double getSpeed(){
		return this.speed;
	}
	public Image getImage(){
		
		return img;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	boolean death() {
		if(health>0) {
			return false;
		}else 
			return true;
				
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
	protected abstract void moveDown();

	protected abstract void moveUp();

	protected abstract void moveLeft();

	protected abstract void moveRight();
	
}