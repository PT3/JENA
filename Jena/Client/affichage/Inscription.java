package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;


/* Page d'inscription, 
 * L'utilisateur doit remplir : Pseudo, Password, Confirm Password, Email
 * Les données sont vérfiés , puis envoyer à la base de données 
 */
public class Inscription extends JFrame implements ActionListener
{
	
	public static String m;
	public static String log;
	public static String pass;
	public static String pass2;
	//Instanciation des différents éléments graphiques
	private JPanel mainPanel,inscriptionPanel;
	private JTextField tfLogin,tfEmail;
	private JPasswordField tfPassword,tfConfirmPassword;
	private JLabel lLogin,lPassword,lConfirmPassword,lEmail;
	private JButton bValider;
	private Socket socket;

	
	/**
	 * Constructeur de la fenêtre d'Inscriptiion
	 *@param x: Largeur de la fenêtre
	 *@param y: Longueur de la fenêtre
	 *@param socket: Connexion au serveur
	 */
	public Inscription(int x,int y,Socket socket)
	{
		super("Inscription");
		this.socket=socket;
		//Initialisation des éléments graphiques
		inscriptionPanel = new JPanel( new GridLayout(9,1));
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		lConfirmPassword = new JLabel("Confirm Password(*)");
		lEmail = new JLabel("Email :");
		tfLogin = new JTextField("",20);
		tfPassword = new JPasswordField(20);
		tfConfirmPassword = new JPasswordField(20);
		tfEmail = new JTextField("",20);
		bValider = new JButton("Valider");
		bValider.addActionListener(this);
		
		//Implantation des éléments dans la fenêtre
		inscriptionPanel.add(lLogin);
		inscriptionPanel.add(tfLogin);
		
		inscriptionPanel.add(lPassword);
		inscriptionPanel.add(tfPassword);
		
		inscriptionPanel.add(lConfirmPassword);
		inscriptionPanel.add(tfConfirmPassword);
		
		inscriptionPanel.add(lEmail);
		inscriptionPanel.add(tfEmail);
		
		inscriptionPanel.add(bValider);
		
		mainPanel = new JPanel( new BorderLayout(4, 4));
		mainPanel.add(inscriptionPanel);
		
		this.add(mainPanel);
		
		//Paramètres de la fenêtre
		setPreferredSize(new Dimension(x,y));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);
	}

	/**
	 * Actions effectuées lorsque l'utilisateur appuie sur le bouton valider (envoie du formulaire au serveur)
	 * Nécessite une validation du serveur
	 * */
	public void actionPerformed(ActionEvent e)
	{
		String Login = tfLogin.getText();
		String Email = tfEmail.getText();
		String Password = tfPassword.getText();
		String ConfirmPassword = tfConfirmPassword.getText();
		
	    BufferedReader in = null;   // Receveur
	    PrintWriter out = null;     // Envoyeur
	    
        // Initialisation de l'envoyeur
        try {
			out = new PrintWriter(socket.getOutputStream());
		} 
        catch (IOException e2) 
        {e2.printStackTrace();}
        
        // Initialisation du receveur
        try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} 
        catch (IOException e2)
        	{e2.printStackTrace();}

        	out.println("¤Inscription¤");
        	out.flush();
            out.println(Login); 
            out.flush();        			
            out.println(Password); 
            out.flush(); 
            out.println(ConfirmPassword); 
            out.flush();
            out.println(Email);  
            out.flush();
            boolean verif = false;
            
			try {
				verif = in.readLine().equals("true");
	            if(verif)//Si le serveur valide l'inscription on relance un Login
	            {
	                System.out.println("Inscription validée ");
	               new Login(300,300,socket);
	    			this.dispose();
	            }
	            // Si les informations sont incorrectes on recrée une page d'inscription
	            else
	            {
	            	new Inscription(300,350,socket);
	            	this.dispose();
	        		
	            }
			}
			catch (IOException e1)
			{e1.printStackTrace();}
	}
}
