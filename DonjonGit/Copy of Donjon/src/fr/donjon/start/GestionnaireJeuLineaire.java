package fr.donjon.start;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.salles.Salle_foret;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		
		super(game);
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		
		SalleAbs firstRoom = new Castle_Room(game.ecran, new Heros(200,200), Orientation.random()); //On cr�e la premiere Salle 
		currentRoom = firstRoom;
		//On initialise la liste des salles avec la premi�re
		this.listeSalles = new LinkedList<SalleAbs>();
		this.listeSalles.add(firstRoom);
	}


	@Override
	public void changerDeSalle(Link l) {
		if(!l.hasDestination()){
			createNextRoom(l);
		}
		Vecteur v = l.destinationSalle.destination.get(Orientation.opposite(l.orientation));
		l.destinationSalle.hero.setLocation(v);
		this.currentRoom = l.destinationSalle;
	}


	@Override
	public void createNextRoom(Link l) {
		super.createNextRoom(l);
	}

	@Override
	public Orientation mustChange() {
		return super.mustChange();
	}

}