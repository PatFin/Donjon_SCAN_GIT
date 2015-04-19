/**
 * 
 */
package fr.donjon.classes.cases;

import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;

/**
 * @author Patrick
 *
 */
public abstract class Case_Obstacle extends Case {

	/**
	 * @param ImageName
	 */
	public Case_Obstacle(String ImageName) {
		super(ImageName);
	}
	
	/**
	 * Cette méthode est générique à toutes les cases de type obstacle.
	 * Le personnage z est repoussé à le limite du rectangle de collision. 
	 * @param z Le personnage en collisionavec la case
	 */
	public void inCollision(Ennemis z) {
		System.out.println("En collision avec :"+z);
		z.setLocation((int)z.lPos.x, (int) z.lPos.y);
		
	}
	public void inCollision(Heros z) {
		System.out.println("En collision avec :"+z);
		z.setLocation((int)z.lPos.x, (int) z.lPos.y);
		
	}

}
