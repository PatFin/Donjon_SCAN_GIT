package fr.donjon.utils;

/**
 * Méthode Vecteur permettant de définir un vecteur en 2D
 * @author chwat
 */
public class Vecteur {
	
	public static final Vecteur vNord  = new Vecteur(0,-1);
	public static final Vecteur vSud  = new Vecteur(0,1);
	public static final Vecteur vEst  = new Vecteur(1,0);
	public static final Vecteur vOuest  = new Vecteur(-1,0);
	public static final Vecteur vNull  = new Vecteur(0,0);
	
	public int x, y;
	
	/**
	 * Constructeur de Vecteur
	 * @param ax coordonnées horizontales
	 * @param ay coordonnées verticales
	 */
	public Vecteur (int ax, int ay){
		x=ax;
		y=ay;
	}
	
	/**
	 * Méthode permettant de multiplier un vecteur par un scalaire a
	 * @param a
	 * @return u
	 */
	public Vecteur multiplie (float a){
		return new Vecteur((int)a*x, (int)a*y);
	}
	
	/**
	 * Méthode permettant d'additionner le vecteur "w" à un autre vecteur
	 * @param w
	 * @return u
	 */
	public Vecteur ajoute (Vecteur w){
		return new Vecteur(this.x+w.x, this.y+w.y);
	}
	
	public Vecteur normalise(Vecteur v){
		int module = (int) Math.sqrt( Math.pow(v.x, 2) + Math.pow(v.y, 2) );
		return v.multiplie(1/module);
	
	}
}
