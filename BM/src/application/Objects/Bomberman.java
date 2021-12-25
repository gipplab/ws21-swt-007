package application.Objects;



import javafx.scene.image.Image;

public class Bomberman extends Character{

	
	
public Bomberman(double x, double y,Image img, Boolean p) {
	super(x,y,img,p);

	this.bombanzahl=2;
	this.speed=2.5;//2.5, 5, 7 ,8,75 
				// Rows= 15
	this.explosion=1;
	this.dead=false;
	this.health=1;
		}
	


//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
public void gethit() {
		this.health--;
		if(health<=0)
			dead=true;
		
	}
	




public String getName() {

	return Name;
}

public void setName(String name) {
	Name = name;
}


@Override
public boolean getDeath() {
	return this.dead;
}

 public boolean death() {
	if(health>0) {
		return false;
	}else 
		return true;
			
}


public void ExplosionUp(){
	this.explosion++;
}
public void HealthUp(){
	this.health++;
}
	



@Override
public void update() {
	// TODO Auto-generated method stub

	if(!isFreeExplosion(this.x,this.y)) {
		this.dead=true;
	GameObjects.bomberObjects.remove(this);
	}
	
}
	
}
