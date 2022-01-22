package application.SceneControllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GuideController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button BACKGUIDE;

    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	// TODO Autogenerated
    			root = FXMLLoader.load(getClass().getResource("/application/Scenes/LandingPage.fxml"));
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			scene = new Scene(root);
    			stage.setScene(scene);
    			stage.show();

    }

}