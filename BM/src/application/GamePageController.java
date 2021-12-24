package application;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GamePageController {
	@FXML
	private Label gameField;
	public void initialize() {
		String resp="bbb";		
		resp=Client.serverUpdates(resp);
					
}
}
