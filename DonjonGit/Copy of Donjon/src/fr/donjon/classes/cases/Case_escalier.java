package fr.donjon.classes.cases;

import fr.donjon.utils.Orientation;



public class Case_escalier extends Case {
	
	final static String image="Ressources/Images/Case_escalier.png";
	
	public Orientation o;
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * La position du rectangle de collision est standard.
	 * @param o l'endroit ou se trouve l'escalier dans la salle.
	 * @param s La salle vers laquelle l'escalier va pointer.
	 */
	public Case_escalier() {
		super(image);
	}
	
	public Case_escalier(Orientation o){
		super(image);
		this.o = o;
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_escalier();
	}
	
}
