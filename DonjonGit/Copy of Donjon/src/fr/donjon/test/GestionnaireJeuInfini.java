package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuInfini implements EcouteurChangementSalle{

	GamePanel game;
	public ListeSalle listeSalles;
	
	
	public GestionnaireJeuInfini(GamePanel game) {
		
		this.game = game;
		
		SalleInteractive firstRoom = new SalleInteractive(new Heros(200,200), game.ecran, this);
		
		this.listeSalles = new ListeSalle(firstRoom);
		
	}


	@Override
	public void changerDeSalle(Orientation o, Heros h) {
		
		SalleInteractive newRoom = new SalleInteractive(h, game.ecran, this);
		
		
		if(!listeSalles.addElement(new SalleElement(newRoom), o)){
			Salle.numberOfRooms--;
		}
		
		game.changeSalle(listeSalles.getCurrent().getSalle());

	}
	
	public Salle getCurrentRoom(){
		return this.listeSalles.getCurrent().getSalle();
		
	}


	

}
