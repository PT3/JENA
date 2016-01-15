package affichage;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Receptionne les messages du serveur
 * @author esteban et adrien
 */
public class Reception implements Runnable
{
    
    public String message = null;  // Allocation memoire pour les messages recus
    private BufferedReader in;      // Receveur
    private Principale p;
    
    /**
     * Constructeur de Reception
     * @param in : Entrée des messages
     * @param p : Fenêtre principale associée
     */
    public Reception(BufferedReader in,Principale p)
    {
        this.in = in;
        this.p=p;
    }
    
    /**
     * Lancement de la thread qui re�oit les messages
     */
    public void run()
    {
        while(true)
        {
            try
            {
                message = in.readLine();        // Reception du message
                if(message.equals("quit"))
                {
                	System.exit(0);
                }
                System.out.println(message);
                p.reception(message);
                 // Affichage du message
            }
            catch (IOException e)
            {
                e.printStackTrace();    // Affichage d'un erreur de reception
            }
        }
    }

}