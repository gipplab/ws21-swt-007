package application.SceneControllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SinglePlayPanelController {
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button BackButton;

    @FXML
    private TextField PlayerNicknameTextField;

    @FXML
    private Button RunRoomButton;

    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("../Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void RunRoomButtonIsClicked(ActionEvent event) {

    }

    @FXML
    void nextMap(MouseEvent event) {

    }

    @FXML
    void vorMap(MouseEvent event) {

    }

}
