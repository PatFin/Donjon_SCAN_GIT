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

		float nh = amount/(w*h) ;

		float t = 0;

		for(int y = 0 ; y < h ; y++){
			for(int x = 0 ; x < w ; x++){
				if(  t%1 < 0.001 || 1 - (t%1) < 0.001  )l.add( new Squelette( (x+1)*Case.TAILLE, (y+1)*Case.TAILLE, he, 1, s) );
				t+=nh;

			}

		}

		return l;
	}

	public static ArrayList<Ennemis> generateCircle( Heros he, Salle s, int amount, int radius){

		ArrayList<Ennemis> l = new ArrayList<Ennemis>();

		double angle = Math.PI*2/amount ;

		Vecteur pc = s.getRoomCenter().multiplie(Case.TAILLE);

		Vecteur np = new Vecteur(0,0);

		for(double i = 0 ; i < 2*Math.PI ; i+=angle){

			np = pc.ajoute((new Vecteur( Math.cos(i), Math.sin(i) )).multiplie(radius));

			l.add( new Squelette( (int)np.x, (int)np.y, he, 1, s) );
		}

		return l;
	}

	public static ArrayList<Ennemis> generateOverLave(Heros he, Salle s, float proba){

		float w = (s.cases.length - 2) ;
		float h = (s.cases[0].length - 2) ;

		ArrayList<Case> csp = new ArrayList<Case>();
		ArrayList<Ennemis> l = new ArrayList<Ennemis>();

		for(int y = 0 ; y < h ; y++){
			for(int x = 0 ; x < w ; x++){
				s.cases[x][y].setCollisionBoxLocation(x, y);
				if(!s.cases[x*+1][y+1].getClass().getSimpleName().equals("CaseMur")){
					csp.add(s.cases[x+1][y+1]);
				}

			}
		}

		for( Case c : csp){

			double r = Math.random();

			if(r <= proba) l.add( new Squelette(c.limites.x + Case.TAILLE/2 , c.limites.y + Case.TAILLE/2, he,1 ,s) );

		}

		return l;
	}
}
