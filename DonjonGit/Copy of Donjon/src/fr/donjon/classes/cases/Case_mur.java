package fr.donjon.classes.cases;

import java.awt.Rectangle;


/**
 * 
 * @author Vincent
 *
 */

public class Case_mur extends Case_Obstacle{
	
	final static String image="Case_mur.png";
	
	
	/**
	 * Constructeur
	 */
	public Case_mur(){
		super(image);
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_mur();
	}
	
	
	@Override
	public void setCollisionBoxLocation(int horizontal, int vertical) {
		// TODO Auto-generated method stub
		this.collision = new Rectangle(Case.TAILLE*horizontal, Case.TAILLE*vertical, 64, 64);
	}
	
	

}