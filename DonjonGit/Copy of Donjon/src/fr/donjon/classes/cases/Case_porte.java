package fr.donjon.classes.cases;

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
	
	@Override
	public void inCollision(Personnage p) {
		switch(p.type){
		case HERO:
			//On laisse passer sur la case
			break;
		case ALIE:
			//aussi
			break;
		case ENNEMI:
			super.inCollision(p);	 //on laisse pas passer !
			break;
		case UNDEFINED:
			super.inCollision(p);	//on sait pas on laisse pas passer
			break;
		default:
			super.inCollision(p);
			break;
		}
		
	}
	
}
