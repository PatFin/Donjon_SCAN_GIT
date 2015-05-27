package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.cases.Case;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Alexandre
 *
 */
public abstract class Deplacable extends Objet {
	
	public int vitDeplacement;
	public Vecteur vvitesse;
	
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
	 * Dessin du personnege et de son arme
	 */
	@Override
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
	 * Attention! The coordinates of the vector are in pixels.
	 * To place the object on a tile in a room, use method setLocationCase(Vecteur)
	 * @param v the coordinates of the tile the character should be placed on.
	 */
	public void setLocation(Vecteur v){
		this.setLocation((int)v.x, (int)v.y);
	}
	
	/**
	 * Places the object on the tile which coordinates in the room are given by the parameter
	 * Attention! This method will place the object at the tile of coordinates given as a vector in the room.
	 * To place the object at coordinates as pixels, use method setLocation(Vecteur) or setLocation(int, int)
	 * @param v the coordinates of the tile where the object should be placed
	 */
	public void setLocationCase(Vecteur v){
		this.setLocation((int)v.x*Case.TAILLE,(int) v.y*Case.TAILLE);
	}

	@Override
	public void update(long t){

	}

}

