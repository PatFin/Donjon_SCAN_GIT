/**
 * 
 */
package fr.donjon.start;

import java.awt.Graphics;

import fr.donjon.classes.salles.SalleAbs;

/**
 * @author Baptiste
 *
 */
public class SimplePanel extends GamePanel {

	/**
	 * 
	 */
	
	SalleAbs salle;
	
	public SimplePanel(SalleAbs salle) {
		super();
		this.salle = salle;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		salle.update(temps);
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		salle.draw(temps, g);
	}

}
