package fr.donjon.classes.cases;

public class Case_dalle_sol extends Case {
	
	final static String image = "Ressources/Images/Case_dalle.png";
	
	/**
	 * Constructeur de cette case.
	 * Ils'agit d'une case ordinaire qui n'a pas de comportement particulier.
	 */
	public Case_dalle_sol(){
		super(image);
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_dalle_sol();
	}
	
}