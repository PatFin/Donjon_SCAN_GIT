package fr.donjon.classes;

import java.awt.Rectangle;


/**
 * 
 * @author Vincent
 *
 */

public class Case_mur extends Case_Obstacle{
	
	final static String image="Ressources/Images/Case_mur.png";
	
	
	/**
	 * Constructeur
	 */
	public Case_mur(){
		super(image);
	}
	
	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		this.collision = new Rectangle(Case.TAILLE*horizontal, Case.TAILLE*vertical, 64, 64);
	}

}