package serveur;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Receptionne et renvoie les messages des utilisateurs
 * @author esteban et Adrien
 *
 */
public class Reception implements Runnable {

    private String message = null;	// Message du client
    private ArrayList<Client> user;
    private int i;
    
    /**
     * Constructeur d'un receveur
     * @param user (ArrayList<Client>) Tableau des utilisateurs connectés
     * @param i (int) Numéro de l'utilisateur dans le tableau
     */
    public Reception(ArrayList<Client> user,int i){
    	this.user=user;
    	this.i=i;
    }

    
    /**
     * Lancement de la thread qui reçoit et renvoie les messages client
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
	                if (message.equals("/quit"))
	                {
            			user.get(i).getWriter().println("quit");
            			user.get(i).getWriter().flush();
	                	ChatServeur.getInstance().deleteUser(user.get(i));
	                }
	                String[] cmd;
	                cmd=message.split(" ");
	                String admin=user.get(i).getLogin();
	                if (cmd[0].equals("/kick"))
	                {
	                	for(Client kickUser:user)
	                	{
	                		if(cmd[1].equalsIgnoreCase(kickUser.getLogin()) && !(admin.equals(kickUser.getLogin())))
	                			System.out.println(kickUser.getLogin());
		            			kickUser.getWriter().println("quit");
		            			kickUser.getWriter().flush();
		            			new Emission(user,kickUser.getLogin()+" a été kické").messageServ();
			                	ChatServeur.getInstance().deleteUser(kickUser);
		                	
	                	}
	                }
	                else
	                {
	                    message = user.get(i).getLogin() + " : " + message;
	                    	for(int j=0;j<user.size();j++)
	                    	{
	                    			user.get(j).getWriter().println(message);
	                    			user.get(j).getWriter().flush();
	                    	}
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