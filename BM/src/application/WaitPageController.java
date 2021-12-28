package application;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import application.GamePanelOnline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WaitPageController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Label infoRoom;
	@FXML
	private Button test;
    public void initialize() {
    	Timer fall = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run(){
						String msg = "Waiting-"+Client.roomToJoin;	
						String resp;
						resp=Client.accessServer(msg);
						System.out.println("ttttt"+resp);
						String[] message = resp.split("-");
						 
						
						if(message[0].equals("Complited")) {
							for(int i=1 ;i<message.length;i++) 
							{
								if(Client.playerpseudo.equals(message[i])) {
									GamePanelOnline.mainPlayerIndex=i-1;
								}

								Client.addPlayer(message[i]);
								for(String name : Client.players) {
									System.out.println("/"+name);
								}
							}
							fall.cancel();
							fall.purge();
							
							
							try {
								Main.online=true;
								GamePanelOnline game= new GamePanelOnline();
								game.init();
								stage = (Stage) infoRoom.getScene().getWindow();
								stage.setTitle("Bomberman");
								stage.setScene(game.getScene());
								stage.setResizable(false);
								stage.show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}else {
							infoRoom.setText("wait "+resp);
						}
												
					}
				});
			}
		};
		fall.schedule(task, 0, 300); 
		
    	
		
}
    public void testAction(ActionEvent event) throws IOException {
		// TODO Autogenerated
    	
    }
}
