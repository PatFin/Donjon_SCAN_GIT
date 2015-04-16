/**
 * 
 */
package fr.donjon.start;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case;
import fr.donjon.test.GestionnaireJeuInfini;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel{

	
	/**
	 * Permet de creer un jeu de type linéaire
	 * Le chateau (castle) genère les salles a afficher (TODO)
	 */
	public JeuLineaireBlac(){
		super();
		gestionnaire = new GestionnaireJeuInfini(this);
		this.changeSalle(gestionnaire.getCurrentRoom());
		
	}
	
	public JeuLineaireBlac(Case[][] cases){
		super();
		Salle s  =  new Salle(new Heros(100,100), cases, new Rectangle(0,0,64*15,64*10), null);
		this.changeSalle(s);
	}

	public void update() {
		super.update();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}

