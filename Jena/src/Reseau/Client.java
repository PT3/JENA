package reseau;

import java.io.*;
import java.net.*;

/* class client , gère la connexion de l'utilisateur*/
public class Client 
{
		
	private Socket socket;
        private BufferedReader in;      // Receveur
        private PrintWriter out;        // Envoyeur
        
    /* Constructeur prenant en paramètres l'adresse du serveur et son port , permet de créer la connexion */
    public Client (InetAddress adress, int port)
    {
        try
        {
            // Création du socket prenant en paramètre une adresse et un port
            // (ici l'adresse est localhost et le port 2000
            socket = new Socket(adress,port);
            System.out.println("\nConnexion établie.");
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            // Initialisation du receveur
            in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
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
    public void giveMessage(String message)
    {
        try
        {
            // Ecoute et affichage d'un message Serveur
            String messageServ = in.readLine();
            System.out.println("Message Seveur: " + messageServ);
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
            socket.close();
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
