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
	
	public double x, y;
	
	/**
	 * Constructeur de Vecteur
	 * @param ax coordonnées horizontales
	 * @param ay coordonnées verticales
	 */
	public Vecteur (double ax, double ay){
		x=ax;
		y=ay;
	}
	
	/**
	 * Méthode permettant de multiplier un vecteur par un scalaire a
	 * @param a
	 * @return u
	 */
	public Vecteur multiplie (double a){
		return new Vecteur(a*x, a*y);
	}
	
	/**
	 * Méthode permettant d'additionner le vecteur "w" à un autre vecteur
	 * @param w
	 * @return u
	 */
	public Vecteur ajoute (Vecteur w){
		return new Vecteur(this.x+w.x, this.y+w.y);
	}
	
	public Vecteur normalise(){
		if(getNorm() == 0)return this;
		return this.multiplie(1/getNorm());
	
	}
	
	public Vecteur setLocation(double x, double y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public double getNorm(){
		return Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2) );
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "X:"+x + " Y:"+y;
	}
	
	public Orientation projectMainDirection(Vecteur v){
		if(Math.abs(v.x) > Math.abs(v.y)){
			if(v.x<0){
				return Orientation.OUEST;
			}else{
				return Orientation.EST;
			}
		}else{
			if(v.y<0){
				return Orientation.NORD;
			}else{
				return Orientation.SUD;
			}
		}
	}
	
	public Orientation projectMainDirection(Vecteur from, Vecteur to){
		Vecteur v = new Vecteur(to.x-from.x, to.y-from.y);
		return projectMainDirection(v);
	}
	
	public Orientation projectMainDirection(){
		return projectMainDirection(this);
	}
}
