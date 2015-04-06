// /!\ Classe à compléter

package fr.donjon.classes;

import java.awt.Rectangle;


public class Case_escalier extends Case {
	
	final static String image="C:/SVN/Case_escalier.png";
	
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * @param image
	 */
	public Case_escalier() {
		super(image, false);
	}


	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		this.collision = new Rectangle(Case.TAILLE*vertical, Case.TAILLE*horizontal, 64, 64);
	}


	@Override
	public void inCollision(Personnage z) {
		// TODO Auto-generated method stub
		System.out.println("Changement de niveau si hero");
	}
}
