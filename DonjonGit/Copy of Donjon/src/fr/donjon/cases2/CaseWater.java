/**
 * 
 */
package fr.donjon.cases2;


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
