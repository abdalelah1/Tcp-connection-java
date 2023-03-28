package tcpserverclient;

import java.net.*;
import java.io.*;

public class Server {
    
    
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;
    
    public Server(){}
    
    private void log(String message)
    {
        System.out.println(message);
    }
   // (1)
    private void startConnection()
    {
        
        waitingConnection();
        openInputStream();
    }
    // (2)
    private void Connecting(){
        String line = "";
        while(! line.equals(Constants.STOP))
        {
            line = read();
            System.out.println("Client : " + line);
            
        }

    }
    // (3)
    private void stopConnection(){
        closeSocket();
        closeInputStream();
    }
    
    private void openSocket(){
        try{
            serverSocket = new ServerSocket(Constants.PORT);
            log("Server Started...");
        }catch(IOException ex){
            log("start server : " + ex.getMessage());
        }
    }
    
    private void waitingConnection()
    {
        log("Waiting for client...");
        
        try 
        {
            socket = serverSocket.accept();
            log("Client accepted");      
        } 
        catch (IOException ex) 
        {
            log("waiting Connection : " + ex.getMessage());
        }        
    }
    
    private void openInputStream()
    {
        try
        {
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
        }
        catch(IOException ex)
        {
            log("open InputStream : " + ex.getMessage());
        }
    }
    
    private String read()
    {
        String line = "";
        try
        {
            line = input.readUTF();            
        }
        catch(IOException ex)
        {
            log("read : " + ex.getMessage());
        }
        return line;
    }
   
    private void closeSocket()
    {
        try
        {
            socket.close();            
        }
        catch(IOException ex)
        {
            log("close socket : " + ex.getMessage());
        }  
    }
    
    private void closeInputStream()
    {
        try
        {
            input.close();
        }
        catch(IOException ex)
        {
            log("close Input Stream : " + ex.getMessage());
        }
    }
    
        public static void main(String[] args)
    {
        Server server = new Server();
        server.openSocket();
        while(true)
                   {
        server.startConnection();
        server.Connecting();
        server.stopConnection();
                   }
    }
}
