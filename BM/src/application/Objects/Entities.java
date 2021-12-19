package application.Objects;

import java.awt.image.BufferedImage;

import application.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entities {
	double x,y;
	Image img;
	
	double width;
	double height;
	private boolean destroyed;
	
	
	
	public Entities(double x, double y, Image img) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.img=img;
		
	
	}
	public Entities(Image img) {
        this.img = img;
        this.width = this.img.getWidth();
        this.height = this.img.getHeight();
	
	}
	 void destroy() {
	        this.destroyed = true;
	    }

	    public boolean isDestroyed() {
	        return destroyed;
	    }
	
	
	
	public void drawImage(GraphicsContext gc) {
		gc.drawImage(this.img,this.x, this.y,
				GamePanel.SQUARE_SIZE,GamePanel.SQUARE_SIZE);
	}

}
