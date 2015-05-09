/**
 * 
 */
package fr.donjon.cases2;


/**
 * @author Baptiste
 *
 */
public class CaseLave extends Case {

	final static String src = "Case_lava.png";
	final static int DPS = 11;
	
	/**
	 * @param ImageName
	 */
	public CaseLave() {
		super(src,true, new CollisionDegats(DPS));
	}
	
	@Override
	public Case clone() {
		return new CaseLave();
	}

}
