public class Message
{
	private String type;// Can be sign in , sign up , message
	private String sender;//Username
	private String content;//Message, password or registration information separated by §
	private String recipient;//SERVER or recipient for PM 

	/*
	Create a new message , it will be send and treated by the server, please verify that param aren't empty !
	*@param type // Can be sign in , sign up , message
	*@param sender //Sender Username
	*@param content //Message, password or registration information separated by §
	*@param recipient //SERVER or recipient for PM 
	*/
	public Message(String type,String sender,String content,String recipient)
	{
		this.type=type,this.sender=sender,this.content=content,this.recipient=recipient;
	}
}