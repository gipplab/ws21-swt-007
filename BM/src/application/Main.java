package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import uet.oop.bomberman.GamePanel;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	
		
		try {
			GamePanel game= new GamePanel();
			
//			Parent root = FXMLLoader.load(getClass().getResource("Scenes/LandingPage.fxml"));
//			Scene scene = new Scene(root);
			primaryStage.setTitle("Bomberman");
			primaryStage.setScene(game.getScene());
			//primaryStage.setMaxWidth(820);
			primaryStage.setResizable(true);
			primaryStage.show();
			
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
