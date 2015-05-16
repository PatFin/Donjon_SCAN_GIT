/**
 * 
 */
package fr.donjon.utils;

/**
 * @author Baptiste
 *
 */


public interface EcouteurClavier {

	/**
	 * Fait attaquer le héros contrôlé par le joueur
	 * @param o
	 */
	public void attaque(Orientation o);
	
	/**
	 * Fait arrêter d'attaquer le héro contrôlé par le joueur quand celui-ci relache les touches d'attaques.
	 */
	public void stopAttaque();
	
	/**
	 * Fait se déplacer le héros contrôlé par le joueur
	 * @param v
	 */
	public void deplacement(Vecteur v);
	
	/**
	 * Fait utiliser le héros un objet de son inventaire
	 * @param reference
	 */
	public void utiliseObjet(int reference);
	
	/**
	 * Permet de mettre le jeu en pause
	 */
	public void togglePause();
	
	/**
	 * Fait s'arrêter de bouger le héros quand le joueur relache les touches de déplacement
	 */
	public void stopDeplacement();
	
}
