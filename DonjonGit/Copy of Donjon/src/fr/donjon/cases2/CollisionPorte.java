/**
 * 
 */
package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.testblac.Salle;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CollisionPorte implements CollisionPattern {

	//TODO remove unused bits
	
	//Vecteur directionSalle;
	public Link lien;
	
	/**
	 * 
	 */
	public CollisionPorte(Salle s, Vecteur palier, Orientation o) {
		lien = new Link(s,palier,o);
		//this.directionSalle = arrivee;
	}

	
	public CollisionPorte(Link l) {
		lien=l;
	}


	@Override
	public void enCollision(Personnage p) {
		lien.getSalleOrigine().passerLaPorte(lien);
	}


	@Override
	public void nonCollision(Personnage p) {
		
	}


	@Override
	public void enCollision(Projectile p) {
		p.living = false;
	}

}
