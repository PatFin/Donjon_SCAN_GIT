package fr.donjon.zpoubelle;

import fr.donjon.classes.Heros;
import fr.donjon.start.GamePanel;
import fr.donjon.start.Gestionnaire;
import fr.donjon.utils.EcouteurChangementSalle;
import fr.donjon.utils.Orientation;

/**
 * Permet de g�rer la suite des Salles dans un jeu de type infini
 * 
 * @author Baptiste
 *
 */
public class GestionnaireJeuInfini extends Gestionnaire implements EcouteurChangementSalle{

	
	public GestionnaireJeuInfini(GamePanel game) {
		super(game);
		
		
	}

	
	/**
	 * 
	 * Methode utilis�e lorsque la Salle pr�vient qu'il faut changer de Salle
	 * 
	 */
	@Override
	public void changerDeSalle(Orientation o, Heros h) {
		
		SalleInteractive newRoom = new SalleInteractive(h, game.ecran, this); //On cr�e une nouvelle salle
		
		if(!listeSalles.addElement(new SalleElement(newRoom), o)){	//Si une Salle existe deja a l'emplacement demand�
			Salle.numberOfRooms--;									//on ajoute pas de nouvelle salle 
		}															//La methode addElement gere le d�placement (voir ListeSalle)
		
		game.changeSalle(listeSalles.getCurrent().getSalle());		//On demande le changement de Salle

	}
	
	

	

}
