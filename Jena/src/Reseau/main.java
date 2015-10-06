package Reseau;

import java.net.*;

import java.util.Scanner;

public class main {
    
    public static void main(String[] zero) {
     
        int port = 2000;
        Scanner scn = new Scanner(System.in);
        
        try{
            Client Roderick = new Client(InetAddress.getLocalHost(),port);
            
            String messg = scn.nextLine();
            Roderick.sendMessage(messg);
            Roderick.close();
        }catch (UnknownHostException e) {}
    }
}


/*
 try{
    //pouette
 }
 catch (UnknownHostException e) {e.printStackTrace();}
 catch (IOException e) {e.printStackTrace();}
*/