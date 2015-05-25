package fr.donjon.utils;

import fr.donjon.Donjons.GestionnaireSalle;

public interface EcouteurLauncher {

	/**
	 * Permet de demander au launcher de rafficher le menu
	 */
	public void requestBackToMenu();
	
	/**
	 * Permet de demander l'affichage d'un �cran GAMEOVER
	 * @param win true si le h�ros est vivant, false si le jeu a �t� termin� parce-que le h�ro a �t� vaincu.
	 */
	public void requestGameOver(Boolean win);
	
	/**
	 * Permet de demander au launcher de lancer un nouveau jeu
	 * @param g le gestionnaire associ� au jeu
	 */
	public void requestNewGame(GestionnaireSalle g);

}
