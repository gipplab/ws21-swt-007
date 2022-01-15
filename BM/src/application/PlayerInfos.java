package application;



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
		if(gameUpdates.equals("")) {
			gameUpdates=updates;
		}else {
			gameUpdates=gameUpdates+"-"+updates;
		}
		
	}
	
	
	
	
	

	
}
