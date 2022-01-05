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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SinglePlayPanelController  implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
    public static int playerFarbe;
	@FXML
    
    private Button BackButton;

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
    	root = FXMLLoader.load(getClass().getResource("../Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void RunRoomButtonIsClicked(ActionEvent event) throws IOException {
    		//	player.SetAddress("192.168.1.107");
    		    
    	setPlayerFarbe();
    	
    			GamePanel game= new GamePanel();
    			game.init();
    			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    		    stage.setTitle("Bomberman");
    		    stage.setScene(game.getScene());
    		    stage.setResizable(false);
    		    stage.show();
    			
    }

  	void setPlayerFarbe() {
  		
  		if(    RedPlayersRadioButton.isSelected())
  			playerFarbe=2;
  		else if(  WhitePlayersRadioButton.isSelected())
  			playerFarbe=0;
  			else if(  BluePlayersRadioButton.isSelected())
  				playerFarbe=3;
  				else if(  BlackPlayersRadioButton.isSelected())
  					playerFarbe=1;
  			System.out.println(playerFarbe+"DFFGSFASFASF");

  	}

    @FXML
    void nextMap(ActionEvent event) {
    counter= (counter +1)%4;
    MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
    GamePanel.mapIndex=counter;
  
    }

    @FXML
    void vorMap(ActionEvent event) {
    	
    	   counter= (counter-1)%4;
    	   if(counter <0) 
    		 counter=3;
    	   MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
    	    GamePanel.mapIndex=counter;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		try {
			Ressourcen.readFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter=0;
		MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
	}
    

}
