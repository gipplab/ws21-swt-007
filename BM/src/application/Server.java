package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
				buffer = new byte[256];
				inPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(inPacket);
				clientAddress = inPacket.getAddress();
				clientPort = inPacket.getPort();
				messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
				String[] message = messageIn.split("-");
				//System.out.println(messageIn);
				if(message[0].equals("Host")) 
				{
					PlayerInfos host = new PlayerInfos(message[1]); 
					Room rm = new Room(message[1],Integer.parseInt(message[2]));
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
									}
									
									break;
								}
							}
						}
						outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
					    datagramSocket.send(outPacket);
					
					
				}else if(message[0].equals("Play"))
				{
					messageOut="Noting";
					for(Room room : roomsList)
					{
						if(message[1].equals(room.getHostName()))
						{
							for(PlayerInfos player : room.players)
							{
								if(message[2].equals(player.getName())) 
								{
									//if(!player.GetUpdates().equals(""))
									//{
									messageOut=player.GetUpdates();
									player.SetUpdates("");
									//}
								}else 
								{		if(message[3].equals("Updates"))
										{
											if(!player.GetUpdates().equals(""))
											{
												String updates=message[2]+"-"+message[4];
												System.out.println("#2323213#"+updates);
												player.AddUpdatesToPlayers(updates);
											}
										}
										
								}
							}
						}
					}
					System.out.println("##"+messageOut);
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