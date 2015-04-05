package fr.donjon.utils;


/*Cette classe permet d'afficher les images au bonnes coordon�es en prenant 
	en compte les insets de la fenetre, il suffit d' appeller EcranAdapteur.adapte(x,y) et 
	utiliser le vecteur recu pour avoir les bonnes corrdonn�es.
*/
public class EcranAdapteur {

	
	public EcranAdapteur() {

	}
	
	/**
	 * Retourne les coordonn�es pour afficher sur l'�cran utilisable
	 * @param x Coordonn�e x
	 * @param y Coordonn�e y
	 * @return Retourne le vecteur avec les coordonn�es modifi�es
	 */
	public static Vecteur adapte(int x, int y){
		return new Vecteur(x+3 , y+26);
	}

}
