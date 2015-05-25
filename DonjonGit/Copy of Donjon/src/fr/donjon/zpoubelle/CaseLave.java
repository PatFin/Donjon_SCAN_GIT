/**
 * 
 */
package fr.donjon.zpoubelle;

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
