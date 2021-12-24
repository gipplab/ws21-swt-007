package application;

import java.util.ArrayList;

public class PlayerInfos {
		
	String name;
	ArrayList<String> gameUpdates;
	public PlayerInfos(String playerName) {
		name = playerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String playerName) {
		name = playerName;
	}
	/*public String GetUpdates() {
		// TODO Auto-generated method stub	
		String output="";
		for (String s : gameUpdates)
		{
			output += s + "-";
		}
		gameUpdates.clear();
		return output;
	}*/
	public void SetUpdates(String updates) {
		// TODO Auto-generated method stub
		gameUpdates.add(updates);
	}
	
	
	
	
	

	
}
