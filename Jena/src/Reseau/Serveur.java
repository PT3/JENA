package Reseau;

import java.io.*;
import java.net.*;

public class Serveur {
    
    protected ServerSocket socketserver ;
    private Socket socketduserveur;
    private BufferedReader in;      // Receveur
    private PrintWriter out;        // Envoyeur
    
    
    public static void main(String[] zero) {
        Serveur host = new Serveur(2000);
        String text = "";
        
        Thread t = new Thread(new Connexion(host.socketserver));
        t.start();
        
        while (text != "#"){
            long tempDebut = System.nanoTime();

            if (text != null)
                text = host.giveMessage();
            
            
            while(1000000000 - ( System.nanoTime() - tempDebut) > 0);
        }
        host.close();
    }



    public Serveur (int port){
        try{
            
            socketserver = new ServerSocket(port);

            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            System.out.println("\nServeur en attente...");
            
            socketduserveur = socketserver.accept();
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socketduserveur.getOutputStream());
            // Initialisation du receveur
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
    
    
    /** Envoie de message */
    public void sendMessage(String message){
        out.println(message);      // Stockage et envoie de la phrase
        out.flush();               // Vidage du buffer
    }
    
    
    /** Reception de message */
    public String giveMessage(){
        try{
            
            // Ecoute et affichage d'un message messageClient
            String messageClient = in.readLine();
            if(messageClient != null){
                System.out.println(messageClient);
                return messageClient;
            }
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return null;
    }
    
    
    /** Liberation du socket */
    public void close(){
        try{
            
            // Liberation du socket IMPERATIF !
            socketduserveur.close();
            socketserver.close();
            System.out.println("\nDeconnexion.");
            
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
    
}