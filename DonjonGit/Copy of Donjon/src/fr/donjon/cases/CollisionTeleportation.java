package fr.donjon.cases;

import fr.donjon.classes.Deplacable;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.sound.SoundLoop;

public class CollisionTeleportation implements CollisionPattern {

	private final static SoundLoop teleportSound = new SoundLoop(SoundLoop.TELEPORT);
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
				arrivee.limites.y + arrivee.limites.height/2 - p.image.height/2 -1);
	}

	@Override
	public void persoCollision(Personnage p) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void persoEnterCase(Personnage p) {
		teleportSound.playOnce();
		teleport(p);
		arrivee.persos.add(p);
	}

	@Override
	public void persoLeaveCase(Personnage p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void projEnterCase(Projectile p) {
		teleportSound.playOnce();
		teleport(p);
		arrivee.projs.add(p);
	}

	@Override
	public void projLeaveCase(Projectile p) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString(){
		return "Teleportation";
	}


}
