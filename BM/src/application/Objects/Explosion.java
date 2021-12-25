package application.Objects;


import javafx.scene.image.Image;

public class Explosion extends Entities{
	double time;
	double timeToExplosion;
	Boolean death;
	 
	Explosion(double x, double y, Image img){
		super(x,y,img);
		time= System.currentTimeMillis();
		GameObjects.spawn(this);
		timeToExplosion=1000;
		death=false;
		System.out.println("erzeuge Ex");
	}
	

	
public void update() {
		if(System.currentTimeMillis()-time>=timeToExplosion && !death) {
			death=true;
			GameObjects.explosionObjects.remove(this);
			for(int i=0;i<GameObjects.tileObjects.size();i++)
				if(GameObjects.tileObjects.get(i).getEntityX()==this.x &&
				GameObjects.tileObjects.get(i).getEntityY()==this.y )
					GameObjects.tileObjects.remove(i);
			System.out.println("remove Explosion");
		}
	}
public boolean getDeath() {
	return death;
}



@Override
public boolean isPlayer() {
	// TODO Auto-generated method stub
	return false;
}
}
