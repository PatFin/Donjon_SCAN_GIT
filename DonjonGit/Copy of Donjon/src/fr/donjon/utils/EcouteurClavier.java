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
	
	public void stopAttaque(Orientation o);
	
	public void deplacement(Orientation o);
	
	public void utiliseObjet(int reference);
	
	public void togglePause();
	
	public void stopDeplacement(Orientation o);
	
}
