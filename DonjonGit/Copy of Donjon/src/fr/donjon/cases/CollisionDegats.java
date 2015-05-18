/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

/**
 * @author Baptiste
 *
 */
public class CollisionDegats implements CollisionPattern {

	int dps;
	int cumul;
	int division = 200;
	
	long time;
	long lastTime;

	/**
	 * 
	 */
	public CollisionDegats(int dps) {

		this.dps = dps;
		this.time = -1;
		this.lastTime = 0;
		this.cumul = 0;
	}

	@Override
	public void persoEnterCase(Personnage p) {
	}

	@Override
	public void projEnterCase(Projectile p) {
	}

	@Override
	public void persoLeaveCase(Personnage p) {
	}

	@Override
	public void projLeaveCase(Projectile p) {
	}

	@Override
	public void persoCollision(Personnage p) {


		if( time == -1){
			lastTime  = System.currentTimeMillis();
		}

		time = System.currentTimeMillis();

		cumul += time - lastTime;

		if(cumul >= division){
			p.receiveDammages( (int) (p.stats.def + dps*((double)division/1000) ) );
			cumul = 0;
		}


		lastTime = System.currentTimeMillis();
		
	}

	

}
