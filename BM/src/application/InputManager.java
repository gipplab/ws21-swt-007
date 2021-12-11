package application;



import java.util.List;

import application.Objects.Bomberman;
import javafx.scene.input.KeyCode;

/**
*
* @author Ashish
*/
public class InputManager {

   public static void handlePlayerMovements(){
       List keyboardInputs = KeysHandler.getInputList();
       Bomberman player = GamePanel.getPlayer();
      
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
       {
    	  // player.unmove();       }
       
       //Drop bomb
       if(keyboardInputs.contains(KeyCode.SPACE)){
           //if(player.hasMoreBombs()) {
             //  Sandbox.addEntityToGame(new BlackBomb(player.getPositionX()+ GlobalConstants.PLAYER_WIDTH/2, player.getPositionY()+GlobalConstants.PLAYER_HEIGHT/2));
            //   player.decrementBombCount();
    	   //placeBomb();
           }
       }
   }
}
