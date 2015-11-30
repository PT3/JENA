/**
 * Login.java
 * @date 06/11/2015
 * @author Julien
 */

package iGraphique;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Login extends JFrame implements ActionListener, FocusListener
{
	/**
	 * Boutton d'inscription et d'invitation
	 */
	private JButton b_inscription,b_invite,b_connexion;
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
	private JLabel lLogin,lPassword,lPsLoose;
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
	 */
	Login(int x, int y)
	{
		super("Connexion : ");
		setPreferredSize(new Dimension(x,y));
		b_inscription = new JButton("Inscription");
		b_inscription.addActionListener(this);
		b_invite = new JButton("Invité");
		b_invite.addActionListener(this);
		b_connexion = new JButton("Connexion");
		b_connexion.addActionListener(this);
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		tfLogin = new JTextField("Obligatoire",20);
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
	 * Ajout des différents éléments dans la fenètre
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
		bouton.add(b_invite);
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
	 * Action liées aux cliques boutons
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
	        	Inscription i = new Inscription(200,300);
	        	i.setVisible(true);
	        	this.setVisible(false);
	        }
	        else if(source==b_invite)
	        {
	        	Principale p = new Principale(500,500);
	        	p.setVisible(true);
	        	this.setVisible(false);
	        }
	        
	        String TextLogin = tfLogin.getText();
	        String TextPassword = tfPassword.getText();
	        
	        String url = "jdbc:mysql://localhost:3306/";
	        
	        /**
	         * The MySQL user.
	         */
	        String user = "root";
	        
	        /**
	         * Password for the above MySQL user. If no password has been 
	         * set (as is the default for the root user in XAMPP's MySQL),
	         * an empty string can be used.
	         */
	        String bddpassword = "";
	        
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            Connection con = DriverManager.getConnection(url, user, bddpassword);
	            
	            Statement stt = con.createStatement();
	            
	            /**
	             * select a database for use. 
	             */
	            stt.execute("USE jena");
	            
	            /**
	             * Query people entries with the lname 'Bloggs'
	             */
	            ResultSet res = stt.executeQuery("SELECT * FROM user WHERE login = 'popotheone'");
	            
	            /**
	             * Iterate over the result set from the above query
	             */
	            while (res.next())
	            {
	                System.out.println(res.getString("login") + " " + res.getString("mail"));
	            }
	            System.out.println("");
	            
	            /**
	             * Same as the last query, but using a prepared statement. 
	             * Prepared statements should be used when building query strings
	             * from user input as they protect against SQL injections
	             */
	            PreparedStatement prep = con.prepareStatement("SELECT * FROM user WHERE login = ?");
	            prep.setString(1, "popotheone");
	            
	            res = prep.executeQuery();
	            while (res.next())
	            {
	                System.out.println(res.getString("login") + " " + res.getString("mail"));
	            }
	            
	            /**
	             * Free all opened resources
	             */
	            res.close();
	            stt.close();
	            prep.close();
	            con.close();
	            
	        }
	        
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
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
