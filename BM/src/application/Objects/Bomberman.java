package application.Objects;

import application.Client;
import application.GamePanel;

import javafx.scene.image.Image;

public class Bomberman extends Entities{

	int bombanzahl;
	double speed;
	double explosion;
	int health;
	boolean dead ;
	
	
public Bomberman(double x, double y,Image img) {
	super(x,y,img);
	this.bombanzahl=5;
	this.speed=2;
	this.explosion=1;
	this.dead=false;
	this.health=2;
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
public void BombanzahlUp(int b){
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
void gethit() {
		this.health--;
		
	}
	
	
	
public void moveRight() {
	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )&& (GamePanel.isFree(this.x+  this.speed,this.y)))
		this.x= this.x+  this.speed;
	// Client.accessServer("Play-RIGHT");
}

public void moveLeft() {
	if((this.x >GamePanel.SQUARE_SIZE)&& (GamePanel.isFree(this.x- this.speed ,this.y)))
		this.x=this.x- this.speed;
	//Client.accessServer("Play-LEFT");

}

public void moveUp() {
	if((this.y >GamePanel.SQUARE_SIZE)&& (GamePanel.isFree(this.x ,this.y - this.speed)))
		this.y=this.y - this.speed;
	 //Client.accessServer("Play-UP");
	
}

public void moveDown() {
	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)&& (GamePanel.isFree(this.x ,this.y+ this.speed)))
		this.y=this.y+ this.speed;
	// Client.accessServer("Play-DOWN");
	}
 	
	
}
