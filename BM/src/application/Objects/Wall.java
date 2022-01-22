package application.Objects;

import javafx.scene.image.Image;

public  class Wall extends TileObjects{
	boolean zerstoerbar;
	Boolean death;
	public Wall(double x,double y,Image img,boolean breakable) {
		super(x,y,img);
		zerstoerbar=breakable; 
		death=false;
	}
	public boolean isBreakable() 
	{
		return zerstoerbar;
	}
	 
	//hier werden zersbarede zersrt, wenn sie eine Explosion treffen.
	public void onDestroy() {
		if(zerstoerbar) {
			GameObjects.tileObjects.remove(this);
			
		}
			
		
		
	}
	public boolean getDeath() {
		return death;
	}


	@Override
	public void update() {
	
	}


	@Override
	public boolean isPlayer() {
		
		return false;
	}


	@Override
	protected int getItemtype() {
		return -1;
	}

}
