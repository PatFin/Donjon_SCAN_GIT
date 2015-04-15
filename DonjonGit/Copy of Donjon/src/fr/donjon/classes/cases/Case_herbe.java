package fr.donjon.classes.cases;


/**
 * 
 * @author Vincent
 *
 */

public class Case_herbe extends Case{


	final static String image="Ressources/Images/Case_herbe.png";

	/**
	 * Constructeur
	 */
	public Case_herbe(){
		super(image);
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_herbe();
	}
	

}
