/**
 * Login.java
 * @date 06/11/2015
 * @author Julien
 */

package affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener, FocusListener, KeyListener
{
	public static String logConnection;
	public static String passConnection;
	private Socket socket;
	private String login;
	/**
	 * Boutton d'inscription et d'invitation
	 */
	private JButton b_inscription,b_connexion;
	/**
	 * Champ de texte : Login / Password
	 */
	private JTextField tfLogin, tfPassword;
	/**
	 * Panel : Principale contenant : Bouton(panel ou sont placé les boutons) / Log(Contenant les informations de connexion)
	 */
	private JPanel principale,bouton,log;
	/**
	 *  Label : Login/Password/Mot de passe perdu
	 */
	private JLabel lLogin,lPassword,lPsLoose,lErreur;
	/**
	 * CheckBox : Se souvenir de moi;
	 */
	private JCheckBox rememberMe;
	/**
	 * Layout : Choix du GridLayout a 7 lignes et 0 collones
	 */
	private GridLayout gL = new GridLayout(7,1);
	/**
	 * Constructeur de fenetre : Implémentation des éléments utiles
	 * @param x : Taille en x
	 * @param y : Taille en y
	 * @param socket: Socket, connexion avec le serveur
	 */
	public Login(int x, int y,Socket socket)
	{	
		super("Connexion : ");
		this.socket=socket;
		setPreferredSize(new Dimension(x,y));
		b_inscription = new JButton("Inscription");
		b_inscription.addActionListener(this);
		b_connexion = new JButton("Connexion");
		b_connexion.addActionListener(this);
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		tfLogin = new JTextField("Login",20);
		tfLogin.addFocusListener(this);
		tfPassword = new JPasswordField("password",20);
		tfPassword.addFocusListener(this);
		rememberMe = new JCheckBox("Se souvenir de moi");
		lPsLoose = new JLabel("Mot de passe oublié ?");
		principale = new JPanel(new FlowLayout());
		bouton = new JPanel();
		log = new JPanel();
		log.setLayout(gL);
		construireFenetre();
	}
	
	/**
	 * Constructeur de fenetre avec erreur : Implémentation des éléments utiles
	 * @param x : Taille en x
	 * @param y : Taille en y
	 * @param erreur: Notifie une erreur dans les identifiants
	 * @param socket: Socket, connexion avec le serveur
	 */
	
	public Login(int x,int y,String erreur, Socket socket)
	{
		super("Connexion : ");
		this.socket=socket;
		setPreferredSize(new Dimension(x,y));
		b_inscription = new JButton("Inscription");
		b_inscription.addActionListener(this);
		b_connexion = new JButton("Connexion");
		b_connexion.addActionListener(this);
		lErreur = new JLabel("Mauvais mot de passe/login");
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		tfLogin = new JTextField("Login",20);
		tfLogin.addFocusListener(this);
		tfPassword = new JPasswordField("password",20);
		tfPassword.addFocusListener(this);
		rememberMe = new JCheckBox("Se souvenir de moi");
		lPsLoose = new JLabel("Mot de passe oublié ?");
		principale = new JPanel(new FlowLayout());
		bouton = new JPanel();
		log = new JPanel();
		log.setLayout(new GridLayout(8,1));
		construireFenetreErreur();	
	}
	
	/* Ajoute un label d'erreur a la page de login */
	private void construireFenetreErreur()
	{
		log.add(lErreur);
		construireFenetre();
	}
	/**
	 * Ajout des différents éléments dans la fenètre
	 */
	private void construireFenetre()
	{
		tfPassword.addKeyListener(this);
		log.add(lLogin);
		log.add(tfLogin);
		log.add(lPassword);
		log.add(tfPassword);
		log.add(lPsLoose);
		log.add(rememberMe);
		bouton.add(b_inscription);
		bouton.add(b_connexion);
		log.add(bouton);
		principale.add(log);
		
		
		
		this.pack();
		this.add(principale);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * Connexion au serveur avec les identifiants passé dans les champs 
	 */
	public void connect()
	{
		String TextPassword = tfPassword.getText();
		
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

            out.println(tfLogin.getText()); 
            login=tfLogin.getText();
            out.flush();        				// Envoie de la saisi du login et vidage du buffer
            
            out.println(tfPassword.getText());  // Saisi du password et placer dans le buffer
            out.flush();        				// Envoie de la saisi du password et vidage du buffer
            
            boolean verif = false;
            
			try {
				verif = in.readLine().equals("true");
	            if(verif)
	            {
	                System.out.println("Je suis connecte ");
	                Principale p = new Principale(500,500,socket,in,login);
	    			p.setVisible(true);
	    			this.dispose();
	            }
	            // Si les informations sont incorrectes
	            else
	            {
	            	new Login(300,350,"erreur",socket);
	            	this.dispose();
	        		
	            }
			}
			catch (IOException e1)
			{e1.printStackTrace();}
			
            // Si le serveur envoie la confirmation

		
		logConnection = tfLogin.getText();
		passConnection = TextPassword;
	}
	
	/**
	 * Gestion des clics sur les boutons
	 */
	public void actionPerformed(ActionEvent e)
	    {
			/**
			 * On recupère la source de l'event
			 */
        	Object source = e.getSource();
        	/**
        	 * Si la source est le bouton d'inscription, on va sur la fenetre de chat principale
        	 */
        	if (source == b_inscription)
        	{
        		Inscription i = new Inscription(300,400,socket);
        		i.setVisible(true);
        		this.setVisible(false);
        	}
        	
        	else if (source==b_connexion)
        	{
        		connect();
        	}
	    }
	/**
	 * Gestion du focus des TextFields, efface les valeurs par défaut en cas de focus
	 */
	public void focusGained(FocusEvent e) 
	{
		Object source=e.getSource();
		if (source == tfLogin)
		{
			if(tfLogin.getText().equals("")|| tfLogin.getText().equals("Login"))
			{
				tfLogin.setText("");
			}
		}
		else if (source == tfPassword)
		{
			if(tfPassword.getText().equals("") || tfPassword.getText().equals("password"))
        	{
				tfPassword.setText("");
        	}
		}
	}
	 
	/**
	 * Gestion du focus des TextFields, ajoute les valeurs par défaut en cas de perte de focus sans ajout d'identifiant
	 */
	public void focusLost(FocusEvent e) 
	{
		 Object source=e.getSource();
	        if (source == tfLogin)
	        {
	        	if(tfLogin.getText().equals("")|| tfLogin.getText().equals("Login"))
	        	{
	        		tfLogin.setText("Login");
	        	}
	        }
	        else if (source == tfPassword)
	        {
	        	if(tfPassword.getText().equals("") || tfPassword.getText().equals("password"))
	        	{
	        		tfPassword.setText("password");
	        	}   	
	        }
	}
	@Override
	/**
	 * Gestion des touches pressées, si on presse entrée dans le TextField password on sera connecté
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			connect();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	
}
