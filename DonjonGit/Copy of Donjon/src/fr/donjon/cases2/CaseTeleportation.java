/**
 * 
 */
package fr.donjon.cases2;

import java.awt.Rectangle;

import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CaseTeleportation extends CaseSource {

	static Vecteur pos = new Vecteur(11,6);


	public CaseTeleportation() {
		super(pos);
		this.limites = new Rectangle(32, 32);
	}
	
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		this.limites.setLocation(x*Case.TAILLE +16, y*Case.TAILLE + 16);
	}

}
