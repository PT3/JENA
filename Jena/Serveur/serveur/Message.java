package serveur;

/**
 * Classe qui servira à normaliser les échanges 
 * @author esteban
 *
 */
public class Message
{
	private String type;// Can be sign in , sign up , message
	private String sender;//Username
	private String content;//Message, password or registration information separated by Â§
	private String recipient;//SERVER or recipient for PM 

	/**
	*Create a new message , it will be send and treated by the server, please verify that param aren't empty !
	*@param type // Can be sign in , sign up , message
	*@param sender //Sender Username
	*@param content //Message, password or registration information separated by Â§
	*@param recipient //SERVER or recipient for PM 
	*/
	public Message(String type,String sender,String content,String recipient)
	{
		this.type=type;
		this.sender=sender;
		this.content=content;
		this.recipient=recipient;
	}
	
	/**
	 * Crée un message à partir d'un tableau contenant Type,Envoyeur,Contenu,Destinataire
	 * @param tab (String[])
	 */
	public Message(String[] tab)
	{
		this(tab[0],tab[1],tab[2],tab[3]);
	}
	/**
	 * Renvoie le type du Message (Kick, Message,Connexion....)
	 * @return (String) Type du message
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Renvoie l'envoyeur du message
	 * @return (String) Envoyeur
	 */
	public String getSender()
	{
		return sender;
	}
	
	/**
	 * Renvoie le contenu du Message
	 * @return (String) Contenu du Message
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Renvoie le destinataire
	 * @return (String)Destination du Message
	 */
	public String getRecipient()
	{
		return recipient;
	}
	/**
	 * Transforme un String en Message, les parties doivent être séparées par un ¤
	 * @param in
	 * @return
	 */
	public Message FromString(String in)
	{
	 	String[] tmp = in.split("¤");
	 	return new Message(tmp);
	}
	/**
	 * Transforme un Message en String
	 */
	public String toString()
	{
		return (type+"Â¤"+sender+"Â¤"+content+"Â¤"+recipient);
	}
}