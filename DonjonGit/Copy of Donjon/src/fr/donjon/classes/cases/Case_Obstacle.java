/**
 * 
 */
package fr.donjon.classes.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Vecteur;

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
	 * Cette méthode est générique à toutes les cases de type obstacle.
	 * Le personnage z est repoussé à le limite du rectangle de collision. 
	 * @param z Le personnage en collisionavec la case
	 */
	public void inCollision(Personnage z) {
		/**
		 * En fonction de la direction du personnage on peut savoir de quel côté il est venu.
		 * On peut donc le replacer à l'endroit approprié.
		 */
		Vecteur v = z.vvitesse;
		

		if(v.y < 0)z.setLocation(z.image.x, this.collision.y+this.collision.height+z.collisionDecor.height-z.image.height);
		
		if(v.x > 0)z.setLocation(this.collision.x+z.collisionDecor.width-z.image.width, z.image.y);
		
		if(v.x < 0)z.setLocation(this.collision.x+this.collision.width-z.image.width+2*z.collisionDecor.width+2, z.image.y);
		
		if(v.y > 0) z.setLocation(z.image.x, this.collision.y-z.image.height);

		//NEOS
		
	}

}
