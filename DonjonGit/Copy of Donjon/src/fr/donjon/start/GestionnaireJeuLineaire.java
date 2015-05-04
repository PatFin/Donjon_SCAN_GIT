package fr.donjon.start;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.Enigme_teleportation;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.salles.Salle_foret;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		
		super(game);
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		sallesDisponibles.add(new Enigme_teleportation());
		
		SalleAbs firstRoom = null;
		try {
			firstRoom = new Castle_Room(game.ecran, new Heros(200,200), Orientation.random());
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //On cr�e la premiere Salle 
		currentRoom = firstRoom;
		//On initialise la liste des salles avec la premi�re
		this.listeSalles = new LinkedList<SalleAbs>();
		this.listeSalles.add(firstRoom);
	}


	@Override
	public void createNextRoom(Link l) {
		Orientation a = Orientation.opposite(l.orientation);
		SalleAbs s = createRandomNewRoom(l.origineSalle.hero, l);
		l.setDestination(s, s.destination.get(a));
		
		//We add a random unique door to the room.
		s.addDoor(Orientation.random(a), false);
		
		//We generate the image of the room
		s.generateImage();
		
		listeSalles.add(s);
	}

	@Override
	public Orientation mustChange() {
		return super.mustChange();
	}

}