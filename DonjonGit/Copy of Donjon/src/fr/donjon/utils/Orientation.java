package fr.donjon.utils;

import java.util.ArrayList;

/**
 * Enumérateur. 
 * @author Patrick
 * 
 * Particulièrement utile pour décrire l'orientation d'un personnage et de choisir l'image associée.
 * Utilisé également dans les salles pour indiquer l'orientation des portes.
 * @see fr.donjon.utils.Animation
 * @see fr.donjon.cases.CollisionPorte
 */
public enum Orientation {
	EST,NORD,OUEST,SUD;
	

	/**
	 * Renvoi le vecteur associé à l'orientation.
	 * @return Vecteur unitaire dans la direction de l'orientation.
	 * @see fr.donjon.utils.Orientation#getUnitVector(Orientation)
	 */
	public Vecteur getUnitVector() {
		return Orientation.getUnitVector(this);
	}
	
	/**
	 * Renvoi l'orientation opposée à this.
	 * @return l'opposé de this.
	 * @see fr.donjon.utils.Orientation#opposite(Orientation)
	 */
	public Orientation opposite(){
		return Orientation.opposite(this);
	}

	
	/**
	 * Cette méthode renvoi une liste contennat chacune des orientations.
	 * Est utile lorsqu'on cherche à faire des boucles sur chaque orientation.
	 * @return ArrayList<Orientation> contenant {OUEST,EST,NORD,SUD} 
	 */
	public static ArrayList<Orientation> getOrientationList(){
		ArrayList<Orientation> o = new ArrayList<Orientation>();
		o.add(OUEST);
		o.add(EST);
		o.add(NORD);
		o.add(SUD);
		return o;
	}
	
	/**
	 * Donne le vecteur unitaire associé à une orientation.
	 * Par exemple l'orientation NORD correspond au vecteur (0,-1)
	 * L'orientation EST donnera le vecteur (-1,0)
	 * @param o l'orientation dont on veut obtenir un vecteur
	 * @return vecteur unitaire dans la direction de l'orientation donnée.
	 */
	public static Vecteur getUnitVector(Orientation o){
		switch(o){
		case NORD:
			return Vecteur.vNord;
		case SUD:
			return Vecteur.vSud;
		case EST:
			return Vecteur.vEst;
		case OUEST:
			return Vecteur.vOuest;
		default:
			return Vecteur.vNull;
		}
	}
	
	/**
	 * Donne l'orientation opposée à celle donnée en paramètre.
	 * NORD est l'opposé de SUD et vice-versa.
	 * EST est l'opposé de OUEST et vice-versa
	 * @param o l'orientation dont on veut obtenir l'opposé.
	 * @return orientation opposé à o.
	 */
	public static Orientation opposite(Orientation o){
		if(o==null){
			return null;
		}
		switch(o){
		case NORD:
			return Orientation.SUD;
		case SUD:
			return Orientation.NORD;
		case EST:
			return Orientation.OUEST;
		case OUEST:
			return Orientation.EST;
		default:
			return null;
		}
	}
	
	
	/**
	 * Renvoi une orientation aléatoire.
	 * @return orientation aléatoire.
	 */
	public static Orientation random(){
		Orientation[] o = {NORD,SUD,EST,OUEST};
		
		return o[(int)(Math.random()*4)];
	}
	
	/**
	 * Renvoi une orientation aléatoire différente de celle donnée en paramètre
	 * @param o l'orientation qu'on ne veut pas obtenir.
	 * @return une orientation aléatoire différente de o.
	 */
	public static Orientation random(Orientation o){
		Orientation a;
		do{
			a=random();
		}while(a==o);
		return a;
	}

	/**
	 * Renvoi une orientation aléatoire différente de celles données en paramètre.
	 * Attention si les deux paramètres sont identiques, renverra une orientation parmi les trois restantes.
	 * @param a orientation non voulue.
	 * @param b orientation non voules bis.
	 * @return une orientation différente de a et de b.
	 */
	public static Orientation random(Orientation a, Orientation b){
		Orientation r;
		do{
			r=random();
		}while(r==a || r==b);
		return r;
	}
}
