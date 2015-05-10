/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CaseDalle;
import fr.donjon.cases2.CaseHerbe;
import fr.donjon.cases2.CaseLave;
import fr.donjon.cases2.CaseRocher;
import fr.donjon.cases2.CaseWater;



/**
 * @author Baptiste
 *
 */
public class MapGenerator {

	/**
	 * 
	 */
	public MapGenerator() {

	}


	public static Case[][] randomForet(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseHerbe() , 0.90 );

		map.addProba( new CaseWater() , 0.10 );

		return generateFromProbabilities(map, w, h);
	}

	public static Case[][] randomChateauLave(int w, int h){
		
		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseDalle() , 0.80 );

		map.addProba( new CaseLave() , 0.10 );
		
		map.addProba( new CaseRocher() , 0.10 );

		return generateFromProbabilities(map, w, h);
	}

	public static Case[][] randomChateauRochers(int w, int h){
		
		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseDalle() , 0.80 );

		map.addProba( new CaseRocher() , 0.20 );

		return generateFromProbabilities(map, w, h);

	}

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

}
