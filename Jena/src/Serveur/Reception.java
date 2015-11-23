package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Reception implements Runnable {

    private PrintWriter out;		// Envoyeur
    private BufferedReader in;		// Receveur
    private String login = null;	// Login du client
    private String message = null;	// Message du client

    /**
     * Constructeur du receveur
     * @param in
     * @param out
     * @param login
     */
    public Reception(BufferedReader in, PrintWriter out, String login){
        this.in = in;
        this.out = out;
        this.login = login;
    }

    
    /**
     * Lancement de la thread qui reçoit les messages client
     */
    public void run()
    {
        while(true)
        {
        	try
        	{
                message = in.readLine();	// Receptionne la saisi du client
                // Si le message contient quelque chose
                if(message != null)
                {
                	// On colle le login du client a sont message
                    message = login + " : " + message;
                    out.println(message);	// On met le message dans le buffer
                    out.flush();		// Envoie et vidage du contenu du buffer
                }
                
            }
        	catch (IOException e)
            {
        		// Affichage de l'erreur 
        		e.printStackTrace();
        	}
        }
    }

}