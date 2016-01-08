package client;

import java.util.Scanner;
import java.net.*;
import java.io.*;


public class Chat implements Runnable
{

    public BufferedReader in = null;    // Receveur
    public PrintWriter out = null;      // Envoyeur
    public Thread threadEnvoi;      // Procedure de gestion des envois
    public Thread threadRece;       // Procedure de gestion des receptions
    public Socket socket;       // Lien avec le serveur
    public Scanner sc;          // Scan les saisi

    
    /**
     * Constructeur de Chat
     * @param socket
     */
    public Chat(Socket socket)
    {
        this.socket = socket;
    }
    

    /**
     * Lancement du chat
     */
    public void run()
    {
        try
        {
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            
            // Initialisation du receveur
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Lançement de la thread de d'envoi
            Thread threadEnvoi = new Thread(new Envoie(out));
            threadEnvoi.start();
            
            // Lançement de la thread de reception
            Thread threadRece = new Thread(new Reception(in));
            threadRece.start();
            
        }
        catch (IOException e)
        {
            // En cas de perte de la connexion serveur
            System.err.println("Le serveur distant s'est déconnecté !");
        }
    }

}