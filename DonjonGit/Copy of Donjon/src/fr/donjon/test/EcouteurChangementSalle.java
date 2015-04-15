package fr.donjon.test;

import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;

public interface EcouteurChangementSalle {

	/**
	 * Permet de prevenir l'�couteur que l'on veut changer de Salle dans une certaine direction
	 * La salle renvoie son h�ros pour le placer dans la nouvelle Salle
	 * 
	 * @param o Orientation du changement
	 * @return	Renvoie le Heros de la Salle
	 * 
	 */
	public void changerDeSalle(Orientation o, Heros h);
	
}
