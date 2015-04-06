package fr.donjon.classes;

import java.awt.Rectangle;


public class Case_rocher extends Case {

	final static String image="C:/SVN/Case_rocher.png";


	/**
	 * Constructeur
	 */
	public Case_rocher (){
		super(image, false);
	}

	@Override
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		// TODO Auto-generated method stub
		this.collision = new Rectangle(Case.TAILLE*horizontal+15, Case.TAILLE*vertical+15, 32, 32);
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
