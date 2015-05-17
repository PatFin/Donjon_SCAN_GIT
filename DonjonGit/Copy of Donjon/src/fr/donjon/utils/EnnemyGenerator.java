/**
 * 
 */
package fr.donjon.utils;

import java.util.ArrayList;

import fr.donjon.cases.Case;
import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.salles.Salle;

/**
 * @author Baptiste
 *
 */
public class EnnemyGenerator {

	/**
	 * 
	 */
	public EnnemyGenerator() {

		
	}
	
	public static ArrayList<Ennemis> fillQuareSquare(Heros he, Salle s, float amount){

		ArrayList<Ennemis> l = new ArrayList<Ennemis>();
	
		float w = (s.cases.length - 2) ;
		float h = (s.cases[0].length - 2) ;
		
		System.out.println("W: "+w);
		System.out.println("H: "+h);
		
		float nh = amount/(w*h) ;
		
		float t = 0;
		
		for(int y = 0 ; y < h ; y++){
			for(int x = 0 ; x < w ; x++){
				if(  t%1 < 0.001 || 1 - (t%1) < 0.001  )l.add( new Squelette( (x+1)*Case.TAILLE, (y+1)*Case.TAILLE, he, 1) );
				t+=nh;
				
			}
			
		}
		
		System.out.println("Generated: "+l.size());
		return l;
	}

}
