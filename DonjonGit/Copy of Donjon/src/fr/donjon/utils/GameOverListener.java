package fr.donjon.utils;

/**
 * Interface pour un dialog de fin de jeu.
 * @author Patrick
 *
 */
public interface GameOverListener {

	/**
	 * Fermera la fenêtre
	 */
	public void quit();
	
	/**
	 * Retournera à la fenêtre de menu
	 */
	public void retourMenu();
}
