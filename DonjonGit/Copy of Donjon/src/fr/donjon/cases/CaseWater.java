/**
 * 
 */
package fr.donjon.cases;



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
		super(src,true, new CollisionVitesse(-2));
	}
	
	@Override
	public Case clone() {
		return new CaseWater();
	}
	

}
