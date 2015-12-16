package estebanwip;

import java.io.IOException;
import java.net.*;

public class Accepter_connexion implements Runnable 
{
    private ServerSocket socketserver = null;   // Socket du serveur (flux entrant)
    private Socket socket = null;   // Allocation mémoire pour un socket client potentiel
    
    // Thread déclenche la procédure d'autentification à chaque demande client
    private Thread threadAcc;
    
    /**
     * Constructeur de la procédure d'acceptation
     * @param socketserver
     */
    public Accepter_connexion(ServerSocket socketserver)
    {
    	this.socketserver = socketserver;
    }

    /**
     * Lancement de la Thread
     */
    public void run()
    {
        try
        {
        	// Cette Thread reçoit en continu les demandes de connexion client 
        	// et lance une procédure d'autentification
            while(true)
            {
            	// Le socket du serveur établie la connexion avec le socket du client
                socket = socketserver.accept();
                System.out.println("Nouvelle demande de connexion client.");
                
                // Lançement de la thread d'autentification client
                threadAcc = new Thread(new Authentification(socket));
                threadAcc.start();
            }
        }
        catch (IOException e)
        {
        	System.err.println("Erreur serveur");
        }
    }
}
