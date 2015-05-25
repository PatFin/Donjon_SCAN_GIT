/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.salles.Salle;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CollisionPorte implements CollisionPattern {

	public Link lien;
	
	public CollisionPorte(Link l) {
		lien=l;
	}

	
	/**
	 * 
	 */
	public CollisionPorte(Salle s, Vecteur palier, Orientation o) {
		lien = new Link(s,palier,o);
	}


	@Override
	public void persoCollision(Personnage p) {
	}


	@Override
	public void persoEnterCase(Personnage p) {
		lien.getSalleOrigine().passerLaPorte(lien);
	}

	@Override
	public void persoLeaveCase(Personnage p) {
	}

	@Override
	public void projEnterCase(Projectile p) {
		p.living = false;
	}


	@Override
	public void projLeaveCase(Projectile p) {
	}


	@Override
	public String toString(){
		return "Porte";
	}

}
