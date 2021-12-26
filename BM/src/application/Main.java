package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static Client player= new Client();
	public static Boolean  online= false;
	@Override
	public void start(Stage primaryStage) {
	
		
		try {

		
			
			player.SetAddress("192.168.2.112");

			Ressourcen.readFiles();
			//GamePanel game= new GamePanel();
			//game.init();
		   Parent root = FXMLLoader.load(getClass().getResource("Scenes/LandingPage.fxml"));
		    Scene scene = new Scene(root);
			primaryStage.setTitle("Bomberman");
			primaryStage.setScene(scene);
	
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
