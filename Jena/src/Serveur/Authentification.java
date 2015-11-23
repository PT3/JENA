package Serveur;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Authentification implements Runnable
{

    private Socket socket;		// Instance du Socket client 
    private PrintWriter out = null;     // Envoyeur
    private BufferedReader in = null;   // Receveur
    private String login = "";		// Login a completer par le client
    private String pass = null;		// password a completer par le client

    public boolean authentifier = false;	// Vérification de l'authentification
    public Thread threadChat;	// Instance de la thread de chat
 
    /**
     * Constructeur de la procédure d'authentification
     * @param socket
     */
    public Authentification(Socket socket)
    {
    	this.socket = socket;
    }

    /**
     * Lancement d'une procédure d'authentification
     */
    public void run()
    {

        try
        {
        	 
            // Initialisation du receveur 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());

            while(!authentifier)
            {

             	// Placement de la demande de login dans le buffer
                out.println("Entrez votre login : ");
                out.flush();			// Envoie de la demande de login et vidage du buffer
                login = in.readLine();	// reception de la saisi client
                
        	 	// Placement de la demande de password dans le buffer
                out.println("Entrez votre mot de passe : ");
                out.flush();			// Envoie de la demande de password et vidage du buffer
                pass = in.readLine();	// reception de la saisi client
                
                // Verification des informations
                if(isValid(login, pass))
                {
                    // Envoie de la confirmation de connexion
                    out.println("true");
                    
                	// Placement de l'annonce de connexion dans le buffer
                    System.out.println(login + " vient de se connecter ");
                    out.flush();			// Envoie et vidage du contenu du buffer
                    
                    // Test de connexion approuvé (pemet de sortir de la boucle)
                    authentifier = true;
                }
                else
                {
                    out.println("erreur");	// Placement du message erreur dans le buffer
                    out.flush();		// Envoie et vidage du contenu du buffer
                }
            }
            
            // Lançement de la thread de chat
            threadChat = new Thread(new ChatServeur(socket,login));
            threadChat.start();
            
        }
        catch (IOException e)
        {
        	// En cas de deconexion du client
        	System.err.println(login + " ne répond pas !");
        }
    }

    /**
     * Verification des information de connexion client 
     * @param login
     * @param pass
     * @return connexion
     */
    private boolean isValid(String login, String pass)
    {
        
        boolean connexion = false;
        
        try
        {
        	// Ouverture d'un fichier text contenant les données 
        	// d'autentification des membres enregistrer
            Scanner sc = new Scanner(new File("login.txt"));
            
            // Tant que la ligne n'est pas null
            while(sc.hasNext())
            {
            	// Si la ligne contient le login et le pass saisi
                if(sc.nextLine().equals(login + " % " + pass))
                {
                    connexion = true; 	//  La connexion est confirmé 
                    break;		// On sort donc de la boucle de lecture
                }
            }
            
        }catch (FileNotFoundException e)
        {
        	// Si le fichier n'existe pas on affiche un message d'erreur
        	System.err.println("Le fichier n'existe pas !");
        }
        
        return connexion;
    }
}
