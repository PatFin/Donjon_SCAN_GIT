/**
 * 
 */
package fr.donjon.classes.cases;

import java.awt.Color;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Effet;
import fr.donjon.zpoubelle.DrawableSlow;

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

}
