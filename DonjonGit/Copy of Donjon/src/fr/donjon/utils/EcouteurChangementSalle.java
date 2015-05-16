package fr.donjon.utils;




public interface EcouteurChangementSalle {

	/**
	 * Permet de prevenir l'écouteur que l'on veut changer de Salle, on connait la direction
	 * de changement de Salle et le héro a utiliser dans la nouvelle Salle
	 * @param l le lien de la salle actuelle vers la prochaine salle
	 */
	public void changerDeSalle(Link l);
	
	
}