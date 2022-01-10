package application.Objects;

import application.GamePanel;
import application.Main;
import application.Ressourcen;
import javafx.scene.image.Image;

public class BotBomb extends TileObjects {
	Character player;
	int power;
	double time;
	final double timeToExplosion=3000;
	 Boolean death;
	
public BotBomb(double x2, double y2,int power, Image image,Character p)  {
	super(x2,y2,image);
	
	this.x=x2;
	this.y=y2;
	time= System.currentTimeMillis();

	death=false;
	this.power=power;
	player=p;
	
	if (BombeDuplikate()) {
		player.BombanzahlUp();
		death=true;
		GameObjects.tileObjects.remove(this);
	}
	}


//die Bombe im richtigen Block platzieren 
public void BombCollision(double x1, double y1) {
	
	int a = (int) (x1 % GamePanel.SQUARE_SIZE);
	int b= (int) (y1 % GamePanel.SQUARE_SIZE);
	System.out.println(a+", "+ b);
	if(a < GamePanel.SQUARE_SIZE/2) {
		this.x=(int) x1-a ;

	}else {
		this.x = (int)x1+(GamePanel.SQUARE_SIZE-a);

	}
	if(b < GamePanel.SQUARE_SIZE/2) {
		this.y=(int) y1-b;

		}
		else {
			this.y=(int)y1 +(GamePanel.SQUARE_SIZE-b);
		}
}


public double getX() {return this.x;}

public double getY() {return this.y;}



public boolean isFreeExplosionbot(double x,  double y) {
	
	double restX= x% GamePanel.SQUARE_SIZE;
	double restY= y% GamePanel.SQUARE_SIZE;
	
	if(restX==0 && restY==0) {
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
			if(GameObjects.explosionObjectsbot.get(i).getEntityX()==x&& GameObjects.explosionObjectsbot.get(i).getEntityY()==y) {
				 System.out.println(this.x +", " +this.y);
				return false;}
			
		}
	}
	
	else if(restX==0 && restY!=0) {
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
		 if((GameObjects.explosionObjectsbot.get(i).getEntityX() == x &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY ))) {
			 System.out.println(this.x +", " +this.y);
			 return false;}
		 }
		
	}
	
	if(restX!=0 && restY==0) {
		
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
			
			 if((GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
					 GameObjects.explosionObjectsbot.get(i).getEntityY() == y)
					 ||
					 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE- restX)
							 &&
					 GameObjects.explosionObjectsbot.get(i).getEntityY() == y ))
			 {
				 System.out.println(this.x +", " +this.y);
				return false;}
		}
			}
	
	else if(restX!=0 && restY!=0) {
		
	for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
	
		 if(	(GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() ==y+(GamePanel.SQUARE_SIZE- restY ))
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY )))
		 {
			 System.out.println(this.x +", " +this.y);
			return false;}
	}
		
	}
	
	
	
	
	return true;
	
}


//Bombezustand und Bombeanzahl aktualisieren.
public void update() {
	if((System.currentTimeMillis()-time>=timeToExplosion&& !death)|| !isFreeExplosionbot(this.x,this.y)) {
		death=true;
		GameObjects.tileObjects.remove(this);
		System.out.println("remove Bome "+this.x+", "+ this.y);
		Explosionbot ex0= new Explosionbot((int)this.x,(int) this.y);
		Explosionbot ex1= new Explosionbot((int)this.x,(int) this.y, 0, this.power);
		Explosionbot ex2= new Explosionbot((int)this.x,(int) this.y, 1, this.power);
		Explosionbot ex3= new Explosionbot((int)this.x, (int)this.y, 2, this.power);
		Explosionbot ex4= new Explosionbot((int)this.x,(int) this.y, 3, this.power);
		if(Main.online)
			player.BombanzahlUp();
		else
			player.BombanzahlUp();
	}
}
//prüft, ob in einem Block mehrere Bomben gibt
private Boolean BombeDuplikate() {
	   for(int i=0; i< GameObjects.tileObjects.size(); i++) 
		   if(this.x==(GameObjects.tileObjects.get(i).getX()) && this.y==(GameObjects.tileObjects.get(i).getY())) 
			   return true;
	   return false;
	   
}
public boolean getDeath() {
	return death;
}



@Override
public boolean isPlayer() {
	// TODO Auto-generated method stub
	return false;
}

@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
} 

}


