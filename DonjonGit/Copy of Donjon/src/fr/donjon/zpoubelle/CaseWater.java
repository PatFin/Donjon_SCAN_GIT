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
public class CaseWater extends Case {

	final static String src = "Case_water.png";
	
	
	/**
	 * @param ImageName
	 */
	public CaseWater() {
		super(src);
	}
	
	@Override
	public Case clone() {
		return new CaseWater();
	}
	
}
