package fr.donjon.zpoubelle;

import java.awt.Rectangle;

import fr.donjon.classes.Personnage;


public class Case_rocher extends Case_Obstacle {

	final static String image="Case_rocher.png";


	/**
	 * Constructeur
	 */
	public Case_rocher (){
		super(image);
	}

	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_rocher();
	}
	
	
	@Override
	/**
	 * Positionne le rectangle de collision associ� � la case.
	 */
	public void setCollisionBoxLocation(int horizontal, int vertical) {
		
		this.collision = new Rectangle(Case.TAILLE*horizontal+16, Case.TAILLE*vertical+16, Case.TAILLE/2, Case.TAILLE/2);
	}

	@Override
	/**
	 * Au cas ou les personnage essaie de traverser le rectangle (interdit) de la case, 
	 * il est remis � la limite de la case.
	 */
	public void inCollision(Personnage z) {
		
		super.inCollision(z);
	}
}
