package fr.donjon.cases;

import fr.donjon.classes.Deplacable;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

public class CollisionTeleportation implements CollisionPattern {

	Case arrivee;
	Case depart;

	/**
	 * Constructeur vide
	 */
	public CollisionTeleportation() {
		arrivee = null;
		depart = null;
	}

	/**
	 * Constructeur initialisant une destination
	 * @param destination vecteur de position dans la salle
	 */
	public CollisionTeleportation(Case c1, Case c2){
		this.depart = c1;
		this.arrivee = c2;
	}


	private void teleport(Deplacable p){
		p.setLocation( arrivee.limites.x + arrivee.limites.width/2 - p.image.width/2, 
				arrivee.limites.y + arrivee.limites.height/2 - p.image.height/2 );
	}

	@Override
	public void persoEnterCase(Personnage p) {
		teleport(p);
		arrivee.persos.add(p);
	}

	@Override
	public void projEnterCase(Projectile p) {
		teleport(p);
		arrivee.projs.add(p);
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
