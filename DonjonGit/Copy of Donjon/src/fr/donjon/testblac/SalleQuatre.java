/**
 * 
 */
package fr.donjon.testblac;

import java.util.ArrayList;

import fr.donjon.cases2.Case;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class SalleQuatre extends Salle {

	//TODO remove unused bits
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

	/**
	 * Detecte les salles potentielles situées autours de cette instance
	 * @param sMap le tableau de salle
	 */
	public void createPorteSalleVoisines(Salle[][] sMap){
		Vecteur position = detectSalleDansTableau(sMap);
		
		if(position.x>0){
			this.addDoor(Orientation.OUEST);
		}
		if(position.x<sMap.length-1){
			this.addDoor(Orientation.EST);
		}

		if(position.y>0){
			this.addDoor(Orientation.NORD);
		}
		if(position.x<sMap[0].length-1){
			this.addDoor(Orientation.SUD);
		}
		
	}
	
	/**
	 * Créé une case sur un côté de la salle selon l'orientation
	 * @param o
	 */
	private void addDoor(Orientation o) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Detecte les coordonnée d'une salle dans un tableau
	 * @param sMap le tableau de salle
	 * @return vecteur indiquent la position dans le tableau, (-1,-1) si non trouvée
	 */
	private Vecteur detectSalleDansTableau(Salle[][] sMap){
		Vecteur v = new Vecteur(-1,-1);
		
		for(int i=0; i<sMap.length; i++){
			for(int j=0; j<sMap[0].length; j++){
				if(sMap[i][j] == this){
					v.setLocation(i, j);
				}
			}
		}
		
		return v;
	}

}
