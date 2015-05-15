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
public class CollisionVitesse implements CollisionPattern {

	int vitesse;

	boolean actif;

	/**
	 * 
	 */
	public CollisionVitesse(int vitesse) {
		this.vitesse = vitesse;
		this.actif = false;
	}


	
	@Override
	public void persoEnterCase(Personnage p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void projEnterCase(Projectile p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void persoLeaveCase(Personnage p) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void projLeaveCase(Projectile p) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void persoCollision(Personnage p) {
		// TODO Auto-generated method stub
		
	}

}
