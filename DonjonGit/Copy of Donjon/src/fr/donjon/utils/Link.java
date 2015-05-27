package fr.donjon.utils;

import fr.donjon.salles.Salle;

/**
 * Cette class contient la description d'un tï¿½lï¿½porteur d'une
 * salle ï¿½ une autre. Chaque salle ontient une LinkedList de link 
 * @author Patrick
 *		Il pourrait ï¿½tre possible de la faire fonctionner avec des vecteurs. Une "amï¿½lioration" future?
 */
public class Link {

	private Vecteur destinationCase;			//coordonnï¿½es de la case de destination.
	private Salle destinationSalle;				//la destination du lien, l'autre salle.
	
	private Orientation orientation;
	private Vecteur origineCase;
	
	private Salle origineSalle;
	
	/**
	 * Constructeur utilise quand on ne connait pas la salle de destination
	 * On initialise les parametres connus, laissant les parametres de destination null.
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte
	 * @param o l'orientation du lien. ie la position de la porte dans la salle d'origine
	 */
	public Link(Salle origine,Vecteur origVecteur, Orientation o){
		
		this.destinationSalle = null;
		
		this.origineSalle = origine;
		
		this.orientation =o;
		
		this.origineCase=origVecteur;
		
		this.destinationCase = null;
	}
	
	/**
	 * Constructeur de link lorsque tous les elements sont connus d'avance.
	 * @param destination la salle de destination
	 * @param desVecteur le vecteur qui indique la case de destination
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte dans la salle d'origine
	 * @param o l'orientation du lien. ie la position de la porte dans la salle origine
	 */
	public Link(Salle destination, Vecteur desVecteur, Salle origine,Vecteur origVecteur, Orientation o) {
		this.destinationSalle = destination;
		this.destinationCase = desVecteur;
		
		this.origineSalle = origine;
		this.origineCase = origVecteur;
		
		this.orientation = o;
	}
	
	/**
	 * Accesseur
	 * Renvoi le vecteur indiquant la position (dans la salle de destination) de la case sur laquelle le personnage doit atterrir
	 * @return vecteur utilisable par la méthode setLocationCase(Vecteur) du personnage.
	 * @see fr.donjon.classes.Deplacable#setLocationCase(Vecteur)
	 */
	public Vecteur getDestinationVecteur(){
		return destinationCase;
	}
	
	
	public Orientation getOrientation(){
		return this.orientation;
	}
	
	
	public Vecteur getPalier(){
		return this.origineCase;
	}
	
	public Salle getSalleDestination(){
		return destinationSalle;
	}
	
	public Salle getSalleOrigine(){
		return this.origineSalle;
	}
	
	/**
	 * Indique si une salle de destination a ete initialisée
	 * Plus spécifiquement, regarde si la salle de destination a pour valeur null. 
	 * @return true si destination n'est pas 'null'. false sinon.
	 */
	public boolean hasDestination(){
		return destinationSalle == null ? false : true;
	}
	
	/**
	 * Mutateur
	 * Methode utilisee quand une salle de destination n'a pas ete definie dans le constructeur
	 * ou que l'on veut chager la destination d'une porte.
	 * @param des salle de destination
	 * @param desVecteur vecteur vers la case de destination dans la salle destination
	 */
	public void setDestination(Salle des, Vecteur desVecteur){
		this.destinationSalle = des;
		this.destinationCase = desVecteur;
	}
	
	/**
	 * Renvoi les informations pertinentes du lien.
	 * Donne le numéro de la salle d'origine
	 * Donne le numéro de la salle de destination s'il existe
	 */
	@Override
	public String toString(){
		if(destinationSalle!=null){
			return ("Lien de salle "+origineSalle.roomNumber+" vers salle "+destinationSalle.roomNumber);
		}else{
			return ("Lien de salle "+origineSalle.roomNumber+" vers null");
		}
	}
	
	
	/**
	 * Gives a reciprocal link. 
	 * If there exists a link from room A to B, this method creates the link from room B to A
	 * @param l the link which reciprocal is to be created
	 * @return a new link which is the reciprocal of that of l. It basically have the same arguments as l but switched: salleOrigine becomes salleDestination and so on. The orientation becomes the opposite.
	 * @see fr.donjon.utils.Orientation#opposite()
	 */
	public static Link getReciprocal(Link l){
		return new Link(l.origineSalle,l.origineCase, l.destinationSalle, l.destinationCase, Orientation.opposite(l.orientation));
	}
}