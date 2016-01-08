package estebanwip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Reception implements Runnable {

    private String message = null;	// Message du client
    private ArrayList<Client> user;
    private int i;
    /**
     * Constructeur du receveur
     * @param in
     * @param out
     * @param login
     */
    public Reception(ArrayList<Client> user,int i){
    	this.user=user;
    	this.i=i;
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
        		while(true)
        		{
	                message = user.get(i).getBuffer().readLine();	// Receptionne la saisi du client
	                String test=message;
	                System.out.println(test);
	                if (message.equals("quit"))
	                {
            			user.get(i).getWriter().println("quit");
            			user.get(i).getWriter().flush();
	                	ChatServeur.getInstance().deleteUser(user.get(i));
	                }
	                    message = user.get(i).getLogin() + " : " + message;
	                    	for(int j=0;j<user.size();j++)
	                    	{
	                    			user.get(j).getWriter().println(message);
	                    			user.get(j).getWriter().flush();
	                    	}
	                    	//message=null;
		             //   
	        		//}
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