package estebanwip;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionMessage implements Runnable
{
    private ArrayList<Socket> socket;		// Instance du Socket client 
    private PrintWriter out = null;     // Envoyeur
    private BufferedReader in = null;   // Receveur
    public Thread threadChat;	// Instance de la thread de chat
 
    /**
     * Constructeur de la procédure d'GestionMessage
     * @param socket
     */
    
    private GestionMessage()
    {
    	socket = new ArrayList<Socket>();
    }
    public GestionMessage(Socket socket)
    {
    	this.socket.add(socket);
    }

//    private static GestionMessage INSTANCE = new GestionMessage();
//    
//    public static GestionMessage getInstance()
//    {
//    	return INSTANCE;
//    } 
//    
    /**
     * 
     */
    public void run()
    {
    	String login = "";
        try
        {
            boolean authentifier = false;
            in = new BufferedReader(new InputStreamReader(socket.get(1).getInputStream()));
            out = new PrintWriter(socket.get(1).getOutputStream());
		    String pass = null;
            while(!authentifier)
            {

                out.println("Entrez votre login : ");
                out.flush();		
                login = in.readLine();	
                out.println("Entrez votre mot de passe : ");
                out.flush();			
                pass = in.readLine();
                if(isValid(login, pass))
                {
                    out.println("true");   
                    System.out.println(login + " vient de se connecter ");
                    out.flush();
                    authentifier = true;
                }
                else
                {
                    out.println("erreur");
                    out.flush();
                }
            }
            
            threadChat = new Thread(new ChatServeur(socket.get(0),login));
            threadChat.start();
            
        }
        catch (IOException e)
        {
        
        	System.err.println(login + " ne répond pas !");
        }
    }

    /**
     * Verification des information de connexion client 
     * @param login
     * @param pass
     * @return connexion
     */
    private boolean isValid(String login, String pass)
    {
        
        boolean connexion = false;
        
        try
        {
        
            Scanner sc = new Scanner(new File("src/Serveur/login.txt"));
            
           
            while(sc.hasNext())
            {
            
                if(sc.nextLine().equals(login + " % " + pass))
                {
                    connexion = true; 	
                    break;
                }
            }
            
        }catch (FileNotFoundException e)
        {
  
        	System.err.println("Le fichier n'existe pas !");
        }
        
        return connexion;
    }
}
