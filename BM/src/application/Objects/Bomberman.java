package application.Objects;

import application.Client;
import application.GamePanel;
import application.Main;
import application.Ressourcen;
import javafx.scene.image.Image;

public class Bomberman extends Entities{

	int bombanzahl;
	double speed;
	double explosion;
	int health;
	boolean dead ;
	String Name;
	
	
public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

public Bomberman(double x, double y,Image img) {
	super(x,y,img);

	this.bombanzahl=3;
	this.speed=5;// 5, 7 ,8,75 
				// Rows= 15
	this.explosion=1;
	this.dead=false;
	this.health=1;
		}
	
	boolean death() {
		if(health>0) {
			return false;
		}else 
			return true;
				
	}
	

	

public int getBombanzahl(){
	return this.bombanzahl;
}

public double getExplosion(){
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


//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
public void gethit() {
		this.health--;
		if(health<=0)
			dead=true;
		
	}
	
	
	
public void moveRight() {
	
	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )&& (isFree(this.x+  this.speed,this.y))) {
	 	   if(Main.online)
		Client.updateString =Client.updateString+"/RIGHT";
		this.x= this.x+  this.speed;
	// Client.accessServer("Play-RIGHT");
}
	}

public void moveLeft() {
	if((this.x >GamePanel.SQUARE_SIZE)&& (isFree(this.x- this.speed ,this.y))) {
		this.x=this.x- this.speed;
	 	   if(Main.online)
		Client.updateString =Client.updateString+"/LEFT";
	//Client.accessServer("Play-LEFT");
	}
}

public void moveUp() {
	if((this.y >GamePanel.SQUARE_SIZE)&& (isFree(this.x ,this.y - this.speed))) {
		this.y=this.y - this.speed;
	 	   if(Main.online)
	Client.updateString =Client.updateString+"/UP";}
	 //Client.accessServer("Play-UP");
	
}

public void moveDown() {
	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)&& (isFree(this.x ,this.y+ this.speed))) {
		this.y=this.y+ this.speed;
	 	   if(Main.online)
	 		   Client.updateString =Client.updateString+"/DOWN";
	 	   // Client.accessServer("Play-DOWN");
	}
	}

//Methode isFree gibt true zuerueck ,wenn SQUARE kein Wall enthaelt.	
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
public  Boolean isFreeExplosion() {
  
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
@Override
public boolean getDeath() {
	return this.dead;
}

@Override
public void update() {
	// TODO Auto-generated method stub

	if(!isFreeExplosion())
		this.dead=true;
	
}
	
}
