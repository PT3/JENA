package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

import affichage.Message;


/**
 * Fenêtre Principale, contient le chat
 * @author Julien
 */
public class Principale extends JFrame implements ActionListener , KeyListener
{
	/*private JButton send;
	private JTextField mess;
	private JPanel connecte, chat, message,global,chatMess;*/ 
	
	protected JPanel mainPanel, userPanel, chatPanel, onlinePanel, optionPanel;
	private JSplitPane topPanel, splitPaneHautBas;
	protected JTextArea userText;
	private JButton selecSmilley,selecColor;
	protected JScrollPane scrollPane,chatScroll;
	protected boolean testAlternance;
	protected Insets c1,c2;
	protected SelColor color;
	protected ArrayList<Integer> listRetourligne;
	protected int mess = 0;
	private boolean testShift;
	private Socket socket;
	private Thread tRecep;
	private BufferedReader in;
	private String logUser;
	private PrintWriter out = null;  
	
	/**
	 * 
	 * @param x: Largeur de la fenêtre
	 * @param y : Hauteur de la fenêtre
	 * @param socket: Connexion au serveur
	 * @param in : Entrée des messages 
	 * @param login : Login de l'utilisateur ayant la fenêtre ouverte
	 */
	public Principale(int x, int y,Socket socket,BufferedReader in,String login)
	{
		this.color =new SelColor();
		this.socket=socket;
		this.in=in;
		listRetourligne = new ArrayList<Integer>();
		logUser=login;
		
		//Définition du onlinePanel
		onlinePanel = new JPanel();
		onlinePanel.setBackground(new Color(189,190,240));
		onlinePanel.setPreferredSize(new Dimension(150,0));
		
		//Ajout insets
		c2= new Insets(5,100,10,0);
		c1= new Insets(5,0,10,100);
		testAlternance=true;
		
		//Définition du chatPanel
		chatPanel= new JPanel();
		chatPanel.setPreferredSize(new Dimension(350, 200));
		chatPanel.setBackground(new Color(237,243,255));
		chatScroll = new JScrollPane(chatPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,onlinePanel, chatScroll);
		topPanel.setAutoscrolls(true);
		
		//Définition du optionPanel
		
		optionPanel = new JPanel(new FlowLayout());
		optionPanel.setBackground(new Color(136,206,227));
		optionPanel.setPreferredSize(new Dimension(800,40));
		
		selecSmilley = new JButton("Smilley");
		selecSmilley.setBackground(new Color(189,190,240));
		selecColor = new JButton("Couleur");
		selecColor.setBackground(new Color(189,190,240));
		
		optionPanel.add(selecColor);
		optionPanel.add(selecSmilley);
		
		//DÃ©finition du userPanel
		
		userPanel= new JPanel(new BorderLayout());
		userText = new JTextArea(5,50);//Hauteur puis Largeur
		userText.setBackground(new Color(136,206,227));
		userText.setBorder(BorderFactory.createMatteBorder(4,4,4,4, new Color(105,185,209)));
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
		this.setBackground(new Color(49,232,235));
		/**
		 * Réaffectation de la croix (quitter)
		 */
		this.addWindowListener(new java.awt.event.WindowAdapter() 
		{
			 public void windowClosing(java.awt.event.WindowEvent windowEvent)
			 {
				 out.println("quit");     // Saisi du login et placer dans le buffer
		         out.flush();     
			 }
		});

		/**
		 * Vérifie les modifications de tailles de la fenêtre
		 */
		addComponentListener(new ComponentAdapter() 
		{

			public void componentResized(ComponentEvent e) 
			{
				//System.out.println("Fenetre modifier");
				Dimension dim = getSize();
				int h = dim.height;
				int w = dim.width;	
				userText.setSize(new Dimension(w,h/5));
				scrollPane.setSize(new Dimension(w,h/5));
				optionPanel.setSize(new Dimension(w,40));
				validate();
			}
		});
		tRecep=new Thread(new Reception(in,this));
		tRecep.start();

	}
	/**
	 * Gestion de l'appui sur les boutons, seul Couleur est fonctionnel
	 */
	public void actionPerformed(ActionEvent e)
	{	
		Object source = e.getSource();
		
		if(source.equals(selecColor))
		{
			String c = userText.getText();
			JOptionPane.showInputDialog(color);
		}
	}
	
	/**
	 * Gestion de l'appui sur les touches , SHIFT + ENTREE = Saut de ligne , ENTREE = Envoie
	 */
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
   // Envoyeur
			    
		        // Initialisation de l'envoyeur
		        try {
					out = new PrintWriter(socket.getOutputStream());
				} 
		        catch (IOException e2) 
		        {e2.printStackTrace();}
		        
		        // Initialisation du receveur
		        try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					} 
		        catch (IOException e2)
		        	{e2.printStackTrace();}

		            out.println(userText.getText());     // Saisi du login et placer dans le buffer
		            out.flush();        				
				userText.setText("");
				e.consume();
			}
			else
			{
				userText.setText(userText.getText()+"\n");
			}
		}
	}
	
	/**
	 * Gestion des touches relachés , indique quand Shift est relaché
	 */
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
	
	/**
	 * Reçoit et gère les messages reçus
	 * @param message: Message reçu (String)
	 */
	public void reception(String message)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = c.REMAINDER;
		Message m  = null;
		JScrollBar sb = chatScroll.getVerticalScrollBar();
		sb.setValue(sb.getMaximum());
		String test[]= message.split(" ");
		if (test[0].equals(logUser))
		{
			m = new Message(message,color.getColor(),500,new Color(136,206,227));
			System.out.println(message);
			c.insets=c1;
			testAlternance = false;
		}
		else
		{
			m=new Message(message,color.getColor(),500,new Color(236,158,255));
			c.insets=c2;
			testAlternance = true;
		}
		//m = new Message(userText.getText(),color.getColor(), listRetourligne,500,Color.blue);
		listRetourligne.clear();
		mess+=m.getHeight()+5;
		chatPanel.add(m,c);
		chatPanel.validate();
		chatPanel.updateUI();;
		
		if (mess>chatPanel.getHeight())
		{
			chatPanel.setPreferredSize(new Dimension(chatPanel.getWidth(),chatPanel.getHeight()+(mess-chatPanel.getHeight())+5));
		}
		chatPanel.repaint();
	}
	

}
		