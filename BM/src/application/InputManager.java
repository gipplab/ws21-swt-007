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
 		   	Client.updateString =System.currentTimeMillis()+"-UP-"+player.getEntityX()+"-"+player.getEntityY();
 		   	String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
 		   	String resp= "";
 			resp=Client.accessServer(messageout);
 			System.out.println(resp);
 			
 	   		}
       }
     //nach Unten bewegen.
       if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
	  if(!player.getDeath()) 
    	  player.moveDown();
    	   if(Main.online) {
	 		   Client.updateString =System.currentTimeMillis()+"-DOWN-"+player.getEntityX()+"-"+player.getEntityY();
	 		   String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
	 		   	String resp= "";
	 		   System.out.println(messageout);
	 			resp=Client.accessServer(messageout);
	 			System.out.println(resp);
	 		   }
    	
       }
       //nach Links bewegen.
       if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
	  if(!player.getDeath())
    	  player.moveLeft();
   	   if(Main.online) {
		   	Client.updateString =System.currentTimeMillis()+"-LEFT-"+player.getEntityX()+"-"+player.getEntityY();
		   	String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
 		   	String resp= "";
 			resp=Client.accessServer(messageout);
 			System.out.println(resp);
		   	   }
       }
     //nach Rechts bewegen.
       if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
	   if(!player.getDeath())
    	   player.moveRight();  
    	   if(Main.online) {
	 		   Client.updateString =System.currentTimeMillis()+"-RIGHT-"+player.getEntityX()+"-"+player.getEntityY(); 
	 		   String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
	 		   	
	 		   String resp= "";
	 			resp=Client.accessServer(messageout);
	 			System.out.println(resp);
	 	
	 	   }
       }

       //Drop bomb, wenn die Taste Space gedrckt werden.
       
       if(KeysHandler.SPACEPRESSED){
           if((player.getBombanzahl()>0)&&!player.getDeath()) {
        	   System.out.println("Bombe");
        
        	   Bomb b= new Bomb( player.getEntityX() , player.getEntityY() ,player.getExplosion(), Ressourcen.IMAGES.BOMBE.getImage(), player);
        	   b.BombCollision(player.getEntityX(),player.getEntityY());
        	   player.BombanzahlDown();
        	   if(Main.online) {
        		   Client.updateString =System.currentTimeMillis()+"-BOMB-"+b.getX()+"-"+b.getY();
            	   String messageout= "Play-"+Client.roomToJoin+"-"+Client.playerpseudo+"-SetUpdates-"+Client.updateString;
        		   	String resp= "";
        			resp=Client.accessServer(messageout);
        			System.out.println(resp);
        	   }        	  
        	   GameObjects.spawn(b);
        	   
        	   }
        	   KeysHandler.setSPACEPRESSED();
        	   
           					}
          			}
       
       
       		}
   


