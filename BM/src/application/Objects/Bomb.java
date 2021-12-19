package application.Objects;

import application.Ressourcen;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;

public class Bomb extends TileObjects {
	
	private Bomberman bomber;
	double x,y;
	Image img;
	private int BombTimer;
	private int timeToExplode;
	private int firepower;
	private int TimeRemaining;
	
	
public Bomb(double x, double y,int firepower,int timeToExplode, Image image,Bomberman bomber) {
	super(x,y,Ressourcen.IMAGES.BOMBE.getImage());
	this.BombTimer = 0;
	this.firepower = firepower;
	this.timeToExplode = timeToExplode;
	this.bomber = bomber;
	this.TimeRemaining = 0;
	}

private void explode() {
    this.snapToGrid();
  
    GameObjects.spawn(new Explosion.Horizontal(this.x , this.y, this.firepower));
    
    GameObjects.spawn(new Explosion.Vertical(this.x, this.y, this.firepower));
    
    this.bomber.restoreAmmount();

   // Sound.play(Sound.EXPLORE, 0);
}



public double getX() {return this.x;}

public double getY() {return this.y;}



@Override
public boolean isBreakable() {
    return this.breakable;
}

	
}
 

