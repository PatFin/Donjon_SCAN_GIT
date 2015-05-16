package fr.donjon.cases;


/**
 * 
 * @author Vincent
 *
 */

public class CaseHerbe extends Case{


	final static String image="Case_herbe.png";

	/**
	 * Constructeur
	 */
	public CaseHerbe(){
		super(image);
	}
	
	@Override
	public Case clone() {
		return new CaseHerbe();
	}
	

}
