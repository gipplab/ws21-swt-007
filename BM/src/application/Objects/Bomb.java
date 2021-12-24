package application.Objects;

import application.GamePanel;
import application.GamePanelOnline;
import application.Main;
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
	if((System.currentTimeMillis()-time>=timeToExplosion&& !death)|| !isFreeExplosion()) {
		death=true;
		GameObjects.tileObjects.remove(this);
		System.out.println("remove Bome "+this.x+", "+ this.y);
		Explotionart ex0= new Explotionart((int)this.x,(int) this.y);
		Explotionart ex1= new Explotionart((int)this.x,(int) this.y, 0, GamePanel.player.getExplosion());
		Explotionart ex2= new Explotionart((int)this.x,(int) this.y, 1, GamePanel.player.getExplosion());
		Explotionart ex3= new Explotionart((int)this.x, (int)this.y, 2, GamePanel.player.getExplosion());
		Explotionart ex4= new Explotionart((int)this.x,(int) this.y, 3, GamePanel.player.getExplosion());
		if(Main.online)
			GamePanelOnline.player[GamePanelOnline.mainPlayerIndex].BombanzahlUp();
		else
			GamePanel.player.BombanzahlUp();
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



@Override
public boolean isPlayer() {
	// TODO Auto-generated method stub
	return false;
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

}


