/**
 * 
 */
package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.Orientation;

/**
 * Permet la gestion des salles
 * 
 * @author Baptiste
 *
 */
public abstract class Gestionnaire implements EcouteurChangementSalle {

	GamePanel game;					//Le jeu
	public ListeSalle listeSalles;	//La liste des salles
	
	
	public Gestionnaire(GamePanel game) {
		
		this.game = game;
		
		SalleInteractive firstRoom = new SalleInteractive(new Heros(200,200), game.ecran, this);
		
		this.listeSalles = new ListeSalle(firstRoom);
		
	}


	@Override
	public abstract void changerDeSalle(Orientation o, Heros h);
	
	public Salle getCurrentRoom(){
		return this.listeSalles.getCurrent().getSalle();
		
	}


}
