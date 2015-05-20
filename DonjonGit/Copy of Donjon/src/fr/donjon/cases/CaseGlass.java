/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.utils.Vecteur;



/**
 * @author Baptiste
 *
 */
public class CaseGlass extends CaseSource {

	static Vecteur pos1 = new Vecteur(3,0);
	
	/**
	 * @param ImageName
	 */
	public CaseGlass() {
		super(pos1);
		setCollision(new CollisionVitesse(2));
	}
	
	@Override
	public Case clone() {
		return new CaseGlass();
	}
	

}
