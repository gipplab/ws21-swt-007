package application.Objects;

import application.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entities {
	double x,y;
	Image img;
	public Entities(double x, double y, Image img) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.img=img;
	
	}
	public void drawImage(GraphicsContext gc) {
		gc.drawImage(this.img,this.x, this.y,
				GamePanel.SQUARE_SIZE,GamePanel.SQUARE_SIZE);
	}

}
