package client;

import java.net.*;
import java.io.*;

public class AnotherClient 
{
	    public static Socket socket = null; // Socket local
	    private static String ip = null;    // Ip du serveur
	    public static Thread threadConn;    // thread qui lance la préocédure de connexion au serveur
	    public static void main(String[] args)
	    {
	        try
	        {
	            ip = "92.138.249.243";   // Ip du serveur
	            ip = "192.168.1.18";    // Redefinition temporaire pour les tests
	            ip = "localhost";       // Redefinition temporaire pour les tests
	            

	            socket = new Socket(ip, 2000);  // Création du lien avec le serveur
	            System.out.println("Connexion serveur est etablie");
	            
	            // Lançement de la thread de Connexion
	            threadConn = new Thread(new Connexion(socket));
	            threadConn.start();
	            
	        }
	        catch (UnknownHostException e)
	        {
	            // Erreur de connexion
	            System.err.println("Impossible de se connecter à l'adresse " + ip);
	        }
	        catch (IOException e)
	        {
	            // Aucun Serveur connecter le Raspberry
	            System.err.println("Aucun serveur à l'écoute du port ");
	        }
	    }

	}


