package fr.donjon.testblac;

import fr.donjon.cases2.CaseDalle;
import fr.donjon.cases2.CaseHerbe;
import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class GestionnaireBasique extends GestionnaireSalle {


	public GestionnaireBasique() {
		super(new Salle2PA(new Heros(50,50), new CaseHerbe(), Orientation.EST), 3, 3);

		
		this.sActuelle.setEcouteur(this);
		this.sActuelle.activerLesPortes(true);
	}


	@Override
	public void changerDeSalle(Vecteur dir) {
		changementSalle(dir);
	}


	@Override
	public Salle fournirNouvelleSalle(int x, int y) {

		
		Salle sh = null;

		int w = smap.length;
		int h = smap[0].length;

		if(x == 0 && y == 0){
			sh = new Salle2PA(sActuelle.hero, new CaseDalle(), Orientation.EST);
		}
		else if( x == w - 1 && y == 0){
			sh = new Salle2PA(sActuelle.hero, new CaseDalle(), Orientation.SUD);
		}
		else if( x == 0 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, new CaseDalle(), Orientation.NORD);
		}
		else if( x == w - 1 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, new CaseDalle(), Orientation.OUEST);
		}
		else if( x == 0){
			sh = new Salle3P(sActuelle.hero, new CaseDalle(), Orientation.OUEST);
		}
		else if( y == 0){
			sh = new Salle3P(sActuelle.hero, new CaseDalle(), Orientation.NORD);
		}
		else if( x == w - 1){
			sh = new Salle3P(sActuelle.hero, new CaseDalle(), Orientation.EST);
		}
		else if( y == h - 1){
			sh = new Salle3P(sActuelle.hero, new CaseDalle(), Orientation.SUD);
		}
		else{
			sh = new Salle4P(sActuelle.hero, new CaseDalle());
		}

		sh.setEcouteur(this);
		
		return sh;
	}

}
