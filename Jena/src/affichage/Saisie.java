/**
* @File:    Editeur.java
* @Autor:   Claudel_Adrien
* @Date:    026/05/2015
*/

package affichage;
import java.awt.*;

import javax.swing.*;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.filechooser.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.border.*;

 
public class Saisie
{
	public static void main(String[] args) 
	{ 
		EventQueue.invokeLater(new Runnable() 
		{ 
			public void run() 
			{
				 TP4 f = new TP4 ("");
				 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 f.pack();
				 f.setVisible(true);  
			 }
		 }); 
	}
}
 
class TP4 extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JTextArea jta;
    private JScrollPane jsp;
    private JMenu fichier, edition;
    private JMenuBar br; 
    private JMenuItem nouveau, ouvrir, save, copier, couper, coller;

	public TP4(String titre)
	{
		super(titre);

		JMenuBar br = new JMenuBar();
		
		JMenu fichier = new JMenu("Fichier");
		JMenu edition = new JMenu("Edition");
		
		nouveau = new JMenuItem("Nouveau");
		fichier.add(nouveau);
		nouveau.addActionListener(this);
		ouvrir = new JMenuItem("Ouvrir");
		fichier.add(ouvrir);
		ouvrir.addActionListener(this);
		JMenuItem save = new JMenuItem("Enregistrer sous");
		fichier.add(save);
		save.addActionListener(this);
		
		copier = new JMenuItem("Copier");
		edition.add(copier);
		copier.addActionListener(this);
		couper = new JMenuItem("Couper");
		edition.add(couper);
		couper.addActionListener(this);
		coller = new JMenuItem("Coller");
		coller.addActionListener(this);
		edition.add(coller);
		
        br.add(fichier);
        br.add(edition);

		
        jta = new JTextArea(30,30);
        jta.setEditable(true); // la zone est eÌ�ditable
        jta.setLineWrap(true); // le texte passe aÌ€ la ligne automatiquement jsp= new JScrollPane(jta);//association de la zone de texte avec unascenseur
        
        setJMenuBar(br);

        this.add(jta);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();

		if(source.equals(this.copier))
			{
				jta.copy();
			}
		if(source.equals(couper))
			{
				jta.cut();
			}
		if(source.equals(coller))
			{
				jta.paste();
			}
		if(source.equals(nouveau))
			{
				jta.setText("");
			}
		
		
		
		
		if(e.getActionCommand().equals("Ouvrir")){
            JFileChooser filechoose = new JFileChooser();
            filechoose.setCurrentDirectory(new File("."));
            String approve = new String("OUVRIR");
            String monFichier= null; 
            int resultatOuvrir = filechoose.showDialog(filechoose,approve); 
            
            if(resultatOuvrir == filechoose.APPROVE_OPTION)
            {   
               monFichier = filechoose.getSelectedFile().toString();
               try
               {
            FileInputStream fis = new FileInputStream(monFichier);
            int n; 
            while ((n = fis.available())> 0){ 
            	byte[] b = new byte[n];
            	int result = fis.read(b); 
            	if (result == -1) break; 
            	String s = new String(b);
            	jta.setText(s); 
            	}
            }
    catch (Exception err) 
               {;}
               }
            }
    

		
		
		if(source.equals(this.save)){
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser filechoose = new JFileChooser();
					filechoose.setCurrentDirectory(new File("."));     
					String approve = new String("ENREGISTRER");                
					int resultatEnregistrer = filechoose.showDialog(filechoose,approve); 
					if (resultatEnregistrer ==JFileChooser.APPROVE_OPTION) { 
						String monFichier= new String(filechoose.getSelectedFile().toString());
						if(monFichier.endsWith(".txt")|| monFichier.endsWith(".TXT")) {;}
						else ;{
							try
							{ 
								FileWriter lu = new FileWriter(monFichier);
								BufferedWriter out = new BufferedWriter(lu);
								out.write(jta.getText()); 
								out.close(); 
							} 
							catch (IOException er) {;}
						}
					}
    
				}
			}
			);
		}
			
}}


    //proposer une arborescence
    //placer composant girdBagLayout
