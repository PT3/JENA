import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class testlayout extends JFrame implements ActionListener , KeyListener
{
	
	private JPanel mainPanel, userPanel, chatPanel, onlinePanel;
	private JSplitPane topPanel, splitPaneHautBas;
	private JTextArea userText;
	private JScrollPane scrollPane;

	//Boolean de test Shift 
	private boolean testShift;
	
	public testlayout()
	{
		//Définition du onlinePanel
		onlinePanel = new JPanel(new FlowLayout());
		onlinePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		onlinePanel.setPreferredSize(new Dimension(150,0));
		onlinePanel.setBackground(Color.red);
		
		//Définition du chatPanel
		chatPanel= new JPanel();
		chatPanel.setPreferredSize(new Dimension(350, 400));
		chatPanel.setBackground(Color.green);
		topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,onlinePanel, chatPanel);
		
		//Définition du userPanel
		userPanel= new JPanel(new FlowLayout());
		userText = new JTextArea(5,50);//Hauteur puis Largeur
		userText.setBackground(Color.black);
		scrollPane = new JScrollPane(userText);
		userPanel.add(scrollPane);
		userPanel.setBackground(Color.blue);
		
		//Définition des Listener
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
				
				userText.setBackground(Color.yellow);
			}
		});
	}
	
	/*Méthode main*/
	public static void main(String[] args) 
	{
		testlayout frame = new testlayout();
	}
	
	/*Méthode permettant d'éxecuter une action lorsqu'on appuie sur le bouton envoyer*/
	public void actionPerformed(ActionEvent e) 
	{
		userText.setText("test");
	}
	
	/* Méthode permettant d'éxecuter une action lorsqu'on tape la touche ENTER */
    public void keyTyped(KeyEvent e) 
    {
    	
    }
    
	/* Méthode permettant d'éxecuter une action lorsqu'on presse la touche ENTER */
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
				userText.setText("aaaa");
			}
			else
			{
				userText.setText(userText.getText()+"\n");
			}
		}
		
		/*
		int test=e.getKeyCode();
		if (test==10)
		{
			
			//int cpt=e.get
			//if(){}
		}*/
	}
	
	/* Méthode permettant d'éxecuter une action lorsqu'on relâche la touche ENTER */
	public void keyReleased(KeyEvent e) 
	{
	
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)
		{
			testShift = false;
		}
	}
}
	