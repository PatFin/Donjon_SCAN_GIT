package fr.donjon.cases;

/**
 * CaseFendue est très similaire à CaseDalle.
 * Elle difère en apparence par les quatre dalles qui composent son image, elles sont fendues et envahies par de l'herbe.
 * @author Patrick
 *
 */
public class CaseFendue extends Case {
	
	final static String image="Case_fendue_sol.png";
	
	/**
	 * Constructeur
	 * Aucun paramètre nécessaire
	 */
	public CaseFendue(){
		super(image);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseFendue();
	}
	

}
