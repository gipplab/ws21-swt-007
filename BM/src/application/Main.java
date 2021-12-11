package application;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	
		
		try {
			GamePanel game= new GamePanel();
			game.init();
//			Parent root = FXMLLoader.load(getClass().getResource("Scenes/LandingPage.fxml"));
//			Scene scene = new Scene(root);
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
