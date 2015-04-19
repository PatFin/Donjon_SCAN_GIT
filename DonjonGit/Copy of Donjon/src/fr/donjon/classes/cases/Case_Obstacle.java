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

		z.setLocation((int)z.lPos.x, (int) z.lPos.y);
		
	}

}
