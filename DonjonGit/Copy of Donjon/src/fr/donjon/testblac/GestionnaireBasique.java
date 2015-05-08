package fr.donjon.testblac;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class GestionnaireBasique extends GestionnaireSalle {

	
	public GestionnaireBasique() {
		super(new SalleSimple(new Heros(200,200), new Case_herbe()), 100, 1);
		
		this.sActuelle.setEcouteur(this);
		this.sActuelle.activerLesPortes(true);
	}

	
	@Override
	public void changerDeSalle(Vecteur dir) {
		changementSalle(position.ajoute(dir));
	}

	
	@Override
	public Salle fournirNouvelleSalle() {
		SalleSimple sh = new SalleSimple(sActuelle.hero, new Case_dalle_sol());
		sh.setEcouteur(this);
		return sh;
	}

}
