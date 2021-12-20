package application.Objects;

import application.Client;
import application.GamePanel;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class Bomberman extends Transition {

	double playerX;
	double playerY;
	int bombanzahl;
	double speed;
	double explosion;
	int health;
	boolean dead ;
	Image image;
	
	
	private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public Bomberman(double x , double y,
    		ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    	this.playerX=x;
    	this.playerY=y;
    	this.bombanzahl=5;
    	this.speed=0.25;
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

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
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
	
	return image;
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
	if( this.playerX < GamePanel.ROWS-2 )
		this.playerX= this.playerX+  this.speed;
	// String resp=Client.accessServer("Play-RIGHT");
}

public void moveLeft() {
	if(this.playerX >1)
		this.playerX=this.playerX- this.speed;
	// String resp=Client.accessServer("Play-LEFT");

}

public void moveUp() {
	if( this.playerY >1)
		this.playerY=this.playerY - this.speed;
	 //String resp=Client.accessServer("Play-UP");
}

public void moveDown() {
	if( this.playerY < GamePanel.ROWS-2)
		this.playerY=this.playerY+ this.speed;
	// String resp=Client.accessServer("Play-DOWN");
	}
 	
	
}