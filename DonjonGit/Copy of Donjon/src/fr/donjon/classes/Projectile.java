/**
 * 
 */
package fr.donjon.classes;

import java.awt.Rectangle;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class Projectile extends Deplacable {

	public boolean living;
	int degats;
	Animation anim;
	Type type;

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
	 * @param vvitesse
	 * @param vitd
	 * @param type		Le projectile peu etre ami ou ennemi
	 * @param degats	Degats du projectile
	 */
	public Projectile(int ax, int ay, int longueur, int largeur, String nom, 
			Rectangle collisionArmes, Rectangle collisionDecor,boolean toDisplay, 
			Vecteur vvitesse, int vitd,
			Type type, int degats) {

		super(ax, ay, longueur, largeur, nom, collisionArmes, collisionDecor,
				toDisplay, vvitesse, vitd);
		
		this.type = type;
		this.degats = degats;
		this.living = true;
	}

	/**
	 * Doit affecter le personnage avec des dégats spécifiques au projectile.
	 * @param p le personnage touché par le projectile
	 */
	public abstract void inCollision(Personnage p);


	@Override
	abstract public void update(long t);


	public boolean enCollision(Rectangle r) {
		return super.enCollision(r);
	}



}
