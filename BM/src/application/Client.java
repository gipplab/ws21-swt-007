package application;

import java.io.*;
import java.net.*;

public class Client
{
private static InetAddress host;
public static String playerpseudo;
public static String roomToJoin;
private static final int PORT=1234;
private static DatagramSocket datagramSocket;
private static DatagramPacket inPacket,outPacket;
private static byte[] buffer;
public void SetAddress(String addr){
	 try {
		host = InetAddress.getByName(addr);
	} catch (UnknownHostException e) {
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
         // accessServer();
     }
   public static String accessServer(String message)
{ 
	   String response="";
try
  {
    datagramSocket=new DatagramSocket();
    //Scanner userEntry=new Scanner(System.in);

         System.out.println(InetAddress.getLocalHost()+"  enter message :");
         //message=userEntry.nextLine();
         if(!message.equals("**CLOSE**"))
           {
        	 System.out.println(" \n sent msg--<<" +message+">>  "+host);
         outPacket=new DatagramPacket(message.getBytes(),message.length(),host,PORT);
         System.out.println(" \n sent msg--<<" +message+">> 1 "+host);
         datagramSocket.send(outPacket);
         buffer=new byte[256];
         inPacket=new DatagramPacket(buffer,buffer.length);
         datagramSocket.receive(inPacket);
         response=new String(inPacket.getData(),0,inPacket.getLength());
          System.out.println(" \n SERVER-->>" +response);
          datagramSocket.close();
          return response;          
      }
}
catch(IOException ioEx)
{
 ioEx.printStackTrace();
}

finally
{
   System.out.println("\n closing connection.... ");
    datagramSocket.close();
 }
return response;
}
}