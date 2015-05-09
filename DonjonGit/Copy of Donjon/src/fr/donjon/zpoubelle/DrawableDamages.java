/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Color;
import java.awt.Graphics;

import fr.donjon.classes.Personnage;

/**
 * @author Baptiste
 *
 */
public class DrawableDamages implements Drawable{

	/**
	 * 
	 */
	public DrawableDamages() {
		
	}

	@Override
	public void draw(Personnage p, Graphics g, long t) {
		g.setColor(new Color(255,255,0,200));
		g.fillOval(p.collisionArmes.x, p.collisionArmes.y, p.collisionArmes.width, p.collisionArmes.height);
	}

}
