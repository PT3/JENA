package iGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class Message extends JPanel
{
	private String texte;
	private int x,y;
	private Color color;
	private ArrayList<String> listMessages;
	private ArrayList<String> listSmiley;
	private ArrayList<String> listImages;
	private int largeur;
	private Color colorBack;
	
	public Message(String a, Color c, ArrayList<Integer> list,int la,Color colora)
	{
		colorBack = colora;
		texte = a;
		color = c;
		listMessages = new ArrayList<String>();
		listSmiley = new ArrayList<String>();
		listImages = new ArrayList<String>();
		
		remplissageListeSmiley();
		remplissageListeImages();
		
		largeur = la;
		
	//Effectue la séparation entre tout les retours à la ligne dans un message 
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
	
	public int getHeight()
	{
		return y;
	}
	public int getWidth()
	{
		return x;
	}
	private void remplissageListeImages() 
	{
		listImages.add(".\\ImageSmileys\\barbouille.jpg");
		listImages.add(".\\ImageSmileys\\complice.jpg");
		listImages.add(".\\ImageSmileys\\content.jpg");
		listImages.add(".\\ImageSmileys\\happy.jpg");
		listImages.add(".\\ImageSmileys\\class.jpg");
		listImages.add(".\\ImageSmileys\\deter.jpg");
		listImages.add(".\\ImageSmileys\\etonn.jpg");
		listImages.add(".\\ImageSmileys\\indiff.jpg");
		listImages.add(".\\ImageSmileys\\langue.jpg");
		listImages.add(".\\ImageSmileys\\peur.jpg");
		listImages.add(".\\ImageSmileys\\pleure.jpg");
		listImages.add(".\\ImageSmileys\\stup.jpg");
		listImages.add(".\\ImageSmileys\\triste.jpg");
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
		//Set la couler de fond
		g.setColor(colorBack);
		//Dessine le fond
		g.fillRect(0, 0, x, y);

		g.setColor(color);
		//Variable qui va contenir la taille en largeur des caractere avant un smiley
		int taille= 0;
		
		//Parcour lignes apres lignes du message
		for (int i = 0; i < listMessages.size(); i++) 
		{
			String m = listMessages.get(i);
			
			//Decoupe le message avec comme séparateur les espaces pour les stocker dans un tableau.
			String[] tab = m.split(" ");
			
			//Variable stockant un texte avant un smiley
			String avant = "";
			taille = 0;
			
			//Booleen permettant de dire si au moins un smiley est présent ou non dans la ligne de texte 
			boolean testImage = false;
			//Parcour tout les mot d'une ligne
			for (int j = 0; j < tab.length; j++) 
			{
				//Variable pour savoir si on détecte un smiley
				boolean test  = false;
				
				//Recupere la position du smiley dans le tableau de mot
				int index = 0;
				
				//Parcour la liste des smiley enregistrer 
				for (int j2 = 0; j2 < listSmiley.size(); j2++)
				{
					
					//test pour savoir si le mot courant est un smiley
					if(tab[j].equals(listSmiley.get(j2)))
					{
						test = true;
						index = j2;
					}
				}
				
				//Si c'est un smiley on va ecrire le txet avant le smiley et apres l'image du smiley
				if (test)
				{
					Image im = getToolkit().getImage(listImages.get(index));
					g.drawString(avant, 5+taille, 15+20*i);
					g.drawImage(im, (int)(taille+avant.length()*5+5),20*i,20,20, null);
					testImage = true;
					
					taille = avant.length()*5+25;
					avant= "";
				}
				else
				{
					avant += tab[j]+" ";
				}
			}
			
			//Si la ligne ne comporte pas de smiley on dessine simplement le texte.
			if (!testImage)
				g.drawString(listMessages.get(i), 5, 15+20*i);
			
		}
	}
	
	public int getW()
	{
		return y;
	}
}
