/**
 * 
 */
package fr.donjon.editor;

import fr.donjon.utils.Vecteur;

/**
 * 
 * Interface d'écoute pour un résultat VALIDER/ANNULER
 * 
 * @author Baptiste
 *
 */
public interface DialogListener {

	/**
	 * 
	 * @param v	Vecteur contenant la taille du terrain a créer 
	 */
	public void onValidate(Vecteur v);
	
	public void onCancel();
	
}
