package fr.donjon.cases2;

import fr.donjon.testblac.CollisionObstacle;
import fr.donjon.utils.Orientation;



public class CaseEscalier extends Case {
	
	final static String image="Case_escalier.png";
	
	public Orientation o;
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * La position du rectangle de collision est standard.
	 * @param o l'endroit ou se trouve l'escalier dans la salle.
	 */
	public CaseEscalier() {
		super(image, true, new CollisionObstacle());
	}
	
	public CaseEscalier(Orientation o){
		super(image);
		this.o = o;
	}
	
	@Override
	public Case clone() {
		return new CaseEscalier();
	}
	
}
