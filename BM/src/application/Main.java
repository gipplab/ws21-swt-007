package application;

import java.nio.file.Paths;

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
	static MediaPlayer mediaPlayer;
	static double volumen=0.50;;
	public void music() {
		String s = "src/application/music/BG.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setVolume(volumen);
	
		
	}
	static void  stopmusic() {
		mediaPlayer.stop();
	}
	
	static void playmusic() {
		
		mediaPlayer.play();
	}
	static void volumeUp() {
		if(volumen<=0.95)
		volumen+=0.005;
		else volumen=1;
		mediaPlayer.setVolume(volumen);
		 System.out.println(volumen);
	}
	static void volumeDown() {
		if(volumen>=0.05)
		volumen-=0.005;
		else volumen=0;
		mediaPlayer.setVolume(volumen);
		 System.out.println(volumen);
	}

}
