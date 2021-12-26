package application;

import java.util.ArrayList;

public class PlayerInfos {
		
	String name;
	String gameUpdates="";
	public PlayerInfos(String playerName) {
		name = playerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String playerName) {
		name = playerName;
	}
	public String GetUpdates() {
		// TODO Auto-generated method stub	
		return gameUpdates;
	}
	public void SetUpdates(String updates) {
		// TODO Auto-generated method stub
		gameUpdates="";
	}
	public void AddUpdatesToPlayers(String updates) {
		// TODO Auto-generated method stub
		gameUpdates=gameUpdates+"-"+updates;
	}
	
	
	
	
	

	
}
