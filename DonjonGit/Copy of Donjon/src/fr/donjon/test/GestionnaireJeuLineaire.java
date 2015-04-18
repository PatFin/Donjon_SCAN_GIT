package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Link;
import fr.donjon.classes.cases.Case;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class GestionnaireJeuLineaire extends Gestionnaire {

	public GestionnaireJeuLineaire(GamePanel game) {
		super(game);
	}


	@Override
	public void changerDeSalle(Link l) {
		// TODO Auto-generated method stub
		if(!l.hasDestination()){
			createNextRoom(l);
		}
		Vecteur v = l.destinationSalle.destination.get(Orientation.opposite(l.orientation));
		l.destinationSalle.hero.setLocation((int)(v.x*Case.TAILLE), (int)(v.y*Case.TAILLE));
		
		
		
	}


	@Override
	public void createNextRoom(Link l) {
		// TODO Auto-generated method stub
		Orientation a = Orientation.opposite(l.orientation);
		SalleAbs s = new Salle_foret(l.origineSalle.hero, l, Orientation.opposite(l.orientation));
		l.setDestination(l.origineSalle, s.destination.get(a));
	}


	@Override
	public boolean mustChange(SalleAbs s) {
		
		
		
		
		return false;
	}

}
