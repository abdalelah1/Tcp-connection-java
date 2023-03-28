
package tcpserverclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Serverlab2 {


    DatagramSocket socket;
    DatagramPacket recievepacket,sendpacket;
    public Serverlab2()
    {
        try 
        {
            socket=new DatagramSocket(5001);
        } 
        catch (SocketException ex) 
        {
            Logger.getLogger(Serverlab2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void sendpackettoclient(){
//        byte[] data="reeeeemiiii".getBytes();
//        try 
//        {
//            sendpacket=new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5002);
//            socket.send(sendpacket);
//        } 
//        catch (IOException ex)
//        {
//            Logger.getLogger(Serverlab2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void waitforpacket(){
        System.out.println("waiting for clients....................."); 
        while(true)
    {
        
        try 
        {
            byte[] data = new byte[100];
            recievepacket = new DatagramPacket(data, data.length);
            socket.receive(recievepacket);
            System.out.println("packet recieved from" + recievepacket.getAddress() + "\n host port num " + recievepacket.getPort() + "\n contain : " + new String(recievepacket.getData(), 0,recievepacket.getLength()));
        } catch (IOException ex) 
        {
            Logger.getLogger(Serverlab2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
        public static void main(String[] args) 
    {
     
      Serverlab2 s=new Serverlab2();
         while(true)
         {
        
           s.waitforpacket();
         }
        
    }
    
}
