package sayit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import com.mongodb.internal.connection.Server;

public class CheckServerStatus {
    Frame frame;
    final String URL = "http://localhost:8100/";
    final String HOST = "localhost";
    final int PORT = 8100;
    

    CheckServerStatus(Frame frame){
        this.frame = frame;
    }

    //Code taken from 
    //https://stackoverflow.com/questions/3584210/preferred-java-way-to-ping-an-http-url-for-availability
    public boolean SocketStatus(){
        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(HOST, PORT), 4000);
            return true;
        }

        catch(IOException e){
            return false;
        }
    }

    public boolean ServerStatus(){
        try{
            System.out.println("server status checked.");
            return InetAddress.getByName(URL).isReachable(4000);
        }
        catch(Exception e){
            return false;
        }
    }

    public void updateServerStatus(){
        if (SocketStatus() == false){
            System.out.println("server not found.");
            frame.updateQuestionBox("Server not found!");
        }

        else{
            System.out.println("server status checked.");
            frame.updateQuestionBox("Connected to Server!");
        }
    }
    
}
