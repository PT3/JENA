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
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.List;

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
	private JButton selecSmilley,selecColor;
	private JScrollPane scrollPane,chatScroll;
	private boolean testAlternance;
	private Insets c1,c2;
	private SelColor color;
	private ArrayList<Integer> listRetourligne;
	private int lastMessageWidth;
	private int mess = 0;
	//Boolean de test Shift 
	private boolean testShift;
	
	public Principale(int x, int y)
	{
		this.color =new SelColor();
		
		listRetourligne = new ArrayList<Integer>();
		
		//Définition du onlinePanel
		onlinePanel = new JPanel();
		onlinePanel.setPreferredSize(new Dimension(150,0));
		
		//Ajout insets
		c2= new Insets(5,100,10,0);
		c1= new Insets(5,0,10,100);
		testAlternance=true;
		
		//Définition du chatPanel
		chatPanel= new JPanel();
		chatPanel.setPreferredSize(new Dimension(350, 200));
//		chatPanel.setBackground(Color.green);
		chatScroll = new JScrollPane(chatPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,onlinePanel, chatScroll);
		topPanel.setAutoscrolls(true);
		
		//Définition du optionPanel
		
		optionPanel = new JPanel(new FlowLayout());
		optionPanel.setPreferredSize(new Dimension(800,40));
		
		selecSmilley = new JButton("Smilley");
		selecColor = new JButton("Couleur");
		
		optionPanel.add(selecColor);
		optionPanel.add(selecSmilley);
		
		//DÃ©finition du userPanel
		
		userPanel= new JPanel(new BorderLayout());
		userText = new JTextArea(5,50);//Hauteur puis Largeur
		userPanel.add(optionPanel,BorderLayout.NORTH);
		scrollPane = new JScrollPane(userText);
		userPanel.add(scrollPane,BorderLayout.CENTER);
		
		//DÃ©finition des Listener
		userText.addKeyListener(this);
		selecColor.addActionListener(this);
		
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
				validate();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e)
	{
		userText.setText("test");
		
		Object source = e.getSource();
		
		if(source.equals(selecColor))
		{
			String c = userText.getText();
			JOptionPane.showInputDialog(color);
		}
	}

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
				listRetourligne.add(userText.getText().length());
				Message m  = null;
				
				GridBagConstraints c = new GridBagConstraints();
				c.gridwidth = c.REMAINDER;
				
				if (testAlternance)
				{
					m = new Message(userText.getText(),color.getColor(), listRetourligne,500,Color.blue);
					c.insets=c1;
					
					testAlternance = false;
				}
				else
				{
					m = new Message(userText.getText(),color.getColor(), listRetourligne,500,Color.red);
					c.insets=c2;
					testAlternance = true;
				}
				//m = new Message(userText.getText(),color.getColor(), listRetourligne,500,Color.blue);
				listRetourligne.clear();
				
				//Permet de désactiver l'effet originelle de l'action d'un JTextArea
				e.consume();
				mess+=m.getHeight()+5;
				chatPanel.add(m,c);
				chatPanel.validate();
				chatPanel.updateUI();
				if (mess>chatPanel.getHeight())
				{
					chatPanel.setPreferredSize(new Dimension(chatPanel.getWidth(),chatPanel.getHeight()+(mess-chatPanel.getHeight())+5));
				}/* Alors là il suffit de trouver la taille en hauteur du message pour régler le PB */
				chatPanel.repaint();
				userText.setText("");
			}
			else
			{
				listRetourligne.add(userText.getText().length());
				userText.setText(userText.getText()+"\n");
			}
		}
	}

	public void keyReleased(KeyEvent e) 
	{
	
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)
		{
			testShift = false;
		}
	}
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
		