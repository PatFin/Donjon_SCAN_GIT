package fr.donjon.utils;

import fr.donjon.testblac.Salle;

/**
 * Cette class contient la description d'un tï¿½lï¿½porteur d'une
 * salle ï¿½ une autre. Chaque salle ontient une LinkedList de link 
 * @author Patrick
 *		Il pourrait ï¿½tre possible de la faire fonctionner avec des vecteurs. Une "amï¿½lioration" future?
 */
public class Link {

	private Salle destinationSalle;				//la destination du lien, l'autre salle.
	private Vecteur destinationCase;				//coordonnï¿½es de la case de destination.
	
	private Salle origineSalle;
	private Vecteur origineCase;
	
	private Orientation orientation;
	//TODO remove unused argument : rectangleCollision
	//public Rectangle rectangleCollision;	//Si le hï¿½ro marche sur ce rectangle et que enabled vaut true, alors on change de salle.
	//public Boolean enabled;					//autorise ou pas le changement de salle. Attribut commun ï¿½ celui de la case porte associï¿½e.
	
	/**
	 * Constructeur de link lorsque tous les ï¿½lï¿½ments sont connus d'avance.
	 * @param destination la salle de destination
	 * @param desVecteur le vecteur qui indique la case de destination
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte
	 * @param o l'orientation du lien. ie la position de la porte dans la salle origine
	 * @param enabled
	 */
	public Link(Salle destination, Vecteur desVecteur, Salle origine,Vecteur origVecteur, Orientation o) {
		this.destinationSalle = destination;
		this.destinationCase = desVecteur;
		
		this.origineSalle = origine;
		this.origineCase = origVecteur;
		
		this.orientation = o;
		
		//TODO remove unused bit
		//this.rectangleCollision=origine.cases[(int)origineCase.x][(int)origineCase.y].collision;
	}
	
	/**
	 * Constructeur utilisï¿½ quand on ne connait pas la salle de destination
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte
	 * @param o l'orientation du lien. ie la position de la porte dans la salle origine
	 * @param enabled
	 */
	public Link(Salle origine,Vecteur origVecteur, Orientation o){
		
		this.destinationSalle=null;
		
		this.origineSalle = origine;
		this.orientation =o;
		
		this.origineCase=origVecteur;
		
		//TODO remove unused bit
		//this.rectangleCollision=origine.cases[(int)origineCase.x][(int)origineCase.y].collision;
	}
	
	
	/**
	 * Indique si une salle de destination a ï¿½tï¿½ spï¿½cifiï¿½e
	 * @return true si destination n'est pas 'null'. false sinon.
	 */
	public boolean hasDestination(){
		boolean z = true;
		if(this.destinationSalle == null){
			z=false;
		}
		return z;
	}
	
	//TODO remove unused method
	/**
	 * Methode qui vï¿½rifie si le hï¿½ros a le droit de changer de salle
	 * ie, il marche sur le tï¿½lï¿½porteur et le tï¿½lï¿½porteur est "enabled".
	 * @param p le hero ï¿½ faire (ou pas) changer de salle
	 * @return true si p est ï¿½ changer de salle, false sinon.
	 */
	//public boolean mustChangeRoom(Heros p){
		//return this.enabled && p.enCollision(rectangleCollision);
	//}
	
	
	
	/**
	 * Mï¿éthode utilisée quand une salle de destination n'a pas été définie dans le constructeur
	 * ou que l'on veut chager la destination d'une salle.
	 * @param des salle de destination
	 * @param desVecteur vecteur vers la case de destination dans la salle destination
	 */
	public void setDestination(Salle des, Vecteur desVecteur){
		this.destinationSalle = des;
		this.destinationCase = desVecteur;
	}
	
	
	public String toString(){
		if(destinationSalle!=null){
			return ("Lien desalle "+origineSalle.roomNumber+" vers salle "+destinationSalle.roomNumber);
		}else{
			return ("Lien de salle "+origineSalle.roomNumber+" vers null");
		}
	}
	
	public Vecteur getDestinationVecteur(){
		return new Vecteur(this.destinationCase.x, this.destinationCase.y);
	}
	
	public Salle getSalleDestination(){
		return destinationSalle;
	}
	
	public Orientation getOrientation(){
		return this.orientation;
	}
	
	public Salle getSalleOrigine(){
		return this.origineSalle;
	}
	
	public Vecteur getPalier(){
		return this.origineCase;
	}
	
	
	/**
	 * Gives a reciprocal link. If there exists a link from room A to B, this method creates the link from room B to A
	 * @param l the link which reciprocal is to be created
	 * @return a new link which is the reciprocal of that of l
	 */
	public static Link getReciprocal(Link l){
		return new Link(l.origineSalle,l.origineCase, l.destinationSalle, l.destinationCase, Orientation.opposite(l.orientation));
	}
}