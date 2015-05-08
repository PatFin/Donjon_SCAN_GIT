/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.utils.Orientation;

/**
 * @author Baptiste
 *
 */
public class CasePorte extends CaseCollision {

	final static String src = "CaseTP.png";
	
	Orientation o;
	
	/**
	 * @param ImageName
	 */
	public CasePorte() {
		super(src);
		this.setCollision(new CollisionObstacle());
	}
	
	public CasePorte(Orientation o) {
		this();
		this.o = o;
	}

}
