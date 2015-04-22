/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.start.GamePanel;
import fr.donjon.start.GestionnaireJeuLineaire;


/**
 * Permet de cr�er un jeu de type infini (lin�aire)
 * 
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel{

	
	/**
	 * Permet de creer un jeu de type lin�aire, le gestionnaire associ� et donc un GestionnaireJeuInfini
	 */
	public JeuLineaireBlac(){
		super();
		gestionnaire = new GestionnaireJeuLineaire(this);	//On cr�e un gestionnaire infini
		this.changeSalle(gestionnaire.getCurrentRoom());	//On affiche la premi�re salle du donjon
		
	}
	
	/**
	 * TODO !
	 * Pour l'�diteur ?
	 * @param cases
	 */
	public JeuLineaireBlac(Case[][] cases){
		super();
		Salle s  =  new Salle(new Heros(100,100), cases, new Rectangle(0,0,64*15,64*10), null);
		//TODO: modifier ca : this.changeSalle(s);
	}

	public void update() {
		super.update();
	}




	
	
}

