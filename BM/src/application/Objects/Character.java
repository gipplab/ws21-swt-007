package application.Objects;
import application.GamePanel;
import application.Ressourcen;
import javafx.scene.image.Image;

public abstract class Character extends Entities {

	public int bombanzahl;
	public double speed;
	public int explosion;
	public int health;
	boolean dead ;
	double time;
	protected double startX;
	protected double startY;
	final double timeToExplosion=1200;
	protected boolean dontMove=false;
	Boolean Player= false;
	int framePlayer = 0, intervalPlayer = 5, indexAnimPlayer = 0;
	public int PlayerFarbe;
public Character(double x, double y,Image img, Boolean isPlayer) {
	super(x,y,img);
	// TODO Auto-generated constructor stub
	this.Player=isPlayer;
	startX=x;
	startY=y;
	bombanzahl=1;
	 explosion=1;
	 health=1;
	 dead=false ; 

}
public int getPlayerFarbe() {
	return PlayerFarbe;
}



public void setPlayerFarbe(int playerFarbe) {
	PlayerFarbe = playerFarbe;
}
//Hier wird geprï¿½ft, ob das Block frei(ein Weg) ist.
	public  boolean isFree(double nextX, double nextY) {
	     boolean  frei = true;
	     Entities obje;
	     
	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);

	     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	   
	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
	    	   
	   	    obje = GameObjects.tileObjects.get(i);
	   	    
	 	        if( obje instanceof Wall ) {
	 	        	
	 		      if(	(obje.getEntityX() == nextX_1 * GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
	 		            (obje.getEntityX() == nextX_2 * GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
	 			        (obje.getEntityX() == nextX_3 * GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
	 			        (obje.getEntityX() == nextX_4 * GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
	 			           frei = false;
	 		    }             
	 	  }
	     
	      	return frei;
	 }
<<<<<<< HEAD
	//Hier wird geprï¿½ft, ob es in diesem Block eine Bombe gibt.
	@SuppressWarnings("unused")
	private  boolean isFreeBomb(double nextX, double nextY) {
	     Entities obje;
	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
=======
	
>>>>>>> Yazan-Main

public boolean isFreeExplosion(double x,  double y) {
	
	double restX= x% GamePanel.SQUARE_SIZE;
	double restY= y% GamePanel.SQUARE_SIZE;
	
	if(restX==0 && restY==0) {
		for(int i=0; i < GameObjects.explosionObjects.size(); i++) {
			if(GameObjects.explosionObjects.get(i).getEntityX()==x&& GameObjects.explosionObjects.get(i).getEntityY()==y) {
				 System.out.println(this.x +", " +this.y);
				return false;}
			
		}
	}
	
	else if(restX==0 && restY!=0) {
		for(int i=0; i < GameObjects.explosionObjects.size(); i++) {
		 if((GameObjects.explosionObjects.get(i).getEntityX() == x &&
				 GameObjects.explosionObjects.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjects.get(i).getEntityX() == x &&
				 GameObjects.explosionObjects.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY ))) {
			 System.out.println(this.x +", " +this.y);
			 return false;}
		 }
		
	}
	
	if(restX!=0 && restY==0) {
		
		for(int i=0; i < GameObjects.explosionObjects.size(); i++) 
		{
			
			 if((GameObjects.explosionObjects.get(i).getEntityX() == x-restX &&
					 GameObjects.explosionObjects.get(i).getEntityY() == y)
					 ||
					 (GameObjects.explosionObjects.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE- restX)
							 &&
					 GameObjects.explosionObjects.get(i).getEntityY() == y ))
			 {
				return false;}
		}
			 }
	
	else if(restX!=0 && restY!=0) {
		
	for(int i=0; i < GameObjects.explosionObjects.size(); i++) {
	
		 if(	(GameObjects.explosionObjects.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjects.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjects.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjects.get(i).getEntityY() ==y+(GamePanel.SQUARE_SIZE- restY ))
				 ||
				 (GameObjects.explosionObjects.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjects.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjects.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjects.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY )))
		 {
			 System.out.println(this.x +", " +this.y);
			return false;}
	}
		
	}
	
	
	
	
	return true;
	
}


<<<<<<< HEAD
	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	   
	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
	    	   
	   	    obje = GameObjects.tileObjects.get(i);
	   	    
	 	        if((obje.getEntityImage().equals(Ressourcen.IMAGES.BOMBE.getImage()))) {
	 		    	  		return false;
	 		    }             
	 	  }
	      	return true;
	 }
	//Hier wird geprï¿½ft, ob es in diesem Block eine Explosion gibt.
	public boolean isFreeExplosion(double nextX, double nextY) {
		// TODO Auto-generated method stub
		  Entities obje;
=======
public boolean isFreeExplosionbot(double x,  double y) {
>>>>>>> Yazan-Main
	
	double restX= x% GamePanel.SQUARE_SIZE;
	double restY= y% GamePanel.SQUARE_SIZE;
	
	if(restX==0 && restY==0) {
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
			if(GameObjects.explosionObjectsbot.get(i).getEntityX()==x&& GameObjects.explosionObjectsbot.get(i).getEntityY()==y) {
				 System.out.println(this.x +", " +this.y);
				return false;}
			
		}
	}
	
	else if(restX==0 && restY!=0) {
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
		 if((GameObjects.explosionObjectsbot.get(i).getEntityX() == x &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY ))) {
			 System.out.println(this.x +", " +this.y);
			 return false;}
		 }
		
	}
	
	if(restX!=0 && restY==0) {
		
		for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
			
			 if((GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
					 GameObjects.explosionObjectsbot.get(i).getEntityY() == y)
					 ||
					 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE- restX)
							 &&
					 GameObjects.explosionObjectsbot.get(i).getEntityY() == y ))
			 {
				 System.out.println(this.x +", " +this.y);
				return false;}
		}
			}
	
	else if(restX!=0 && restY!=0) {
		
	for(int i=0; i < GameObjects.explosionObjectsbot.size(); i++) {
	
		 if(	(GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x-restX &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() ==y+(GamePanel.SQUARE_SIZE- restY ))
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y-restY)
				 ||
				 (GameObjects.explosionObjectsbot.get(i).getEntityX() == x+(GamePanel.SQUARE_SIZE-restX ) &&
				 GameObjects.explosionObjectsbot.get(i).getEntityY() == y+(GamePanel.SQUARE_SIZE- restY )))
		 {
			 System.out.println(this.x +", " +this.y);
			return false;}
	}
		
	}
	
	
	
	
	return true;
	
}
// 0 Up, 1 Right, 2 Down, 3 Left
public boolean isFreeBomb(double x1,  double y1,int richtung) {
double y2=y1;
double x2=x1;

switch(richtung) {
case 0:
	if(x1%GamePanel.SQUARE_SIZE!=0) {
		y1 -= GamePanel.SQUARE_SIZE-(y1%GamePanel.SQUARE_SIZE);
		y2=y1;
		
		x1-= x1%GamePanel.SQUARE_SIZE ;
		
		x2 =x1+GamePanel.SQUARE_SIZE;
		}
	else if(x1 %GamePanel.SQUARE_SIZE==0) {
		y1 -= GamePanel.SQUARE_SIZE-(y1%GamePanel.SQUARE_SIZE);
		y2=y1;
		x2=x1;
	}
	break;
case 1:
	if(y1%GamePanel.SQUARE_SIZE!=0) {
		y1-= y1%GamePanel.SQUARE_SIZE ;
		y2=y1+GamePanel.SQUARE_SIZE;
		x1+= GamePanel.SQUARE_SIZE-(x1% GamePanel.SQUARE_SIZE);
		x2=x1;
		}
	else if(y1%GamePanel.SQUARE_SIZE==0) {
		y2=y1;
		
		x1+= GamePanel.SQUARE_SIZE-(x1% GamePanel.SQUARE_SIZE);
		x2=x1;
	}
		
	break;
case 2:
	if(x1%GamePanel.SQUARE_SIZE!=0) {
		y1 += GamePanel.SQUARE_SIZE-(y1%GamePanel.SQUARE_SIZE);
		y2=y1;
		x1-= x1%GamePanel.SQUARE_SIZE ;
		x2 =x1+GamePanel.SQUARE_SIZE;
		}
	else if(x1 %GamePanel.SQUARE_SIZE==0) {
		y1 += GamePanel.SQUARE_SIZE-(y1%GamePanel.SQUARE_SIZE);
		y2=y1;
		x2=x1;
	}
	break;
case 3:
	if(y1%GamePanel.SQUARE_SIZE!=0) {
		y1-= y1%GamePanel.SQUARE_SIZE ;
		y2=y1+GamePanel.SQUARE_SIZE;
		if(x1%GamePanel.SQUARE_SIZE!=0) 
			x1-= x1% GamePanel.SQUARE_SIZE;
		else
			x1-= GamePanel.SQUARE_SIZE;
			x2=x1;
		}
	else if(y1%GamePanel.SQUARE_SIZE==0) {
		y2=y1;
		if(x1%GamePanel.SQUARE_SIZE!=0) 
			x1-= x1% GamePanel.SQUARE_SIZE;
		else
			x1-= GamePanel.SQUARE_SIZE;
		x2=x1;
	}
	break;
}

	for(int i=0;i< GameObjects.tileObjects.size();i++)
	{
		if(GameObjects.tileObjects.get(i) instanceof Bomb) {
			
			if(GameObjects.tileObjects.get(i).getEntityX()==x1 && GameObjects.tileObjects.get(i).getEntityY()==y1
					||
				GameObjects.tileObjects.get(i).getEntityX()==x2 && GameObjects.tileObjects.get(i).getEntityY()==y2)
				return false;
			
		}
			}
	
	
	return true;	
}


public boolean getDeath() {
	return this.dead;
}
//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
public void gethit() {
	--this.health;
	if(this.health<=0)
		this.dead=true;
if(!dead)
	dontMove=false;

this.x=startX;
this.y=startY;
}

public boolean isPlayer() {
		return Player;
		
	}
public int getBombanzahl(){
		return this.bombanzahl;
	}

public int getExplosion(){
		return this.explosion;
	}
<<<<<<< HEAD
	//Bombenanzahl erhï¿½hen.
	public void BombanzahlUp(){
=======
	//Bombenanzahl erhöhen.
public void BombanzahlUp(){
>>>>>>> Yazan-Main
		 this.bombanzahl++;
	}
	//Bombenanzahl reduzieren.
public void BombanzahlDown(){
		 this.bombanzahl--;
	}
//Explosion reichweite erhï¿½hen.  
public void ExplosionUp(){
	this.explosion++;
}
//Health erhï¿½hen.
public void HealthUp(){
	this.health++;
}
public int getHealth(){
	return this.health;
}
public Image getImage(){	
	return img;
}
double getSpeed(){
	return this.speed;
}

public double getX(){
	return this.x;
}

public double getY(){
	return this.y;
}
@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
}
public int indexAnimPlayer() {
		 framePlayer++;
	        if (framePlayer > intervalPlayer) {
	            framePlayer = 0;
	            indexAnimPlayer++;
	            if (indexAnimPlayer > 3) {
	                indexAnimPlayer = 0;
	            }
	        }
	        return indexAnimPlayer;
	 }
	 
//Hier wird geprï¿½ft, ob es in diesem Block ein Item gibt.
		public int isItem(double nextX, double nextY) {
		// TODO Auto-generated method stub
		  Entities obje;
	
		     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
	
		     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
		    
		     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
		     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		 
		     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
		  
		    for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
		 	    obje = GameObjects.tileObjects.get(i);
		 	    
		 	   if(obje instanceof Items) {
				      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
				            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
					        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
				      	{  		GameObjects.tileObjects.remove(obje);
			    	  		return obje.getItemtype();
				     
				      	}
				    }             
			  }
		    	return -1;
		} 
//Geschwindigkeit erhï¿½hen.		
protected void speedUp() {
		if(speed ==2.5)
			speed=5;
		else if(speed ==5)
			speed=7;
		else if(speed==7)
			speed=7;
		
		
		
}
//Rechts laufen.	
public void moveRight() {
	
	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )&&!dontMove) {
	 	 if(isFree(this.x+  this.speed,this.y)&& isFreeBomb(this.x,this.y,1)){
	 	    this.x= this.x+  this.speed;
<<<<<<< HEAD
		 }
	 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.65)) {
	 		 this.y= this.y+  (this.speed/4);	
	 	      }
	 	 else if((this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.35)) {
	 		  this.y= this.y-  (this.speed/4);		 		 
	 	      }
        } //die Pixel verteilung kï¿½nnte in der Calss Bopmberman implementiert werden und die Strucktur zu vereinfachen 
=======
		 } 
	 	 else if(this.x % GamePanel.SQUARE_SIZE !=0) {
	 		 this.x+= GamePanel.SQUARE_SIZE - (this.x % GamePanel.SQUARE_SIZE);
	 		 
	 	 }
	 	 
	 	this.img = Ressourcen.IMAGES.playerLeft[this.PlayerFarbe][indexAnimPlayer()];

        } //die Pixel verteilung könnte in der Calss Bopmberman implementiert werden und die Strucktur zu vereinfachen 
>>>>>>> Yazan-Main
	// und nicht immer auf Ressourcen greifen 
	// die Player Matrix wird ein Teil des class  Charackter werden

	this.img = Ressourcen.IMAGES.playerRight[this.PlayerFarbe][indexAnimPlayer()];	
}
//Links laufen.
public void moveLeft() {
	
	if(((this.x) >GamePanel.SQUARE_SIZE)&&!dontMove) {
	 	 if(isFree(this.x- this.speed ,this.y)&&isFreeBomb(this.x,this.y,3)){
	            this.x=this.x- this.speed;
		 }
	 	 else if(this.x % GamePanel.SQUARE_SIZE !=0) {
	 		 this.x-= this.x % GamePanel.SQUARE_SIZE;
	 		 
	 	 }
	 	  
	}
	this.img = Ressourcen.IMAGES.playerLeft[this.PlayerFarbe][indexAnimPlayer()];
}
//nach Oben laufen.
public void moveUp() {
	
	if((this.y >GamePanel.SQUARE_SIZE)&&!dontMove) {
		if(isFree(this.x ,this.y - this.speed)&& isFreeBomb(this.x,this.y,0)) {
		   this.y=this.y - this.speed;
		}
		 else if(this.y % GamePanel.SQUARE_SIZE !=0) {
			 this.y-= this.y % GamePanel.SQUARE_SIZE;
	 		 
	 	 }

	}
	this.img = Ressourcen.IMAGES.playerUp[this.PlayerFarbe][indexAnimPlayer()];	
	 

		
}
//nach Unten laufen.
public void moveDown() {
	
	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)&&!dontMove) {
		if(isFree(this.x ,this.y+ this.speed)&& isFreeBomb(this.x,this.y,2)) {
		   this.y=this.y+ this.speed;
		}
		 else if(this.y % GamePanel.SQUARE_SIZE !=0) {
	 		 this.y+= GamePanel.SQUARE_SIZE - (this.y % GamePanel.SQUARE_SIZE);
	 		 
	 	 }
 	  
	}
	this.img = Ressourcen.IMAGES.playerDown[this.PlayerFarbe][indexAnimPlayer()];
}

	
}
