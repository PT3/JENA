package serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServeur implements Runnable {

    private ArrayList<Client> user;
    private ArrayList<Thread> userThread;
	private static String login;		// Login du client
    private static Socket socket;		// lien avec le socket Client
    private PrintWriter out;	// Envoyeur
    private BufferedReader in;	// Receveur

    
    
    /**
     * Constructeur du chat d'un client
     * @param s
     * @param login
     */
    public ChatServeur(){
    	user=new ArrayList<Client>();
    	userThread=new ArrayList<Thread>();

    }

    private static ChatServeur INSTANCE = new ChatServeur();
    
    public static ChatServeur getInstance(Socket soc,String log)
    {
    	socket=soc;
    	login=log;
    	return INSTANCE;
    } 
    public static ChatServeur getInstance()
    {
    	return INSTANCE;
    } 
    
    public void deleteUser(Client delUser)
    {
    	int i=0;
    	for(Client test:user)
    	{
    		if(delUser.getLogin()==test.getLogin())
    		{
    			try 
    			{
					delUser.getSocket().close();
					System.out.println(delUser.getLogin()+" déconnecté");
					user.remove(i);
					userThread.remove(i);
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
        }
        catch (IOException e)
        {
        	// Si la connexion client est perdu on affiche un message d'erreur
        	System.err.println(login + " a été déconnecté ");
        }

        
    }
}
