package estebanwip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Reception implements Runnable {

    private String message = null;	// Message du client
    private ArrayList<Client> user;
    /**
     * Constructeur du receveur
     * @param in
     * @param out
     * @param login
     */
    public Reception(ArrayList<Client> user){
    	this.user=user;
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
        		for(int i=1;i<user.size();i++)
        		{
	                message = user.get(i).getBuffer().readLine();	// Receptionne la saisi du client
	                // Si le message contient quelque chose
	                if(message != null)
	                {
	                	// On colle le login du client a sont message
	                    message = user.get(i).getLogin() + " : " + message;
	                    Thread tEmiss = new Thread(new Emission(user,message));
	                    tEmiss.start();
	                    
	                }
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