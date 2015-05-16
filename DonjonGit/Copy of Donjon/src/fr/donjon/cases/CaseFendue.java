package fr.donjon.cases;


public class CaseFendue extends Case {
	
	final static String image="Case_fendue_sol.png";
	/**
	 * Constructeur
	 */
	public CaseFendue(){
		super(image);
	}
	
	@Override
	public Case clone() {
		return new CaseFendue();
	}
	

}
