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
            while(true)
            {
                socket = socketserver.accept();
                System.out.println("Nouvelle demande de connexion client.");
                threadAcc = new Thread(new GestionMessage(socket));
                threadAcc.start();
            }
        }
        catch (IOException e)
        {
        	System.err.println("Erreur serveur");
        }
    }
}
