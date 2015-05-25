/**
 * 
 */
package fr.donjon.salles;

import java.util.ArrayList;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseFendue;
import fr.donjon.cases.CasePorte;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 * Salle qui est destinée à ne contenir que Quatre portes au maximum au points cardinaux,
 * la gestion du changement de salle et simplifiée pour ce type de salle.
 * 
 * @author Baptiste
 *
 */
public class SalleQuatre extends Salle {


	/**
	 * Constructeur
	 * 
	 * @param h le h�ros control� par le joueur
	 * @param persos la liste des personnage pr�sent dans la salle (h doit s'y trouver)
	 * @param c tableau de cases composant la salle
	 */
	public SalleQuatre(Heros h, ArrayList<Personnage> persos, Case[][] c) {
		super(h, persos, c);
	}

	/**
	 * Constructeur
	 * 
	 * @param h h�ro contr�l� par le joueur
	 * @param cases le tableau de cases composant la salle
	 */
	public SalleQuatre(Heros h, Case[][] cases) {
		super(h, cases);
	}

	/**
	 * Constructeur
	 * 
	 * @param w largeur de la salle
	 * @param h hauteur de la salle
	 */
	public SalleQuatre(int w, int h) {
		super(w, h);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.salles.Salle#addDoor(fr.donjon.utils.Orientation, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void addDoor(Orientation o, Salle[][] sMap) {

		int w = cases.length;
		int h = cases[0].length;
		
		//On ajoute une case porte et une case sur laquelle on peut marcher juste devant.
		switch(o){
		case NORD:
			cases[w/2][0] = new CasePorte(this, new Vecteur(w/2,1), Orientation.NORD);
			cases[w/2][1] = new CaseFendue();
			break;
		case SUD:
			cases[w/2][h-1] = new CasePorte(this, new Vecteur(w/2, h-2), Orientation.SUD);
			cases[w/2][h-2] = new CaseFendue();
			break;
		case EST:
			cases[w-1][h/2] = new CasePorte(this, new Vecteur(w-2, h/2), Orientation.EST);
			cases[w-2][h/2] = new CaseFendue();
			break;
		case OUEST:
			cases[0][h/2] = new CasePorte(this, new Vecteur(1, h/2), Orientation.OUEST);
			cases[1][h/2] = new CaseFendue();
			break;

		}
		
		super.trouverLesPortes();
		
		Vecteur voisin =  this.detecteSalleDansTableau(sMap).ajoute(o.getUnitVector());
		SalleQuatre sVoisine = (SalleQuatre) sMap[(int)voisin.x][(int)voisin.y];
		
		if(sVoisine != null){
			CasePorte cV = sVoisine.getPorte(o.opposite());
			cV.setDestination(this.getPorte(o));
			this.getPorte(o).setDestination(cV);
		}
		
	}
	
	/**
	 * Detecte les salles potentielles situ�es autours de cette instance
	 * @param sMap le tableau de salle
	 */
	@Override
	public void createPorteSalleVoisines(Salle[][] sMap){
		
		Vecteur position = detecteSalleDansTableau(sMap);
		
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
	 * Donne la case porte de l'orientation donnée en parametre
	 * 
	 * @param o l'orientation de la porte souhaitée
	 * 
	 * @return La CasePorte dans l'orientation souhaitée, celle ci est unique car on est dans une SalleQuatre
	 */
	public CasePorte getPorte(Orientation o){
		
		for(CasePorte c : this.portes){
			if(c.collisionPorte.lien.getOrientation() == o){
				return c;
			}
		}
		return null;
	}


}
