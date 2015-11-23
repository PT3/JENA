package Serveur;

import java.io.*;
import java.net.*;

public class ChatServeur implements Runnable {

    private String login;		// Login du client
    private Socket socket;		// lien avec le socket Client
    private PrintWriter out;	// Envoyeur
    private BufferedReader in;	// Receveur
    @SuppressWarnings("unused")
	private Thread tRecep, tEmiss;	// Thread de reception et d'émission des message
    
    
    /**
     * Constructeur du chat d'un client
     * @param s
     * @param login
     */
    public ChatServeur(Socket socket, String login){
        this.socket = socket;
        this.login = login;
    }

    
    /**
     * Lancement d'une procédure de chat
     */
    public void run(){
        
        try {
            /* Erreur a corriger */
        	
            // Initialisation du receveur
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            
            // Thread de reception des messages 
            Thread tRecep = new Thread(new Reception(in, out, login));
            tRecep.start();
            
            // Thread d'envoie des messages depuis le serveur
            Thread tEmiss = new Thread(new Emission(out));
            tEmiss.start();
            
        }
        catch (IOException e)
        {
        	// Si la connexion client est perdu on affiche un message d'erreur
        	System.err.println(login + " a été déconnecté ");
        }
        
    }
}
