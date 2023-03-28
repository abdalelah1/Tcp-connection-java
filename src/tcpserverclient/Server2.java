package tcpserverclient;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server2 {
    
    int num1,num2,result=0;
    String operation ;
    
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    
    public Server2(){}
    
    public static void main(String[] args){
        Server2 server = new Server2();
        
        server.openSocket();
        while(true)
                   {
        server.startConnection();
        server.Connecting();
        server.stopConnection();
                   }
    }
   // (1)
    private void startConnection(){
        
        waitingConnection();
        openInputStream();
    }
    // (2)
    private void Connecting(){
        
            num1 =Integer.valueOf(read());
            System.out.println("Number1: : " + num1);
            
            num2 =Integer.valueOf(read());
            System.out.println("Number2: : " + num2);
            
            operation = read();
            System.out.println("Operation: : " + operation);
        
    }
    // (3)
    private void stopConnection(){
        closeStreams();
        closeSocket();
    }
    
    private void openSocket(){
        try{
            serverSocket = new ServerSocket(1998);
            log("Server Started...");
        }catch(IOException ex){
            log("start server : " + ex.getMessage());
        }
    }
    
    private void waitingConnection(){
        log("Waiting for client...");
        
        try {
            socket = serverSocket.accept();
            log("Client accepted");
            
        } catch (IOException ex) {
            log("waiting Connection : " + ex.getMessage());
        }        
    }
    
    private void openInputStream(){
        try{
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
            
        }catch(IOException ex){
            log("open InputStream : " + ex.getMessage());
        }
    }
    
    private String read(){
        String line = "";
        try{
            line = input.readUTF();            
        }catch(IOException ex){
            log("read : " + ex.getMessage());
        }
        return line;
    }
    private void write(String message){
        try{
            output.writeUTF(message);
            
        }catch(IOException ex){
            log("write : " + ex.getMessage());
        }
    }
    private void closeSocket(){
        try{
            socket.close();            
        }catch(IOException ex){
            log("close socket : " + ex.getMessage());
        }  
    }
    
    private void closeStreams(){
        try
        {
            if(operation.equals("+"))
            {
                result=num1+num2;
                log("Reslt="+result);
                write(String.valueOf(result));
            }
            else if(operation.equals("*"))
            {
                result=num1*num2;
                log("Reslt="+result);
                write(String.valueOf(result));
            }
            input.close();
            output.close();
        }
        catch(IOException ex){
            log("close Input Stream : " + ex.getMessage());
        }
    }
    
    private void log(String message){
        System.out.println(message);
    }
}
