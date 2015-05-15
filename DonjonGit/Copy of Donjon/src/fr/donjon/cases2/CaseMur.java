package fr.donjon.cases2;



/**
 * 
 * @author Vincent
 *
 */

public class CaseMur extends Case{
	
	final static String image="Case_mur.png";
	
	
	/**
	 * Constructeur
	 */
	public CaseMur(){
		super(image,true, new CollisionObstacle());
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases2.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseMur();
	}
	
	

}