package Reseau;

import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable
{    
    private PrintWriter out;		// Envoyeur
    private String message = null;	// Message a transmettre
    private Scanner sc = null;		// Scanner de saisi 
    
    
    /**
     * Constructeur de l'envoyeur de message
     * @param out
     */
    public Emission(PrintWriter out)
    {
    	this.out = out;
    }
    
    
    /**
     * Lancement de la thread d'envoi
     */
    public void run() {
        
        sc = new Scanner(System.in);	// Initialisation du scanner
        
        while(true){
        	message = sc.nextLine();	// Saisi du message
			out.println(message);		// On met le message dans le buffer
			out.flush();	// Envoie et vidage du contenu du buffer
        }
    }
}