package fr.donjon.classes.cases;


public class Case_fendue_sol extends Case {
	
	final static String image="Ressources/Images/Case_fendue_sol.png";
	/**
	 * Constructeur
	 */
	public Case_fendue_sol(){
		super(image);
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_fendue_sol();
	}
	

}
