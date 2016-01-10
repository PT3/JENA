package serveur;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GestionMessage implements Runnable
{
    private Socket socket;		// Instance du Socket client 
    private PrintWriter out = null;     // Envoyeur
    private BufferedReader in = null;   // Receveur
    public Thread threadChat;	// Instance de la thread de chat
 
    /**
     * Constructeur de la procédure d'GestionMessage
     * @param socket
     */
    

    public GestionMessage(Socket socket)
    {
    	this.socket=socket;
    }
    
    public void run()
    {
    	String login = "";
        try
        {
            boolean authentifier = false;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
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
            ChatServeur chat=null;
            threadChat = new Thread(chat=ChatServeur.getInstance(socket,login));
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
        
            Scanner sc = new Scanner(new File("Serveur/serveur/login.txt"));
            
           
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
