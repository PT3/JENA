package inscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BdInscription
{
	
	public String log;
	public String m;
	public String pass;
	public String pass2;
	
	public BdInscription(String a, String b, String c, String d) {
		
		this.m = Inscription.m;
		this.log = Inscription.log;
		this.pass = Inscription.pass;
		this.pass2 = Inscription.pass2;
		
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

			/**
			 * Add entries to the example table
			 */
			stt.executeUpdate("INSERT INTO user (rang, login, mail, password) VALUES"
					+ "(0,'" + log + "','" + m + "','" + pass + "')");

			/**
			 * Free all opened resources
			 */

			stt.close();
			con.close();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
