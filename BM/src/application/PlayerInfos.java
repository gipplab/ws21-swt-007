package application;

import java.util.ArrayList;

public class PlayerInfos {
		
	String name;
	String position;
	String bomb="NOBOMB";
	public String action="ACTIF"; 
	public String getPosition() {
		return position;
	}
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
