package application.Objects;

import application.GamePanel;
import application.Ressourcen;
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
	//Pr�fen, ob das Objekt Character ist.
	static Boolean isBomber(int x, int y){
		
		for(int i=0;i< GameObjects.bomberObjects.size();i++)
			if(GameObjects.bomberObjects.get(i).getEntityX()==x &&
				GameObjects.bomberObjects.get(i).getEntityY()==y) {
				GameObjects.bomberObjects.get(i).gethit();
				return true;
				}
		return false;
	}
	//Pr�fen, ob das Objekt eine Wand ist.
	static int isWall(int x, int y){
		
		for(int i=0;i< GameObjects.tileObjects.size();i++) {
			if(GameObjects.tileObjects.get(i).getEntityX()==(double)x &&
				GameObjects.tileObjects.get(i).getEntityY()==(double)y)
			{	
				if(GameObjects.tileObjects.get(i) instanceof Wall && GameObjects.tileObjects.get(i).isBreakable()) {
					//GameObjects.tileObjects.get(i).onDestroy();
					return 0;
				}else if(GameObjects.tileObjects.get(i) instanceof Wall && !GameObjects.tileObjects.get(i).isBreakable())
						return 1;
			}

		
		}
		
		return 2;
	}
	public double getEntityX() {
		return this.x;
        }
	
	public double getEntityY() {
		return this.y;
        }
	public void setEntityX(double xpos) {
		this.x= xpos;
        }
	
	public void setEntityY(double ypos) {
		 this.y=ypos;
        }
	
	public Image getEntityImage() {
		return this.img;
	}
	//Objekte auf Map zeigen.
	public void drawImage(GraphicsContext gc) {
	
		gc.drawImage(this.img,this.x, this.y, GamePanel.SQUARE_SIZE,GamePanel.SQUARE_SIZE);
		
	}
	public abstract  void update();
	
	public abstract boolean getDeath();
	public void gethit() {}
	public abstract boolean isPlayer();
	protected abstract int getItemtype();

	
	
	
}

