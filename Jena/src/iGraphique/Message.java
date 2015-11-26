package iGraphique;

import java.awt.Color;

import javax.swing.*;

public class Message extends JPanel
{
	String texte;
	
	public Message(String a)
	{
		texte=a;
		//COUCOU 
		//ROUGE 
		//TAILLE 20
		
		//red-20-coucou
		
		//cou-ze-ezc-ezc-ezc-
		//red-20-coucou
		
		//2 premisere case du tableau 
		//tab[0] et tab[1]
		
		
		if(texte.substring(texte.length()-2).equals(":)"))
		{
			texte += "SMILEY :)";
			JLabel image = new JLabel( new ImageIcon( "mon_image.jpg"));
			
		}
		
		
		String[] tab = texte.split("/ ");//SYMBOLE de SEPARATION !!!!!!!!
		JLabel l;
		
		//message va contenir le message 
		/*
		String message = "";
		for (int i = 2; i < tab.length; i++) 
		{
			message +=tab[i];
		}*/
		
		
		if(tab.length > 1)
		{
			l = new JLabel(tab[1]);
			
			
			if(tab[0].equals("red"))
			{
				l.setForeground(Color.RED);
			}
			else if(tab[0].equals("yellow"))
			{
				l.setForeground(Color.YELLOW);
			}
			else if(tab[0].equals("black"))
			{
				l.setForeground(Color.BLACK);
			}
			else if(tab[0].equals("blue"))
			{
				l.setForeground(Color.BLUE);
			}
			else if(tab[0].equals("green"))
			{
				l.setForeground(Color.GREEN);
			}
			else if(tab[0].equals("orange"))
			{
				l.setForeground(Color.ORANGE);
			}
			else if(tab[0].equals("gray"))
			{
				l.setForeground(Color.GRAY);
			}
			else if(tab[0].equals("cyan"))
			{
				l.setForeground(Color.CYAN);
			}
			else if(tab[0].equals("magenta"))
			{
				l.setForeground(Color.MAGENTA);
			}
			else if(tab[0].equals("pink"))
			{
				l.setForeground(Color.PINK);
			}

		}
		else
		{
			l = new JLabel(texte);
		}
		
		
		add(l);
	}
}
