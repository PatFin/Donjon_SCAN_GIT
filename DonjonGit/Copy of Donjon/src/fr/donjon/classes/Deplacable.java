package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.classes.cases.Case;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Alexandre
 *
 */
public abstract class Deplacable extends Objet {
	
	public Vecteur vvitesse;
	public int vitDeplacement;
	
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

	/**
	 * Places the object at a certain position given in pixels.
	 * @param x horizontal position.
	 * @param y vertical position.
	 */
	public void setLocation(int x, int y){
		image.setLocation(x, y);
		collisionArmes.setLocation(image.x + offArm.x, image.y + offArm.y);
		collisionDecor.setLocation(image.x + offCol.x, image.y + offCol.y);
	}
	
	/**
	 * Places the object at a certain position given as a vector.
	 * @param v the coordinates of the tile the character should be placed on.
	 */
	public void setLocation(Vecteur v){
		this.setLocation((int)v.x*Case.TAILLE, (int)v.y*Case.TAILLE);
	}
	
	@Override
	public void update(long t){

	}
}
