package fr.donjon.classes;

import java.awt.Rectangle;


/**
 * 
 * @author Vincent
 *
 */

public class Case_mur extends Case{
	
	final static String image="C:/SVN/Case_mur.png";
	
	
	/**
	 * Constructeur
	 */
	public Case_mur(){
		super(image, false);
	}
	
	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		this.collision = new Rectangle(Case.TAILLE*horizontal, Case.TAILLE*vertical, 64, 64);
	}
	
	@Override
	public void inCollision(Personnage z) {
		// TODO Auto-generated method stub
		switch(z.o){

		case NORD:
			z.setLocation(z.image.x, this.collision.y+this.collision.height+z.collisionDecor.height-z.image.height);
			break;
		case EST:
			z.setLocation(this.collision.x+z.collisionDecor.width-z.image.width, z.image.y);
			break;
		case OUEST:
			z.setLocation(this.collision.x+this.collision.width-z.image.width+2*z.collisionDecor.width+2, z.image.y);
			break;
		case SUD:
			z.setLocation(z.image.x, this.collision.y-z.image.height);

		}
	}

}