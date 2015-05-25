package fr.donjon.zpoubelle;

public class JeuNonLineaire extends GamePanel {

	public JeuNonLineaire() throws CustomException {
		//The GamePanel is created and initialized
		super();
				
		//The associated "gestionnaire" is created
		gestionnaire = new GestionnaireJeuInfini(this);
	}

}
