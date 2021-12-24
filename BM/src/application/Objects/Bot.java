package application.Objects;
import application.GamePanel;
import application.Ressourcen;
import javafx.scene.image.Image;



public class Bot extends Character {


	Image Bombimag= Ressourcen.IMAGES.BOMBE.getImage();
	public Bot(double x, double y, Image img, Boolean p) {
		super(x, y, img,p);
		// TODO Auto-generated constructor stub
			this.bombanzahl=3;
			this.speed=5;// 5, 7 ,8,75 
						// Rows= 15
			this.explosion=1;
			this.dead=false;
			this.health=1;
		}
	

	
	public void moveRandom() {
		 int keyRandom = (int) Math.round(Math.random() * 4);
		 if(keyRandom == 0)
			 moveRight();
		 else if(keyRandom == 1)
			 moveLeft();
		 else if(keyRandom == 2)
			 moveUp();
		 else if(keyRandom == 3)
		    moveDown();
		 else if(keyRandom==4) {
			 Bomb b= new Bomb( this.x , this.y , Bombimag );
			 b.BombCollision(this.getX(),this.getY());
			 if(!b.BombeDuplikate()) {
				 this.BombanzahlDown();
				 GameObjects.spawn(b);
  	   }
	}
		 }

	
	@Override
	protected void moveDown() {
		// TODO Auto-generated method stub
		if((this.y < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE)&& (isFree(this.x ,this.y+ this.speed))) 
			this.y=this.y+ this.speed;
	
	}
	@Override
	protected void moveUp() {
		// TODO Auto-generated method stub
		if((this.y >GamePanel.SQUARE_SIZE)&& (isFree(this.x ,this.y - this.speed))) 
			this.y=this.y - this.speed;
	
	}
	@Override
	protected void moveLeft() {
		// TODO Auto-generated method stub
		if((this.x >GamePanel.SQUARE_SIZE)&& (isFree(this.x- this.speed ,this.y))) 
			this.x=this.x- this.speed;

	}
	@Override
	protected void moveRight() {
		// TODO Auto-generated method stub
		if(( this.x < (GamePanel.ROWS-2)*GamePanel.SQUARE_SIZE )&& (isFree(this.x+  this.speed,this.y))) {
			this.x= this.x+  this.speed;
	}
	}
	

	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(!isFreeExplosion()) {
			this.dead=true;
			GameObjects.bomberObjects.remove(this);
			}
		else
		moveRandom();
	}
	

	@Override
	public boolean getDeath() {
		// TODO Auto-generated method stub
		return this.dead;
	}
	
	
	
	
	}