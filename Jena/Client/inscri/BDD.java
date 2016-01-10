package inscri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDD 
{
	public String log;
	public String mail;
	public String pass;
	public String pass2;
	
	public void BdInscriptionConf(String a, String b, String c, String d) 
	{	
		mail = a;
		log = b;
		pass = c;
		pass2 = d;
		
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
					+ "(0,'" + log + "','" + mail + "','" + pass + "')");

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
	
	public Boolean BdSelect(String a)
	{
		
		log = a;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
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
				System.out.println(""+BdId+BdRang+BdLogin+BdMail+BdPass);
			}
			System.out.println(BdId);
			res.close();
			stt.close();
			con.close();
			
			return true;	
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean LogValid(String loginlog, String pass)
	{

		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String bddpassword = "";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager
					.getConnection(url, user, bddpassword);

			Statement stt = con.createStatement();
			stt.execute("USE jena");

			ResultSet res = stt
					.executeQuery("SELECT password FROM user WHERE login = '" + loginlog +"'");
			String BdPass=null;
			while (res.next()) {
				BdPass=res.getString("password");
			}

			stt.close();
			con.close();

			
			return  pass.equals(BdPass);
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;

	}
	
	public boolean EqualPassword(String a, String b, String pass, String pass2) 
	{	
		this.pass = pass;
		this.pass2 = pass2;
		
		
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
			String BdLog=null;
			while (res.next()) {
				BdLog=res.getString("login");
			}
			/**
			 * Free all opened resources
			 */

			stt.close();
			con.close();

			
			return a.equals(BdLog);
		}

		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return false;
		

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
			String BdMail=null;
			while (res.next()) {
				BdMail=res.getString("mail");
			}
			/**
			 * Free all opened resources
			 */

			stt.close();
			con.close();			
			return a.equals(BdMail);
		}

		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return false;
	}


}
