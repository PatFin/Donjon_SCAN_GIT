package fr.donjon.classes.cases;

import java.awt.Rectangle;

import fr.donjon.classes.Personnage;


public class Case_rocher extends Case_Obstacle {

	final static String image="Ressources/Images/Case_rocher.png";


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
	public void setCollisionBoxLocation(int vertical, int horizontal) {
		
		this.collision = new Rectangle(Case.TAILLE*horizontal+16, Case.TAILLE*vertical+16, Case.TAILLE/2, Case.TAILLE/2);
	}

	@Override
	/**
	 * Au cas ou les personnage essaie de traverser le rectangle (interdit) de la case, 
	 * il est remis � la limite de la case.
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