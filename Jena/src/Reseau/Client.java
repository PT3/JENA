package reseau;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread{
		
		private Socket socket;
        private BufferedReader in;      // Receveur
        private PrintWriter out;        // Envoyeur
        
    
    public void run(){
        Scanner scn = new Scanner(System.in);
        String text = "";
        
        while (text != "#"){
            long tempDebut = System.nanoTime();
            
            text = scn.nextLine();
            sendMessage(text);
            
            while(1000000000 - ( System.nanoTime() - tempDebut) > 0);
        }
        close();
    }
    
    
    public Client (InetAddress adress, int port, String name){
        super(name);
        try{
            
            // Création du socket prenant en paramètre une adresse et un port
            // (ici l'adresse est localhost et le port 2000
            socket = new Socket(adress,port);
            System.out.println("\nConnexion établie.");
            
            
            // Initialisation de l'envoyeur
            out = new PrintWriter(socket.getOutputStream());
            // Initialisation du receveur
            in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
            
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
    

    /** Envoie de message */
    public void sendMessage(String message){
        if (message == "#"){
            out.println((String)message);      // Stockage et envoie de la phrase
            out.flush();               // Vidage du buffer
        }
        else{
            out.println(this.getName() + ": " + message);      // Stockage et envoie de la phrase
            out.flush();               // Vidage du buffer
        }
    }
    
    
    /** Reception de message */
    public void giveMessage(String message){
        try{
            
            // Ecoute et affichage d'un message Serveur
            String messageServ = in.readLine();
            System.out.println("Message Seveur: " + messageServ);
            
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
    

    /** Liberation du socket */
    public void close(){
        try{
            
            // Liberation du socket IMPERATIF !
            socket.close();
            System.out.println("\nDeconnexion.");
            
        }
        catch (UnknownHostException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
}