package inscription;

public class EmailValidator {
	
	public static boolean validateEmailAddress(String email)
	{
		if (email == null || "".equals(email))
			return false;
		
		email = email.trim();
		
		EmailValidator ev = EmailValidator.getInstance();
		return ev.isValid(email);
	}

	private static EmailValidator getInstance() {
		// TODO Auto-generated method stub
		return validationEmailAddress;
	}
}
