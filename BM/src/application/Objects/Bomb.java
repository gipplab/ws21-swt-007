package application.Objects;

import application.GamePanel;
import application.Ressourcen;
import javafx.scene.image.Image;

public class Bomb extends TileObjects {

	
	double time;
	double timeToExplosion;
	 Boolean death;
	
public Bomb(double x2, double y2, Image image)  {
	super(x2,y2,image);
	this.x=x2;
	this.y=y2;
	time= System.currentTimeMillis();
	timeToExplosion=5000;
	death=false;
	}



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

public void update() {
	if(System.currentTimeMillis()-time>=timeToExplosion&& !death) {
		death=true;
		GameObjects.tileObjects.remove(this);
		System.out.println("remove Bome "+this.x+", "+ this.y);
		Explotionart ex0= new Explotionart((int)this.x,(int) this.y);
		Explotionart ex1= new Explotionart((int)this.x,(int) this.y, 0, 3);
		Explotionart ex2= new Explotionart((int)this.x,(int) this.y, 1, 3);
		Explotionart ex3= new Explotionart((int)this.x, (int)this.y, 2, 3);
		Explotionart ex4= new Explotionart((int)this.x,(int) this.y, 3, 3);
		GamePanel.player[GamePanel.mainPlayerIndex].BombanzahlUp();
		
	}
}

public Boolean BombeDuplikate() {
	   for(int i=0; i< GameObjects.tileObjects.size(); i++) 
		   if(this.x==(GameObjects.tileObjects.get(i).x) && this.y==(GameObjects.tileObjects.get(i).y)) 
			   return true;
	   return false;
	   
}
public boolean getDeath() {
	return death;
}

}


