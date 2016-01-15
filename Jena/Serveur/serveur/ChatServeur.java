package serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServeur implements Runnable 
{

    private ArrayList<Client> user;
    private ArrayList<Thread> userThread;
	private static String login;		// Login du client
    private static Socket socket;		// lien avec le socket Client
    private PrintWriter out;	// Envoyeur
    private BufferedReader in;	// Receveur

    
    public ChatServeur()
    {
    	user=new ArrayList<Client>();
    	userThread=new ArrayList<Thread>();
    }
    //Singleton
    private static ChatServeur INSTANCE = new ChatServeur();
    /**
     * Permet de passer le socket et le login 
     * @param soc
     * @param log
     * @return
     */
    public static ChatServeur getInstance(Socket soc,String log)
    {
    	socket=soc;
    	login=log;
    	return INSTANCE;
    } 
    
    /**
     * Permet de retourner l'instance de ChatServeur
     * @return
     */
    public static ChatServeur getInstance()
    {
    	return INSTANCE;
    } 
    
    /**
     * Fait quitter un utilisateur
     * @param delUser : Un utilisateur connecté 
     */
    public void deleteUser(Client delUser)
    {
   
    	for(Client test:user)
    	{
    		if(delUser.getLogin()==test.getLogin())
    		{
    			try 
    			{
					delUser.getSocket().close();
					System.out.println(delUser.getLogin()+" déconnecté");
					user.remove(delUser);
					userThread.remove(delUser);
				} 
    			catch (IOException e) 
    			{
					e.printStackTrace();
				}
    		}
    	}
    }
    
    
    /**
     * Lancement d'une procÃ©dure de chat
     */
    public void run(){
        
        try {
        	
            // Initialisation du receveur
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            
            user.add(new Client(login,socket,out,in));
            
            userThread.add(new Thread(new Reception(user,user.size()-1)));
            userThread.get(userThread.size()-1).start();
            new Emission(user,login+ " vient de se connecter").messageServ();
        }
        catch (IOException e)
        {
        	// Si la connexion client est perdu on affiche un message d'erreur
        	System.err.println(login + " a été déconnecté ");
        }

        
    }
}
