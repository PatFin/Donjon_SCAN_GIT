package fr.donjon.zpoubelle;


/**
 * 
 * @author Vincent
 *
 */

public class Case_herbe extends Case{


	final static String image="Case_herbe.png";

	/**
	 * Constructeur
	 */
	public Case_herbe(){
		super(image);
	}
	
	@Override
	public Case clone() {
		return new Case_herbe();
	}
	

}
