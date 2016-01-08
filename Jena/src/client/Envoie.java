package client;

import java.io.PrintWriter;
import java.util.Scanner;


public class Envoie implements Runnable
{

    private String message = null;  // Allocation memoire pour les messages recus
    private String test = null;    // Login du client
    private Scanner sc = null;      // Scanner de saisi du message
    private PrintWriter out;        // Envoyeur
    
    
    /**
     * Constructeur de Envoie
     * @param out
     */
    public Envoie(PrintWriter out)
    {
        this.out = out;
    }
    
    
    /**
     * Lancement de la thread qui envoie les messages
     */
    public void run()
    {
        sc = new Scanner(System.in);    // Initilisation du scanner
        
        while(true){
            message = sc.nextLine();    // Saisi du message
            test=message;
            System.out.println(test);
            out.println(message);

            out.flush();       // Envoie et vidage du contenu du buffer

        }
    }

}
