package Reseau;

import java.net.*;

import java.util.Scanner;

public class Nina {
    
    public static void main(String[] zero) {
        
        int port = 2000;
        
        try{

            Client Nina = new Client(InetAddress.getLocalHost(), port, "Nina");
            Nina.start();

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