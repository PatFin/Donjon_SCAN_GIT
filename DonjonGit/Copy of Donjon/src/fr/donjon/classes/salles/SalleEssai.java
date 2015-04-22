/**
 * 
 */
package fr.donjon.classes.salles;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

/**
 * 
 * Permet d'essayer une salle crée a l'éditeur
 * 
 * @author Baptiste
 *
 */
public class SalleEssai extends SalleAbs {

	/**
	 * @param ecran
	 * @param h
	 */
	public SalleEssai(Rectangle ecran, Heros h, Case[][] cases) {
		super(ecran, h);
		this.cases = cases;
		generateImage();
		
	}

	
	@Override
	protected void generateRoom() {
		
	}

	
	@Override
	protected void setDestinationPlaces() {

	}

	
	@Override
	protected void setDoorPlaces() {

	}


	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SalleAbs clone(Heros h, Link l, Orientation o) {
		// TODO Auto-generated method stub
		return null;
	}

}
