package application;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	//public static Client player= new Client();
	
	@Override
	public void start(Stage primaryStage) {
	
		
		try {
		//	player.SetAddress("192.168.1.107");
		
<<<<<<< Updated upstream
			GamePanel game= new GamePanel();
			game.init();
//			Parent root = FXMLLoader.load(getClass().getResource("Scenes/LandingPage.fxml"));
//			Scene scene = new Scene(root);
=======
			player.SetAddress("localhost");
			
			Ressourcen.readFiles();
			//GamePanel game= new GamePanel();
			//game.init();
		   Parent root = FXMLLoader.load(getClass().getResource("Scenes/LandingPage.fxml"));
		    Scene scene = new Scene(root);
>>>>>>> Stashed changes
			primaryStage.setTitle("Bomberman");
			primaryStage.setScene(game.getScene());
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
