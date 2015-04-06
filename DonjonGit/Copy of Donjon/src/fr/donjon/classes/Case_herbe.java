package fr.donjon.classes;


/**
 * 
 * @author Vincent
 *
 */

public class Case_herbe extends Case{


	final static String image="C:/SVN/Case_herbe.png";

	/**
	 * Constructeur
	 */
	public Case_herbe(){
		super(image, true);
	}

	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		//No collisions
		return;
	}

	@Override
	public void inCollision(Personnage z) {
		// TODO Auto-generated method stub
		//Pas de comportement specifique
		return;
	}

}
