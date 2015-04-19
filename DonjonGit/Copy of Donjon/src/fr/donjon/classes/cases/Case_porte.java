package fr.donjon.classes.cases;

import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;

public abstract class Case_porte extends Case_Obstacle {

	boolean enabled;
	
	/**
	 * Constructor
	 * @param ImageName the string containing the adressof the image
	 * @param enabled boolean that indicates if the passage to the next room is possible
	 */
	public Case_porte(String ImageName, boolean enabled) {
		super(ImageName);
	}
	
	/**
	 * This method makes it impossible for Ennemis 
	 * to walk on the door.
	 * @param p
	 */
	@Override
	public void inCollision(Ennemis p){
		super.inCollision(p);
	}
	
	/**
	 * This method allows the hero to walk on the tile
	 * if the associated link is enabled.
	 * @param h
	 */
	@Override
	public void inCollision(Heros h){
		System.out.println("Hero in collision "+enabled);
		if(!enabled){
			super.inCollision(h);
		}
		//else do nothing.
	}
	
	
}
