package fr.donjon.classes;

import java.awt.Rectangle;


public class Case_rocher extends Case_Obstacle {

	final static String image="Ressources/Images/Case_rocher.png";


	/**
	 * Constructeur
	 */
	public Case_rocher (){
		super(image);
	}

	@Override
	/**
	 * Positionne le rectangle de collision associé à la case.
	 */
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		
		this.collision = new Rectangle(Case.TAILLE*horizontal+15, Case.TAILLE*vertical+15, Case.TAILLE/2, Case.TAILLE/2);
	}

	@Override
	/**
	 * Au cas ou les personnage essaie de traverser le rectangle (interdit) de la case, 
	 * il est remis à la limite de la case.
	 */
	public void inCollision(Personnage z) {
		
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
