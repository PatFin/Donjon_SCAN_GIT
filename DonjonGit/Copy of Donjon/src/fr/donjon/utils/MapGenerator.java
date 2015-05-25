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
	 * Rempli un tableau des dimensions indiqu�es de la taille demand�es
	 * @param c le type de cases dont il faut remplir le tableau
	 * @param w largeur du tableau � fournir
	 * @param h hauteur du tableau � fournir
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
	 * G�n�re un tableau de cases al�atoires des dimensions indiqu�es � partir d'un ProbabilityMap
	 * @param map contient les types de cases et leurs probabilit�s respectives
	 * @param w largeur du tableau � fournir
	 * @param h hauteur du tableau � fournir
	 * @return un tableau al�atoire de dimensions w*h rempli de cases
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
	 * G�n�re un tableau de cases de dimensions donn�es en param�tre. 
	 * randomChateauLave propose une distribution al�atoire de:
	 * CaseLave 	proba 0.1
	 * CaseRocher 	proba 0.1
	 * CaseDalle 	proba 0.8
	 * @param w largeur du tableau � fournir.
	 * @param h hauteur du tableau � fournir.
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
	 * G�n�re un tableau de cases de dimensions donn�es en param�tre. 
	 * randomChateauRocher propose une distribution al�atoire de:
	 * CaseRocher 	proba 0.2
	 * CaseDalle 	proba 0.8
	 * @param w largeur du tableau � fournir.
	 * @param h hauteur du tableau � fournir.
	 * @return un tableau de largeur w et hauteur h.
	 */
	public static Case[][] randomChateauRochers(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseRocher() , 0.10 );
		
		map.addProba( new CaseDalle() , 0.90 );

		return generateFromProbabilities(map, w, h);

	}

	/**
	 * G�n�re un tableau de cases de dimensions donn�es en param�tre. 
	 * randomFor�t propose une distribution al�atoire de cases:
	 * CaseLiane 	proba 0.1
	 * CaseHerbe 	proba 0.9
	 * @param w largeur du tableau � fournir.
	 * @param h hauteur du tableau � fournir.
	 * @return un tableau de largeur w et hauteur h.
	 */
	public static Case[][] randomForet(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseLiane() , 0.10 );

		map.addProba( new CaseHerbe() , 0.90 );

		return generateFromProbabilities(map, w, h);
	}

	/**
	 * Donne un tableau de cases g�n�r� al�atoirement ou une salle d�j� existante cr�� avec l'�diteur.
	 * Attention ! les param�tres ne peuvent pas changer les dimensions des salles cr�� avec l'�diteur!
	 * @param w largeur du tableau
	 * @param h hauteur du tableau
	 * @return tableau de cases qui pourra (entre-autres) servir � cr�er un salle
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
