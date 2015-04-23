package fr.donjon.start;

public class JeuNonLineaire extends GamePanel {

	public JeuNonLineaire() {
		//The GamePanel is created and initialized
		super();
				
		//The associated "gestionnaire" is created
		gestionnaire = new GestionnaireJeuInfini(this);
	}

}
