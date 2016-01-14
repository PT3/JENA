package client;

import java.io.PrintWriter;
import java.util.Scanner;


public class Envoie implements Runnable
{

    private String message = null;  // Allocation memoire pour les messages recus
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
    
    public Envoie(String messsage)
    {
    	this.message=message;
    }
    
    
    /**
     * Lancement de la thread qui envoie les messages
     */
    public void run()
    { 
    	sc=new Scanner(System.in);
        while(true){
            out.println(message);
            sc.nextLine();
            out.flush();       // Envoie et vidage du contenu du buffer

        }
    }

}
