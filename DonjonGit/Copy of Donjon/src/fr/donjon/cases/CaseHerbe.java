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
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseHerbe();
	}
	

}
