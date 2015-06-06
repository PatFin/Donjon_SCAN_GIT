package fr.donjon.utils;

/**
 * Vecteur 2D et méthodes associées.
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
	 * Les coordonnées du vecteur seront celles données en paramètre
	 * @param ax coordonnée horizontale
	 * @param ay coordonnée verticale
	 */
	public Vecteur (double ax, double ay){
		x=ax;
		y=ay;
	}
	
	/**
	 * Créé un nouveau vecteur dont les coordonnées sont la somme the this et du vecteur donné en paramètre
	 * Cette méthode ne modofie pas this ou w.
	 * @param w vecteur à ajouter à this
	 * @return u nouveau vecteur qui est la somme de w et this.
	 */
	public Vecteur ajoute (Vecteur w){
		return new Vecteur(this.x+w.x, this.y+w.y);
	}
	
	/**
	 * Méthode affichant le vecteur dans la console sur une nouvelle ligne.
	 * Affiche ce que renvoi la méthode toString()
	 * @see #toString()
	 */
	public void display() {
		System.out.println(this);
	}
	
	
	/**
	 * Renvoi la norme enclidienne du vecteur, c'est à dire sqrt(x²+y²) où x et y sont les coordonnées du vecteur.
	 * @return double dont la valeur est la norme du vecteur.
	 */
	public double getNorm(){
		return Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2) );
	}
	
	/**
	 * Méthode permettant de multiplier un vecteur par un scalaire a
	 * Le vecteur this n'est pas modifié, un nouveau vecteur est créé, ses coordonnées étant celle de this, multipliées par a
	 * @param a coefficient de multiplication de this.
	 * @return un nouveau vecteur de coordonnée (this.x*a,this.y*a) 
	 */
	public Vecteur multiplie (double a){
		return new Vecteur(a*x, a*y);
	}
	
	/**
	 * Normalise le vecteur.
	 * Prend this et créé un nouveau vecteur dont les coordonnées sont divisées par la norme du vecteur.
	 * Attention, si le vecteur est nul (0,0), renvoi le même vecteur.
	 * Le vecteur this n'est pas modifié, un nouveau vecteur est créé.
	 * @return vecteur unitaire de même direction que this
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
	 * Permet d'affecter de nouvelles coordonnnées au vecteur
	 * @param x nouvelle coordonnée horizontale
	 * @param y nouvelle coordonnée verticale
	 * @return this
	 */
	public Vecteur setLocation(double x, double y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Renvoi un string selon le format suivant: "X:"+x+" Y:"+y
	 * @return String contennat les coordonnées de this
	 */
	@Override
	public String toString() {
		return "X:"+x + " Y:"+y;
	}
	
	/**
	 * Renvoi l'orientation principale d'un vecteur.
	 * Si les composantes du vecteur sont égales en valeur absolue, renvoi par défaut la composante verticale (NORD ou SUD).
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
	 * Renvoi l'orientation principale du vecteur du point from au point to, càd du vecteur to-from
	 * @param from le point de départ du vecteur
	 * @param to le point d'arrivée du vecteur
	 * @return orientation principale du vecteur from-->to
	 * @see #projectMainDirection(Vecteur)
	 */
	public static Orientation projectMainDirection(Vecteur from, Vecteur to){
		return projectMainDirection(to.ajoute(from.multiplie(-1)));
	}
}
