package fr.donjon.zpoubelle;



public class JeuLineaire extends GamePanel {

	public JeuLineaire() {
		//The GamePanel is created and initialized
		super();
		
		//The associated "gestionnaire" is created
		gestionnaire = new GestionnaireJeuLineaire(this);
	}

}
