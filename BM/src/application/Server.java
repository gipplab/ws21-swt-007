package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;



public class Server {
	private static final int PORT = 1234;
	private static ArrayList<Room> roomsList = new ArrayList<>();//Liste zum Speichern von Raumen
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;// DatagramPacket-Objekt zum Empfangen und Senden definieren
	private static byte[] buffer;//Byte-Array zum Empfangen von Netzwerkdaten 

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("opening port \n" + InetAddress.getLocalHost() + "||" + InetAddress.getByName("localhost"));
		try {
			//Erstellt und binden den Datagramm-Socket an den angegebenen Port
			datagramSocket = new DatagramSocket(PORT);
		} catch (SocketException sockEx) {
			System.out.println("unable to open ");
			System.exit(1); 
		}
		handleClient();
	}

	private static void handleClient() {
		try {
			String messageIn, messageOut = "";
			InetAddress clientAddress = null;
			int clientPort;
			do {
				buffer = new byte[4096];//Byte-Array zum Empfangen von Netzwerkdaten 
				inPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(inPacket);//Empfangen der Paketen von client
				clientAddress = inPacket.getAddress();//Gibt die IP-Adresse des Clients zurück, von dem das Datagramm empfangen wurde.
				clientPort = inPacket.getPort();//Gibt die Portnummer des Clients zurück, von dem das Datagram empfangen wurde.
				messageIn = new String(inPacket.getData(), 0, inPacket.getLength());//konvertiert ein empfangenes DatagramPacket in einen String
				System.out.println(messageIn);
				String[] message = messageIn.split("-");//  String in mehrere subStrings aufteilen . Trennzeichen "-"
				
				if(message[0].equals("Host")) // when Client einen Raum erstellen 
				{
					PlayerInfos host = new PlayerInfos(message[2]); 
					Room rm = new Room(message[1],Integer.parseInt(message[3]),Integer.parseInt(message[4]));// neuer raum erstellen
					rm.AddPlayerToRoom(host); //Spieler zum Raum hinzufügen
					roomsList.add(rm);//fuegen den Raum der Raumliste hinzu
					System.out.print(" : ");
					System.out.println(messageIn);
					messageOut = "Created";
					outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					datagramSocket.send(outPacket);//Senden an den Client, dass der Raum erstellt wird
				}else if(message[0].equals("Player")){
					if(message[1].equals("AllHosts")) 
					{
						messageOut= "";
						for(Room room : roomsList) 
						{    //die Raume nennen , wie viele Spieler sich in jedem Raum befinden und die Kapazitaet jedes Raums an den Client senden
							messageOut =room.getHostName()+"-("+room.GetPlayersNumber()+"/"+room.getHowManyPlayers()+")-"+messageOut ;													
						}
						outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					    datagramSocket.send(outPacket);// an den Client senden
					}else if(message[1].equals("Join")) // wenn Spieler einen Raum betreten moechte
					{
						boolean found= false;
							messageOut= "";
							for(Room room : roomsList)
							{    // wenn es platz im ausgewaelten raum gibt, fuege den Spieler hinzu
								if(message[2].equals(room.getHostName()))  
								{
									if(room.GetPlayersNumber() < room.getHowManyPlayers())
									{
										PlayerInfos player = new PlayerInfos(message[3]); 
										room.AddPlayerToRoom(player);										
										found=true;
										messageOut= "Added"; // send an den Client, dass der Spieler hinzugefuegt ist
										break;
									}
								}
							}
							if(!found) 
							{
								messageOut= "NoRoomFound";// send an den Client, dass es  im ausgewaelten raum gibt
							}
							
							outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
						    datagramSocket.send(outPacket);
					}
				}else if(message[0].equals("Waiting"))//wenn der Raumersteller auf andere Spieler wartet
				{
						messageOut="";
						for(Room room : roomsList)
						{
							if(message[1].equals(room.getHostName()))
							{
								//Wenn die Anzahl der Spieler im Raum nicht vollstaendig ist, 
								//senden dem Client die Anzahl der Spieler im Raum und die Kapazitaet des Raums
								if(room.GetPlayersNumber() < room.getHowManyPlayers())
								{
									messageOut= room.GetPlayersNumber()+"/"+room.getHowManyPlayers();
									break;
								}else if(room.GetPlayersNumber() == room.getHowManyPlayers()) {
									//Wenn die Anzahl der Spieler im Raum vollstaendig ist,
									//senden dem Client, dass den Raum vollstaendig ist mit der Namen der Spieler
									messageOut="Complited-"+room.mapIndex;
									for(PlayerInfos player : room.players) 
									{
										messageOut=messageOut+"-"+player.getName();
										//System.out.println("txt"+messageOut);
									}
									break;
								}
							}
						}
						outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					    datagramSocket.send(outPacket);
					
					
				}else if(message[0].equals("Play"))
				{
					messageOut="Nothing";
					for(Room room : roomsList)
					{
						if(message[1].equals(room.getHostName()))
						{ 
							if(message[3].equals("SetUpdates")) {
								if(message[5].equals("WALL")) {
									for(int i=0;i<room.map.size();i++) {
										if(room.map.get(i)[0].equals(message[6]) && room.map.get(i)[1].equals(message[7]))
											room.map.remove(i);
									}
									
								}else if(message[5].equals("DEAD")) { // wenn der Spieler getoetet wird
									room.decreasePlayernumber();//Anzahl der Spieler verringern
									messageOut="SuccessUpdate";
								}else {
									for(PlayerInfos player : room.players) {									
										if(message[2].equals(player.getName())) {
											player.setAction(message[4]);
											if(message[5].equals("BOMB")) {// wenn Spieler Bombe liegt
												player.setBomb(message[5]+"-"+message[6]+"-"+message[7]);
											}else if(message[5].equals("NOBOMB")) {
												player.setBomb(message[5]);
											}
											else { 
												player.setPosition(message[5]+"-"+message[6]+"-"+message[7]);
												
											}
											messageOut="SuccessUpdate";
											break;
										}
										
									}
								}
																								
							}else if(message[3].equals("GetUpdates")) {
								messageOut="ServerUpdates";
								for(PlayerInfos player : room.players) {
									if(!message[2].equals(player.getName())) {										
											messageOut=messageOut+"-PLAYER-"+player.action+"-"+player.getName()+"-"+player.getPosition()+"-"+player.getBomb();
											
									}else {
										player.setPosition("STOP-"+message[5]+"-"+message[6]);
										player.action = message[4];
									}
								}
							}else if(message[3].equals("GetMap")) {
								messageOut="ServerUpdates-MAP-"+room.getHowManyPlayers()+"-";
								for(String[] mp : room.map) {
									messageOut=messageOut+mp[0]+"/"+mp[1]+"/";
								}
							}
							break;
						}
						
						
					}
					
					outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
				    datagramSocket.send(outPacket);
				}
			
			} while (true);
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			System.out.println("\n Closing srever connection.. ");
			datagramSocket.close();
		}
	}

}          