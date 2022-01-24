package application.SceneControllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.GamePanel;
import application.Ressourcen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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



public class SinglePlayPanelController{
	private Stage stage;
	private Scene scene;
	private Parent root;
    public static int playerFarbe=0;
    public static String name = null;
	@FXML
	private Button SingelPlayerBackButton;
	@FXML
	private Label SinglePlayColorLabel;
    @FXML
	private Label SinglePlayNameLabel;
    @FXML
    private Button nextMapbutton;
    @FXML
    private RadioButton BluePlayersRadioButton;
    @FXML
    private RadioButton BlackPlayersRadioButton;
    @FXML
    private ToggleGroup FarbeGroup;


    @FXML
    private RadioButton RedPlayersRadioButton;

    @FXML
    private Button VorMapbutton;

    @FXML
    private RadioButton WhitePlayersRadioButton;

    @FXML
    private TextField PlayerNicknameTextField;

    @FXML
    private Button RunRoomButton;
    @FXML
    ImageView MapImage;
    int counter;
    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/application/Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
    public void initialize() {
    	SingelPlayerBackButton.setText(Ressourcen.language.get(8).get(LandingPageController.languageIndex));
    	SinglePlayColorLabel.setText(Ressourcen.language.get(6).get(LandingPageController.languageIndex));
    	SinglePlayNameLabel.setText(Ressourcen.language.get(5).get(LandingPageController.languageIndex));  
        RunRoomButton.setText(Ressourcen.language.get(7).get(LandingPageController.languageIndex));	
        try {
			Ressourcen.readFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter=0;
		MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
	}

    @FXML
    void RunRoomButtonIsClicked(ActionEvent event) throws IOException {
    		//	player.SetAddress("192.168.1.107");
    	setPlayerFarbe();
    	if(validate()) 
    	{
    			name = PlayerNicknameTextField.getText();
    			GamePanel game= new GamePanel();
    			game.init();
    			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    		    stage.setTitle("Bomberman");
    		    stage.setScene(game.getScene());
    		    stage.setResizable(false);
    		    stage.show();
    			
    	}
   	}
public boolean validate() {
		
		StringBuilder errors = new StringBuilder();
		
	
	    // Confirm mandatory fields are filled out
	    if (PlayerNicknameTextField.getText().trim().isEmpty()) {
	         errors.append("- Please enter your name.\n");
	    }
	 
	    if (playerFarbe==0 && !WhitePlayersRadioButton.isSelected()) {
	 
	         errors.append("- Please choose your color.\n");
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


  	void setPlayerFarbe() {
  		
  		if( RedPlayersRadioButton.isSelected())
  			playerFarbe=2;
  		else if(  WhitePlayersRadioButton.isSelected())
  			playerFarbe=0;
  			else if(  BluePlayersRadioButton.isSelected())
  				playerFarbe=3;
  				else if(  BlackPlayersRadioButton.isSelected())
  					playerFarbe=1;
  		
  	}

    @FXML
    void nextMap(ActionEvent event) {
    counter= (counter +1)%5;
    MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
    GamePanel.mapIndex=counter;
  
    }

    @FXML
    void vorMap(ActionEvent event) {
    	
    	   counter= (counter-1)%5;
    	   if(counter <0) 
    		 counter=4;
    	   MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
    	    GamePanel.mapIndex=counter;
    }

	

    

}
