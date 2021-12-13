package application;



import java.util.List;

import application.Objects.Bomb;
import application.Objects.Bomberman;
import javafx.scene.input.KeyCode;

/**
*
* @author Ashish
*/
public class InputManager {

   public static void handlePlayerMovements(Bomberman player){
       List keyboardInputs = KeysHandler.getInputList();
      
       if(keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)){
         player.moveUp();
         
       }
       if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
    	  player.moveDown();
    	  
       }
       if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
    	  player.moveLeft();
    
       }
       if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
    	   player.moveRight(); 
    	    
       }
       if( !keyboardInputs.contains(KeyCode.LEFT) &&
           !keyboardInputs.contains(KeyCode.RIGHT) &&
           !keyboardInputs.contains(KeyCode.UP) &&
           !keyboardInputs.contains(KeyCode.DOWN) &&
           !keyboardInputs.contains(KeyCode.W) &&
           !keyboardInputs.contains(KeyCode.A) &&
           !keyboardInputs.contains(KeyCode.S) &&
           !keyboardInputs.contains(KeyCode.D)
         )
       { // player.unmove(); 
    	   }
       
    	       
       
       //Drop bomb
       if(KeysHandler.SpaceRessed){
           if(player.getBombanzahl()>0) {
        	   System.out.println("Bombe");
        	   Bomb b= new Bomb(player.getX(),player.getY());
        	   player.BombanzahlDown();
        	   GamePanel.Objekte.add(b);
        	   
        	   KeysHandler.setSpaceRessed();
        	   
           }
             //  Sandbox.addEntityToGame(new BlackBomb(player.getPositionX()+ GlobalConstants.PLAYER_WIDTH/2, player.getPositionY()+GlobalConstants.PLAYER_HEIGHT/2));
            //   player.decrementBombCount();
    	   //placeBomb();
           }
       }
   }

