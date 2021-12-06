package application;


import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GamePanel  {
	private static final int WIDTH= 600 ;
    private static final int HEIGHT=WIDTH ;
    private static final int ROWS= 22 ;
    private static final int COLUMNS=22;
    // Rectangle genau auf die Size anpassen
    private static final double SQUARE_SIZE= WIDTH*1.0/ROWS ;
    
	 private GraphicsContext gc;
	 private boolean gameOver;
	 private Scene scene;
	 Canvas canvas;
	 Group root;

	 public GamePanel()  {
		
		root  = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		scene = new Scene(root,WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
		run();
		System.out.println(SQUARE_SIZE);
		 
		 
	}
	public void init() {
	
	};


public Scene getScene() {
	return scene;
}

public void run() {
	
	drawBackground(gc);
	drawPoint(gc);
		
	}
	
private void drawBackground( GraphicsContext gc) {
	
	for(int i=0 ; i<ROWS; i++) {
		for(int j=0;j<COLUMNS;j++) {
			if((i+j)%2==0)
				gc.setFill(Color.web("#873e23"));
			else gc.setFill(Color.web("A2d149"));
			
			gc.fillRect(i*SQUARE_SIZE,j*SQUARE_SIZE , SQUARE_SIZE, SQUARE_SIZE);
		}
		}
		

}

private void drawPoint (GraphicsContext gc)	{
	Image iv = new Image(getClass().getResource("img/player.jpg").toExternalForm());
	 gc.drawImage(iv, 15*SQUARE_SIZE, SQUARE_SIZE*2,SQUARE_SIZE,SQUARE_SIZE);
	
}


}

// KeyListner müssen wir noch hinzufügen um den Character bewegen zu können




