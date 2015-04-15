/**
 * 
 */
package fr.donjon.start;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Linear_Castle;
import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel implements EcouteurClavier {

	Linear_Castle castle; //The objet that manage the rooms
	
	/**
	 * Permet de creer un jeu de type linéaire
	 * Le chateau (castle) genère les salles a afficher (TODO)
	 */
	public JeuLineaireBlac(){
		super(null);
		this.castle = new Linear_Castle(new Heros(200,200), ecran);
		this.changeSalle(castle.currentRoom);
		
	}
	
	public JeuLineaireBlac(Case[][] cases){
		super(null);
		Salle s  =  new Salle(new Heros(100,100), cases, new Rectangle(0,0,64*15,64*10), null);
		this.changeSalle(s);
	}

	public void update() {
		super.update();
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

