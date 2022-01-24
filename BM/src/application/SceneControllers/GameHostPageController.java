package application.SceneControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Client;
import application.GamePanelOnline;
import application.Ressourcen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Label HostNameLabel;

    @FXML
    private Button GameHostBackButton;
    
    @FXML
    private Label RoomNameLabel;

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
    
    public void initialize() {
		// TODO Auto-generated method stub
    	FourPlayersRadioButton.setText(Ressourcen.language.get(13).get(LandingPageController.languageIndex));
        HostNameLabel.setText(Ressourcen.language.get(10).get(LandingPageController.languageIndex));
        GameHostBackButton.setText(Ressourcen.language.get(15).get(LandingPageController.languageIndex));
        RoomNameLabel.setText(Ressourcen.language.get(9).get(LandingPageController.languageIndex));
        RunRoomButton.setText(Ressourcen.language.get(14).get(LandingPageController.languageIndex));
        TowPlayersRadioButton.setText(Ressourcen.language.get(11).get(LandingPageController.languageIndex));
        threePlayersRadioButton.setText(Ressourcen.language.get(12).get(LandingPageController.languageIndex));
		try {
			Ressourcen.readFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter=0;
		LevelImageView.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
	}

    @FXML
    void GameHostBackButtonIsClicked(ActionEvent event) throws IOException {
    	// TODO Autogenerated
    	root = FXMLLoader.load(getClass().getResource("/application/Scenes/OnlinePage.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show(); 
    	
    }
	
	@FXML
	void NextLevelButtonIsClicked(ActionEvent event) {
	    counter= (counter +1)%5;
	    LevelImageView.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
	    GamePanelOnline.mapIndex=counter;
	    System.out.println("map index init ..."+GamePanelOnline.mapIndex);
	    
	}
	
	@FXML
	void PreviousLevelButtonIsClicked(ActionEvent event) {
 	   counter= (counter-1)%5;
 	   if(counter <0) 
 		   	counter=4;
 	   	LevelImageView.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
 	   	GamePanelOnline.mapIndex=counter;
	  
	}
	
	@FXML
	public void RoomNameTextField(ActionEvent event) throws IOException {}
	public boolean validate(){
		StringBuilder errors = new StringBuilder();
		
	    // Confirm mandatory fields are filled out
		if (RoomNameTextField.getText().trim().isEmpty()) {
	         errors.append("- Please enter your room name.\n");
	    }else if(RoomNameTextField.getText().trim().isEmpty()== false) {
				
					String valid_name = RoomNameTextField.getText();
					CharSequence seq = "-"; 
				    if(valid_name.contains(seq)) {	
				         errors.append("Your room name must not contain a minus - sign\n");
				    }
		}
		if (PlayerNicknameTextField.getText().trim().isEmpty()) {
	         errors.append("- Please enter your name.\n");
	    }else if(PlayerNicknameTextField.getText().trim().isEmpty()== false) {
				
					String valid_name = PlayerNicknameTextField.getText();
					CharSequence seq = "-"; 
				    if(valid_name.contains(seq)) {	
				         errors.append("Your name must not contain a minus - sign\n");
				    }
		}
	    

	    // If any missing information is found, show the error messages and return false
	    if (errors.length() > 0) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Warning");
	        alert.setHeaderText("Required Fields Empty");
	        alert.setContentText(errors.toString());
	        alert.showAndWait();
	            return false;
	     }
	
	    // No errors
	        return true;
	    
	}
	
	@FXML
	void RunRoomButtonIsClicked(ActionEvent event) throws IOException {
	   // check the empty fields
		if(validate()) {
			String msg = "Host-"+RoomNameTextField.getText()+"-"+PlayerNicknameTextField.getText();
			if(FourPlayersRadioButton.isSelected()) {
				msg = msg+"-4-"+counter;	
			}
			else if(threePlayersRadioButton.isSelected()){
				msg = msg+"-3-"+counter;	
			}
			else if(TowPlayersRadioButton.isSelected()){
				msg = msg+"-2-"+counter;	
			}
	    			
	   //TextRecieved.setText(player.accessServer(msg));
	    String resp;
	    resp=Client.accessServer(msg);
	    if(resp.equals("Created")){
	    	Client.playerpseudo=PlayerNicknameTextField.getText();
	    	Client.roomToJoin=RoomNameTextField.getText();	
	    	TextRecieved.setText(resp);
	    	root = FXMLLoader.load(getClass().getResource("/application/Scenes/WaitPage.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);		
		    stage.show();
	    }else if(resp.equals("Exist")) {
	    	Alert alrt = new Alert(Alert.AlertType.WARNING);
			alrt.setTitle("Warning");
			alrt.setHeaderText("Alert!");
			alrt.setContentText("This room name is already existing!");
			alrt.showAndWait();	    	
	    }else if(resp.equals("NoConnexion")) {
	    	System.out.println("************* Connexion lost *************");
			Alert alrt = new Alert(Alert.AlertType.WARNING);
			alrt.setTitle("Warning");
			alrt.setHeaderText("No conexion");
			alrt.setContentText("You are not connected to the game server!");
			alrt.showAndWait();
	    }
		}
			
	    		
	    
		}
	
	
	@FXML
	void playersButton(ActionEvent event) throws IOException {}
	    
	
    
}
