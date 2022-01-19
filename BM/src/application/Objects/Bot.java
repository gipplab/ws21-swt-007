package application.Objects;
import java.util.List;

import application.GamePanel;
import application.KeysHandler;
import application.Ressourcen;
import javafx.scene.image.Image;


public class Bot extends Character {
	List<?> keyboardInputs = KeysHandler.getInputList();
	public static int killbot = 0;
	int lastRichtung=0;
	long time;
	int timeToExplosion=10000;
	Image Bombimag= Ressourcen.IMAGES.BOMBE.getImage();
public Bot(double x, double y, Image img, Boolean p)
{
		super(x, y, img,p);
		// TODO Auto-generated constructor stub

		this.speed=1.25;//2.5, 5, 7 ,8,75 
			time= System.currentTimeMillis();
			
	
}
<<<<<<< HEAD
	//Bombenanzahl prüfen aund eine Bombe Platzieren.
=======
	//Bombenanzahl prfen aund eine Bombe Platzieren.
>>>>>>> Marouan
void placeBomb() 
{
//		if(bombanzahl>0) 
//		{
//			Bomb b= new Bomb( this.x , this.y, explosion , Bombimag, this );
//			b.BombCollision(this.x,this.y);
//			bombanzahl--;
//			GameObjects.spawn(b);
//		}
}

  public void moveRandom(){
	isFreeBot();
        for (int i = 0; i < GameObjects.bomberObjects.size(); i++) {		   
	     Entities obje = GameObjects.bomberObjects.get(i);	   	    
	     if(obje.isPlayer()) { 	        	
	         try {
				if (Character.isFree(this.x , this.y - this.speed) && isFreeBomb(this.x,this.y ,0) && (this.y > obje.getEntityY())) { 
				     moveUp();
				 } else  if (Character.isFree(this.x + this.speed , this.y) && isFreeBomb(this.x ,this.y,1) && (this.x < obje.getEntityX())) {
				                moveRight();
				              } 
				              else if (Character.isFree(this.x - this.speed , this.y) && isFreeBomb(this.x ,this.y,3)&& (this.x > obje.getEntityX())) {
				                       moveLeft();		
				                    } 
				               else  if (Character.isFree(this.x , this.y + this.speed) &&  isFreeBomb(this.x,this.y ,2) && (this.y < obje.getEntityY()))  {
				                     moveDown();
				                     }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	   }  
        }
  }
	
//Hier bewegt sich der Bot random. void moveRandom(){
	
/*	
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
<<<<<<< HEAD
*/
=======

//die nchste Bewegung des Botes stimmen.
//void isFreeBot()
//{
//	
//		switch(lastRichtung) {
//		case 0:// Up
//		{
//			if((isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE))==0 
//				|| isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE)==1 
//				|| !isFreeExplosion((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE)
//				|| !isFreeExplosion((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE))
//			{
//				lastRichtung=(int) Math.round(Math.random() * 3);
//				placeBomb();
//				}
//			break;
//		}
//		case 1: // right
//		{	if((isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y))==0 
//		||isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y)==1
//		|| !isFreeExplosion((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y)
//		|| !isFreeExplosion((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y))
//		{
//			lastRichtung=(int) Math.round(Math.random() * 3);
//				placeBomb();
//				}
//			
//		break;
//		}
//		case 2:// Left
//		{if((isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y))==0 
//			||isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y)==1
//			|| !isFreeExplosion((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y)
//			|| !isFreeExplosion((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y))
//		{
//		lastRichtung=(int) Math.round(Math.random() * 3);
//		if((System.currentTimeMillis()-time>=timeToExplosion)) {
//			placeBomb();
//			time=System.currentTimeMillis();
//		}
//		
//		}
//			
//		break;
//		}
//		case 3:// Down
//		{
//			if((isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE)))==0 
//					||isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))==1
//					|| !isFreeExplosion((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))
//					|| !isFreeExplosion((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))){
//				lastRichtung=(int) Math.round(Math.random() * 3);
//				if((int) Math.round(Math.random() * 15)==2)
//						placeBomb();
//					}
//			break;
//		}
//		default:
//			break;
//		}
//}

>>>>>>> Marouan
void isFreeBot()
{
	
		switch(lastRichtung) {
		case 0:// Up
		{
			if((isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE))==0 
				|| isWall((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE)==1 
				|| !isFreeExplosion((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE)
				|| !isFreeExplosion((int)this.x,(int)this.y-GamePanel.SQUARE_SIZE))
			{
				lastRichtung=(int) Math.round(Math.random() * 3);
				placeBomb();
				}
			break;
		}
		case 1: // right
		{	if((isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y))==0 
		||isWall((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y)==1
		|| !isFreeExplosion((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y)
		|| !isFreeExplosion((int)this.x+GamePanel.SQUARE_SIZE,(int)this.y))
		{
			lastRichtung=(int) Math.round(Math.random() * 3);
				placeBomb();
				}
			
		break;
		}
		case 2:// Left
		{if((isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y))==0 
			||isWall((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y)==1
			|| !isFreeExplosion((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y)
			|| !isFreeExplosion((int)this.x-(GamePanel.SQUARE_SIZE),(int)this.y))
		{
		lastRichtung=(int) Math.round(Math.random() * 3);
		if((System.currentTimeMillis()-time>=timeToExplosion)) {
			placeBomb();
			time=System.currentTimeMillis();
		}
		
		}
			
		break;
		}
		case 3:// Down
		{
			if((isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE)))==0 
					||isWall((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))==1
					|| !isFreeExplosion((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))
					|| !isFreeExplosion((int)this.x,(int)this.y+(GamePanel.SQUARE_SIZE))){
				lastRichtung=(int) Math.round(Math.random() * 3);
				if((int) Math.round(Math.random() * 15)==2)
						placeBomb();
					}
			break;
		}
		default:
			break;
		}
}





//wenn der Bot die Explosion trifft, entweder muss sterben oder Health reduzieren.
@Override
public void update() {
	// TODO Auto-generated method stub
	if(!isFreeExplosion(this.x,this.y)) {
		gethit();
		this.img = Ressourcen.IMAGES.playerDead[0][indexAnimPlayer()];
	    GameObjects.bomberObjects.remove(this);
        killbot+=100;
	}else if(!isFreeExplosionbot(this.x,this.y)) {
		
		gethit();
		this.img = Ressourcen.IMAGES.playerDead[0][indexAnimPlayer()];
	    GameObjects.bomberObjects.remove(this);	
	      }else
 			moveRandom();

}
public void gethit() {
	--this.health;
	if(this.health<=0) 
		this.dead=true;
		
if(!dead)
	dontMove=false;

}

}
