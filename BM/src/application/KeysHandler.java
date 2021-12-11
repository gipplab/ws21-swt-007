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
	
	    public static char lastKeyPress;
	    public static char lastKeyReleased;
	    public static ArrayList<KeyCode> inputList = new ArrayList<KeyCode>();

	    public static void attachEventHandlers(Scene scene){
	        keyReleaseHanlder released = new keyReleaseHanlder();
	        keyPressedHandler pressed = new keyPressedHandler();
	        scene.setOnKeyReleased(released);
	        scene.setOnKeyPressed(pressed);
	    }

	    public boolean isKeyDown(KeyCode k) {
	    	if( inputList.contains(k)){
	    		return true;
	        }else{
	            return false;
	        }
	    }
	    
	    public static List getInputList(){
	        return inputList;
	    }
	}

	class keyReleaseHanlder implements javafx.event.EventHandler<KeyEvent>{
	    public keyReleaseHanlder() {
	    }
	    @Override
	    public void handle(KeyEvent evt) {
	        //System.out.println("The key released is : "+evt.getText()+" with keycode "+evt.getCode().getName());

	        KeyCode code = evt.getCode();

	        if ( KeysHandler.inputList.contains(code) )
	        	KeysHandler.inputList.remove( code );
	    }
	}
	class keyPressedHandler implements javafx.event.EventHandler<KeyEvent>{
	    @Override
	    public void handle(KeyEvent evt) {
	        //System.out.println("The key pressed is : "+evt.getText()+" with keycode "+evt.getCode().getName());
	        KeyCode code = evt.getCode();
	        
	        // only add once... prevent duplicates
	        if ( !KeysHandler.inputList.contains(code) )
	        	KeysHandler.inputList.add( code );
	    }
	}

