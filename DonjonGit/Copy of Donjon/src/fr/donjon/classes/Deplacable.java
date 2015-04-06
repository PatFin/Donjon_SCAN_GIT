// /!\ Classe à compléter

package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Alexandre
 *
 */
public abstract class Deplacable extends Objet {
	
	Vecteur vvitesse;
	int vitDeplacement;
	
	/**
	 * 
	 * @param ax
	 * @param ay
	 * @param longueur
	 * @param largeur
	 * @param nom
	 * @param ofArm
	 * @param offCol
	 * @param toDisplay
	 * @param vvitesse		Vecteur vitesse (unitaire!)
	 * @param vitd			(Norme du vecteur)
	 */
	public Deplacable(int ax, int ay,int longueur,int largeur, String nom, Rectangle offArm, Rectangle offCol, boolean toDisplay,
			Vecteur vvitesse, int vitd) {
		
		super(ax, ay, longueur, largeur, nom, offArm, offCol, toDisplay);
		
		this.vvitesse = vvitesse;
		this.vitDeplacement = vitd;
	}
	
	/**
	 * Mise a jour du personnage et de son arme selon le temps ecoulé
	 * 
	 * @param t Temps écoule en ms
	 */
	
	/**
	 * Dessin du personnege et de son arme
	 */
	abstract public void draw(long t, Graphics g);

	public void setLocation(int x, int y){
		image.setLocation(x, y);
		collisionArmes.setLocation(image.x + offArm.x, image.y + offArm.y);
		collisionDecor.setLocation(image.x + offCol.x, image.y + offCol.y);
	}
	
	@Override
	public void update(long t){
		//Maj des rectangles pour les objets deplacables

	}
}
