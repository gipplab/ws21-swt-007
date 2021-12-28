package application.SceneControllers;

import java.io.IOException;

import application.Client;
import application.Ressourcen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameHostPageController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	int counter;
    @FXML
    private RadioButton FourPlayersRadioButton;

    @FXML
    private Button GameHostBackButton;

    @FXML
    private ImageView LevelImageView;

    @FXML
    private Label MapNumberLabel;

    @FXML
    private Button NextLevelButton;

    @FXML
    private ToggleGroup NumberOfPlayers;

    @FXML
    private TextField PlayerNicknameTextField;

    @FXML
    private Button PreviousLevelButton;

    @FXML
    private TextField RoomNameTextField;

    @FXML
    private Button RunRoomButton;

    @FXML
    private RadioButton TowPlayersRadioButton;

    @FXML
    private RadioButton threePlayersRadioButton;
    
    @FXML
    private Label TextRecieved;

    @FXML
    void GameHostBackButtonIsClicked(ActionEvent event) throws IOException {
    	// TODO Autogenerated
    	root = FXMLLoader.load(getClass().getResource("../Scenes/OnlinePage.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void NextLevelButtonIsClicked(ActionEvent event) {
    	counter= (counter +1)%4;
    	LevelImageView.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
      

    }

    @FXML
    void PreviousLevelButtonIsClicked(ActionEvent event) {
    	   counter= (counter-1)%4;
    	   if(counter <0) 
    		 counter=3;
    	   LevelImageView.setImage(Ressourcen.IMAGES.MAP.getMap(counter));

    }

    @FXML
    public void RoomNameTextField(ActionEvent event) throws IOException {
    	
    		}

    @FXML
    void RunRoomButtonIsClicked(ActionEvent event) throws IOException {
    	// TODO Autogenerated
    	String msg = "Host-"+RoomNameTextField.getText()+"-"+PlayerNicknameTextField.getText();
    	if(FourPlayersRadioButton.isSelected()) {
    		msg = msg+"-4";	
    	}else if(threePlayersRadioButton.isSelected()){
    		msg = msg+"-3";	
    	}else if(TowPlayersRadioButton.isSelected()){
    		msg = msg+"-2";	
    	}
    			
    			//TextRecieved.setText(player.accessServer(msg));
    			String resp;
    			resp=Client.accessServer(msg);
    			if(resp.equals("Created"))
    			{
    				Client.playerpseudo=PlayerNicknameTextField.getText();
    				Client.roomToJoin=RoomNameTextField.getText();
    				TextRecieved.setText(resp);
    			}
    			root = FXMLLoader.load(getClass().getResource("../Scenes/WaitPage.fxml"));
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			scene = new Scene(root);
    			stage.setScene(scene);		
    			stage.show();

    }

    @FXML
    void playersButton(ActionEvent event) throws IOException {
    	

    }

}
