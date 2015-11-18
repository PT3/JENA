package reseau;

import java.net.*;
import java.util.Scanner;

public class Raphael {
    
    public static void main(String[] zero) {
        
        int port = 2000;
        
        try{
            
            Client Raphael = new Client(InetAddress.getLocalHost(), port, "Raphael");
            Raphael.start();

        }catch (UnknownHostException e) {e.printStackTrace();}
    }
}


/*
 try{
 //pouette
 }
 catch (UnknownHostException e) {e.printStackTrace();}
 catch (IOException e) {e.printStackTrace();}
 */