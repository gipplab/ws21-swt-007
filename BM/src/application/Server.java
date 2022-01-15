package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javafx.scene.control.Alert;

public class Server {
	private static final int PORT = 1234;
	private static ArrayList<Room> roomsList = new ArrayList<>();
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("opening port \n" + InetAddress.getLocalHost() + "||" + InetAddress.getByName("localhost"));
		try {
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
				buffer = new byte[2560];
				inPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(inPacket);
				clientAddress = inPacket.getAddress();
				clientPort = inPacket.getPort();
				messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
				System.out.println(messageIn);
				String[] message = messageIn.split("-");
				
				if(message[0].equals("Host")) 
				{
					PlayerInfos host = new PlayerInfos(message[2]); 
					Room rm = new Room(message[1],Integer.parseInt(message[3]));
					rm.AddPlayerToRoom(host);
					roomsList.add(rm);
					System.out.print(" : ");
					System.out.println(messageIn);
					messageOut = "Created";
					outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					datagramSocket.send(outPacket);
				}else if(message[0].equals("Player")){
					if(message[1].equals("AllHosts"))
					{
						messageOut= "";
						for(Room room : roomsList)
						{
							messageOut =room.getHostName()+"-("+room.GetPlayersNumber()+"/"+room.getHowManyPlayers()+")-"+messageOut ;													
						}
						outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					    datagramSocket.send(outPacket);
					}else if(message[1].equals("Join"))
					{
						boolean found= false;
							messageOut= "";
							for(Room room : roomsList)
							{
								if(message[2].equals(room.getHostName()))
								{
									if(room.GetPlayersNumber() < room.getHowManyPlayers())
									{
										PlayerInfos player = new PlayerInfos(message[3]); 
										room.AddPlayerToRoom(player);										
										found=true;
										messageOut= "Added";
										break;
									}
								}
							}
							if(!found) 
							{
								messageOut= "NoRoomFound";
							}
							
							outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
						    datagramSocket.send(outPacket);
					}
				}else if(message[0].equals("Waiting"))
				{
						messageOut="";
						for(Room room : roomsList)
						{
							if(message[1].equals(room.getHostName()))
							{
								if(room.GetPlayersNumber() < room.getHowManyPlayers())
								{
									messageOut= room.GetPlayersNumber()+"/"+room.getHowManyPlayers();
									break;
								}else if(room.GetPlayersNumber() == room.getHowManyPlayers()) {
									
									messageOut="Complited";
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
								for(PlayerInfos player : room.players) {
									if(message[2].equals(player.getName())) {
										player.setAction("ACTIF");
										if(message[4].equals("BOMB")) {
											player.setBomb(message[4]+"-"+message[5]+"-"+message[6]);
										}else if(message[4].equals("NOBOMB")) {
											player.setBomb(message[4]);
										}
										else { 
											player.setPosition(message[4]+"-"+message[5]+"-"+message[6]);
											
										}
										messageOut="SuccessUpdate";
										break;
									}
									
								}																
							}else if(message[3].equals("GetUpdates")) {
								messageOut="ServerUpdates";
								for(PlayerInfos player : room.players) {
									if(!message[2].equals(player.getName())) {
										if(player.action.equals("ACTIF")) {
											messageOut=messageOut+"-PLAYER-"+player.getName()+"-"+player.getPosition()+"-"+player.getBomb();
											player.action = "NACTIF";
										}else {
											messageOut=messageOut+"-PLAYER-"+player.getName()+"-NACTIF";
										}
									}else {
										player.setPosition("STOP-"+message[4]+"-"+message[5]);
										player.action = "ACTIF";
									}
								}
							}
						
						}
						
						break;
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