/**
 * 
 */
package fr.donjon.classes.cases;

import fr.donjon.classes.Personnage;
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
		p.addEffect("LAVE_SLOW",  new Effet(0 , 0, -2, 0, 40,false));
		p.addEffect("LAVE_HURT",  new Effet(-1 , 0, 0, 0, 100,true));
	}

}
