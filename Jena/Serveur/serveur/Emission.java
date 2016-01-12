package serveur;

import java.util.ArrayList;

public class Emission implements Runnable
{    
    private ArrayList<Client> user;		// Envoyeur
    private String message = null;	// Message a transmettre
    
    /**
     * Constructeur de l'envoyeur de message
     * @param out
     */
    public Emission(ArrayList<Client> user,String message)
    {
    	this.user=user;
    	this.message=message;
    }
    
    
    /**
     * Lancement de la thread d'envoi
     */
    public void run() {
    	
        
        while(message!=null){
        	for(int i=0;i<user.size();i++)
        	{
        			user.get(i).getWriter().println(message);
        			user.get(i).getWriter().flush();
        	}
        	message=null;
        		
        }
    }
}