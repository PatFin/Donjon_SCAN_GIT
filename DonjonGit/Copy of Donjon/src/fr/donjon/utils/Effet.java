/**
 * 
 */
package fr.donjon.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * 
 * Décrit un bonus/malus sur les stats d'un personnage
 * 
 * @author Baptiste
 *
 */
public class Effet {

	public int vie;
	public int def;
	public int vit;
	public int atk;

	int duree;

	boolean enCours;
	boolean constant;

	Timer t;

	/**
	 * 
	 * Crée un effet de durée infinie
	 * 
	 * @param eVie
	 * @param eDef
	 * @param eVit
	 * @param atk
	 */
	public Effet(int eVie, int eDef, int eVit, int atk, boolean cst) {
		this(eVie, eDef, eVit, atk, 0, cst);
	}

	public Effet( int eVie, int eDef, int eVit, int atk, int eDur, boolean cst) {

		this.vie = eVie;
		this.def = eDef;
		this.vit = eVit;
		this.atk = atk;

		this.duree = eDur;
		this.enCours = true;
		
		this.constant = cst;

		if(duree > 0){
			t = new Timer(duree, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					enCours = false;
					t.stop();
				}
			});

			t.start();
		}
	}

	/**
	 * Renvoie la somme avec un autre effet sans modifier l'objet actuel,
	 * la somme d'une stats vaut zéro si la valeur est négative.
	 * 
	 * @param e
	 * @return La somme des effets
	 */
	public Effet sum(Effet e){
		return new Effet( vie+ e.vie /*>= 0 ? vie+ e.vie : 0*/ ,
				def+ e.def >= 0 ? def+ e.def : 0,
						vit+ e.vit >= 0 ? vit+ e.vit : 0,
								atk+ e.atk >= 0 ? atk+ e.atk : 0, false);
	}


}
