package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client
{
	public static String updateString="";
private static InetAddress host;
public static ArrayList<String> players=new ArrayList<>();
public static String playerpseudo;
public static String roomToJoin;
private static final int PORT=1234;
private static DatagramSocket datagramSocket;
private static DatagramPacket inPacket,outPacket;
private static byte[] buffer;
public void SetAddress(String addr){
	 try {
		host = InetAddress.getByName(addr);
		datagramSocket=new DatagramSocket();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 
public static void main(String[] args)
  {
    try
      {
         host=InetAddress.getByName("localhost");
         
      }
      catch(UnknownHostException uhEx)
      {
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
         outPacket=new DatagramPacket(message.getBytes(),message.length(),host,PORT);
         //System.out.println(" \n sent msg--<<" +message+">> 1 "+host);
         datagramSocket.send(outPacket);
         datagramSocket.setSoTimeout( 2000 ) ;
         buffer=new byte[2560];
         inPacket=new DatagramPacket(buffer,buffer.length);
         //System.out.println(" \n sent msg--<<" +message+">> 4 "+host);        
         datagramSocket.receive(inPacket);        
         //System.out.println(" \n sent msg--<<" +message+">> 5 "+host);
         response=new String(inPacket.getData(),0,inPacket.getLength());
          //System.out.println(" \n SERVER-->>" +response);
          //datagramSocket.close();
          return response;          
      }
}
catch(SocketTimeoutException s)
{
	System.out.println("NoConnexion");
	return "NoConnexion";
}
catch(IOException ioEx)
{
 ioEx.printStackTrace();
}

finally
{
   //System.out.println("\n close connection.... ");
    //datagramSocket.close();
 }
return response;
}
public static void addPlayer(String player) {
	// TODO Auto-generated method stub
    players.add(player);
}
   }
