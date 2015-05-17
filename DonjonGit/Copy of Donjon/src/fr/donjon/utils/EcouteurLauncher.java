package fr.donjon.utils;

import fr.donjon.Donjons.GestionnaireSalle;

public interface EcouteurLauncher {

	/**
	 * Permet de demander au launcher de rafficher le menu
	 */
	public void requestBackToMenu();
	
	/**
	 * Permet de demander au launcher de lancer un nouveau jeu
	 * @param g le gestionnaire associé au jeu
	 */
	public void requestNewGame(GestionnaireSalle g);

}
