package iGraphique;

import inscription.BdInscription;
import inscription.BdVerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;


/* Page d'inscription, 
 * L'utilisateur doit remplir : Pseudo, Password, Confirm Password, Email
 * Les données sont vérfiés , puis envoyer à la base de données 
 */
public class InscriptionPass extends JFrame implements ActionListener
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
	
	/* Constructeur de la fenêtre, prends en paramètre la longueur ( int X) et la longueur (int Y)de la fenêtre */
	public InscriptionPass(int x,int y)
	{
		super("Inscription");
		
		//Initialisation des éléments graphiques
		inscriptionPanel = new JPanel( new GridLayout(9,1));
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		lConfirmPassword = new JLabel("Confirm Password(*). Vos mots de passe doivent correspondre");
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

	/* Actions effectuées lorsque l'utilisateur appuie sur le bouton valider*/
	public void actionPerformed(ActionEvent e)
	{
		String TextLogin = tfLogin.getText();
		String TextEmail = tfEmail.getText();
		String TextPassword = tfPassword.getText();
		String TextConfirmPassword = tfConfirmPassword.getText();
		
		m = TextEmail;
		log = TextLogin;
		pass = TextPassword;
		pass2 = TextConfirmPassword;
		
		BdVerif pass1 = new BdVerif();
		BdVerif log1 = new BdVerif();
		BdVerif mail1 = new BdVerif();
		BdInscription insc = new BdInscription();
		
		boolean validPass = pass1.EqualPassword(m, log, pass, pass2);
		boolean EqualLog = log1.EqualLogin(log);
		boolean EqualMail = mail1.EqualMail(m);
		
		if(EqualLog)
		{
			InscriptionPass ip = new InscriptionPass(500,500);
			this.dispose();
		}
		
		else if(EqualMail)
		{
			InscriptionPass ip = new InscriptionPass(500,500);
			this.dispose();
		}
		
		else if(!validPass)
		{
			InscriptionPass ip = new InscriptionPass(500,500);
			this.dispose();
		}
		
		else
		{
			insc.BdInscriptionConf(m, log, pass, pass2);
			Login l = new Login(250,320);
			this.dispose();
		}

	}
}
