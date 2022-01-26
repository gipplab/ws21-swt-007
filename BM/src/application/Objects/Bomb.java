package application.Objects;


import application.Client;
import application.GamePanel;
import application.KeysHandler;
import application.Main;
import application.Ressourcen;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Bomb extends TileObjects 
{
	Character player;
	int power;
	double time;
	final double timeToExplosion=3000;
	Boolean death;
	static MediaPlayer mediaPlayer;
public Bomb(double x2, double y2,int power, Image image,Character p)  {
	super(x2,y2,image);
	this.x=x2;
	this.y=y2;
	

	death=false;
	this.power=power;
	player=p;
	
	if (BombeDuplikate()||ExDuplikate()) {
		player.BombanzahlUp();
		death=true;
		GameObjects.tileObjects.remove(this);
	}
	if(KeysHandler.playMusik)
	music();
	time= System.currentTimeMillis();
	}


//die Bombe im richtigen Block platzieren 
public void BombCollision(double x1, double y1) 
{
	int a = (int) (x1 % GamePanel.SQUARE_SIZE);
	int b= (int) (y1 % GamePanel.SQUARE_SIZE);
	System.out.println(a+", "+ b);
	if(a < GamePanel.SQUARE_SIZE/2) {
		this.x=(int) x1-a ;

	}else {
		this.x = (int)x1+(GamePanel.SQUARE_SIZE-a);

	}
	if(b < GamePanel.SQUARE_SIZE/2) {
		this.y=(int) y1-b;

		}
		else {
			this.y=(int)y1 +(GamePanel.SQUARE_SIZE-b);
		}
}


public double getX() {return this.x;}

public double getY() {return this.y;}

public boolean isFreeExplosion() 
{
	// TODO Auto-generated method stub
	 Entities obje;
	    
	    int nextX_1 = (int) (this.x / GamePanel.SQUARE_SIZE);
	    int nextY_1 = (int) (this.y / GamePanel.SQUARE_SIZE);

	    int nextX_2 = (int) ((this.x + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	    int nextY_2 = (int) (this.y / GamePanel.SQUARE_SIZE);

	    int nextX_3 = (int) (this.x / GamePanel.SQUARE_SIZE);
	    int nextY_3 = (int) ((this.y + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);

	    int nextX_4 = (int) ((this.x + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	    int nextY_4 = (int) ((this.y + GamePanel.SQUARE_SIZE - 1) / GamePanel.SQUARE_SIZE);
	  
	    for (int i = 0; i < GameObjects.explosionObjects.size(); i++) {	
	 	    obje = GameObjects.explosionObjects.get(i);
	 	    
		        if(obje.getEntityImage().equals(Ressourcen.IMAGES.EXPLOSION.getImage())) {
		        	
			      if((obje.getEntityX()==nextX_1*GamePanel.SQUARE_SIZE  && obje.getEntityY()==nextY_1* GamePanel.SQUARE_SIZE)||
			            (obje.getEntityX()==nextX_2* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_2* GamePanel.SQUARE_SIZE)||
				        (obje.getEntityX()==nextX_3* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_3* GamePanel.SQUARE_SIZE)||
				        (obje.getEntityX()==nextX_4* GamePanel.SQUARE_SIZE && obje.getEntityY()==nextY_4* GamePanel.SQUARE_SIZE))
				           return false;
			    }             
		  }
	    	return true;
	}

//START java -jar --module-path "java\openjfx-17.0.1_windows-x64_bin-sdk\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.media  Bomberman.jar
//prüft, ob in einem Block mehrere Bomben gibt
private Boolean BombeDuplikate() 
{
	   for(int i=0; i< GameObjects.tileObjects.size(); i++) 
		   if(this.x==(GameObjects.tileObjects.get(i).getX()) && this.y==(GameObjects.tileObjects.get(i).getY())) 
			   return true;
	   return false;
	   
}
private Boolean ExDuplikate() 
{
	   for(int i=0; i< GameObjects.explosionObjects.size(); i++) 
		   if(this.x==(GameObjects.explosionObjects.get(i).getEntityX()) && this.y==(GameObjects.tileObjects.get(i).getEntityY())) 
			   return true;
	   return false;
	   
}
public boolean getDeath()  
{
	return death;
}



@Override
public boolean isPlayer() 
{
	// TODO Auto-generated method stub
	return false;
}

//Hier wird geprüft, ob es in diesem Block eine Explosion gibt.
@Override
protected int getItemtype() 
{
	return -1;
}


@Override
public boolean isBreakable() {
	// TODO Auto-generated method stub
	return true;
} 

public void music() {
	Media h = new Media(getClass().getResource("Explosion.mp3").toExternalForm());
	mediaPlayer = new MediaPlayer(h);
	mediaPlayer.setVolume(0.30);		
	
}


//Bombezustand und Bombeanzahl aktualisieren.
public void update()  
{
	
	if((System.currentTimeMillis()-time>=timeToExplosion&& !death)|| !isFreeExplosion()) {
		mediaPlayer.play();
		death=true;
		GameObjects.tileObjects.remove(this);
		if(player instanceof Bomberman) { 
		@SuppressWarnings("unused")
		Explotionart ex0= new Explotionart((int)this.x,(int) this.y);
		@SuppressWarnings("unused")
		Explotionart ex1= new Explotionart((int)this.x,(int) this.y, 0, this.power);
		@SuppressWarnings("unused")
		Explotionart ex2= new Explotionart((int)this.x,(int) this.y, 1, this.power);
		@SuppressWarnings("unused")
		Explotionart ex3= new Explotionart((int)this.x, (int)this.y, 2, this.power);
		@SuppressWarnings("unused")
		Explotionart ex4= new Explotionart((int)this.x,(int) this.y, 3, this.power);
		if(Main.online) {
			Client.updateString =System.currentTimeMillis()+"-NOBOMB";
     	    String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
 		   	String resp= "";
 			resp=Client.accessServer(messageout);
 			System.out.println(resp);
		}
		}
		else if(player instanceof Bot)
		{
			@SuppressWarnings("unused")
			Explosionbot ex0= new Explosionbot((int)this.x,(int) this.y);
			@SuppressWarnings("unused")
			Explosionbot ex1= new Explosionbot((int)this.x,(int) this.y, 0, this.power);
			@SuppressWarnings("unused")
			Explosionbot ex2= new Explosionbot((int)this.x,(int) this.y, 1, this.power);
			@SuppressWarnings("unused")
			Explosionbot ex3= new Explosionbot((int)this.x, (int)this.y, 2, this.power);
			@SuppressWarnings("unused")
			Explosionbot ex4= new Explosionbot((int)this.x,(int) this.y, 3, this.power);
		}
		    
			player.BombanzahlUp();
	}
}

}


