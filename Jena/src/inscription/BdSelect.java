package inscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import iGraphique.Inscription;

/**
 * Affiche les infos de la bases de données pour la personne qui s'inscrit
 */
public class BdSelect
{
	
	public String log;
	
	public BdSelect() {
		
		this.log = Inscription.log;
		
		String url = "jdbc:mysql://localhost:3306/";

		/**
		 * The MySQL user.
		 */
		String user = "root";

		/**
		 * Password for the above MySQL user. If no password has been set (as is
		 * the default for the root user in XAMPP's MySQL), an empty string can
		 * be used.
		 */
		String bddpassword = "";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection(url, user, bddpassword);

			Statement stt = con.createStatement();

			/**
			 * select a database for use.
			 */
			stt.execute("USE jena");

			ResultSet res = stt
					.executeQuery("SELECT * FROM user WHERE login = '" + log +"'");

			/**
			 * Iterate over the result set from the above query
			 */
			String BdId=null;
			String BdRang=null;
			String BdLogin=null;
			String BdMail=null;
			String BdPass=null;
			while (res.next()) {
				BdId= res.getString("id");
				BdRang=res.getString("rang");
				BdLogin=res.getString("login");
				BdMail=res.getString("mail");
				BdPass=res.getString("password");
				System.out.println("azzza"+BdId+BdRang+BdLogin+BdMail+BdPass);
//				System.out.println("| id : " + res.getString("id") + " | "
//						+ "rang : " + res.getString("rang") + " | "
//						+ "login : " + res.getString("login") + " | "
//						+ "mail : " + res.getString("mail") + " | "
//						+ "password : " + res.getString("password") + " | ");
			}
			System.out.println(BdId);

			
			/**
			 * Free all opened resources
			 */
			res.close();
			stt.close();
			con.close();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
