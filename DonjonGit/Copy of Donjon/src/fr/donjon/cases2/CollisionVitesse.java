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
	public void enCollision(Personnage p) {

		if(actif) return;

		else{
			actif = true;
			//p.stats.vit += vitesse;
		}



	}


	@Override
	public void nonCollision(Personnage p) {
		
		if(actif){
			actif = false;
			//p.stats.vit -= vitesse;
		}

	}


	@Override
	public void enCollision(Projectile p) {
	}

}
