package application.Objects;

import application.Ressourcen.IMAGES;
import javafx.scene.image.Image;

public abstract class TileObjects extends Entities{
	
	
	protected boolean breakable;

	TileObjects(double x, double y, Image img) {
		// TODO Auto-generated constructor stub
		super(x,y,img);
	}
 
	 protected void snapToGrid() {
	        // Snap bombs to the grid on the map
	        x = Math.round(this.x / 30) * 30;
	        y = Math.round(this.y / 30) * 30;
	       //this.position.setLocation(x, y);
	       
	    }


	
	public abstract boolean isBreakable();
}
