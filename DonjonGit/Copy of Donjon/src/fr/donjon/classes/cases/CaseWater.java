/**
 * 
 */
package fr.donjon.classes.cases;

import java.awt.Color;
import java.awt.Graphics;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Drawable;
import fr.donjon.utils.DrawableSlow;
import fr.donjon.utils.Effet;

/**
 * @author Baptiste
 *
 */
public class CaseWater extends Case {

	final static String src = "Case_water.png";
	
	
	/**
	 * @param ImageName
	 */
	public CaseWater() {
		super(src);
	}
	
	@Override
	public Case clone() {
		return new CaseWater();
	}
	
	@Override
	public void inCollision(Personnage p) {
		Effet e1 = new Effet(p, 0 , 0, -1, 0, 20,false, false);
		e1.setDrawable(new DrawableSlow(new Color(0,50,255)));
		p.addEffect("WATER_SLOW",  e1);
	}

}
