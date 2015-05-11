/**
 * 
 */
package fr.donjon.cases2;

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
	public void enCollision(Personnage p) {
		p.setLocation((int)p.lPos.x, (int) p.lPos.y);
	}


	@Override
	public void nonCollision(Personnage p) {
	}


	@Override
	public void enCollision(Projectile p) {
		p.living = false;
	}

}
