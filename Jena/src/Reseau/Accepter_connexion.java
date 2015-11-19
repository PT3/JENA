import java.io.*;
import java.net.*;

public class Accepter_connexion implements Runnable {

    private ServerSocket socketserver = null;   // Socket du serveur (flux entrant)
    private Socket socket = null;   // Allocation memoire pour un socket client potentiel
    
    // Thread déclenche la procedure d'autentification a chaque demande client
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
     * Lancement de la Threads
     */
    public void run(){

        try
        {
        	// Cette Threads reçoit en continuellement les demandes de connexion client 
        	// et lance une procédure d'autentification
            while(true)
            {
            	// Le socket du serveur etablie la connexion avec le socket du client
                socket = socketserver.accept();
                System.out.println("Nouvelle demande de connexion client.");
                
                // Lançment de la threads d'autentification client
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