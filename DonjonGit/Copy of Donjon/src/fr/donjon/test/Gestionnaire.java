/**
 * 
 */
package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * Permet la gestion des salles
 * 
 * @author Baptiste
 *
 */
public abstract class Gestionnaire implements EcouteurChangementSalle, EcouteurClavier {

	GamePanel game;					//Le jeu qui gère l'affichage de la Salle
	public ListeSalle listeSalles;	//La liste des salles du donjon
	
	
	public Gestionnaire(GamePanel game) {
		
		this.game = game;
		
		SalleInteractive firstRoom = new SalleInteractive(new Heros(200,200), game.ecran, this); //On crée une premiere Salle 
		
		this.listeSalles = new ListeSalle(firstRoom); //On initialise la liste avec la première salle
		
	}


	@Override
	public abstract void changerDeSalle(Orientation o, Heros h);
	
	public Salle getCurrentRoom(){
		return this.listeSalles.getCurrent().getSalle();
		
	}
	
	/////////////////////////////////////
	//INTERFACE D'ECOUTE/////////////////
	/////////////////////////////////////


	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		this.listeSalles.getCurrent().getSalle().attaque(o);
	}

	@Override
	public void stopAttaque() {
		// TODO Auto-generated method stub
		this.listeSalles.getCurrent().getSalle().stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		this.listeSalles.getCurrent().getSalle().deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		this.listeSalles.getCurrent().getSalle().utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		//Nothing to do, its done in game panel
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		this.listeSalles.getCurrent().getSalle().stopDeplacement();
	}
	
	

}
