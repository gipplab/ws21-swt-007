package application.Objects;

import javafx.scene.image.Image;

public class Bomb extends TileObjects {
	
	double x,y;
	Image img;
	
	
	
	
public Bomb(double x2, double y2, Image image) {
	super(x2,y2,image);
	this.x=x2;
	this.y=y2;
	}

public double getX() {return this.x;}

public double getY() {return this.y;}
	
}


