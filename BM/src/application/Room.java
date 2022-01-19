package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Room {
	private String hostName;
	private int howManyPlayers;
	int mapIndex;
	ArrayList<String[]> map = new ArrayList<>();
	public static  ArrayList<ArrayList<String>> mapLayout; 
    ArrayList<PlayerInfos> players = new ArrayList<>();   
	public Room(String hostName, int howManyPlayers, int mapIndex) {
		this.hostName = hostName;
		this.howManyPlayers = howManyPlayers; 
		this.mapIndex= mapIndex;
		loadMapFile(mapIndex);
		int row=0;
		int column=0;
		for(ArrayList<String> x : mapLayout) {
			for(String y : x) {
				if(y.equals("S")||y.equals("P")||y.equals("E")||y.equals("A")||y.equals("Q")) {
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
	public void decreasePlayernumber () {
		this.howManyPlayers--;
	}
	public void AddPlayerToRoom(PlayerInfos player) {
		this.players.add(player);
	}
	public int GetPlayersNumber() {
		return this.players.size();
	}
private void loadMapFile(int mapIndex)  {
	InputStreamReader[] file_Server = new InputStreamReader[5] ;
	file_Server[0] = new InputStreamReader(Room.class.getResourceAsStream("maps/map1server.csv"));
	file_Server[1] = new InputStreamReader(Room.class.getResourceAsStream("maps/map2server.csv"));
	file_Server[2] = new InputStreamReader(Room.class.getResourceAsStream("maps/map3server.csv"));
	file_Server[3] = new InputStreamReader(Room.class.getResourceAsStream("maps/map4server.csv"));
	file_Server[4] = new InputStreamReader(Room.class.getResourceAsStream("maps/map5server.csv"));

		//BufferedReader br =new BufferedReader(new InputStreamReader(Ressourcen.class.getResourceAsStream("maps/map1server.csv"))); 
		BufferedReader br =new BufferedReader(file_Server[mapIndex]); 

		
	    mapLayout = new ArrayList<>();
	 
        try {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
               if (currentLine.isEmpty()) {
                  continue;
               }
                mapLayout.add(new ArrayList<>(Arrays.asList(currentLine.split(","))));
            }   
          
        } catch (IOException | NullPointerException e) {
            System.out.println(e + "Error beim LoadMapFile()");
            e.printStackTrace();
        }
	 }
	

}