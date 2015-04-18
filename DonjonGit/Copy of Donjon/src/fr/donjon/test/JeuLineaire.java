package fr.donjon.test;

import fr.donjon.start.GamePanel;

public class JeuLineaire extends GamePanel {

	public JeuLineaire() {
		//The GamePanelis created and initialized
		super();
		
		//The associated "gestionnaire" is created
		gestionnaire = new GestionnaireJeuLineaire(this);
	}

}
