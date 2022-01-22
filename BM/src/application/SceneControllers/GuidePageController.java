package application.SceneControllers;

import java.io.IOException;

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
    private Text LEFT1;

    @FXML
    private Text LEFT2;

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
    private Text UP1;

    @FXML
    private Text UP2;

    @FXML
    private Button backButton;

    @FXML
    void backButtonOnClick(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/application/Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
