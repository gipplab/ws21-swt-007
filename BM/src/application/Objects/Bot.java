package application.Objects;
import java.util.List;

import application.GamePanel;
import application.KeysHandler;
import application.Ressourcen;
import javafx.scene.image.Image;



public class Bot extends Character {
	 List<?> keyboardInputs = KeysHandler.getInputList();

	int lastRichtung=0;
	
	
	Image Bombimag= Ressourcen.IMAGES.BOMBE.getImage();
	public Bot(double x, double y, Image img, Boolean p) {
		super(x, y, img,p);
		// TODO Auto-generated constructor stub
			this.speed=1.25;//2.5, 5, 7 ,8,75 
	
	
		
		}
	void placeBomb() {
		if(bombanzahl>0) 
		{
		
			Bomb b= new Bomb( this.x , this.y, explosion , Bombimag, this );
			b.BombCollision(this.x,this.y);
				bombanzahl--;
				GameObjects.spawn(b);
			
		}
}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(!isFreeExplosion(this.x,this.y)) {
			this.dead=true;
			GameObjects.bomberObjects.remove(this);
			}
		
			moveRandom();
		
	}
void moveRandom(){
	
	isFreeBot();
    if (lastRichtung == 0) {
        moveUp();
    }
    else if (lastRichtung == 1) {
    	  moveRight();
    }
    else if (lastRichtung == 2) {
    	  moveLeft();
    }
    else if (lastRichtung == 3) {
    	  moveDown();
    }
	
}



void isFreeBot(){
		
		int a = (int) (this.x % GamePanel.SQUARE_SIZE);
		int b= (int) (this.y % GamePanel.SQUARE_SIZE);
	
		switch(lastRichtung) {
		case 0:// Up
		{
			if((isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE))==0 
					||isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE)==1) {
				lastRichtung=(int) Math.round(Math.random() * 3);
				placeBomb();
				}
			
			break;
		}
		case 1: // right
		{	if((isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y))==0 
		||isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y)==1) {
				lastRichtung=(int) Math.round(Math.random() * 3);
				placeBomb();
				}
			
		break;
		}
		case 2:// Left
		{if((isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y))==0 
		||isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y)==1){
		lastRichtung=(int) Math.round(Math.random() * 3);
		placeBomb();
		}
			
		break;
		}
		case 3:// Down
		{
			if((isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE)))==0 
					||isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))==1) {
				lastRichtung=(int) Math.round(Math.random() * 3);
				placeBomb();}
			break;
		}
		default:
			break;
		}
	
	
	
}

void isWall() {
	lastRichtung = (int) Math.round(Math.random() * 3);
	
}
	public void gethit() {
		this.health--;
		if(health<=0)
			dead=true;
		
	}
	

public int getHealth(){
	return this.health;
}

public Image getImage(){
	
	return img;
}


@Override
public boolean getDeath() {
	return this.dead;
}
public double getX(){
	return this.x;
}
public double getY(){
	return this.y;
}
boolean death() {
	if(health>0) {
		return false;
	}else 
		return true;
			
}


@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
}
	}