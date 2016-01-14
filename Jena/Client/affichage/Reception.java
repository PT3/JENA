package affichage;

import java.io.BufferedReader;
import java.io.IOException;

import client.Chat;

public class Reception implements Runnable
{
    
    public String message = null;  // Allocation memoire pour les messages recus
    private BufferedReader in;      // Receveur
    private Principale p;
    
    /**
     * Constructeur de Reception
     * @param in
     */
    public Reception(BufferedReader in,Principale p)
    {
        this.in = in;
        this.p=p;
    }
    public Reception(BufferedReader in)
    {
        this.in = in;
    }


    
    /**
     * Lancement de la thread qui reçoit les messages
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
                	Chat.getInstance().quit();
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