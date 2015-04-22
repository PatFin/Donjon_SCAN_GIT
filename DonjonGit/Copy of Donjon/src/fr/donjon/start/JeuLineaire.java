package fr.donjon.start;


public class JeuLineaire extends GamePanel {

	public JeuLineaire() {
		//The GamePanelis created and initialized
		super();
		
		//The associated "gestionnaire" is created
		gestionnaire = new GestionnaireJeuLineaire(this);
	}

}
