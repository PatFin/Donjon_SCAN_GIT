package fr.donjon.zpoubelle;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.zpoubelle.Castle_Room;
import fr.donjon.zpoubelle.SalleAbs;
import fr.donjon.zpoubelle.SalleEditeur;
import fr.donjon.zpoubelle.Salle_croix;
import fr.donjon.zpoubelle.Salle_foret;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		
		super(game);
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		//sallesDisponibles.add(new Enigme_teleportation());
		int[] index = MapFileHandler.getExistingIndexes();
		
		for(int i=0; i<index.length;i++){
			try{
				sallesDisponibles.add(new SalleEditeur(MapFileHandler.getSalleDescriptionFromFile(index[i])));
			}catch(CustomException e){
				System.out.println(e);
			}
		}
		
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
		SalleAbs s = null;
		try {
			s = createRandomNewRoom(l.origineSalle.hero, l);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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