package affichage;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;




/* Class Main, permet de lancer la fen�tre de connexion et de cr�er le lien avec le serveur*/
public class Main 
{
    public static Socket socket = null; // Socket local
    private static String ip = null;    // Ip du serveur
    public static Thread threadConn;    // thread qui lance la préocédure de connexion au serveur

	public static void main(String[] args) 
	{
        ip = "92.138.249.243";   // Ip du serveur
        ip = "192.168.1.15";    // Redefinition temporaire pour les tests
        ip = "localhost";       // Redefinition temporaire pour les tests
        
        try {
			socket = new Socket(ip, 2000);
		} 
        catch (UnknownHostException e) 
        {e.printStackTrace();} 
        catch (IOException e)
        {e.printStackTrace();}
        
        System.out.println("Connexion serveur est etablie");
        
        // Lancement de la thread de Connexion
        
		new Login(300,300,socket);
	}
}
