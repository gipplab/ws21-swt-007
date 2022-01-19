package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class Main extends Application {
	public static Client player= new Client();
	public static Boolean  online= false;
	
	@Override
	public void start(Stage primaryStage) {
	
		
		try {
			music();
			player.SetAddress("localhost"); 
			Ressourcen.readFiles();
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
	MediaPlayer mediaPlayer;
	public void music() {
		Media h = new Media(getClass().getResource("music/BG.mp3").toExternalForm());
		mediaPlayer = new MediaPlayer(h);
		playmusic();
		
	}
	void stopmusic() {
		mediaPlayer.stop();
	}
	
	void playmusic() {
		mediaPlayer.play();
	}

}
