package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Classe qui gère l'affichage des messages dans la fenêtre Principale
 * @author Julien
 *
 */
public class Message extends JPanel
{
	private String texte;
	private int x,y;
	private Color color;
	private ArrayList<String> listMessages;
	private ArrayList<String> listSmiley;
	private ArrayList<String> listImages;
	private  ArrayList<Integer> list;
	private Color colorBack;
	
	/**
	 * Constructeur d'un Message
	 * @param message : contenu du message
	 * @param colorText : Couleur du texte
	 * @param largeur : Largeur du message 
	 * @param colorFond: Couleur de fond du message
	 */
	public Message(String message, Color colorText,int largeur,Color colorFond)
	{
		colorBack = colorFond;
		texte = message;
		color = colorText;
		listMessages = new ArrayList<String>();
		listSmiley = new ArrayList<String>();
		listImages = new ArrayList<String>();
		this.list=list;
		remplissageListeSmiley();
		remplissageListeImages();
		listMessages.add(texte);
		x = largeur;
		y = listMessages.size()*20;
		setPreferredSize(new Dimension(x,y));
		
	//Effectue la séparation entre tout les retours à la ligne dans un message 
		
	}
	/**
	 * Retourne la hauteur
	 * @return y: Hauteur
	 */
	public int getHeight()
	{
		return y;
	}
	
	/**
	 * Retourne la largeur
	 * @return y: Largeur
	 */
	public int getWidth()
	{
		return x;
	}
	
	/**
	 * Associe à listImages des smileys 
	 */
	private void remplissageListeImages() 
	{
		listImages.add(".\\ImageSmileys\\barbouille.png");
		listImages.add(".\\ImageSmileys\\complice.png");
		listImages.add(".\\ImageSmileys\\content.png");
		listImages.add(".\\ImageSmileys\\happy.png");
		listImages.add(".\\ImageSmileys\\class.png");
		listImages.add(".\\ImageSmileys\\deter.png");
		listImages.add(".\\ImageSmileys\\etonn.png");
		listImages.add(".\\ImageSmileys\\indiff.png");
		listImages.add(".\\ImageSmileys\\langue.png");
		listImages.add(".\\ImageSmileys\\peur.png");
		listImages.add(".\\ImageSmileys\\pleure.png");
		listImages.add(".\\ImageSmileys\\stup.png");
		listImages.add(".\\ImageSmileys\\triste.png");
	}

	/**
	 * Associe à ListeSmiley les Smileys gérés par le chat
	 */
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

	/**
	 * Dessin du message
	 */
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
}
