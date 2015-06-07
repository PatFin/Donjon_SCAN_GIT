package fr.donjon.cases;

/**
 * CaseDalle est une case ordinaire qui n'a pas de comportement particulier.
 * Elle prend l'apparence de quatre petite dalles.
 * @author Patrick
 *
 */
public class CaseDalle extends Case{
	
	final static String image = "Case_dalle.png";
	
	/**
	 * Constructeur
	 * Aucun paramètre nécessaire, elle n'a pas de comportement particulier.
	 * 
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