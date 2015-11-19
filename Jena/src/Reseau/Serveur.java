package Reseau;

import java.io.*;
import java.net.*;

public class Serveur {

    public static ServerSocket socketserver = null;     // Socket du serveur (flux entrant)
    public static Thread t;     // Thread lancée a chaque demande de connexion client

    public static void main(String[] args){
    
        try{
            // Application du Socket sur le port 2000 a l'adresse localhost
            socketserver = new ServerSocket(2000);
            System.out.println("Le serveur est à l'écoute du port " + socketserver.getLocalPort());

            // Lancement de la Thread de detection des demande client
            t = new Thread(new Accepter_connexion(socketserver));
            t.start();
            
        }catch (IOException e)
        // Erreur en cas de surcharge du port demandé 
        {System.err.println("Le port n'est pas disponible !");}

    }
}