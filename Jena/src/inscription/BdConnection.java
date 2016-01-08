package inscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BdConnection {
	
	public static String BdLog=null;
	public static String BdPass=null;
	public String log;
	public String pass;
	
	public BdConnection(String a, String b) {
		
		this.log = Login.logConnection;
		this.pass = Login.passConnection;
		
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
					.executeQuery("SELECT login, password FROM user WHERE login = '" + log +"'");
			
			while (res.next()) {
				BdLog=res.getString("login");
				BdPass=res.getString("password");
			}
			
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
