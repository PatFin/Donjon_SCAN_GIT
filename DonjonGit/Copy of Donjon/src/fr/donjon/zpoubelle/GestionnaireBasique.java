package fr.donjon.zpoubelle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */
public class GestionnaireBasique extends GestionnaireSalle {


	/**
	 * Constructeur
	 */
	public GestionnaireBasique() {
		super( new Salle2PA(new Heros(100,100), 15,10, 
				Orientation.EST ), 
				3, 3 );
		sActuelle.addEnemy(new Squelette(400, 400, sActuelle.hero, 1));
		
		this.sActuelle.setEcouteur(this);
		this.sActuelle.activerLesPortes(true);
	}


	/**
	 * Fournit une nouvelle salle pour ce genre de donjon.
	 * Il s'agit d'un carr� 3x3.
	 */
	@Override
	public Salle fournirNouvelleSalle(int x, int y) {

		
		Salle sh = null;
		
		int w = smap.length;
		int h = smap[0].length;

		if(x == 0 && y == 0){
			sh = new Salle2PA(sActuelle.hero, MapGenerator.randomMap(15, 10), Orientation.EST);
		}
		else if( x == w - 1 && y == 0){
			sh = new Salle2PA(sActuelle.hero, MapGenerator.randomMap(15, 10),Orientation.SUD);
		}
		else if( x == 0 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, MapGenerator.randomMap(15, 10), Orientation.NORD);
		}
		else if( x == w - 1 && y == h - 1){
			sh = new Salle2PA(sActuelle.hero, MapGenerator.randomMap(15, 10), Orientation.OUEST);
		}
		else if( x == 0){
			sh = new Salle3P(sActuelle.hero, MapGenerator.randomMap(15, 10), Orientation.OUEST);
		}
		else if( y == 0){
			sh = new Salle3P(sActuelle.hero, 15, 10, Orientation.NORD);
		}
		else if( x == w - 1){
			sh = new Salle3P(sActuelle.hero,15, 10, Orientation.EST);
		}
		else if( y == h - 1){
			sh = new Salle3P(sActuelle.hero, 15, 10, Orientation.SUD);
		}
		else{
			sh = new Salle4P(sActuelle.hero,15, 10);
		}

		sh.setEcouteur(this);
		
		return sh;
	}

}
