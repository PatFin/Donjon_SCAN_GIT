package fr.donjon.test;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Link;
import fr.donjon.classes.cases.Case;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		super(game);
		
		SalleAbs firstRoom = new Salle_foret(game.ecran, new Heros(200,200), Orientation.NORD); //On crée une premiere Salle 
		
		// TODO: On devrait utiliser la methode changerSale(?) ici
		//comment creer le lien adapté ?
		//
		currentRoom = firstRoom;
		this.listeSalles = new LinkedList<SalleAbs>(); 											//On initialise la liste avec la première salle
		this.listeSalles.add(firstRoom);
		
	}


	@Override
	public void changerDeSalle(Link l) {
		if(!l.hasDestination()){
			createNextRoom(l);
		}
		Vecteur v = l.destinationSalle.destination.get(Orientation.opposite(l.orientation));
		l.destinationSalle.hero.setLocation((int)(v.x*Case.TAILLE), (int)(v.y*Case.TAILLE));
		
		
		
	}


	@Override
	public void createNextRoom(Link l) {
		Orientation a = Orientation.opposite(l.orientation);
		SalleAbs s = new Salle_foret(l.origineSalle.hero, l, Orientation.opposite(l.orientation));
		l.setDestination(l.origineSalle, s.destination.get(a));
	}


	@Override
	public boolean mustChange(SalleAbs s) {
		
		
		
		
		return false;
	}

}
