package fr.donjon.cases;


public class CaseDalle extends Case{
	
	final static String image = "Case_dalle.png";
	
	/**
	 * Constructeur de cette case.
	 * Il s'agit d'une case ordinaire qui n'a pas de comportement particulier.
	 */
	public CaseDalle(){
		super(image);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseDalle();
	}
	
}