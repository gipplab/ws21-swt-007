package application;




import java.io.IOException;
import java.util.ArrayList;

import application.Objects.Bomb;
import application.Objects.Bomberman;


import javafx.animation.AnimationTimer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class GamePanel    {
	public static final int WIDTH= 600 ;
	public static final int HEIGHT=600 ;
	public static final int ROWS= 20;
	public static final int COLUMNS=ROWS;
	public static final double SQUARE_SIZE= WIDTH*1.0/ROWS ;

 
     public static  ArrayList<Bomb>  Objekte= new ArrayList<>();
	 private GraphicsContext gc;
	 private boolean gameOver;
	 private Scene scene;
	 Canvas canvas;
	 Group root;
	 double Playerspeed; 
	 public static double imageX=4, imageY=4;
	 public Bomberman player;

	 public GamePanel()  {
		
		root  = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		scene = new Scene(root,WIDTH,HEIGHT);
		KeysHandler.attachEventHandlers(scene);
		//scene.setOnKeyPressed(this);
		gc = canvas.getGraphicsContext2D();
		  
	}
	public void init() throws IOException {
	player= new Bomberman(2,2, Ressourcen.IMAGES.PLAYER1.getImage());
	Ressourcen.readFiles();
	Playerspeed=0.15;
	//read .CSV File
     Ressourcen.readCSV();
	run();
	AnimationTimer timeline = new AnimationTimer(){

		@Override
		public void handle(long arg0) {
			update();
			try {
			
				Thread.sleep(90);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}
		
	};
	timeline.start();
	
 
	
	};

public  Bomberman getPlayer() {
	return player;
}
public Scene getScene() {
	return scene;
}
public double getSQUARE_SIZE() {
	return SQUARE_SIZE;
}
public double getWidth() {
	return WIDTH;
}
public double getHeight() {
	return HEIGHT;
}
public void run() {
	
	drawBackground(gc);
	drawPlayer(gc,imageX,imageY);
	if(!Objekte.isEmpty()) {
	gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(),SQUARE_SIZE*imageX, SQUARE_SIZE*imageY,SQUARE_SIZE,SQUARE_SIZE);
	Objekte.remove(this);
	}
	}
	
private void update() {
	InputManager.handlePlayerMovements(player);
	drawBackground(gc);
	drawPlayer(gc,player.getX(),player.getY());
	drawBomb(gc);
//	if(Objekte==1) {
//	gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(),SQUARE_SIZE*imageX, SQUARE_SIZE*imageY,SQUARE_SIZE,SQUARE_SIZE);
//	Objekte =0;
//	}
	
	
	
}
private void drawBackground( GraphicsContext gc) {
	
	for(int i=0 ; i<ROWS; i++) {
		for(int j=0;j<COLUMNS;j++) {
			if(i==0 || j==0|| i==ROWS-1||j==COLUMNS-1)
				gc.drawImage(Ressourcen.IMAGES.HARDWALL.getImage(),SQUARE_SIZE*i, SQUARE_SIZE*j,SQUARE_SIZE,SQUARE_SIZE);
				
			else if((i+j)%2==0) {
				gc.setFill(Color.WHITE);
				gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);
				if(i%2 ==0 )
					gc.drawImage(Ressourcen.IMAGES.SOFTWALL.getImage(),SQUARE_SIZE*i, SQUARE_SIZE*j,SQUARE_SIZE,SQUARE_SIZE);}
			else { gc.setFill(Color.WHITE);
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);}
			
		
		}
	}	

}

private void drawPlayer (GraphicsContext gc, double d , double e)	{
	// draw Player in anfangscoordinate
	 gc.drawImage(Ressourcen.IMAGES.PLAYER1.getImage(),SQUARE_SIZE*d, SQUARE_SIZE*e,SQUARE_SIZE,SQUARE_SIZE);
	
}

private void drawBomb (GraphicsContext gc)	{
	// draw Player in anfangscoordinate
	for(Bomb i : Objekte) {
		gc.drawImage(Ressourcen.IMAGES.BOMBE.getImage(),SQUARE_SIZE*i.getX(), SQUARE_SIZE*i.getY(),SQUARE_SIZE,SQUARE_SIZE);
		
	}

	 
	
}






}

// KeyListner m�ssen wir noch verbessern 
//um den Character  in alle Richtungen bewegen zu k�nnen
// Up left && Up right && down left && down right 




