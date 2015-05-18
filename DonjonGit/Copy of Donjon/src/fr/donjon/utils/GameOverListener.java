package fr.donjon.utils;

/**
 * Interface pour un dialog de fin de jeu.
 * @author Patrick
 *
 */
public interface GameOverListener {

	/**
	 * Fermera la fen�tre
	 */
	public void quit();
	
	/**
	 * Retournera � la fen�tre de menu
	 */
	public void retourMenu();
}
