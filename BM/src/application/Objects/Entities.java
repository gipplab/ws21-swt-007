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
	static Boolean isBomber(int x, int y){
		
		for(int i=0;i< GameObjects.bomberObjects.size();i++)
			if(GameObjects.bomberObjects.get(i).getEntityX()==x &&
				GameObjects.bomberObjects.get(i).getEntityY()==y) {
				GameObjects.bomberObjects.get(i).gethit();
				return true;
				}
		return false;
	}
	
	static Boolean isWall(int x, int y){
		
		for(int i=0;i< GameObjects.tileObjects.size();i++) {
			if(GameObjects.tileObjects.get(i).getEntityX()==(double)x &&
				GameObjects.tileObjects.get(i).getEntityY()==(double)y&&
				GameObjects.tileObjects.get(i).img.equals(Ressourcen.IMAGES.HARDWALL.getImage()) ) {
//				System.out.println(x+ ", "+y+", "+GameObjects.tileObjects.get(i).getEntityX()+" "+
//						GameObjects.tileObjects.get(i).getEntityY());
				
				return true;
				}
//				System.out.println(x+ ",, "+y+",, "+GameObjects.tileObjects.get(i).getEntityX()+",,"+
//				GameObjects.tileObjects.get(i).getEntityY());
			 if(GameObjects.tileObjects.get(i).getEntityX()==(double)x &&
				GameObjects.tileObjects.get(i).getEntityY()==(double)y&&
				GameObjects.tileObjects.get(i).img.equals(Ressourcen.IMAGES.SOFTWALL.getImage()))
				 			GameObjects.tileObjects.get(i).onDestroy(); 
		}
		
		return false;
	}
	public double getEntityX() {
		return this.x;
        }
	
	public double getEntityY() {
		return this.y;
        }
	
	public Image getEntityImage() {
		return this.img;
	}
	
	public void drawImage(GraphicsContext gc) {
		
		gc.drawImage(this.img,this.x, this.y, GamePanel.SQUARE_SIZE,GamePanel.SQUARE_SIZE);
		
	}
	public abstract  void update();
	public Boolean isDestroyed() {
		return false;
	
		
	}
	public abstract boolean getDeath();
	public void gethit() {}

	
	
	
}

