package application.SceneControllers;

import java.io.IOException;

import application.Ressourcen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuidePageController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	   @FXML
	    private Text DOMPBOMB2;

	    @FXML
	    private Text DOWN;

	    @FXML
	    private Text DOWN2;

	    @FXML
	    private Text DROPBOMB1;

	    @FXML
	    private Text HEALTH;

	    @FXML
	    private Text HEALTH1;

	    @FXML
	    private Text LEFT1;

	    @FXML
	    private Text LEFT2;

	    @FXML
	    private Text MUTE;

	    @FXML
	    private Text PAUS;

	    @FXML
	    private Text RIGHT1;

	    @FXML
	    private Text RIGHT2;

	    @FXML
	    private Text T1;

	    @FXML
	    private Text T2;

	    @FXML
	    private Text TEXT1;

	    @FXML
	    private Text TEXT2;

	    @FXML
	    private Text TEXT21;

	    @FXML
	    private Text TEXTBOMB;

	    @FXML
	    private Text TEXTFLAME;

	    @FXML
	    private Text UP1;

	    @FXML
	    private Text UP2;

	    @FXML
	    private Text VOLUMEDOWN;

	    @FXML
	    private Text VOLUMEUP;

	    @FXML
	    private Button backButton;

	    @FXML
    public void initialize() {
		// TODO Auto-generated method stub
    	T1.setText(Ressourcen.language.get(25).get(LandingPageController.languageIndex));
    	T2.setText(Ressourcen.language.get(26).get(LandingPageController.languageIndex));
    	TEXT1.setText(Ressourcen.language.get(27).get(LandingPageController.languageIndex));
    	TEXT2.setText(Ressourcen.language.get(28).get(LandingPageController.languageIndex));
    	TEXTBOMB.setText(Ressourcen.language.get(29).get(LandingPageController.languageIndex));
    	TEXTFLAME.setText(Ressourcen.language.get(30).get(LandingPageController.languageIndex));
    	HEALTH.setText(Ressourcen.language.get(31).get(LandingPageController.languageIndex));
    	HEALTH1.setText(Ressourcen.language.get(32).get(LandingPageController.languageIndex));
    	backButton.setText(Ressourcen.language.get(33).get(LandingPageController.languageIndex));   
    
    }
    @FXML
    void backButtonOnClick(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/application/Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
