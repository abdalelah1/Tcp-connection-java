package tcpserverclient;

import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.ch.Net;

public class ClientUDP {

    DatagramSocket socket;
    DatagramPacket recievepacket, sendpacket;

    public ClientUDP() 
    {
        try 
        {
            socket = new DatagramSocket(5002);
        } 
        catch (SocketException ex)
        {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendpackettoserver() throws IOException 
    {
        while (true)
        {
            System.out.println("send data to server");
            Scanner s = new Scanner(System.in);
            String m = s.next();
            byte[] data = m.getBytes();
            try 
            {
                sendpacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5001);
            } 
            catch (UnknownHostException ex) 
            {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            socket.send(sendpacket);
            System.out.println("packet sent");
            if (m.equals("quit")) 
            {
                socket.close();
                break;
            
            }

        }
    }
//
//    public void waitforpacket() throws IOException 
//    {
//        byte[] data = new byte[100];
//        recievepacket = new DatagramPacket(data, data.length);
//        
//        socket.receive(recievepacket);
//        System.out.println("packet recieved from" + recievepacket.getAddress() + "\n host port num " + recievepacket.getPort() + "\n contain : " + new String(recievepacket.getData(), 0,recievepacket.getLength()));
//    }
     public static void main(String[] args) 
     {
     ClientUDP c=new ClientUDP();
        try 
        {
            c.sendpackettoserver();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
