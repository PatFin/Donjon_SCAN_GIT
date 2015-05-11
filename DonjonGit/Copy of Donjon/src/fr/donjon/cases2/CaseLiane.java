/**
 * 
 */
package fr.donjon.cases2;



/**
 * @author Baptiste
 *
 */
public class CaseLiane extends Case {

	final static String src = "Lianes.png";
	
	
	/**
	 * @param ImageName
	 */
	public CaseLiane() {
		super(src,true, new CollisionVitesse(-2));
	}
	
	@Override
	public Case clone() {
		return new CaseLiane();
	}
	

}
