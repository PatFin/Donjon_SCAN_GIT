/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Color;

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

}
