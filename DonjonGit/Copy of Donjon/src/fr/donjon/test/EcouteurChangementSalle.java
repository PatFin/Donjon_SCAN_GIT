package fr.donjon.test;

import fr.donjon.classes.Link;
import fr.donjon.utils.Orientation;

public interface EcouteurChangementSalle {

	/**
	 * Permet de prevenir l'�couteur que l'on veut changer de Salle, on connait la direction
	 * de changement de Salle et le h�ro a utiliser dans la nouvelle Salle
	 * @param l le lien de la salle actuelle vers la prochaine salle
	 */
	public void changerDeSalle(Link l);
	
	/**
	 * Methode � impl�menter dans le Gestionnaire abstrait.
	 * Il sera possible de la red�finir dans les gestionnaires d�rivant
	 * de Gestionnaire selon les besoins.
	 * @return true si on doit changer de salle, false sinon.
	 */
	public Orientation mustChange();
}