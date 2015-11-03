package Reseau;

import java.net.*;

import java.util.Scanner;

public class Roderick {
    
    public static void main(String[] zero) {
        
        int port = 2000;
        
        try{

            Client Roderick = new Client(InetAddress.getLocalHost(), port, "Roderick");
            Roderick.start();

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