/**
 * 
 */
package fr.donjon.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.donjon.utils.Utilisable;


/**
 * @author Baptiste
 *
 */
public class Inventaire {

	final static int iconWidth = 20;
	final static int iconSpacing = 2;
	final static Color backColor = Color.GRAY;
	final static Color objColor = Color.LIGHT_GRAY;
	
	private int taille;
	private ArrayList<Utilisable> utilisables;
	private Personnage porteur;
	
	int boxOffsetLeft;
	int boxOffsetRight;
	
	/**
	 * Crée un inventaire avec une taille définie
	 * @param taille Taille de l'inventaire en nombre de cases
	 * @param porteur Le personnage portant l'inventaire
	 */
	public Inventaire(int taille, Personnage porteur) {
		utilisables = new ArrayList<Utilisable>();
		this.taille = taille;
		this.porteur = porteur;
	}
	
	public void draw(Graphics g){
		if(taille == 0)return;
		
		boxOffsetLeft = porteur.image.x - (taille*iconWidth + (taille+1)*iconSpacing)/2 + porteur.image.width/2;
		boxOffsetRight = porteur.image.y - 40;
		
		g.setColor(backColor);
		g.fillRect(boxOffsetLeft, boxOffsetRight, taille*iconWidth + (taille+1)*iconSpacing, iconWidth + 2*iconSpacing);
		
		g.setColor(objColor);
		for(int i = 0 ; i < taille ; i++){
			g.fillRect( boxOffsetLeft + i * iconWidth + (i+1)*iconSpacing, boxOffsetRight + iconSpacing, iconWidth, iconWidth);
			if(i < utilisables.size())g.drawImage(utilisables.get(i).getIcon(), boxOffsetLeft + i * iconWidth + (i+1)*iconSpacing, boxOffsetRight+  iconSpacing, iconWidth, iconWidth,null);
		}
		
	}
	
	/**
	 * Permet d'ajouter un utilisable a l'inventaire
	 * 
	 * @param u L'objet utilisant l'interface utilisable
	 * @return	Renvoie tru si l'objet a été ajouté, false s'il n'y a plus de place;
	 */
	public boolean addUtilisable(Utilisable u){
		
		if(utilisables.size() < taille){
			utilisables.add(u);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Permet d'utiliser un objet de l'inventaire
	 * 
	 * @param ref 	Case de l'invetaire a utiliser
	 * @return		Renvoie si l'objet a correctement été utilisé
	 */
	public boolean useUtilisable(int ref){
		
		if(ref > (utilisables.size() - 1))return false;
		
		utilisables.get(ref).utilise(porteur);
		
		return true;
	}

}
