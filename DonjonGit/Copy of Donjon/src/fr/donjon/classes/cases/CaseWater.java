/**
 * 
 */
package fr.donjon.classes.cases;

import fr.donjon.classes.Effet;
import fr.donjon.classes.Personnage;

/**
 * @author Baptiste
 *
 */
public class CaseWater extends Case {

	final static String src = "Case_water.png";
	
	Effet effet = new Effet(0 , 0, -1, 0); //L'effet propre a la case
	
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
		p.addEffect(effet);
	}

}
