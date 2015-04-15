package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuInfini extends Gestionnaire implements EcouteurChangementSalle{

	
	public GestionnaireJeuInfini(GamePanel game) {
		super(game);
	}


	@Override
	public void changerDeSalle(Orientation o, Heros h) {
		
		SalleInteractive newRoom = new SalleInteractive(h, game.ecran, this);
		
		
		if(!listeSalles.addElement(new SalleElement(newRoom), o)){
			Salle.numberOfRooms--;
		}
		
		game.changeSalle(listeSalles.getCurrent().getSalle());

	}
	
	

	

}
