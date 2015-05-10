/**
 * 
 */
package fr.donjon.testblac;

import java.util.ArrayList;

import fr.donjon.cases2.Case;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;

/**
 * @author Baptiste
 *
 */
public abstract class SalleQuatre extends Salle {

	//final static int W = 15;
	//final static int H = 10;
	
	public SalleQuatre(Heros h, ArrayList<Personnage> persos, Case[][] c) {
		super(h, persos, c);

	}

	public SalleQuatre(Heros h, Case[][] cases) {
		super(h, cases);

	}

	public SalleQuatre(int w, int h) {
		super(w, h);

	}

	
	
	

}
