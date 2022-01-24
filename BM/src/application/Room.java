package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Room {
	private String hostName;
	private int howManyPlayers;//Anzahl der Spieler
	int mapIndex;//ausgewaehlte mape
	ArrayList<String[]> map = new ArrayList<>();
	public static  ArrayList<ArrayList<String>> mapLayout; 
    ArrayList<PlayerInfos> players = new ArrayList<>(); // liste der Spieler im Raum
    
	public Room(String hostName, int howManyPlayers, int mapIndex) {
		this.hostName = hostName;
		this.howManyPlayers = howManyPlayers; 
		this.mapIndex= mapIndex;
		loadMapFile(mapIndex);//ausgewaehlte Map importieren.
		int row=0;
		int column=0;
		for(ArrayList<String> x : mapLayout) {
			for(String y : x) {
				if(y.equals("M")||y.equals("P")||y.equals("E")||y.equals("L")||y.equals("Q")) {
					String[] softWall= new String[2];
					softWall[0]= String.valueOf(row);
					softWall[1]= String.valueOf(column);
					map.add(softWall);
				}
				row++;
			}
			row=0;
			column++;
		}
		
		/*for (int y = 0; y < GamePanelOnline.ROWS; y++) {
            for (int x = 0; x < GamePanelOnline.COLUMNS; x++) {		
            	int[] wallPos= new int[2];
            	wallPos[0]=1;
            	wallPos[1]=1;
            }
		}*/
		
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	//liste der Spieler im Raum zuruekgeben
	public String getPlayers() {
		String playerList="";
		for(PlayerInfos player : players) 
		{
			playerList=playerList+"-"+player.getName();
		}
		return playerList;
	}
	
	// gibt Anzahl der Spieler im Raum zuruek
	public int getHowManyPlayers() { 
		return howManyPlayers;
	}
	
	// Anzahl der Spieler verringern
	public void decreasePlayernumber () {
		this.howManyPlayers--;
	}
	
	// Spieler hinzufuegen
	public void AddPlayerToRoom(PlayerInfos player) {
		this.players.add(player);
	}
	
	public int GetPlayersNumber() {
		return this.players.size();
	}

//ausgewaehlte Map importieren.
private void loadMapFile(int mapIndex)  {
	InputStreamReader[] file_Server = new InputStreamReader[5] ;// Array for Maps
	file_Server[0] = new InputStreamReader(Room.class.getResourceAsStream("maps/map1server.csv"));
	file_Server[1] = new InputStreamReader(Room.class.getResourceAsStream("maps/map2server.csv"));
	file_Server[2] = new InputStreamReader(Room.class.getResourceAsStream("maps/map3server.csv"));
	file_Server[3] = new InputStreamReader(Room.class.getResourceAsStream("maps/map4server.csv"));
	file_Server[4] = new InputStreamReader(Room.class.getResourceAsStream("maps/map5server.csv"));

		//BufferedReader br =new BufferedReader(new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map1server.csv"))); 
		BufferedReader br =new BufferedReader(file_Server[mapIndex]); //BufferedReader to reads the text from csv file 

		
	    mapLayout = new ArrayList<>();
	 
        try {
            String currentLine;
            while ((currentLine = br.readLine()) != null) { //read file
               if (currentLine.isEmpty()) { // wenn string leer
                  continue;
               }
                mapLayout.add(new ArrayList<>(Arrays.asList(currentLine.split(","))));//String aufteilen , Trennzeichen ","
            }   
          
        } catch (IOException | NullPointerException e) {
            System.out.println(e + "Error beim LoadMapFile()");
            e.printStackTrace();
        }
	 }
	

}