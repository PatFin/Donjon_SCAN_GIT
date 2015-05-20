/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CaseBloquante extends CaseSource {

	static Vecteur pos1 = new Vecteur(1,0);
	
	/**
	 * @param pos
	 */
	public CaseBloquante() {
		super(pos1);
		this.setCollision(new CollisionPattern() {
			
			@Override
			public void projLeaveCase(Projectile p) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void projEnterCase(Projectile p) {
				p.living = false;
			}
			
			@Override
			public void persoLeaveCase(Personnage p) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void persoEnterCase(Personnage p) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void persoCollision(Personnage p) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public Case clone() {
		return new CaseBloquante();
	}

}
