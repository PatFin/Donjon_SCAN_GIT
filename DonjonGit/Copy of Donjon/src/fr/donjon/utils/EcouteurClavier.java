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
	 * Fait attaquer le hero controle par le joueur
	 * @param v le vecteur indiquant la direction de l'attaque
	 */
	public void attaque(Vecteur v);
	
	/**
	 * Fait se deplacer le hero controle par le joueur
	 * @param v le vecteur indiquant la direction de déplacement
	 */
	public void deplacement(Vecteur v);
	
	/**
	 * Stoppe l'attaque du hero controle par le joueur quand celui-ci relache les touches d'attaques.
	 */
	public void stopAttaque();
	
	/**
	 * Fait s'arreter de bouger le heros quand le joueur relache les touches de deplacement
	 */
	public void stopDeplacement();
	
	public void toggleMap();
	
	/**
	 * Permet de mettre le jeu en pause
	 */
	public void togglePause();
	
	/**
	 * Fait utiliser le heros un objet de son inventaire
	 * @param reference l'entier contenant l'index de l'objet à utiliser dans son inventaire
	 */
	public void utiliseObjet(int reference);
	
}
