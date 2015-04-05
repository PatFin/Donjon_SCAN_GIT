package fr.donjon.utils;


/*Cette classe permet d'afficher les images au bonnes coordonées en prenant 
	en compte les insets de la fenetre, il suffit d' appeller EcranAdapteur.adapte(x,y) et 
	utiliser le vecteur recu pour avoir les bonnes corrdonnées.
*/
public class EcranAdapteur {

	
	public EcranAdapteur() {

	}
	
	/**
	 * Retourne les coordonnées pour afficher sur l'écran utilisable
	 * @param x Coordonnée x
	 * @param y Coordonnée y
	 * @return Retourne le vecteur avec les coordonnées modifiées
	 */
	public static Vecteur adapte(int x, int y){
		return new Vecteur(x+3 , y+26);
	}

}
