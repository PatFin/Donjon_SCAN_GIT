/**
 * 
 */
package fr.donjon.classes;

import java.awt.Graphics;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class ArmeEpee extends Arme{

	final static int DMG = 20;
	final static int DUR = 300;
	final static String src = "C:/SVN/hero_map.png";
	
	/**
	 * 
	 */
	public ArmeEpee(Personnage lanceur) {
		super(lanceur, DMG, DUR);
		
		animationN = new Animation(src, new Vecteur(64, 64),12,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),13,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),14,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),15,6,DUR);
		animation = animationS;
		
	}

	@Override
	public void paint(Graphics g, long t) {
		// TODO Auto-generated method stub
		super.paint(g, t);
	}

	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	@Override
	public void stopAttaquer() {
		// TODO Auto-generated method stub
		
	}
}
