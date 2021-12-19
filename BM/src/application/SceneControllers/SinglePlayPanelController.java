package application.SceneControllers;


import java.io.IOException;

import application.GamePanel;
import application.Ressourcen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Application;

public class SinglePlayPanelController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	int numb=0;
    @FXML
    private Button BackButton;

    @FXML
    private TextField PlayerNicknameTextField;

    @FXML
    private Button RunRoomButton;
    @FXML
    ImageView MapImage;
   
    Image image = new Image(Ressourcen.class.getResource("img/Frame 3.png").toString());
    Image image1 = new Image(Ressourcen.class.getResource("img/landscape-mountains-minimalist-o7.jpg").toString());
    Image image2 = new Image(Ressourcen.class.getResource("img/BG.png").toString());
    Image image3 = new Image(Ressourcen.class.getResource("img/BACKG.jpg").toString());

    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("../Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void RunRoomButtonIsClicked(ActionEvent event) throws IOException {
    		//	player.SetAddress("192.168.1.107");
    		    
    			GamePanel game= new GamePanel();
    			game.init();
    			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    		    stage.setTitle("Bomberman");
    		    stage.setScene(game.getScene());
    		    stage.setResizable(false);
    		    stage.show();
    			
    	}

    

    @FXML
    void nextMap(ActionEvent event) {
    	if(numb==0) {
    	
    	MapImage.setImage(image);
    	numb=1;
    	} 
    	else if(numb==1) {
        	
        	MapImage.setImage(image1);
        	numb=2;
        	} 
    	else if(numb==2) {
        	
        	MapImage.setImage(image2);
        	numb=3;
        	}
    	else if(numb==3) {
        	
        	MapImage.setImage(image3);
        	numb=0;
        	}
    }

    @FXML
    void vorMap(ActionEvent event) {
    	if(numb==0) {
        	
        	MapImage.setImage(image);
        	numb=1;
        	} 
        	else if(numb==1) {
            	
            	MapImage.setImage(image3);
            	numb=2;
            	} 
        	else if(numb==2) {
            	
            	MapImage.setImage(image2);
            	numb=3;
            	}
        	else if(numb==3) {
            	
            	MapImage.setImage(image1);
            	numb=0;
            	}

    }

}
