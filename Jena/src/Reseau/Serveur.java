package Reseau;

import java.io.*;
import java.net.*;

/* Class Serveur , destiné à être placé sur le serveur en veille afin de recevoir les données*/
public class Serveur
{
    private ServerSocket socketserver ;
    private Socket socketduserveur;
    private BufferedReader in;      // Receveur
    private PrintWriter out;        // Envoyeur
 
    public static void main(String[] zero)
    {
        int port = 2000;
        
        Serveur Host = new Serveur(port);
        Host.giveMessage();
        Host.close();
    }
    
    public Serveur (int port)
    {
        try
        {
            socketserver = new ServerSocket(port);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            System.out.println("\nServeur en attente...");
            
            socketduserveur = socketserver.accept();
            System.out.println("\nConnexion établie.");
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socketduserveur.getOutputStream());
            // Initialisation du receveur
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
        }
        
        catch (UnknownHostException e) 
        {
        	e.printStackTrace();
        }
        catch (IOException e) 
        {
        	e.printStackTrace();
        }
    }
    
    
    /** Envoie de message */
    public void sendMessage(String message)
    {
        out.println(message);      // Stockage et envoie de la phrase
        out.flush();               // Vidage du buffer
    }
    
    
    /** Reception de message */
    public void giveMessage()
    {
        try
        {
            // Ecoute et affichage d'un message messageClient
            String messageClient = in.readLine();
            if(messageClient != null)
                System.out.println("Message Client: " + messageClient);
        }
        
        catch (UnknownHostException e)
        {
        	e.printStackTrace();
        }
        
        catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
    
    /** Liberation du socket */
    public void close()
    {
        try
        {
            // Liberation du socket IMPERATIF !
            socketduserveur.close();
            socketserver.close();
            System.out.println("\nDeconnexion.");
        }
        
        catch (UnknownHostException e) 
        {
        	e.printStackTrace();
        }
        
        catch (IOException e) 
        {
        	e.printStackTrace();
        }
    }
    
}
