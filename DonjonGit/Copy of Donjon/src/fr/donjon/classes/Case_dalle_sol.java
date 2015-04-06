package fr.donjon.classes;

public class Case_dalle_sol extends Case {
	
	final static String image = "C:/SVN/Case_dalle.png";
	
	/**
	 * Constructeur de cette case.
	 */
	public Case_dalle_sol(){
		super(image,true);
	}

	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void inCollision(Personnage z) {
		// TODO Auto-generated method stub
		return;
	}
}
