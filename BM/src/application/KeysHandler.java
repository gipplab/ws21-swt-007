package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeysHandler {

	public KeysHandler() {
		// TODO Auto-generated constructor stub
	}
	
	    public static ArrayList<KeyCode> inputList = new ArrayList<KeyCode>();
	    public static boolean SPACEPRESSED=false;
	    public static boolean playMusik=true;
	    public static void attachEventHandlers(Scene scene){
	        keyReleaseHanlder released = new keyReleaseHanlder();
	        keyPressedHandler pressed = new keyPressedHandler();
	        scene.setOnKeyReleased(released);
	        scene.setOnKeyPressed(pressed);
	    }
	    
	    public static void setplayMusik() {
	    	if(playMusik) {
	    		Main.playmusic();
	    		playMusik=true;}
	    	else {
	    		Main.stopmusic();
	    		playMusik=false;
	    		}
	    	
	    }
	 
	    public static void setSPACEPRESSED() {
	    	if(SPACEPRESSED)
	    	SPACEPRESSED=false;
	    	else SPACEPRESSED=true;
	    }
	    public static List<?> getInputList(){
	        return inputList;
	    }
	}

	class keyReleaseHanlder implements javafx.event.EventHandler<KeyEvent>{
	    public keyReleaseHanlder() {
	    }
	    @Override
	    public void handle(KeyEvent evt) {
	      

	        KeyCode code = evt.getCode();

	        if ( KeysHandler.inputList.contains(code) )
	        	KeysHandler.inputList.remove( code );
	    }
	}
	class keyPressedHandler implements javafx.event.EventHandler<KeyEvent>{
	    @Override
	    public void handle(KeyEvent evt) {
	     
	        KeyCode code = evt.getCode();
	       
	        if ( !KeysHandler.inputList.contains(code) )
	        	KeysHandler.inputList.add( code );
	        if(code==KeyCode.SPACE) {
	        	KeysHandler.SPACEPRESSED=true;
	        }
	        if(code==KeyCode.NUMBER_SIGN) {

	        	if(KeysHandler.playMusik)
	        		KeysHandler.playMusik=false;
	        	else KeysHandler.playMusik=true;
	        	KeysHandler.setplayMusik();
	        }
	        
	    }
	}


