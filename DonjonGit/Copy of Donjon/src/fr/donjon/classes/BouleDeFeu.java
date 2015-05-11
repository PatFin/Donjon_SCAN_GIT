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
public class BouleDeFeu extends Projectile {

	final static int DGT = 20;
	final static int VIT = 10;
	final static int DIM = 32;
	
	final static String src = "FireBall.png";
	
	Animation aN;
	Animation aE;
	Animation aS;
	Animation aO;
	
	/**
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
	 * @param type
	 * @param degats
	 */
	public BouleDeFeu(int ax, int ay , Vecteur vvitesse) {
		
		super(ax, ay, DIM, DIM, src, new Rectangle(0,0), new Rectangle(0,0),
				true, vvitesse, VIT, Type.ENNEMI, DGT);
		
		
	}


	@Override
	public void update(long t) {
		

	}

}
