/**
 * 
 */
package fr.donjon.utils;

/**
 * @author Baptiste
 *
 */


public interface EcouteurClavier {

	
	public void attaque(Orientation o);
	
	public void stopAttaque();
	
	public void deplacement(Vecteur v);
	
	public void utiliseObjet(int reference);
	
	public void togglePause();
	
	public void stopDeplacement();
	
}
