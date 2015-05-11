/**
 * 
 */
package fr.donjon.zpoubelle;

import fr.donjon.classes.Personnage;

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
	 * Cette m�thode est g�n�rique � toutes les cases de type obstacle.
	 * Le personnage z est repouss� � le limite du rectangle de collision. 
	 * @param z Le personnage en collision avec la case
	 */
	public void inCollision(Personnage p) {
		p.setLocation((int)p.lPos.x, (int) p.lPos.y);	
	}
	
	
	

}
