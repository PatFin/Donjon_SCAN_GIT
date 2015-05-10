/**
 * 
 */
package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.testblac.Salle;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CollisionTeleportation implements CollisionPattern {

	Salle s;
	Vecteur directionSalle;
	
	/**
	 * 
	 */
	public CollisionTeleportation(Salle s, Vecteur arrivee) {
		this.s = s;
		this.directionSalle = arrivee;
	}

	
	@Override
	public void enCollision(Personnage p) {
		s.passerLaPorte(directionSalle);
	}


	@Override
	public void nonCollision(Personnage p) {
		
	}

}