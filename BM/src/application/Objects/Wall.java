package application.Objects;

import application.Ressourcen;
import javafx.scene.image.Image;

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
	
	//hier werden zerstörbare Wände zerstört, wenn sie eine Explosion treffen.
	public void onDestroy() {
		if(zerstoerbar) 
			GameObjects.tileObjects.remove(this);
		
	}
	public boolean getDeath() {
		return death;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
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
