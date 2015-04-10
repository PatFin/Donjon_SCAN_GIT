package fr.donjon.classes;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Vecteur;
/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public class ArmeLance extends Arme {

	final static int DMG = 40;
	final static int DUR = 400;
	final static String src = "C:/SVN/hero_map.png";
	
	/**
	 * 
	 * @param lanceur
	 */
	public ArmeLance(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),4,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),5,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),6,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),7,6,DUR);
		animation = animationS;
	}

}
