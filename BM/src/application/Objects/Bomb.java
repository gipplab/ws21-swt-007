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
	if(System.currentTimeMillis()-time>=timeToExplosion) {
	death=true;
		GameObjects.tileObjects.remove(this);
		GamePanel.player.BombanzahlUp();
		
	}
}
	
}


