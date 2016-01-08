package inscription;

public class MailVerif {

	public static boolean validateEmailAddress(String votreEmail){
		EmailValidator emailValidator = EmailValidator.getInstance();
		return emailValidator.isValid(votreEmail);
		}
}
