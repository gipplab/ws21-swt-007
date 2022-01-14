package application.Objects;

import javafx.scene.image.Image;

public abstract class TileObjects extends Entities{

	TileObjects(double x, double y, Image img) {
		// TODO Auto-generated constructor stub
		super(x,y,img);
	
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	public  abstract boolean isBreakable();
	public abstract  void update();

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
