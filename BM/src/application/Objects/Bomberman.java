package application.Objects;
import javafx.scene.image.Image;
import application.Ressourcen;


public class Bomberman extends Character{
String Name="";
int timeExplosion = 0 ;	
	
public Bomberman(double x, double y,Image img, Boolean p) {
	super(x,y,img,p);
	this.speed=2.5;//2.5, 5, 7 ,8,75 

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


@Override
public void update() {

	if(!isFreeExplosion(this.x,this.y)) {
		timeExplosion++;
		if(timeExplosion == 50) {
			   this.gethit();
			   timeExplosion = 0 ;			  
		}
		this.img = Ressourcen.IMAGES.playerDead[indexAnimPlayer()];
	}
	else {  timeExplosion = 0 ;
	        if(this.dead) {
	           GameObjects.bomberObjects.remove(this);
	        }
	}
	
	int v=isItem(this.x, this.y);
	 
        switch(v) {
	       case 0:{	// Hertz
	    	   HealthUp();
	       	break;
	       }
	       case 1:{ // Bomb
	    		BombanzahlUp();
	    		
	       	break;
	       }
	       case 2:{	// Flamme
	    	   ExplosionUp();
	
	       	break;
	       }
	       case 3:{	// Speed
	    	   speedUp();
	       	break;
	       }
	
	       default:
	       	break;
	       
	       };
	
}



@Override
protected int getItemtype() {
	// TODO Auto-generated method stub
	return -1;
}
	


}





//package application.Objects;
//
//import application.Client;
//import application.GamePanel;
//import application.Main;
//import application.Ressourcen;
//import javafx.scene.image.Image;
//
//public abstract class Character extends Entities {
//
//	int bombanzahl;
//	double speed;
//	int explosion;
//	int health;
//	boolean dead ;
//	Boolean Player= false;
//	int framePlayer = 0, intervalPlayer = 5, indexAnimPlayer = 0;
//	
//	public Character(double x, double y,Image img, Boolean isPlayer) {
//		super(x,y,img);
//		// TODO Auto-generated constructor stub
//		this.Player=isPlayer;
//		bombanzahl=1;
//		 explosion=1;
//		 health=1;
//		 dead=false ;
//		
//	}
//	
//	public  boolean isFree(double nextX, double nextY) {// Wande Kiste 
//	
//	     Entities obje;
//	     
//	     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
//	     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
//
//	     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//	     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
//
//	     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
//	     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//
//	     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//	     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//	   
//	     for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
//	    	   
//	   	    obje = GameObjects.tileObjects.get(i);
//	   	    
//	 	        if((obje.getEntityImage().equals(Ressourcen.IMAGES.SOFTWALL.getImage()))
//	 	          ||(obje.getEntityImage().equals(Ressourcen.IMAGES.HARDWALL.getImage()))) {
//	 	        	
//	 		      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
//	 		            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
//	 			        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
//	 			        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
//	 		    	  		return false;
//	 		    }             
//	 	  }
//	      	return true;
//	 }
//
//public boolean isFreeExplosion(double nextX, double nextY) {
//		// TODO Auto-generated method stub
//		  Entities obje;
//	
//		     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
//		     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
//	
//		     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
//		    
//		     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
//		     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		 
//		     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		  
//		    for (int i = 0; i < GameObjects.explosionObjects.size(); i++) {	
//		 	    obje = GameObjects.explosionObjects.get(i);
//		 	    
//			        if(obje.getEntityImage().equals(Ressourcen.IMAGES.EXPLOSION.getImage())) {
//			        	
//				      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
//				            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
//					        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
//					        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
//					           return false;
//				    }             
//			  }
//		    	return true;
//		} 
//
//	public int isItem(double nextX, double nextY) {
//		// TODO Auto-generated method stub
//		  Entities obje;
//	
//		     int nextX_1 = (int) (nextX / GamePanel.SQUARE_SIZE);
//		     int nextY_1 = (int) (nextY / GamePanel.SQUARE_SIZE);
//	
//		     int nextX_2 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		     int nextY_2 = (int) (nextY / GamePanel.SQUARE_SIZE);
//		    
//		     int nextX_3 = (int) (nextX / GamePanel.SQUARE_SIZE);
//		     int nextY_3 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		 
//		     int nextX_4 = (int) ((nextX + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		     int nextY_4 = (int) ((nextY + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
//		  
//		    for (int i = 0; i < GameObjects.tileObjects.size(); i++) {	
//		 	    obje = GameObjects.tileObjects.get(i);
//		 	    
//			        if(obje instanceof Items) {
//				      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
//				            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
//					        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
//					        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
//				      	{  		GameObjects.tileObjects.remove(obje);
//				    	  		return obje.getItemtype();
//				     
//				      	}
//				    }             
//			  }
//		    	return -1;
//		} 
//
//	public boolean isPlayer() {
//		return Player;
//		
//	}
//	public int getBombanzahl(){
//		return this.bombanzahl;
//	}
//
//public int getExplosion(){
//		return this.explosion;
//	}
//public void BombanzahlUp(){
//		 this.bombanzahl++;
//	}
//
//public void BombanzahlDown(){
//		 this.bombanzahl--;
//	}
//     
//	 public int indexAnimPlayer() {
//		 framePlayer++;
//	        if (framePlayer > intervalPlayer) {
//	            framePlayer = 0;
//	            indexAnimPlayer++;
//	            if (indexAnimPlayer > 3) {
//	                indexAnimPlayer = 0;
//	            }
//	        }
//	        return indexAnimPlayer;
//	 }
//
//	protected void ExplosionUp(){
//	this.explosion++;
//}
//protected void HealthUp(){
//	this.health++;
//}

//	
//public void moveRight() {
//	
//	if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )) {
//	 	 if( isFree(this.x+  this.speed,this.y))
//	 	  this.x= this.x+  this.speed;
//	 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.8) ) {
//	 		  this.y= this.y+  (this.speed/4);	
//	 		}
//	 	 else if( (this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.2) ) {
//	 		  this.y= this.y-  (this.speed/4);	
//	 		 
//	 	 }
//	 	  if(Main.online) {
//	 		   Client.updateString =Client.updateString+"/RIGHT";
//	 		   // Client.accessServer("Play-RIGHT");
//	 	   }
//		
//	         this.img = Ressourcen.IMAGES.playerRight[indexAnimPlayer()];
//
//    } 
//	
//}
//
//public void moveLeft() {
//	if((this.x >GamePanel.SQUARE_SIZE)) {
//	 	 if(isFree(this.x- this.speed ,this.y))
//	 		 this.x=this.x- this.speed;
//		 	 else if(this.y%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.8) ) {
//		 		  this.y= this.y+  (this.speed/4);	
//		 		}
//		 	 else if( (this.y%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.2) ) {
//		 		  this.y= this.y-  (this.speed/4);	
//		 		 
//		 	 }
//	 	   if(Main.online) {
//	 		   	Client.updateString =Client.updateString+"/LEFT";
//	 		   	//Client.accessServer("Play-LEFT");
//	 	   }
//		
//	         this.img = Ressourcen.IMAGES.playerLeft[indexAnimPlayer()];
//	 	  
//	}
//}
//
//public void moveUp() {
//	if((this.y >GamePanel.SQUARE_SIZE)) {
//		if(isFree(this.x ,this.y - this.speed))
//				   this.y=this.y - this.speed;
//		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.8) )
//		 		  this.x= this.x+  (this.speed/4);	
//		else if( (this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.2) ) 
//		 		  this.x= this.x-  (this.speed/4);	
//		
//		if(Main.online) {
//	 		   	Client.updateString =Client.updateString+"/UP";
//	 		   	//Client.accessServer("Play-UP");
//	 	   		}
//		
//	        this.img = Ressourcen.IMAGES.playerUp[indexAnimPlayer()];
//		 	 
//		}
//	
//}
//
//public void moveDown() {
//	if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)) {
//		if(isFree(this.x ,this.y+ this.speed))
//			 this.y=this.y+ this.speed;
//		else if(this.x%GamePanel.SQUARE_SIZE>(GamePanel.SQUARE_SIZE*0.8) )
//	 		  this.x= this.x+  (this.speed/4);	
//		else if( (this.x%GamePanel.SQUARE_SIZE) < (GamePanel.SQUARE_SIZE*0.2) ) 
//	 		  this.x= this.x-  (this.speed/4);	
//	 	   if(Main.online) {
//	 		   Client.updateString =Client.updateString+"/DOWN";
//	 		   }
//		
//	        this.img = Ressourcen.IMAGES.playerDown[indexAnimPlayer()];
//	 	  
//	}
//	}
//
//	
//}

