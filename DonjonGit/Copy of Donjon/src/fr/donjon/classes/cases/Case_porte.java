package fr.donjon.classes.cases;

import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;

public abstract class Case_porte extends Case_Obstacle {

	boolean enabled;
	
	/**
	 * Constructor
	 * @param ImageName the string containing the adressof the image
	 * @param enabled boolean that indicates if the passage to the next room is possible
	 */
	public Case_porte(String ImageName, boolean enabled) {
		super(ImageName);
		this.enabled = enabled;
	}
	
	/**
	 * This method overrides the super class method.
	 * It allows the hero to walk on the tile if it is enbled. 
	 * to walk on the door.
	 * @param p
	 */
	@Override
	public void inCollision(Heros p){
		if(!this.enabled){
			super.inCollision(p);
		}
	}
	
	/**
	 * Ennemis aren't allowed to step on doors.
	 */
	public void inCollision(Ennemis z) {
		super.inCollision(z);
	}
	
	
	
}
