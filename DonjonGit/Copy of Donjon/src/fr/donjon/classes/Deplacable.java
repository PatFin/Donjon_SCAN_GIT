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
	 * @param collisionArmes
	 * @param collisionDecor
	 * @param toDisplay
	 * @param vvitesse		Vecteur vitesse (unitaire!)
	 * @param vitd			(Norme du vecteur)
	 */
	public Deplacable(int ax, int ay,int longueur,int largeur, String nom, Rectangle collisionArmes, Rectangle collisionDecor, boolean toDisplay,
			Vecteur vvitesse, int vitd) {
		
		super(ax, ay, longueur, largeur, nom, collisionArmes, collisionDecor, toDisplay);
		
		this.vvitesse = vvitesse;
		this.vitDeplacement = vitd;
	}
	
	@Override
	public boolean enCollision(Rectangle r) {
		// TODO Auto-generated method stub
		return super.enCollision(r);
	}
	
	/**
	 * Mise a jour du personnage et de son arme selon le temps ecoulé
	 * 
	 * @param t Temps écoule en ms
	 */
	public abstract void update(long t);
	
	/**
	 * Dessin du personnege et de son arme
	 */
	abstract public void draw(long t, Graphics g);

}
