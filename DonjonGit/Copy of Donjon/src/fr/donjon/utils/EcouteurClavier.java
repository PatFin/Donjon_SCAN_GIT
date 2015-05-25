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
	 * Fait attaquer le h�ros contr�l� par le joueur
	 * @param o
	 */
	public void attaque(Vecteur v);
	
	/**
	 * Fait se d�placer le h�ros contr�l� par le joueur
	 * @param v
	 */
	public void deplacement(Vecteur v);
	
	/**
	 * Fait arr�ter d'attaquer le h�ro contr�l� par le joueur quand celui-ci relache les touches d'attaques.
	 */
	public void stopAttaque();
	
	/**
	 * Fait s'arr�ter de bouger le h�ros quand le joueur relache les touches de d�placement
	 */
	public void stopDeplacement();
	
	public void toggleMap();
	
	/**
	 * Permet de mettre le jeu en pause
	 */
	public void togglePause();
	
	/**
	 * Fait utiliser le h�ros un objet de son inventaire
	 * @param reference
	 */
	public void utiliseObjet(int reference);
	
}
