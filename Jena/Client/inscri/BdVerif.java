package inscri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import iGraphique.Login;
import iGraphique.Inscription;



public class BdVerif {
	
	public static String BdLog=null;
	public static String BdPass=null;
	public static String BdMail=null;
	
	public String pass;
	public String pass2;
	
	public BdVerif() 
	{
				
	}
	
	public boolean LogValid(String loginlog, String pass)
	{

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
					.executeQuery("SELECT login, password FROM user WHERE login = '" + loginlog +"'");
			
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
		
		
		
		boolean isEqualPass = pass.equals(BdPass);
		
		return isEqualPass;
	}
	
	public boolean EqualPassword(String a, String b, String c, String d) 
	{	
		this.pass = Inscription.pass;
		this.pass2 = Inscription.pass2;
		
		
		boolean isEqualInscription = (pass.equals(pass2));
		boolean isEqualNullPass = (pass.equals(""));
		boolean isEqualNullConfPass = (pass.equals(""));
		
		if(isEqualInscription && (!isEqualNullPass || !isEqualNullConfPass))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean EqualLogin(String a)
	{
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
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection(url, user, bddpassword);

			Statement stt = con.createStatement();

			/**
			 * select a database for use.
			 */
			stt.execute("USE jena");

			ResultSet res = stt
					.executeQuery("SELECT login FROM user WHERE login = '" + a +"'");
			
			while (res.next()) {
				BdLog=res.getString("login");
			}
			/**
			 * Free all opened resources
			 */

			stt.close();
			con.close();

		}

		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		boolean IsEqualLog = a.equals(BdLog);
		
		return IsEqualLog;
	}
	
	public boolean EqualMail(String a)
	{
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
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection(url, user, bddpassword);

			Statement stt = con.createStatement();

			/**
			 * select a database for use.
			 */
			stt.execute("USE jena");

			ResultSet res = stt
					.executeQuery("SELECT login FROM user WHERE login = '" + a +"'");
			
			while (res.next()) {
				BdMail=res.getString("mail");
			}
			/**
			 * Free all opened resources
			 */

			stt.close();
			con.close();

		}

		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		boolean IsEqualLog = a.equals(BdMail);
		
		return IsEqualLog;
	}
}
