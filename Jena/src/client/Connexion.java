import java.util.Scanner;
import java.net.*;
import java.io.*;


public class Connexion implements Runnable
{

    private boolean connexion = false;  // Variable qui test la connexion
    private BufferedReader in = null;   // Receveur
    private PrintWriter out = null;     // Envoyeur
    public String password = null;      // Mot de passe utilisateur
    private Socket socket = null;   // Socket local
    public String login = null;     // Login utilisateur
    private Thread threadChat;      // Thread de chat
    private Scanner sc = null;      // Scan de saisi utilisateur


    /**
     * Constructeur de Connexion
     * @param s
     */
    public Connexion(Socket socket)
    {
        this.socket = socket;
    }


    /**
     * Lancement de la procedure de connexion client - serveur
     */
    public void run()
    {
        try
        {   
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            
            // Initialisation du receveur
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Initilisation du scanner
            sc = new Scanner(System.in);
            
            
            while(!connexion)
            {
                
                System.out.println(in.readLine());
                login = sc.nextLine();  // Scan du login
                out.println(login);     // Saisi du login et placer dans le buffer
                out.flush();        // Envoie de la saisi du login et vidage du buffer
                
                System.out.println(in.readLine());
                password = sc.nextLine();   // Scan du login
                out.println(password);      // Saisi du password et placer dans le buffer
                out.flush();        // Envoie de la saisi du password et vidage du buffer
                
                
                // Si le serveur envoie la confirmation
                if(in.readLine().equals("true"))
                {
                    System.out.println("Je suis connecté ");
                    connexion = true;
                }
                // Si les informations sont incorrectes
                else
                {
                    System.err.println("Vos informations sont incorrectes ");
                }
                
            }
            
            // Lançement de la thread de chat
            threadChat = new Thread(new Chat(socket));
            threadChat.start();
            
        }
        catch (IOException e)
        {
            // En cas de délais d'attente dépasser
            System.err.println("Le serveur ne répond plus " + e);
        }
    }

}