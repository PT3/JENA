package affichage;

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

import iGraphique.InscriptionPass;
import iGraphique.Login;
import inscri.BdInscription;
import inscri.BdVerif;


/* Page d'inscription, 
 * L'utilisateur doit remplir : Pseudo, Password, Confirm Password, Email
 * Les donn�es sont v�rfi�s , puis envoyer � la base de donn�es 
 */
public class Inscription extends JFrame implements ActionListener
{
	
	public static String m;
	public static String log;
	public static String pass;
	public static String pass2;
	
	//Instanciation des diff�rents �l�ments graphiques
	private JPanel mainPanel,inscriptionPanel;
	private JTextField tfLogin,tfEmail;
	private JPasswordField tfPassword,tfConfirmPassword;
	private JLabel lLogin,lPassword,lConfirmPassword,lEmail;
	private JButton bValider;
	
	/* Constructeur de la fen�tre, prends en param�tre la longueur ( int X) et la longueur (int Y)de la fen�tre */
	public Inscription(int x,int y)
	{
		super("Inscription");
		
		//Initialisation des �l�ments graphiques
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
		
		//Implantation des �l�ments dans la fen�tre
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
		
		//Param�tres de la fen�tre
		setPreferredSize(new Dimension(x,y));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);
	}

	/* Actions effectu�es lorsque l'utilisateur appuie sur le bouton valider*/
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
		
		BdVerif verif1 = new BdVerif();
		BdInscription insc = new BdInscription();
		
		boolean validPass = verif1.EqualPassword(m, log, pass, pass2);
		
		if(validPass)
		{
			insc.BdInscriptionConf(m, log, pass, pass2);
			Login l = new Login(250,320);
			this.dispose();
		}
		
		else
		{
			InscriptionPass ip = new InscriptionPass(500,500);
			this.dispose();
		}
	}
}
