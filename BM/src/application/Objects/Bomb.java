package application.Objects;

import application.GamePanel;
import application.Main;
import application.Ressourcen;
import javafx.scene.image.Image;

public class Bomb extends TileObjects {
	Character player;
	int power;
	double time;
	final double timeToExplosion=3000;
	 Boolean death;
	
public Bomb(double x2, double y2,int power, Image image,Character p)  {
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

//Bombezustand und Bombeanzahl aktualisieren.
public void update() {
	
	if((System.currentTimeMillis()-time>=timeToExplosion&& !death)|| !isFreeExplosion()) {
		death=true;
		GameObjects.tileObjects.remove(this);
		System.out.println("remove Bome "+this.x+", "+ this.y);
		Explotionart ex0= new Explotionart((int)this.x,(int) this.y);
		Explotionart ex1= new Explotionart((int)this.x,(int) this.y, 0, this.power);
		Explotionart ex2= new Explotionart((int)this.x,(int) this.y, 1, this.power);
		Explotionart ex3= new Explotionart((int)this.x, (int)this.y, 2, this.power);
		Explotionart ex4= new Explotionart((int)this.x,(int) this.y, 3, this.power);
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

//Hier wird geprüft, ob es in diesem Block eine Explosion gibt.
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



@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
} 

}


