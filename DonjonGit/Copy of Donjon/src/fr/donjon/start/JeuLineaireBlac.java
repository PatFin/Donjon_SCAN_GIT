/**
 * 
 */
package fr.donjon.start;

import fr.donjon.classes.Salle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel implements EcouteurClavier {

	/**
	 * Permet de creer un jeu de type linéaire
	 * Le chateau (castle) genère les salles a afficher (TODO)
	 */
	public JeuLineaireBlac(){
		super(null); //MDRRR
		this.changeSalle(new Salle(ecran));

		//A faire : gestion deplacements
		this.addKeyListener(new JeuKeyAdapter(this));		
		
	}


	
	
	//INTERFACE D'ECOUTE

	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub

	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stopDeplacement(Orientation o) {
		// TODO Auto-generated method stub
	}



}

