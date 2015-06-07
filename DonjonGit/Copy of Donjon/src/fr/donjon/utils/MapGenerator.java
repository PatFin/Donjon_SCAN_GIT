/**
 * 
 */
package fr.donjon.utils;

import java.util.LinkedList;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseFendue;
import fr.donjon.cases.CaseHerbe;
import fr.donjon.cases.CaseLave;
import fr.donjon.cases.CaseLiane;
import fr.donjon.cases.CaseRocher;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.editor.SalleDescription;



/**
 * @author Baptiste
 *
 */
public final class MapGenerator {

	/**
	 * Rempli un tableau des dimensions indiquï¿½es de la taille demandï¿½es
	 * @param c le type de cases dont il faut remplir le tableau
	 * @param w largeur du tableau ï¿½ fournir
	 * @param h hauteur du tableau ï¿½ fournir
	 * @return un tableau de Case "c" de largeur w hauteur h.
	 */
	public static Case[][] fillWith(Case c, int w, int h){

		Case[][] cs = new Case[w][h];

		for(int y = 0 ; y < h ; y++){
			for(int x = 0 ; x < w ; x++){
				cs[x][y] = c.clone();
				cs[x][y].setCollisionBoxLocation(x, y);
			}
		}

		return cs;
	}

	/**
	 * Gï¿½nï¿½re un tableau de cases alï¿½atoires des dimensions indiquï¿½es ï¿½ partir d'un ProbabilityMap
	 * @param map contient les types de cases et leurs probabilitï¿½s respectives
	 * @param w largeur du tableau ï¿½ fournir
	 * @param h hauteur du tableau ï¿½ fournir
	 * @return un tableau alï¿½atoire de dimensions w*h rempli de cases
	 */
	public static Case[][] generateFromProbabilities( ProbabilityMap map, int w, int h ){

		Case[][] cs = new Case[w][h];

		for( int y = 0 ; y < h ; y++){
			for( int x = 0 ; x < w ; x++){
				cs[x][y] = map.getCase();
				cs[x][y].setCollisionBoxLocation(x, y);
			}
		}

		return cs;
	}

	
	/**
	 * Génère un tableau de cases de dimensions donnï¿½es en paramï¿½tre. 
	 * randomChateauLave propose une distribution alï¿½atoire de:
	 * CaseLave 	proba 0.1
	 * CaseRocher 	proba 0.1
	 * CaseDalle 	proba 0.8
	 * @param w largeur du tableau ï¿½ fournir.
	 * @param h hauteur du tableau ï¿½ fournir.
	 * @return un tableau de largeur w et hauteur h.
	 */
	public static Case[][] randomChateauLave(int w, int h){

		ProbabilityMap map = new ProbabilityMap();
		
		map.addProba( new CaseLave() , 0.10 );

		map.addProba( new CaseFendue() , 0.10 );

		map.addProba( new CaseDalle() , 0.80 );
		
		return generateFromProbabilities(map, w, h);
	}

	
	/**
	 * Gï¿½nï¿½re un tableau de cases de dimensions donnï¿½es en paramï¿½tre. 
	 * randomChateauRocher propose une distribution alï¿½atoire de:
	 * CaseRocher 	proba 0.2
	 * CaseDalle 	proba 0.8
	 * @param w largeur du tableau ï¿½ fournir.
	 * @param h hauteur du tableau ï¿½ fournir.
	 * @return un tableau de largeur w et hauteur h.
	 */
	public static Case[][] randomChateauRochers(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseRocher() , 0.10 );
		
		map.addProba( new CaseDalle() , 0.90 );

		return generateFromProbabilities(map, w, h);

	}

	/**
	 * Gï¿½nï¿½re un tableau de cases de dimensions donnï¿½es en paramï¿½tre. 
	 * randomForï¿½t propose une distribution alï¿½atoire de cases:
	 * CaseLiane 	proba 0.1
	 * CaseHerbe 	proba 0.9
	 * @param w largeur du tableau ï¿½ fournir.
	 * @param h hauteur du tableau ï¿½ fournir.
	 * @return un tableau de largeur w et hauteur h.
	 */
	public static Case[][] randomForet(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseLiane() , 0.10 );

		map.addProba( new CaseHerbe() , 0.90 );

		return generateFromProbabilities(map, w, h);
	}

	/**
	 * Donne un tableau de cases gï¿½nï¿½rï¿½ alï¿½atoirement ou une salle dï¿½jï¿½ existante crï¿½ï¿½ avec l'ï¿½diteur.
	 * Attention ! les paramï¿½tres ne peuvent pas changer les dimensions des salles crï¿½ï¿½ avec l'ï¿½diteur!
	 * @param w largeur du tableau
	 * @param h hauteur du tableau
	 * @return tableau de cases qui pourra (entre-autres) servir ï¿½ crï¿½er un salle
	 */
	public static Case[][] randomMap(int w, int h){

		LinkedList<SalleDescription> sls = MapFileHandler.getWorkingMaps();
		
		int r = (int) (Math.random()*5);
		
		if( r == 0) return randomChateauLave(w, h);
		
		else if (r == 1) return randomChateauRochers(w, h);
		
		else if( r == 2 ) return randomForet(w, h);
		
		r=(int)Math.round((Math.random()*sls.size()));
		return sls.get(r%sls.size()).getMatrix();
		
	}

}
