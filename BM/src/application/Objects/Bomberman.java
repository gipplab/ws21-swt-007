package application.Objects;

import application.Client;
import application.GamePanel;
import application.Main;
import application.Ressourcen;
import javafx.scene.image.Image;

public class Bomberman extends Character{

	
	
public Bomberman(double x, double y,Image img, Boolean p) {
	super(x,y,img,p);

	this.bombanzahl=2;
	this.speed=5;// 5, 7 ,8,75 
				// Rows= 15
	this.explosion=3;
	this.dead=false;
	this.health=1;
		}
	


//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
public void gethit() {
		this.health--;
		if(health<=0)
			dead=true;
		
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

 public boolean death() {
	if(health>0) {
		return false;
	}else 
		return true;
			
}


public void ExplosionUp(){
	this.explosion++;
}
public void HealthUp(){
	this.health++;
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



@Override
public void update() {
	// TODO Auto-generated method stub

	if(!isFreeExplosion()) {
		this.dead=true;
	GameObjects.bomberObjects.remove(this);
	}
	
}
	
}
