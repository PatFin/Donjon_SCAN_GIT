/**
 * 
 */
package fr.donjon.utils;

import java.awt.Color;
import java.awt.Graphics;

import fr.donjon.classes.Personnage;

/**
 * @author Baptiste
 *
 */
public class DrawableSlow implements Drawable {

	Color c;
	
	/**
	 * 
	 */
	public DrawableSlow(Color c) {
		this.c = c;
	}

	
	@Override
	public void draw(Personnage p, Graphics g, long t) {
		g.setColor(c);
		g.fillOval((int)p.getCentre().x-20, (int)p.getCentre().y +22, 40, 10);
	}

}
