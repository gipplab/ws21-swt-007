package application.Objects;


import application.Ressourcen;
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
				GameObjects.tileObjects.get(i).getEntityY()==this.y ) {
					GameObjects.tileObjects.remove(i);
					if( Math.round(Math.random())<0.5)
					createItem();
					}
		
		}
	}

void createItem(){
	   int poweruptype = (int) Math.round(Math.random() * 3);
       Image imageItem = Ressourcen.IMAGES.HERZITEM.getImage();
       switch(poweruptype) {
       case 0:{
       	
       	imageItem =Ressourcen.IMAGES.HERZITEM.getImage();
       	break;
       }
       case 1:{
       	
       	imageItem =Ressourcen.IMAGES.BOMBITEM.getImage();
       	break;
       }
       case 2:{

       imageItem =Ressourcen.IMAGES.FLAMMEITEM.getImage();
       	break;
       }
       case 3:{

       	imageItem = Ressourcen.IMAGES.SPEEDITEM.getImage();
       	break;
       }
       default:

       	break;
       
       }
       Items item= new Items(this.x,this.y, imageItem,poweruptype);
	GameObjects.spawn(item);
}


public boolean getDeath() {
	return death;
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
