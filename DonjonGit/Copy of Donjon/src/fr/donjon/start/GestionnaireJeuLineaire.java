package fr.donjon.start;

import java.util.LinkedList;

import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		
		super(game);
		
		
		SalleAbs firstRoom = new Castle_Room(game.ecran, new Heros(200,200), Orientation.random()); //On crée une premiere Salle 
		
		
		currentRoom = firstRoom;
		this.listeSalles = new LinkedList<SalleAbs>(); 			//On initialise la liste avec la première salle
		this.listeSalles.add(firstRoom);
		
	}


	@Override
	public void changerDeSalle(Link l) {
		if(!l.hasDestination()){
			createNextRoom(l);
		}
		Vecteur v = l.destinationSalle.destination.get(Orientation.opposite(l.orientation));
		l.destinationSalle.hero.setLocation((int)(v.x*Case.TAILLE), (int)(v.y*Case.TAILLE));
		this.currentRoom = l.destinationSalle;
	}


	@Override
	public void createNextRoom(Link l) {
		Orientation a = Orientation.opposite(l.orientation);
		SalleAbs s = new Castle_Room(l.origineSalle.hero, l, Orientation.random(a));
		l.setDestination(s, s.destination.get(a));
	}



	@Override
	public Orientation mustChange() {
		return super.mustChange();
	}

}