package application.Objects;

import application.GamePanel;

public class Bomberman {

	double playerX;
	double playerY;
	int bombanzahl;
	double speed;
	double explosion;
	int health;
	boolean dead ;

	
	
	
public Bomberman(double x, double y) {
	this.playerX=x;
	this.playerY=y;
	this.bombanzahl=1;
	this.speed=0.05;
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
public double getX(){
	return this.playerX;
}
public double getY(){
	return this.playerY;
}
public void BombanzahlUp(int b){
	 this.bombanzahl++;
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
	if( this.playerX < GamePanel.ROWS-2 )
		this.playerX= this.playerX+  this.speed;
	
}

public void moveLeft() {
	if(this.playerX >1)
		this.playerX=this.playerX- this.speed;

}

public void moveUp() {
	if( this.playerY >1)
		this.playerY=this.playerY - this.speed;
	
}

public void moveDown() {
	if( this.playerY < GamePanel.ROWS-2)
		this.playerY=this.playerY+ this.speed;
	
	}
 
	
	
	
	
	
	
	
	
}