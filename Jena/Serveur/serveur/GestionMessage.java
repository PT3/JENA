package serveur;

import java.io.*;
import java.net.*;
import inscri.BDD;
/**
 * Classe gérant les connexions et inscriptions
 * @author esteban et Adrien
 *
 */
public class GestionMessage implements Runnable
{
    private Socket socket;		// Instance du Socket client 
    private PrintWriter out = null;     // Envoyeur
    private BufferedReader in = null;   // Receveur
    public Thread threadChat;	// Instance de la thread de chat
    public BDD bdd;
 

    /**
     * Constructeur de Gestion Message
     * @param socket d'écoute
     */
    public GestionMessage(Socket socket)
    {
    	this.socket=socket;
    	bdd=new BDD();
    }
    /**
     * Réceptionne les connexions / inscriptions envoyées par les clients
     */
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
            	login = in.readLine();
            	pass = in.readLine();
            	if(login.equals("¤Inscription¤"))
            	{
            		login = pass;
            		pass = in.readLine();
            		String confirmPass = in.readLine();
            		String mail= in.readLine();
            		out.println(""+bdd.BdInscriptionConf(login, pass, confirmPass, mail));
            		out.flush();
            	}
                else if(bdd.LogValid(login, pass))
                {  
                    out.println("true");
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
        
        	System.err.println(login + " ne rÃ©pond pas !");
        }
    }
}
