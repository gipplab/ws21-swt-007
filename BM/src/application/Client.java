package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client
{
	public static String updateString="";
	private static InetAddress host; //Javas Darstellung einer IP-Adresse
	public static ArrayList<String> players=new ArrayList<>();
	public static String playerpseudo;
	public static String roomToJoin;
	private static final int PORT=1234; //Define the destination to send the datagram
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket,outPacket;//DatagramPacket-Objekt zum Empfangen und Senden definieren

	private static byte[] buffer;//Byte-Array zum Empfangen von Netzwerkdaten 
	
	public void SetAddress(String addr){
		try {
			//Inet-Adresse des Servers abrufen
			host = InetAddress.getByName(addr);
			//Erstellen eines Datagramm-Sockets
			datagramSocket=new DatagramSocket();
		} catch (UnknownHostException e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
		
		} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
		 // Abrufen die IP-Adresse des aktuellen Computers 
         host=InetAddress.getByName("localhost");  
		}
		catch(UnknownHostException uhEx){
         System.out.println("HOST ID not found.. ");
         System.exit(1);
       }
      //serverUpdates();
     }
	
   public static String accessServer(String message)
   { 
	   String response="Disconnected";
	   try
	   {
    	//datagramSocket=new DatagramSocket();
         //System.out.println(InetAddress.getLocalHost()+"  enter message :");
         //message=userEntry.nextLine();
         if(!message.equals("***CLOSE***"))
         {
        	 //System.out.println(" \n sent msg--<<" +message+">>  "+host);
        	 //Senden von Paketen der Länge length an die angegebene Portnummer auf dem angegebenen Host.
        	 outPacket=new DatagramPacket(message.getBytes(),message.length(),host,PORT);
        	 //System.out.println(" \n sent msg--<<" +message+">> 1 "+host);
        	 datagramSocket.send(outPacket);// paket senden
        	 //ein Aufruf von Receive() für diesen DatagramSocket wird nur für 2 Sekunden blockiert.
        	 //Wenn das Timeout abläuft, wird eine java.net.SocketTimeoutException ausgelöst,
        	 datagramSocket.setSoTimeout( 2000 ) ;
         	 buffer=new byte[4096];
         	 //Empfangen von Paketen der Länge length.
         	 inPacket=new DatagramPacket(buffer,buffer.length);
         	 //System.out.println(" \n sent msg--<<" +message+">> 4 "+host);        
         	 datagramSocket.receive(inPacket);        
         	 //System.out.println(" \n sent msg--<<" +message+">> 5 "+host);
         	 response=new String(inPacket.getData(),0,inPacket.getLength());
         	 //System.out.println(" \n SERVER-->>" +response);
         	 //datagramSocket.close();
          return response;          
         }
	   }catch(SocketTimeoutException s){
		// keine Antwort nach 2 Sekunden erhalten.
		   System.out.println("NoConnexion");
		   return "NoConnexion";
	   }
	   catch(IOException ioEx){//IOException wird ausgelöst, wenn während einer Eingabe-Ausgabe-Operation ein Fehler aufgetreten ist.
		   ioEx.printStackTrace();
	   }

	   finally
	   {
		   //System.out.println("\n close connection.... ");
		   //datagramSocket.close();
	   }
	   return response;
   }
   
   //Hinzufuege einen neuen Spieler zur Liste der Spieler 
   public static void addPlayer(String player) {
	// TODO Auto-generated method stub
    players.add(player);
   }
}
