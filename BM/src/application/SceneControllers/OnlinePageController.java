package application.SceneControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import application.Ressourcen;
import javafx.event.ActionEvent;

public class OnlinePageController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button createGameButten;
	@FXML
	private Button JoinGameButton;
	@FXML
	private Button OnlineBackButton;

	// Event Listener on Button[#createGameButten].onAction
	@FXML
	public void initialize() {
		createGameButten.setText(Ressourcen.language.get(21).get(LandingPageController.languageIndex));
		JoinGameButton.setText(Ressourcen.language.get(22).get(LandingPageController.languageIndex));
		OnlineBackButton.setText(Ressourcen.language.get(23).get(LandingPageController.languageIndex));		
	}
	public void CreateGameButtenIsClicked(ActionEvent event) throws IOException {
		// TODO Autogenerated
		root = FXMLLoader.load(getClass().getResource("/application/Scenes/GameHostPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on Button[#JoinGameButton].onAction
	@FXML
	public void JoinGameButtonIsClicked(ActionEvent event) throws IOException {
		// TODO Autogenerated
		root = FXMLLoader.load(getClass().getResource("/application/Scenes/JoinOnlinePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
		
	}
	// Event Listener on Button[#OnlineBackButton].onAction
	@FXML
	public void OnlineBackButtonIsClicked(ActionEvent event) throws IOException {
		// TODO Autogenerated
		root = FXMLLoader.load(getClass().getResource("/application/Scenes/LandingPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

}
