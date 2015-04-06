package fr.donjon.classes;


public class Case_fendue_sol extends Case {
	
	final static String image="C:/SVN/Case_fendue_sol.png";
	/**
	 * Constructeur
	 */
	public Case_fendue_sol(){
		super(image,true);
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
