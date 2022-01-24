package application.Objects;
import java.awt.Point;
import java.util.ArrayList;
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
	int timeToRandom=2000;
	boolean randBot1 = true ;
	boolean randBot2 = true ;
	boolean randBot3 = true ;
	boolean randBot4 = true ;
	boolean gefahr ;
	int startX ;
	int startY ;
	int zielX ;
	int zielY ;
	Point nextStep ;
	Node nextStepVor ;
	PathFinding pathFinding = new PathFinding();
	Image Bombimag= Ressourcen.IMAGES.BOMBE.getImage();
public Bot(double x, double y, Image img, Boolean p)
{
		super(x, y, img,p);
		// TODO Auto-generated constructor stub

		this.speed=1.25;//2.5, 5, 7 ,8,75 
			time= System.currentTimeMillis();
			
	
}

	//Bombenanzahl prfen aund eine Bombe Platzieren.

void placeBombb( double xx , double yy ) 
{
		if(bombanzahl>0) {
     
			Bomb b= new Bomb( xx , yy, explosion , Bombimag, this );
			b.BombCollision(xx,yy);
			bombanzahl--;
			GameObjects.spawn(b);
		}
}
	
	void placeBomb() 
{
		if(bombanzahl>0) 
		{
			Bomb b= new Bomb( this.x , this.y, explosion , Bombimag, this );
			b.BombCollision(this.x,this.y);
			bombanzahl--;
			GameObjects.spawn(b);
		}
}

//  Methode moveBot()()  suche nach einem guenstigen Weg zum Bomberman  und fuert den Bot zum Bomberman .

public void moveBot()	{

  startX = (int)this.x/GamePanel.SQUARE_SIZE ;
  startY = (int)this.y/GamePanel.SQUARE_SIZE ;

  if ((int)this.x % GamePanel.SQUARE_SIZE != 0 && (int)this.y % GamePanel.SQUARE_SIZE != 0) {
	   moveLeft();
 	   moveUp();
	   return;
  } 
  else {	 
	
	for(int j=0 ; j < GameObjects.tileObjects.size() ; j++) {
		Entities obje = GameObjects.tileObjects.get(j);	
		if(obje instanceof Bomb) {
			
			if( obje.getEntityX() <= this.x && obje.getEntityY() <= this.y ) {
   	             zielX = 14 ;
   	             zielY = 15 ;   	             
			}else  if (obje.getEntityX() < this.x  && obje.getEntityY() > this.y ) {
				 zielX = 14 ;
  	             zielY = 2 ;				
			}
			else  if (obje.getEntityX() > this.x && obje.getEntityY() < this.y ) {
				 zielX = 1 ;
 	             zielY = 15 ;				
			}
			else  if (obje.getEntityX() >= this.x && obje.getEntityY() >= this.y ) {
				 zielX = 1 ;
	             zielY = 2;				
			} 
							
			gefahr = true ;

	    }else gefahr = false ;
	 }


   if (!gefahr) {
	for (int i = 0; i < GameObjects.bomberObjects.size(); i++) {		   
	     Entities obje = GameObjects.bomberObjects.get(i);	  

	     if(obje.isPlayer()) { 	
    	 
	    	 zielX = (int)obje.getEntityX()/GamePanel.SQUARE_SIZE ;
	    	 zielY = (int)obje.getEntityY()/GamePanel.SQUARE_SIZE ;
	       }
	   }
   }
   ArrayList<Node> arrList  = pathFinding.doAlgorithmAStar(startX,startY,zielX, zielY);
	
   if (arrList.isEmpty()) {
	   moveRandom();
	   return;	
   }            
   else{
		 nextStep = pathFinding.nextStep(arrList.get(arrList.size() - 1));
		 
		 nextStepVor = pathFinding.nextStepVor(arrList.get(arrList.size() - 1));

		 if (nextStep.x  > (int)this.x/GamePanel.SQUARE_SIZE ){
		      moveRight();
		 }   
		 if (nextStep.x *GamePanel.SQUARE_SIZE < (int)this.x) {
			 moveLeft();
		 }
         if (nextStep.y > (int)this.y/GamePanel.SQUARE_SIZE){
			 moveDown();
		 }    
		 if (nextStep.y*GamePanel.SQUARE_SIZE  < (int)this.y ){
			 moveUp();
		 }
			           
		 if( !gefahr && (startX - 1 <= zielX ) && (startX + 1 >= zielX  ) && (startY - 1  <= zielY ) && (startY + 1 >= zielY )) {
		     placeBombb(nextStepVor.x *GamePanel.SQUARE_SIZE , nextStepVor.y*GamePanel.SQUARE_SIZE) ;
			        	    
		   //  placeBomb(arrList.get(arrList.size() - 2).x *GamePanel.SQUARE_SIZE , arrList.get(arrList.size() - 2).y*GamePanel.SQUARE_SIZE) ;	        	  
		 }			            
  }
 }
 }

  public void moveBott(){
	isFreeBot();
        for (int i = 0; i < GameObjects.bomberObjects.size(); i++) {		   
	     Entities obje = GameObjects.bomberObjects.get(i);	   	    
	     if(obje.isPlayer()) { 	        	
	         try { 
		     if (randBot1 &&  Character.isFree(this.x , this.y - this.speed) && isFreeBomb(this.x,this.y ,0) && (this.y > obje.getEntityY())) { 
		         moveUp();
			 time= System.currentTimeMillis();
		      } 	 
		      else { 
			    randBot1 = false ;
	                   } 
			 
		      if (randBot2 &&  Character.isFree(this.x + this.speed , this.y) && isFreeBomb(this.x ,this.y,1) && (this.x < obje.getEntityX())) {
			  moveRight();
			  time= System.currentTimeMillis();
		      } else { 
			      randBot2 = false ;
                             } 
			 
		      if (randBot3 &&  Character.isFree(this.x - this.speed , this.y) && isFreeBomb(this.x ,this.y,3)&& (this.x > obje.getEntityX())) {
			  moveLeft();	
			  time= System.currentTimeMillis();
		      } else { 
			      randBot3 = false ;
                             } 
			 
		      if (randBot4 &&  Character.isFree(this.x , this.y + this.speed) &&  isFreeBomb(this.x,this.y ,2) && (this.y < obje.getEntityY()))  {
			  moveDown();
			  time= System.currentTimeMillis();   
		      } 
		      else { 
			     randBot4 = false ;
                           } 
			 
		      if(System.currentTimeMillis()-time <= timeToRandom && !randBot1 && !randBot2 && !randBot3 && !randBot4) {
		         moveRandom();
			 randBot1 = false ;
			 randBot2 = false ;
			 randBot3 = false ;
			 randBot4 = false ;
		      } 
		      else  {
			     randBot1 = true ;
			     randBot2 = true ;
			     randBot3 = true ;
			     randBot4 = true ;
			    } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   }  
        }
  }
	
//Hier bewegt sich der Bot random. void moveRandom(){
	
 public void moveRandom(){
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
//					placeBomb();
//					}
//			break;
//		}
//		default:
//			break;
//		}
//}


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
			//	placeBomb();
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
			//	placeBomb();
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
		//	placeBomb();
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
					//	placeBomb();
					;
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
 			moveBot();

}
	
public void gethit() {
	--this.health;
	if(this.health<=0) 
		this.dead=true;
		
if(!dead)
	dontMove=false;

}

}
