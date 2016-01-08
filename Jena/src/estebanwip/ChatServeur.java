package estebanwip;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServeur implements Runnable {

    private ArrayList<Client> user;
	private static String login;		// Login du client
    private static Socket socket;		// lien avec le socket Client
    private PrintWriter out;	// Envoyeur
    private BufferedReader in;	// Receveur
    private String message = null;
    
    
    /**
     * Constructeur du chat d'un client
     * @param s
     * @param login
     */
    public ChatServeur(){
    	user=new ArrayList<Client>();
    	user.add(new Client("SERVER",null,null,null));
    }

    private static ChatServeur INSTANCE = new ChatServeur();
    
    public static ChatServeur getInstance(Socket soc,String log)
    {
    	socket=soc;
    	login=log;
    	return INSTANCE;
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
            System.out.println("a");
            
            Thread tRecep = new Thread(new Reception(user));
            tRecep.start();

                    
            
        }
        catch (IOException e)
        {
        	// Si la connexion client est perdu on affiche un message d'erreur
        	System.err.println(login + " a été déconnecté ");
        }
        
    }
}
