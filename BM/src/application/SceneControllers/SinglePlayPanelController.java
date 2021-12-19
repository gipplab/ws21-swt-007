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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SinglePlayPanelController  implements Initializable{
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
    counter= (counter +1)%4;
    MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
  
    }

    @FXML
    void vorMap(ActionEvent event) {
    	
    	   counter= (counter-1)%4;
    	   if(counter <0) 
    		 counter=3;
    	   MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		counter=0;
		MapImage.setImage(Ressourcen.IMAGES.MAP.getMap(counter));
	}
    

}
