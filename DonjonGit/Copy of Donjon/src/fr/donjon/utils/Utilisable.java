package fr.donjon.utils;

import java.awt.Image;

import fr.donjon.classes.Personnage;


/**
 * 
 * Definit une classe qui peut etre utilisée (objet, arme,..)
 * 
 * @author Baptiste
 *
 */
public interface Utilisable {

	
	/**
	 * Fonction utiliser de l'objet, lance son utilisation sur le personnage
	 * 
	 * @param p Le personnage utilisant l'objet
	 * @return	L'utilisation (nb de potions consommés ou autres donnée)
	 */
	public int utilise(Personnage p);
	
	/**
	 * Allow the object to be drawn somewhere by sending it's representation (icon, image,...)
	 * @param g
	 */
	public Image getIcon();
	
}
