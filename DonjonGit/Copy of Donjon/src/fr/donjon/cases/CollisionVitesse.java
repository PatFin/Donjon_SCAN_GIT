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
public class CollisionVitesse implements CollisionPattern {

	int vitesse;


	/**
	 * 
	 */
	public CollisionVitesse(int vitesse) {
		this.vitesse = vitesse;
	}


	@Override
	public String toString(){
		return "Vitesse";
	}
	
	@Override
	public void persoEnterCase(Personnage p) {
		p.stats.vit += vitesse;
		
	}


	@Override
	public void projEnterCase(Projectile p) {
		
	}


	@Override
	public void persoLeaveCase(Personnage p) {
		p.stats.vit -= vitesse;
	}



	@Override
	public void projLeaveCase(Projectile p) {
		
	}

	@Override
	public void persoCollision(Personnage p) {
	}

}
