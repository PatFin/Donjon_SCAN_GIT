/**
 * 
 */
package fr.donjon.start;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case;
import fr.donjon.test.GestionnaireJeuInfini;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel {

	public GestionnaireJeuInfini gestionnaire;
	
	/**
	 * Permet de creer un jeu de type linéaire
	 * Le chateau (castle) genère les salles a afficher (TODO)
	 */
	public JeuLineaireBlac(){
		super(null);
		gestionnaire = new GestionnaireJeuInfini(this);
		this.changeSalle(gestionnaire.getCurrentRoom());
		
		
	}
	
	public JeuLineaireBlac(Case[][] cases){
		super(null);
		Salle s  =  new Salle(new Heros(100,100), cases, new Rectangle(0,0,64*15,64*10), null);
		this.changeSalle(s);
	}

	public void update() {
		super.update();
	}
	
	
}

