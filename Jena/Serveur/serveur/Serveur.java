package serveur;

import java.io.*;
import java.net.*;

/**
 * Classe Main qui lance le serveur
 * @author esteban et Adrien
 *
 */
public class Serveur 
{
    public static ServerSocket socketserver = null;
    public static Thread t;
    public static int port=2000;
    
    /*Classe main, crée un serveur qui est à l'écoute du port "port"*/
    public static void main(String[] args)
    {
        try
        {
            socketserver = new ServerSocket(port);
            System.out.println("Le serveur est Ã  l'écoute du port " + socketserver.getLocalPort());
            
            // Lancement de la Thread de détection des demandes client
            t = new Thread(new Accepter_connexion(socketserver));
            t.start();
            
        }catch (IOException e)
        // Erreur en cas de surcharge du port demandé 
        {System.err.println("Le port n'est pas disponible !");}
    }
}
