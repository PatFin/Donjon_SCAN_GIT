/**
 * 
 */
package fr.donjon.cases2;

import java.awt.Graphics;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Vecteur;


/**
 * @author Baptiste
 *
 */
public class CaseLave extends Case {

	final static String src = "AnimLave.png";
	final static int DPS = 20;
	
	Animation anim;
	
	/**
	 * @param ImageName
	 */
	public CaseLave() {
		super(src,true, new CollisionDegats(DPS));
		this.anim = new Animation(src, new Vecteur(64,64), 0, 3, 500);
	}
	
	@Override
	public void draw(Graphics g, long t, int x, int y) {
		anim.drawAnim(x*TAILLE, y*TAILLE, TAILLE, TAILLE, g, t);
	}
	
	@Override
	public Case clone() {
		return new CaseLave();
	}

}
