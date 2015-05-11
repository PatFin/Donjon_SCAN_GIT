package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.Vecteur;

public class CollisionTeleportation implements CollisionPattern {

	private Vecteur destination;	//La case de destination du personnage dans la salle
	
	/**
	 * Constructeur vide
	 */
	public CollisionTeleportation() {
		destination = null;
	}
	
	/**
	 * Constructeur initialisant une destination
	 * @param destination vecteur de position dans la salle
	 */
	public CollisionTeleportation(Vecteur destination){
		this.destination=destination;
	}

	/**
	 * Si le personnage est en collision avec la case, l'envoyer � la case destination
	 */
	@Override
	public void enCollision(Personnage p) {
		try{
			p.setLocation(destination);
		}catch(Exception e){
			System.out.println("Unable to change the location of the hero. Destination  vector may not have been initialized");
		}
	}
	
	
	/**
	 * Mutateur
	 * @param v la nouvelle destination
	 */
	public void setDestination(Vecteur v){
		this.destination=v;
	}
	

	
	@Override
	public void nonCollision(Personnage p) {
		//On ne fait rien
	}

	@Override
	public void enCollision(Projectile p) {
		
	}

}
