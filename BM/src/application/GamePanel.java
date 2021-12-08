package application;



import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


public class GamePanel extends Thread implements EventHandler<KeyEvent>  {
	public static final int WIDTH= 600 ;
	public static final int HEIGHT=600 ;
	public static final int ROWS= 17;
	public static final int COLUMNS=ROWS;
	public static final double SQUARE_SIZE= WIDTH*1.0/ROWS ;
    private Image player1;
    private Image bombe1;
    private Image Background;
    private Image Background1;
    private  int Objekte ;
	 private GraphicsContext gc;
//	 private boolean gameOver;
	 private Scene scene;
	 Canvas canvas;
	 Group root;
	 double Playerspeed; //2, 3
	 public static double imageX=4, imageY=4;

	 public GamePanel()  {
		
		root  = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		scene = new Scene(root,WIDTH,HEIGHT);
		scene.setOnKeyPressed(this);
		gc = canvas.getGraphicsContext2D();
		  
	}
	public void init() {
	player1 = new Image(getClass().getResource("img/CharacterImages/player.jpg").toString());
	bombe1= new Image(getClass().getResource("img/danny.jpg").toString());
	Background= new Image(getClass().getResource("img/hardWall.jpg").toString());
	Background1= new Image(getClass().getResource("img/softWall.jpg").toString());
	Playerspeed=0.15;
	Objekte = 0;
	run();
		int a;
	};


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
	if(Objekte==1) {
	gc.drawImage(bombe1,SQUARE_SIZE*imageX, SQUARE_SIZE*imageY,SQUARE_SIZE,SQUARE_SIZE);
	Objekte =0;}
	}
	

private void drawBackground( GraphicsContext gc) {
	
	for(int i=0 ; i<ROWS; i++) {
		for(int j=0;j<COLUMNS;j++) {
			if(i==0 || j==0|| i==ROWS-1||j==COLUMNS-1)
				gc.drawImage(Background,SQUARE_SIZE*i, SQUARE_SIZE*j,SQUARE_SIZE,SQUARE_SIZE);
				
			else if((i+j)%2==0) {
				gc.setFill(Color.WHITE);
				gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);
				if(i%2 ==0 )
					gc.drawImage(Background1,SQUARE_SIZE*i, SQUARE_SIZE*j,SQUARE_SIZE,SQUARE_SIZE);}
			else { gc.setFill(Color.WHITE);
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);}
			
		}
		}
		

}

private void drawPlayer (GraphicsContext gc, double d , double e)	{
	// draw Player in anfangscoordinate
	 gc.drawImage(player1,SQUARE_SIZE*d, SQUARE_SIZE*e,SQUARE_SIZE,SQUARE_SIZE);
	
	
}
@Override
public void handle(KeyEvent event){
	// TODO Auto-generated method stub
	 KeyCode code = event.getCode();
     if (code == KeyCode.RIGHT || code == KeyCode.D) {
    
    	 moveRight();
     } else if (code == KeyCode.LEFT || code == KeyCode.A) {
    	 moveLeft();
     } else if (code == KeyCode.UP || code == KeyCode.W) {
    	 moveUp();
     } else if (code == KeyCode.DOWN || code == KeyCode.S) {
    	 moveDown();
	
     }else if (code == KeyCode.SPACE || code == KeyCode.ENTER) {
    	 placeBomb();
	
     }
}

private void moveRight() {
	if(imageX <ROWS-2 )
	imageX=imageX+Playerspeed;
	run();	
}

private void moveLeft() {
	if(imageX >1)
	imageX=imageX-Playerspeed;
	run();
}

private void moveUp() {
	if( imageY>1)
	imageY=imageY-Playerspeed;
	run();
}

private void moveDown() {
	if( imageY<ROWS-2)
	imageY=imageY+Playerspeed;
	run();
	}
private void placeBomb() {
	System.out.println(imageX+", "+imageY);
	Objekte =1;
	run();
}
	
}

// KeyListner müssen wir noch verbessern 
//um den Character  in alle Richtungen bewegen zu können
// Up left && Up right && down left && down right 




