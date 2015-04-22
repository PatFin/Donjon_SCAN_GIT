/**
 * 
 */
package fr.donjon.classes;

/**
 * 
 * Décrit un bonus/malus sur les stats d'un personnage
 * 
 * @author Baptiste
 *
 */
public class Effet {
	
	int vie;
	int def;
	int vit;
	int atk;

	public Effet(int eVie, int eDef, int eVit, int atk) {
		super();
		this.vie = eVie;
		this.def = eDef;
		this.vit = eVit;
		this.atk = atk;
	}

	/**
	 * Renvoie la somme avec un autre effet dans modifier l'objet actuel,
	 * la somme d'une stats vaut zéro si la valeur est négative.
	 * 
	 * @param e
	 * @return La somme des effets
	 */
	public Effet sum(Effet e){
		return new Effet( vie+ e.vie >= 0 ? vie+ e.vie : 0 ,
				def+ e.def >= 0 ? def+ e.def : 0,
						vit+ e.vit >= 0 ? vit+ e.vit : 0,
								atk+ e.atk >= 0 ? atk+ e.atk : 0);
	}


}
