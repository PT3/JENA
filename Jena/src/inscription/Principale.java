package iGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Principale extends JFrame implements ActionListener , KeyListener
{
	/*private JButton send;
	private JTextField mess;
	private JPanel connecte, chat, message,global,chatMess;*/ 
	private JPanel mainPanel, userPanel, chatPanel, onlinePanel;
	private JSplitPane topPanel, splitPaneHautBas;
	private JTextArea userText;
	private JScrollPane scrollPane;

	//Boolean de test Shift 
	private boolean testShift;
	
	Principale(int x, int y){
		/*super("JENA : ");
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
		this.add(global);*/
		
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
				
				//DÃ©finition du userPanel
				userPanel= new JPanel(new FlowLayout());
				userText = new JTextArea(5,50);//Hauteur puis Largeur
				userText.setBackground(Color.black);
				scrollPane = new JScrollPane(userText);
				userPanel.add(scrollPane);
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
						
						userText.setBackground(Color.yellow);
					}
				});
			}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
