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
	
	@Override
	public void inCollision(Personnage p) {
		p.addEffect("WATER_SLOW",  new Effet(0 , 0, -1, 0, 40,false));
	}

}
