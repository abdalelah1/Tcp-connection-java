package tcpserverclient;

import java.net.*;
import java.io.*;

public class Client2 {
    
    String num1,num2,operation;
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    private DataInputStream input2 = null;
    
    public Client2(){}
    
    private void log(String message){
        System.out.println(message);
    }
   
    private void openSocket(){
        try{
            socket= new Socket(Constants.IP, 1998);
            
        }catch(UnknownHostException ex){
            log("openSocket : " + ex.getMessage());
        }catch(IOException ex){
            log("openSocket : " + ex.getMessage());
        }
    }
   
    private void openStreams(){
        try{
            input = new DataInputStream((System.in));
            output = new DataOutputStream(socket.getOutputStream());
            
        }catch(IOException ex){
            log("openStreams : " + ex.getMessage());
        }
    }
    //***************(1)****************
    private void startConnection(){
        openSocket();
        if(socket != null){
            openStreams();            
        }
        log("Start Client");
    }
    private String ReadFromUser(){
        String line = "";
        try
        {
            line = input.readLine();  
        }
        catch(IOException ex)
        {
            log("read : " + ex.getMessage());
        }  
       return line;
    }
    
    private String ReadFromServer(){
        String line = "";
        try{
            input2 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            line = input2.readUTF();
            
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
    // ****************(2)****************
    private void connecting(){
            num1 = ReadFromUser();
            write(num1);
            num2 = ReadFromUser();
            write(num2);
            operation = ReadFromUser();
            write(operation);
            
            
    }
   // ****************(3)****************
    private void stopConnection(){
        closeStreams();
        closeSocket();
        log("Close Client");
    }
    
    private void closeStreams(){
        try{
            log(ReadFromServer());
            input2.close();
            input.close();
            output.close();
            
        }catch(IOException ex){
            log("closeStreams : " + ex.getMessage());
        }
    }
    
    private void closeSocket(){
        try{
            socket.close();
        }catch(IOException ex){
            log("closeSocket : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
       Client2 client = new Client2();
       
       client.startConnection();
       client.connecting();
       client.stopConnection();
    }
}
