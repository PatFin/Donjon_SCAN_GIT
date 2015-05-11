package fr.donjon.zpoubelle;

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
			if(!enabled){
				super.inCollision(p);
			}
			break;
		case ALIE:
			if(!enabled){
				super.inCollision(p);
			}
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
	
	/**
	 * 
	 */
	@Override
	public void enablePassage(){
		this.enabled=true;
	}
}
