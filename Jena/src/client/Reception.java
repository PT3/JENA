import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable
{
    
    private String message = null;  // Allocation memoire pour les messages recus
    private BufferedReader in;      // Receveur

    
    /**
     * Constructeur de Reception
     * @param in
     */
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
                System.out.println(message);    // Affichage du message
            }
            catch (IOException e)
            {
                e.printStackTrace();    // Affichage d'un erreur de reception
            }
        }
    }

}