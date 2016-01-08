package estebanwip;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private String login;
	private Socket soc;
	private PrintWriter out;
    private BufferedReader in;	
	
	public Client(String login, Socket soc, PrintWriter out, BufferedReader in)
	{
		this.login=login;
		this.soc=soc;
		this.out=out;
		this.in=in;
		
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public Socket getSocket()
	{
		return soc;
	}
	
	public BufferedReader getBuffer()
	{
		return in;
	}
	public PrintWriter getWriter()
	{
		return out;
	}
}
