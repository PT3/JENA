package reseau;

import java.io.*;
import java.net.*;

public class Connexion implements Runnable{
    
    private ServerSocket socketserver = null;
    private Socket socket = null;
    public Thread t1;
    
    public Connexion(ServerSocket ss){socketserver = ss;}
    
    public void run() {

        while(true){
            try {
                
                socket = socketserver.accept();
                System.out.println("\nConnexion établie. ");
                System.out.println(socket);
                System.out.println(socketserver);
            }
            catch (IOException e) {System.err.println("Erreur serveur");}
        }
        
    }
}