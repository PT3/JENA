package iGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Principale extends JFrame implements ActionListener
{
	private JButton send;
	private JTextField mess;
	private JPanel connecte, chat, message,global,chatMess;
	Principale(int x, int y){
		super("JENA : ");
		setPreferredSize(new Dimension(x,y));
		connecte = new JPanel();
		chat = new JPanel();
		message = new JPanel();
		global = new JPanel();
		chatMess = new JPanel();
		mess = new JTextField("Entré votre message ......");
		send = new JButton("Send");
		message.add(mess);
		message.add(send);
		chat.setBackground(Color.WHITE);
		chatMess.add(chat);
		chatMess.add(message);
		connecte.setPreferredSize(new Dimension(this.getX()/3,this.getY()));
		global.add(connecte, BorderLayout.WEST);
		global.add(chatMess);
		
		this.add(global);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
