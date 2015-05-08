package fr.donjon.testblac;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class GestionnaireBasique extends GestionnaireSalle {


	public GestionnaireBasique() {
		super(new Salle2PA(new Heros(50,50), new Case_herbe(), Orientation.EST), 3, 3);

		
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
			sh = new Salle2PA(sActuelle.hero, new Case_dalle_sol(), Orientation.EST);
		}
		else if( x == w - 1 && y == 0){
			sh = new Salle2PA(sActuelle.hero, new Case_dalle_sol(), Orientation.SUD);
		}
		else if( x == 0 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, new Case_dalle_sol(), Orientation.NORD);
		}
		else if( x == w - 1 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, new Case_dalle_sol(), Orientation.OUEST);
		}
		else if( x == 0){
			sh = new Salle3P(sActuelle.hero, new Case_dalle_sol(), Orientation.OUEST);
		}
		else if( y == 0){
			sh = new Salle3P(sActuelle.hero, new Case_dalle_sol(), Orientation.NORD);
		}
		else if( x == w - 1){
			sh = new Salle3P(sActuelle.hero, new Case_dalle_sol(), Orientation.EST);
		}
		else if( y == h - 1){
			sh = new Salle3P(sActuelle.hero, new Case_dalle_sol(), Orientation.SUD);
		}
		else{
			sh = new Salle4P(sActuelle.hero, new Case_dalle_sol());
		}

		sh.setEcouteur(this);
		
		return sh;
	}

}
