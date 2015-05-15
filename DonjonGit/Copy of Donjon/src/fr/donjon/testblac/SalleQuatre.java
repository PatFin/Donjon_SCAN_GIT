/**
 * 
 */
package fr.donjon.testblac;

import java.util.ArrayList;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CaseFendue;
import fr.donjon.cases2.CasePorte;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.Case_dalle_sol;

/**
 * @author Baptiste
 *
 */
public class SalleQuatre extends Salle {

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
	@Override
	public void createPorteSalleVoisines(Salle[][] sMap){
		Vecteur position = detectSalleDansTableau(sMap);
		
		if(position.x>0){
			this.addDoor(Orientation.OUEST, sMap);
		}
		if(position.x<sMap.length-1){
			this.addDoor(Orientation.EST,sMap);
		}

		if(position.y>0){
			this.addDoor(Orientation.NORD, sMap);
		}
		if(position.y<sMap[0].length-1){
			this.addDoor(Orientation.SUD, sMap);
		}
		
	}
	
	/**
	 * Créé une case sur un côté de la salle selon l'orientation
	 * @param o Orientation de la porte à créé
	 * @param smap le tableau de salles du donjon
	 */
	public void addDoor(Orientation o, Salle[][] sMap) {
		int w = cases.length;
		int h = cases[0].length;
		
		//On ajoute une case porte et une case sur laquelle on peut marcher juste devant.
		switch(o){
		case NORD:
			cases[(int)w/2][0] = new CasePorte(this, new Vecteur((int)w/2,1), Orientation.NORD);
			cases[(int)w/2][1] = new CaseFendue();
			break;
		case SUD:
			cases[(int)w/2][h-1] = new CasePorte(this, new Vecteur((int)w/2, h-2), Orientation.SUD);
			cases[(int)w/2][h-2] = new CaseFendue();
			break;
		case EST:
			cases[w-1][(int)h/2] = new CasePorte(this, new Vecteur(w-2, (int)h/2), Orientation.EST);
			cases[w-2][(int)h/2] = new CaseFendue();
			break;
		case OUEST:
			cases[0][(int)h/2] = new CasePorte(this, new Vecteur(1, (int)h/2), Orientation.OUEST);
			cases[1][(int)h/2] = new CaseFendue();
			break;

		}
		super.trouverLesPortes();
		
		//Si la salle voisine a déjà été créé, on initialise les liens
		//TODO
		Vecteur voisin =  this.detectSalleDansTableau(sMap).ajoute(o.getUnitVector());
		Salle sVoisine = sMap[(int)voisin.x][(int)voisin.y];
		if(sVoisine != null){
			CasePorte cV = sVoisine.getPorte(o.opposite());
			cV.setDestination(this.getPorte(o));
			this.getPorte(o).setDestination(cV);
		}
		
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
