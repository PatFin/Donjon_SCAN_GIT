/**
 * 
 */
package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.testblac.CollisionPattern;

/**
 * @author Baptiste
 *
 */
public class CollisionDegats implements CollisionPattern {

	int dps;
	int deltaT;
	boolean multiple;

	long time;
	long lastTime;

	/**
	 * 
	 */
	public CollisionDegats(int dps) {

		this.dps = dps;
		this.multiple = true;
		this.deltaT = (int) (1000/dps);
		this.time = -1;
		this.lastTime = 0;

		/*

		e1.setDrawable(new DrawableSlow(new Color(255,100,0)));
		p.addEffect("LAVE_SLOW", e1 );//-2 VIT constant
		p.addEffect("LAVE_HURT",  new Effet(p, -1 , 0, 0, 0, 100, true, true)); //10 dmg bruts par seconde


			Effet e1 = new Effet(p, 0 , 0, -1, 0, 20,false, false);
			e1.setDrawable(new DrawableSlow(new Color(0,50,255)));
			p.addEffect("WATER_SLOW",  e1);
		 */

	}


	@Override
	public void enCollision(Personnage p) {



		if( time == -1)	lastTime  = System.currentTimeMillis();

		time = System.currentTimeMillis();

		//TODO

		p.receiveDammages( dps );


		lastTime = System.currentTimeMillis();
	}


	@Override
	public void nonCollision() {
		if(time != -1)time = -1;

	}

}
