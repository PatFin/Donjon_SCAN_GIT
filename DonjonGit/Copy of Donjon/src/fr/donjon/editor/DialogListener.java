/**
 * 
 */
package fr.donjon.editor;

import fr.donjon.utils.Vecteur;

/**
 * 
 * Interface d'�coute pour un r�sultat VALIDER/ANNULER
 * 
 * @author Baptiste
 *
 */
public interface DialogListener {

	public void onCancel();
	
	public void onValidate(String name, int index);
	
	/**
	 * 
	 * @param v	Vecteur contenant la taille du terrain a cr�er 
	 */
	public void onValidate(Vecteur v);
	
}
