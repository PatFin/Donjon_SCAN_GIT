package fr.donjon.cases2;


public class CaseDalle extends Case{
	
	final static String image = "Case_dalle.png";
	
	/**
	 * Constructeur de cette case.
	 * Ils'agit d'une case ordinaire qui n'a pas de comportement particulier.
	 */
	public CaseDalle(){
		super(image);
	}
	
	@Override
	public Case clone() {
		return new CaseDalle();
	}
	
}