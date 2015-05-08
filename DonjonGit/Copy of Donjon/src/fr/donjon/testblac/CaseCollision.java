/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.cases.Case;

/**
 * @author Baptiste
 *
 */
public abstract class CaseCollision extends Case {

	boolean enabled;
	CollisionPattern collision;
	
	/**
	 * @param ImageName
	 */
	public CaseCollision(String ImageName) {
		super(ImageName);
		this.enabled = true;
	}
	
	@Override
	public void inCollision(Personnage p) {
		enCollision(p);
	}
	
	public void enCollision(Personnage p){
		if(enabled)collision.enCollision(p);
	}
	
	public void setCollision(CollisionPattern pattern){
		this.collision = pattern;
	}
	
	public void setEnabled(boolean b){
		this.enabled = b;
	}

}
