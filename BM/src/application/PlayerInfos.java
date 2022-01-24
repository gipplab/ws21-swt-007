package application;


public class PlayerInfos {
		
	String name;// playerpseudo
	String position;// location of player
	String bomb="NOBOMB";
	public boolean isDaed=false;
	public String action="0"; 
	
	// return the loction of player
	public String getPosition() {
		return position;
		
	}
	
	//set the location of player
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getBomb() {
		return bomb;
	}
	public void setBomb(String bomb) {
		this.bomb = bomb;
	}
	public PlayerInfos(String playerName) {
		name = playerName; 
	}
	public String getName() {
		return name;
	}
	public void setName(String playerName) {
		name = playerName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	
	
	
	

	
}
