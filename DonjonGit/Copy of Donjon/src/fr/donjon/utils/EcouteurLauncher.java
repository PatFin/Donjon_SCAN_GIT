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
	
	/**
	 * Permet de demander l'affichage d'un écran GAMEOVER
	 * @param win true si le héros est vivant, false si le jeu a été terminé parce-que le héro a été vaincu.
	 */
	public void requestGameOver(Boolean win);

}
