/**
 * 
 */
package fr.donjon.utils;

import fr.donjon.classes.Personnage;

/**
 * 
 * Dermet de décrire des effet appliquables a un personnage et modifiant ses stats.
 * 
 * @author Baptiste
 *
 */
public class Effet{

	Personnage p;
	
	public int atk;		//					Attaque
	public int def;		//					Defense
	public int vie;		//Bonus/Malus de	Vie
	public int vit;		//					Vitesse
	public Effet(Personnage p, int vie, int def, int vit, int atk) {
		super();
		this.p = p;
		this.vie = vie;
		this.def = def;
		this.vit = vit;
		this.atk = atk;
	}
	/**
	 * @return the atk
	 */
	public int getAtk() {
		return atk;
	}
	/**
	 * @return the def
	 */
	public int getDef() {
		return def;
	}
	/**
	 * @return the p
	 */
	public Personnage getP() {
		return p;
	}
	/**
	 * @return the vie
	 */
	public int getVie() {
		return vie;
	}
	/**
	 * @return the vit
	 */
	public int getVit() {
		return vit;
	}
	/**
	 * @param atk the atk to set
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}
	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}
	/**
	 * @param p the p to set
	 */
	public void setP(Personnage p) {
		this.p = p;
	}
	/**
	 * @param vie the vie to set
	 */
	public void setVie(int vie) {
		this.vie = vie;
	}
	/**
	 * @param vit the vit to set
	 */
	public void setVit(int vit) {
		this.vit = vit;
	}

	
	 
	
	
	


}
