package iGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.NoRouteToHostException;

import javax.swing.*;

import iGraphique.Message;


public class Principale extends JFrame implements ActionListener , KeyListener
{
	/*private JButton send;
	private JTextField mess;
	private JPanel connecte, chat, message,global,chatMess;*/ 
	
	private JPanel mainPanel, userPanel, chatPanel, onlinePanel, optionPanel;
	private JSplitPane topPanel, splitPaneHautBas;
	private JTextArea userText;
	private JButton selecTaillePolice,selecSmilley,selecColor;
	private JScrollPane scrollPane;
	private boolean testAlternance;
	private Insets c1,c2;

	//Boolean de test Shift 
	private boolean testShift;
	
	Principale(int x, int y)
	{
		//Définition du onlinePanel
		onlinePanel = new JPanel(new FlowLayout());
		onlinePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		onlinePanel.setPreferredSize(new Dimension(150,0));
		onlinePanel.setBackground(Color.red);
		
		//Ajout insets
		c1= new Insets(0,0,0,0);
		c2= new Insets(20,50,0,0);
		testAlternance=true;
		
		//Définition du chatPanel
		chatPanel= new JPanel();
		chatPanel.setPreferredSize(new Dimension(350, 400));
		chatPanel.setBackground(Color.green);
		topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,onlinePanel, chatPanel);
		
		
		//Définition du optionPanel
		
		optionPanel = new JPanel(new FlowLayout());
		optionPanel.setPreferredSize(new Dimension(800,40));
		
		selecTaillePolice = new JButton("Police");
		selecSmilley = new JButton("Smilley");
		selecColor = new JButton("Couleur");
		
		optionPanel.add(selecColor);
		optionPanel.add(selecTaillePolice);
		optionPanel.add(selecSmilley);
		
		//DÃ©finition du userPanel
		userPanel= new JPanel(new FlowLayout());
		
		userText = new JTextArea(5,50);//Hauteur puis Largeur
		userText.setBackground(Color.black);
		userText.add(optionPanel,BorderLayout.NORTH);
		scrollPane = new JScrollPane(userText);
		userPanel.add(scrollPane,BorderLayout.SOUTH);
		userPanel.setBackground(Color.blue);
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		//DÃ©finition des Listener
		userText.addKeyListener(this);
	
		//ajout dans le mainPanel
		mainPanel = new JPanel( new BorderLayout(4, 4));
		splitPaneHautBas = new JSplitPane(JSplitPane.VERTICAL_SPLIT,topPanel, userPanel);
		mainPanel.add(splitPaneHautBas,BorderLayout.CENTER);
		
		//proprietes de la JFrame
		setTitle("JENA");
		this.add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);
		
		addComponentListener(new ComponentAdapter() 
		{
			@Override
			public void componentResized(ComponentEvent e) 
			{
				//System.out.println("Fenetre modifier");
				Dimension dim = getSize();
				int h = dim.height;
				int w = dim.width;
				System.out.println(h);
				System.out.println(w);
				userText.setSize(new Dimension(w,h/5));
				scrollPane.setSize(new Dimension(w,h/5));
				optionPanel.setSize(new Dimension(w,40));
				
				userText.setBackground(Color.yellow);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e)
	{
		userText.setText("test");
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)
		{
			testShift = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (!testShift)
			{
				//userText.setText("aaaa");
				Message m = new Message(userText.getText());
				
				GridBagConstraints c = new GridBagConstraints();
				c.gridwidth = c.REMAINDER;
				
				if (testAlternance)
				{
					c.insets=c1;
					testAlternance = false;
				}
				else
				{
					c.insets=c2;
					testAlternance = true;
				}
				chatPanel.add(m,c);
				chatPanel.validate();
				chatPanel.updateUI();
				chatPanel.repaint();
				userText.setText("");
			}
			else
			{
				userText.setText(userText.getText()+"\n");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
	
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)
		{
			testShift = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
