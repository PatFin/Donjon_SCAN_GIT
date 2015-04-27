/**
 * 
 */
package fr.donjon.classes.cases;

import java.awt.Color;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.DrawableSlow;
import fr.donjon.utils.Effet;

/**
 * @author Baptiste
 *
 */
public class CaseLave extends Case {

	final static String src = "Case_lava.png";
	
	/**
	 * @param ImageName
	 */
	public CaseLave() {
		super(src);
	}
	
	@Override
	public Case clone() {
		return new CaseLave();
	}

	@Override
	public void inCollision(final Personnage p) {
		Effet e1 = new Effet(p,0 , 0, -2, 0, 20, false, false);
		e1.setDrawable(new DrawableSlow(new Color(255,100,0)));
		p.addEffect("LAVE_SLOW", e1 );//-2 VIT constant
		p.addEffect("LAVE_HURT",  new Effet(p, -1 , 0, 0, 0, 100, true, true)); //10 dmg bruts par seconde
	}

}
