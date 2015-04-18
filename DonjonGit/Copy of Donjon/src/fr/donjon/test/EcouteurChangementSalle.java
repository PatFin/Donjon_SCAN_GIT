package fr.donjon.test;

import fr.donjon.classes.Link;

public interface EcouteurChangementSalle {

	/**
	 * Permet de prevenir l'�couteur que l'on veut changer de Salle, on connait la direction
	 * de changement de Salle et le h�ro a utiliser dans la nouvelle Salle
	 * @param l le lien de la salle actuelle vers la prochaine salle
	 */

	public void changerDeSalle(Link l);
	
	
	public boolean mustChange(SalleAbs s);
}