/**
 * 
 */
package fr.donjon.classes.cases;

import fr.donjon.classes.Personnage;

/**
 * @author Patrick
 *
 */
public abstract class Case_Obstacle extends Case {

	/**
	 * @param ImageName
	 * @param traversable
	 */
	public Case_Obstacle(String ImageName) {
		super(ImageName);
	}
	
	/**
	 * Cette m�thode est g�n�rique � toutes les cases de type obstacle.
	 * Le personnage z est repouss� � le limite du rectangle de collision. 
	 * @param z Le personnage en collisionavec la case
	 */
	public void inCollision(Personnage z) {
		/**
		 * En fonction de la direction du personnage on peut savoir de quel c�t� il est venu.
		 * On peut donc le replacer � l'endroit appropri�.
		 */
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
