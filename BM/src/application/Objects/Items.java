package application.Objects;

import javafx.scene.image.Image;

public class Items extends TileObjects {

	public static float randomStrongItem;
	
	int poweruptype;
    
	
	
	public Items(double x, double y,Image img,int poweruptype) {
		// TODO Auto-generated constructor stub
	        super(x,y,img);      
	     this.poweruptype=poweruptype;
	         
	    }
	
	public int getItemtype() {
		return  this.poweruptype;
	}
       

	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}


	@Override
	public boolean getDeath() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBreakable() {
		// TODO Auto-generated method stub
		return false;
	}
   

    

}