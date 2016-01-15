package serveur;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Objet permettant de stocker toutes les informations d'un utilisateur
 * @author esteban
 *
 */
public class Client {

	private String login;
	private Socket soc;
	private PrintWriter out;
    private BufferedReader in;
    private Emission chat;
	
    /**
     * Constructeur 
     * @param login
     * @param soc
     * @param out
     * @param in
     */
	public Client(String login, Socket soc, PrintWriter out, BufferedReader in)
	{
		this.login=login;
		this.soc=soc;
		this.out=out;
		this.in=in;	
	}
	
	/**
	 * Renvoie le Login
	 * @return login (String)
	 */
	public String getLogin()
	{
		return login;
	}
	
	/**
	 * Renvoie le socket
	 * @return Socket
	 */
	public Socket getSocket()
	{
		return soc;
	}
	
	/**
	 * Renvoie le Buffer
	 * @return BufferedReader
	 */
	public BufferedReader getBuffer()
	{
		return in;
	}
	
	/**
	 * Renvoie le PrintWriter
	 * @return PrintWriter
	 */
	public PrintWriter getWriter()
	{
		return out;
	}
	
	/**
	 * Retourne l'occurence d'Emission
	 * @return Emission
	 */
	public Emission getEmi()
	{
		return chat;
	}
}
