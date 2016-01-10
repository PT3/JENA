package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

public class Message extends JPanel
{
	private String texte;
	private int x,y;
	private Color color;
	private ArrayList<String> listMessages;
	private ArrayList<String> listSmiley;
	private int largeur;
	private Color colorBack;
	
	public Message(String a, Color c, ArrayList<Integer> list,int la,Color colora)
	{
		colorBack = colora;
		texte = a;
		
		color = c;
		listMessages = new ArrayList<String>();
		listSmiley = new ArrayList<String>();
		
		remplissageListeSmiley();
		
		largeur = la;
		for (int i = 0, j = 0; i < list.size(); i++) 
		{
			System.out.println(texte.substring(j,list.get(i)));
			listMessages.add(texte.substring(j,list.get(i)));
			j = list.get(i);
		}
		
		x = (int)largeur;
		y = listMessages.size()*20;
		setPreferredSize(new Dimension(x,y));
	}
	
	private void remplissageListeSmiley()
	{
		listSmiley.add(":S");
		listSmiley.add(";)");
		listSmiley.add(":)");
		listSmiley.add(":D");
		listSmiley.add("é_è");
		listSmiley.add(":è_é");
		listSmiley.add("o_o");
		listSmiley.add(":X");
		listSmiley.add(":P");
		listSmiley.add(":'");
		listSmiley.add(":O");
		listSmiley.add(":t_t");
		listSmiley.add(":o");
		listSmiley.add(":(");
	}

	public void paintComponent(Graphics g)
	{

		g.setColor(colorBack);
		g.fillRect(0, 0, x, y);
		System.out.println(color);
		g.setColor(color);
		for (int i = 0; i < listMessages.size(); i++) 
		{
			String m = listMessages.get(i);
			
			String[] tab = m.split(" ");
			
			for (int j = 0; j < tab.length; j++) 
			{
				for (int j2 = 0; j2 < listSmiley.size(); j2++)
				{
					
					if(tab[j].equals(listSmiley.get(j2)))
					{
						System.out.println("TTTTTTEEEEEEEEEEEEEEEEESSSSSSSSSTTTTTTTTTTTTTTTTT");
						System.out.println(tab[j]);
						System.out.println(listSmiley.get(j2));
					}
						
				}
			}
			
			g.drawString(listMessages.get(i), 5, 15+20*i);
			System.out.println(listMessages.get(i));
			System.out.println(5*i);
		}
	}
	
	public String getText()
	{
		return texte;
	}
	
}
