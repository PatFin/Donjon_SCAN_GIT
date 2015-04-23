/**
 * 
 */
package fr.donjon.utils;

import java.awt.Graphics;

import fr.donjon.classes.Personnage;

/**
 * 
 * @author Baptiste
 *
 */
public interface Drawable {
	
	public void draw(Personnage p, Graphics g, long t);

}
