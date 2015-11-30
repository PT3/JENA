package iGraphique;

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
public class Inscription extends JFrame implements ActionListener
{
	//Instanciation des différents éléments graphiques
	private JPanel mainPanel,inscriptionPanel;
	private JTextField tfLogin,tfEmail;
	private JPasswordField tfPassword,tfConfirmPassword;
	private JLabel lLogin,lPassword,lConfirmPassword,lEmail;
	private JButton bValider;
	
	/* Constructeur de la fenêtre, prends en paramètre la longueur ( int X) et la longueur (int Y)de la fenêtre */
	public Inscription(int x,int y)
	{
		super("Inscription");
		
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

	/* Actions effectuées lorsque l'utilisateur appuie sur le bouton valider*/
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		String TextLogin = tfLogin.getText();
		String TextEmail = tfEmail.getText();
		String TextPassword = tfPassword.getText();
		String TextConfirmPassword = tfConfirmPassword.getText();
		System.out.println("Login : " + TextLogin);
		System.out.println("Email : " + TextEmail);
		//POPO Requêtes
		
		boolean isEqual = (TextPassword.equals(TextConfirmPassword));
		
		
		/* si les mdp sont égaux, affiche les mdp*/
		if(isEqual)
		{
			/* Affiche une erreur si l'un des deux mdp est vide */
			if(TextPassword.equals("") || TextConfirmPassword.equals(""))
			{
				System.out.println("Veuillez entrer et confirmer votre mot de passe");
			}
			
			else
			{
				String log = TextLogin;
				String m = TextEmail;
				String pass = TextPassword;
				
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
		             * Add entries to the example table
		             */
		            stt.executeUpdate("INSERT INTO user (rang, login, mail, password) VALUES" + 
		                    "(0,'" +log + "','" +m + "','" +pass+ "')");
		            
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
		        
		        System.out.println("Password : " + TextPassword);
				System.out.println("ConfirmPassword : " + TextConfirmPassword);
			}
		}
		
		/* si les mdp sont différents, affiche une erreur*/
		else
		{
			System.out.println("Vos mots de passe doivent correspondre");
		}
		
		/* si les mdp sont égaux, les enregistre en base de données */
		if(isEqual)
		{
			
		}
			
	}
	
}
