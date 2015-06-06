package fr.donjon.utils;

/**
 * Vecteur 2D et m�thodes associ�es.
 *
 */
public class Vecteur {
	
	public static final Vecteur vEst  = new Vecteur(1,0);
	public static final Vecteur vNord  = new Vecteur(0,-1);
	public static final Vecteur vNull  = new Vecteur(0,0);
	public static final Vecteur vOuest  = new Vecteur(-1,0);
	public static final Vecteur vSud  = new Vecteur(0,1);
	
	public double x, y;
	
	/**
	 * Constructeur
	 * Les coordonn�es du vecteur seront celles donn�es en param�tre
	 * @param ax coordonn�e horizontale
	 * @param ay coordonn�e verticale
	 */
	public Vecteur (double ax, double ay){
		x=ax;
		y=ay;
	}
	
	/**
	 * Cr�� un nouveau vecteur dont les coordonn�es sont la somme the this et du vecteur donn� en param�tre
	 * Cette m�thode ne modofie pas this ou w.
	 * @param w vecteur � ajouter � this
	 * @return u nouveau vecteur qui est la somme de w et this.
	 */
	public Vecteur ajoute (Vecteur w){
		return new Vecteur(this.x+w.x, this.y+w.y);
	}
	
	/**
	 * M�thode affichant le vecteur dans la console sur une nouvelle ligne.
	 * Affiche ce que renvoi la m�thode toString()
	 * @see #toString()
	 */
	public void display() {
		System.out.println(this);
	}
	
	
	/**
	 * Renvoi la norme enclidienne du vecteur, c'est � dire sqrt(x�+y�) o� x et y sont les coordonn�es du vecteur.
	 * @return double dont la valeur est la norme du vecteur.
	 */
	public double getNorm(){
		return Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2) );
	}
	
	/**
	 * M�thode permettant de multiplier un vecteur par un scalaire a
	 * Le vecteur this n'est pas modifi�, un nouveau vecteur est cr��, ses coordonn�es �tant celle de this, multipli�es par a
	 * @param a coefficient de multiplication de this.
	 * @return un nouveau vecteur de coordonn�e (this.x*a,this.y*a) 
	 */
	public Vecteur multiplie (double a){
		return new Vecteur(a*x, a*y);
	}
	
	/**
	 * Normalise le vecteur.
	 * Prend this et cr�� un nouveau vecteur dont les coordonn�es sont divis�es par la norme du vecteur.
	 * Attention, si le vecteur est nul (0,0), renvoi le m�me vecteur.
	 * Le vecteur this n'est pas modifi�, un nouveau vecteur est cr��.
	 * @return vecteur unitaire de m�me direction que this
	 * @see #getNorm()
	 */
	public Vecteur normalise(){
		if(getNorm() == 0)return this;
		else return this.multiplie(1/getNorm());
	
	}
	
	/**
	 * Renvoi l'orientation principale du vecteur this.
	 * @return Orientation sur laquelle this a la plus grande composante.
	 * @see #projectMainDirection(Vecteur)
	 */
	public Orientation projectMainDirection(){
		return projectMainDirection(this);
	}
	
	/**
	 * Mutateur
	 * 
	 * Permet d'affecter de nouvelles coordonnn�es au vecteur
	 * @param x nouvelle coordonn�e horizontale
	 * @param y nouvelle coordonn�e verticale
	 * @return this
	 */
	public Vecteur setLocation(double x, double y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Renvoi un string selon le format suivant: "X:"+x+" Y:"+y
	 * @return String contennat les coordonn�es de this
	 */
	@Override
	public String toString() {
		return "X:"+x + " Y:"+y;
	}
	
	/**
	 * Renvoi l'orientation principale d'un vecteur.
	 * Si les composantes du vecteur sont �gales en valeur absolue, renvoi par d�faut la composante verticale (NORD ou SUD).
	 * Si le  vecteur est le vecteur nul (0,0), renvoi SUD.
	 * @param v vecteur dont on souhaite obtenir l'orientation
	 * @return orientation principale du vecteur
	 */
	public static Orientation projectMainDirection(Vecteur v){
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
	
	/**
	 * Renvoi l'orientation principale du vecteur du point from au point to, c�d du vecteur to-from
	 * @param from le point de d�part du vecteur
	 * @param to le point d'arriv�e du vecteur
	 * @return orientation principale du vecteur from-->to
	 * @see #projectMainDirection(Vecteur)
	 */
	public static Orientation projectMainDirection(Vecteur from, Vecteur to){
		return projectMainDirection(to.ajoute(from.multiplie(-1)));
	}
}
