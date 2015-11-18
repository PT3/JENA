package iGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Inscription extends JFrame implements ActionListener
{
	private JPanel mainPanel,inscriptionPanel;
	private JTextField tfLogin,tfEmail;
	private JPasswordField tfPassword,tfConfirmPassword;
	private JLabel lLogin,lPassword,lConfirmPassword,lEmail;
	private JButton bValider;
	
	public Inscription(int x,int y)
	{
		super("Inscription");
		
		inscriptionPanel = new JPanel( new GridLayout(9,1));
		lLogin = new JLabel("Login(*)");
		lPassword = new JLabel("Password(*)");
		lConfirmPassword = new JLabel("Confirm Password(*)");
		lEmail = new JLabel("Email :");
		tfLogin = new JTextField("",20);
		tfPassword = new JPasswordField(20);
		tfConfirmPassword = new JPasswordField(20);
		tfEmail = new JTextField("",20);
		
		bValider = new JButton("Valider");
		bValider.addActionListener(this);
		
		inscriptionPanel.add(lLogin);
		inscriptionPanel.add(tfLogin);
		
		inscriptionPanel.add(lPassword);
		inscriptionPanel.add(tfPassword);
		
		inscriptionPanel.add(lConfirmPassword);
		inscriptionPanel.add(tfConfirmPassword);
		
		inscriptionPanel.add(lEmail);
		inscriptionPanel.add(tfEmail);
		
		inscriptionPanel.add(bValider);
		
		mainPanel = new JPanel( new BorderLayout(4, 4));
		mainPanel.add(inscriptionPanel);
		
		this.add(mainPanel);
		setPreferredSize(new Dimension(x,y));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//POPO Requêtes 
		
	}
}
