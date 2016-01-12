package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import inscri.*;


/* Page d'inscription, 
 * L'utilisateur doit remplir : Pseudo, Password, Confirm Password, Email
 * Les données sont vérfiés , puis envoyer à la base de données 
 */
public class Inscription extends JFrame implements ActionListener
{
	//Instanciation des différents éléments graphiques
	private JPanel mainPanel,inscriptionPanel;
	private JTextField tfLogin,tfEmail;
	private JPasswordField tfPassword,tfConfirmPassword;
	private JLabel lLogin,lPassword,lConfirmPassword,lEmail,lErreur;
	private JButton bValider;
	
	/* Constructeur de la fenêtre, prends en paramètre la longueur ( int X) et la longueur (int Y)de la fenêtre */
	public Inscription(int x,int y)
	{
		super("Inscription");
		inscriptionPanel = new JPanel( new GridLayout(10,1));
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
			
		mainPanel = new JPanel( new BorderLayout(4, 4));
		mainPanel.add(inscriptionPanel);
		
		this.add(mainPanel);
		
		//Paramètres de la fenêtre
		setPreferredSize(new Dimension(x,y));
		construireFenetre();
		
	}
	
	public void construireFenetre()
	{
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
				this.pack();
				this.setLocationRelativeTo(null);
				this.setResizable(false);
				this.setVisible(true);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				this.setBackground(Color.DARK_GRAY);
	}
	
	public Inscription(int x,int y,String erreur)
	{
		super("Inscription");
		inscriptionPanel = new JPanel( new GridLayout(10,1));
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
		lErreur= new JLabel("Champ(s) invalide(s)s");
		mainPanel = new JPanel( new BorderLayout(4,4));
		mainPanel.add(inscriptionPanel);	
		this.add(mainPanel);
		
		//Paramètres de la fenêtre
		setPreferredSize(new Dimension(x,y));
		construireFenetreErreur();	
	}

	public void construireFenetreErreur()
	{
		inscriptionPanel.add(lErreur);
		construireFenetre();
	}
	/* Actions effectuées lorsque l'utilisateur appuie sur le bouton valider*/
	public void actionPerformed(ActionEvent e)
	{
		String log = tfLogin.getText();
		String m = tfEmail.getText();
		String pass = tfPassword.getText();
		String pass2 = tfConfirmPassword.getText();
		
		BDD verif1 = new BDD();
		
		if(verif1.EqualPassword(m, log, pass, pass2))
		{
			verif1.BdInscriptionConf(m, log, pass, pass2);
			new Login(250,320);
			this.dispose();
		}
		
		else
		{
			new Inscription(300,400,"erreur");
			this.dispose();
		}
	}
}
