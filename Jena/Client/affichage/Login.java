/**
 * Login.java
 * @date 06/11/2015
 * @author Julien
 */

package affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

import inscri.*;

public class Login extends JFrame implements ActionListener, FocusListener
{
	public static String logConnection;
	public static String passConnection;
	
	/**
	 * Boutton d'inscription et d'invitation
	 */
	private JButton b_inscription,b_connexion;
	/**
	 * Champ de texte : Login / Password
	 */
	private JTextField tfLogin, tfPassword;
	/**
	 * Panel : Principale contenant : Bouton(panel ou sont plac� les boutons) / Log(Contenant les informations de connexion)
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
	 * Constructeur de fenetre : Impl�mentation des �l�ments utiles
	 * @param x : Taille en x
	 * @param y : Taille en y
	 */
	public Login(int x, int y)
	{
		super("Connexion : ");
		setPreferredSize(new Dimension(x,y));
		b_inscription = new JButton("Inscription");
		b_inscription.addActionListener(this);
		b_connexion = new JButton("Connexion");
		b_connexion.addActionListener(this);
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		tfLogin = new JTextField("Obligatoire",20);
		tfLogin.addFocusListener(this);
		tfPassword = new JPasswordField("password",20);
		tfPassword.addFocusListener(this);
		rememberMe = new JCheckBox("Se souvenir de moi");
		lPsLoose = new JLabel("Mot de passe oubli� ?");
		principale = new JPanel(new FlowLayout());
		bouton = new JPanel();
		log = new JPanel();
		log.setLayout(gL);
		construireFenetre();
	}
	
	public Login(int x,int y,String erreur)
	{
		super("Connexion : ");
		setPreferredSize(new Dimension(x,y));
		b_inscription = new JButton("Inscription");
		b_inscription.addActionListener(this);
		b_connexion = new JButton("Connexion");
		b_connexion.addActionListener(this);
		lErreur = new JLabel("Mauvais mot de passe/login");
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		tfLogin = new JTextField("Obligatoire",20);
		tfLogin.addFocusListener(this);
		tfPassword = new JPasswordField("password",20);
		tfPassword.addFocusListener(this);
		rememberMe = new JCheckBox("Se souvenir de moi");
		lPsLoose = new JLabel("Mot de passe oubli� ?");
		principale = new JPanel(new FlowLayout());
		bouton = new JPanel();
		log = new JPanel();
		log.setLayout(new GridLayout(8,1));
		construireFenetreErreur();	
	}
	/**
	 * Ajout des diff�rents �l�ments dans la fen�tre
	 */
	private void construireFenetre()
	{
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
	
	private void construireFenetreErreur()
	{
		log.add(lErreur);
		construireFenetre();
	}
	
	/**
	 * Action li�es aux cliques boutons
	 */
	public void actionPerformed(ActionEvent e)
	    {
			/**
			 * On recup�re la source de l'event
			 */
        	Object source = e.getSource();
        	/**
        	 * Si la source est le bouton d'inscription, on va sur la fenetre de chat principale
        	 */
        	if (source == b_inscription)
        	{
        		Inscription i = new Inscription(300,400);
        		i.setVisible(true);
        		this.setVisible(false);
        	}
        	
        	else if (source==b_connexion)
        	{
        		String TextLogin = tfLogin.getText();
        		String TextPassword = tfPassword.getText();
    		
        		logConnection = TextLogin;
        		passConnection = TextPassword;
    		
        		BDD con1 = new BDD();
        		
        		
        		if (con1.LogValid(logConnection, passConnection))
        		{
        			Principale p = new Principale(500,500);
        			p.setVisible(true);
        			this.setVisible(false);
        		}
    		
        		else
        		{
        			this.dispose();
            		new Login(300,350,"erreur");
        		}
	        
        	}
	    }
	
	public void focusGained(FocusEvent e) 
	{
		Object source=e.getSource();
		if (source == tfLogin)
		{
			tfLogin.setText("");
			}
		else if (source == tfPassword)
		{
			tfPassword.setText("");
		}
	}
	 
	@Override
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
}