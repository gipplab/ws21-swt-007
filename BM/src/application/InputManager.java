package application;



import java.util.List;

import application.Objects.Bomb;
import application.Objects.Bomberman;
import application.Objects.GameObjects;
import javafx.scene.input.KeyCode;


public class InputManager {

   public static void handlePlayerMovements(Bomberman player) throws InterruptedException{
	  
       List<?> keyboardInputs = KeysHandler.getInputList();
       //nach Oben bewegen.
       if(keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)){
	 if(!player.getDeath())
         player.moveUp();
     	if(Main.online) {
 		   	Client.updateString =Client.updateString+"UP/"+player.getEntityX()+"/"+player.getEntityY()+"/";
 	
 	   		}
       }
     //nach Unten bewegen.
       if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
	  if(!player.getDeath())
    	  player.moveDown();
    	   if(Main.online) {
	 		   Client.updateString =Client.updateString+"DOWN/"+player.getEntityX()+"/"+player.getEntityY()+"/";
	 		   // Client.accessServer("Play-DOWN");
	 		   }
    	
       }
       //nach Links bewegen.
       if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
	  if(!player.getDeath())
    	  player.moveLeft();
   	   if(Main.online) {
		   	Client.updateString =Client.updateString+"LEFT/"+player.getEntityX()+"/"+player.getEntityY()+"/";
		   	   }
       }
     //nach Rechts bewegen.
       if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
	   if(!player.getDeath())
    	   player.moveRight();  
    	   if(Main.online) {
	 		   Client.updateString =Client.updateString+"RIGHT/"+player.getEntityX()+"/"+player.getEntityY()+"/";
	 	
	 	   }
       }

       //Drop bomb, wenn die Taste Space gedr�ckt werden.
       
       if(KeysHandler.SPACEPRESSED){
           if((player.getBombanzahl()>0)&&!player.getDeath()) {
        	   System.out.println("Bombe");
        
        	   player.BombanzahlDown();
        	   Bomb b= new Bomb( player.getEntityX() , player.getEntityY() ,player.getExplosion(), Ressourcen.IMAGES.BOMBE.getImage(), player);
        	   b.BombCollision(player.getEntityX(),player.getEntityY());
        	   if(Main.online)
        	   Client.updateString =Client.updateString+"BOMB/"+b.getX()+"/"+b.getY()+"/";
        	   GameObjects.spawn(b);
        	   
        	   }
        	   KeysHandler.setSPACEPRESSED();
        	   
           					}
          			}
       
       
       		}
   


