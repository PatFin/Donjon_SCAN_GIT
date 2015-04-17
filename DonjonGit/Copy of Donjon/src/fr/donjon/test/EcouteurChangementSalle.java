package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;

public interface EcouteurChangementSalle {

	/**
	 * Permet de prevenir l'�couteur que l'on veut changer de Salle, on connait la direction
	 * de changement de Salle et le h�ro a utiliser dans la nouvelle Salle
	 * 
	 * @param o Orientation du changement
	 * @param h	Heros de la Salle pr�c�dente
	 * 
	 */
	public void changerDeSalle(Orientation o, Heros h);
	
}
