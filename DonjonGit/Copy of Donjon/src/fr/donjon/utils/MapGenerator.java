/**
 * 
 */
package fr.donjon.utils;

import java.io.File;
import java.util.LinkedList;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseHerbe;
import fr.donjon.cases.CaseLave;
import fr.donjon.cases.CaseLiane;
import fr.donjon.cases.CaseRocher;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.editor.SalleDescription;



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

	public static Case[][] randomMap(int w, int h){

		LinkedList<File> files =  MapFileHandler.getMapList();
		LinkedList<SalleDescription> sls = new LinkedList<SalleDescription>();
		
		for( File f : files){
			sls.add(MapFileHandler.getFileToDescription(f));
		}
		
		int r = (int) Math.random() * (3 + sls.size() );
		
		
		if( r == 0) return randomChateauLave(w, h);
		
		else if (r == 1) return randomChateauRochers(w, h);
		
		else if( r == 2 ) return randomForet(w, h);
		
		else return sls.get( (r - 3) ).getMatrix();
		
	}

	public static Case[][] randomForet(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseLiane() , 0.10 );

		map.addProba( new CaseHerbe() , 0.90 );

		return generateFromProbabilities(map, w, h);
	}

	public static Case[][] randomChateauLave(int w, int h){

		//TODO remove this
		return fillWith(new CaseSwitch(), w, h);
		
		/*
		ProbabilityMap map = new ProbabilityMap();
		
		map.addProba( new CaseLave() , 0.10 );

		map.addProba( new CaseRocher() , 0.10 );

		map.addProba( new CaseDalle() , 0.75 );
		
		return generateFromProbabilities(map, w, h);
		*/
	}

	public static Case[][] randomChateauRochers(int w, int h){

		ProbabilityMap map = new ProbabilityMap();

		map.addProba( new CaseRocher() , 0.20 );
		
		map.addProba( new CaseDalle() , 0.80 );

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
