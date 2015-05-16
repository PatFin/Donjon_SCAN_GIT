/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Graphics;

import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

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
		setFocusable(true);
		requestFocusInWindow();
	}

	@Override
	public void update() {
		salle.update(temps);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		salle.draw(temps, g);
	}

	///////////////////////////////////////////////////////
	//INTERFACE D'ECOUTE///////////////////////////////////
	///////////////////////////////////////////////////////

	//On fait passer les ordres au Gestionnaire et on intercepte celui pour stopper le Timer

	@Override
	public void attaque(Orientation o) {
		salle.attaque(o);
	}

	@Override
	public void stopAttaque() {
		salle.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		salle.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		salle.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		if(timer.isRunning())timer.stop();
		else timer.start();
	}

	@Override
	public void stopDeplacement() {
		salle.stopDeplacement();
	}


}
