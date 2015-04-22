package fr.donjon.start;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.salles.Salle_foret;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuInfini extends Gestionnaire {

	/**
	 * Constructeur
	 * @param game
	 */
	public GestionnaireJeuInfini(GamePanel game) {
		super(game);
		
		//On créé la liste des salles à utiliser dans le donjon
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		
		//On créé la première salle
		SalleAbs firstRoom = new Salle_croix(game.ecran, new Heros(0,0), Orientation.random());
		currentRoom = firstRoom;
		//On initialise la liste des salles avec la première
		this.listeSalles = new LinkedList<SalleAbs>();
		this.listeSalles.add(firstRoom);
	}

	@Override
	public void changerDeSalle(Link l) {
		// TODO Auto-generated method stub

	}

}
