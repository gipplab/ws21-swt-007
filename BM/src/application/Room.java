package application;

import java.util.ArrayList;

public class Room {
	private String hostName;
	private int howManyPlayers;
    ArrayList<PlayerInfos> players = new ArrayList<>();
	public Room(String hostName, int howManyPlayers) {
		this.hostName = hostName;
		this.howManyPlayers = howManyPlayers;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPlayers() {
		String playerList="";
		for(PlayerInfos player : players) 
		{
			playerList=playerList+"-"+player.getName();
		}
		return playerList;
	}
	public int getHowManyPlayers() {
		return howManyPlayers;
	}
	public void increasePlayernumber () {
		this.howManyPlayers++;
	}
	public void AddPlayerToRoom(PlayerInfos player) {
		this.players.add(player);
	}
	public int GetPlayersNumber() {
		return this.players.size();
	}
	

}
