/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

/**
 * @author Baptiste
 *
 */
public class CollisionObstacle implements CollisionPattern {

	/**
	 * 
	 */
	public CollisionObstacle() {
		
	}


	@Override
	public void persoEnterCase(Personnage p) {
	}


	@Override
	public void projEnterCase(Projectile p) {
		p.living = false;
	}


	@Override
	public void persoLeaveCase(Personnage p) {
	}


	@Override
	public void projLeaveCase(Projectile p) {
		
	}


	@Override
	public void persoCollision(Personnage p) {
		p.setLocation(p.lPos);
	}

}
