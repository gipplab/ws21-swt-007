package application.Objects;

import javafx.scene.image.Image;

<<<<<<< Updated upstream
public class Wall extends TileObjects{

	public Wall(double x,double y,Image img) {
		// TODO Auto-generated constructor stub
		super(x,y,img);
=======

public  class Wall extends TileObjects{
	boolean zerstoerbar;
	Boolean death;
	public Wall(double x,double y,Image img) {
		// TODO Auto-generated constructor stub
		super(x,y,img);
		if(img.equals(Ressourcen.IMAGES.SOFTWALL.getImage()))
			zerstoerbar=true;
		else zerstoerbar=false;
		death=false;
	}
	
	
	public void onDestroy() {
		if(zerstoerbar)
			GameObjects.tileObjects.remove(this);
		 double random = Math.random(); // random từ 0.0 - 1.0
	        if (random < 0.4) {
	            Items powerup = new Items(this.x, this.y);
	            GameObjects.spawn(powerup);
	}
	}
	public boolean getDeath() {
		return death;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
>>>>>>> Stashed changes
	}

}
