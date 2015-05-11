package fr.donjon.utils;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.zpoubelle.SalleAbs;

/**
 * Cette class contient la description d'un t�l�porteur d'une
 * salle � une autre. Chaque salle ontient une LinkedList de link 
 * @author Patrick
 *		Il pourrait �tre possible de la faire fonctionner avec des vecteurs. Une "am�lioration" future?
 */
public class Link {

	public SalleAbs destinationSalle;				//la destination du lien, l'autre salle.
	public Vecteur 	destinationCase;				//coordonn�es de la case de destination.
	
	public SalleAbs origineSalle;
	public Vecteur origineCase;
	
	public Orientation orientation;
	public Rectangle rectangleCollision;	//Si le h�ro marche sur ce rectangle et que enabled vaut true, alors on change de salle.
	public Boolean enabled;					//autorise ou pas le changement de salle. Attribut commun � celui de la case porte associ�e.
	
	/**
	 * Constructeur de link lorsque tous les �l�ments sont connus d'avance.
	 * @param destination la salle de destination
	 * @param desVecteur le vecteur qui indique la case de destination
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte
	 * @param o l'orientation du lien. ie la position de la porte dans la salle origine
	 * @param enabled
	 */
	public Link(SalleAbs destination, Vecteur desVecteur, SalleAbs origine,Vecteur origVecteur, Orientation o, Boolean enabled) {
		this.destinationSalle = destination;
		this.destinationCase = desVecteur;
		
		this.origineSalle = origine;
		this.origineCase = origVecteur;
		
		this.orientation = o;
		this.enabled = enabled;
		
		this.rectangleCollision=origine.cases[(int)origineCase.x][(int)origineCase.y].collision;
	}
	
	/**
	 * Constructeur utilis� quand on ne connait pas la salle de destination
	 * @param origine la salle d'origine
	 * @param origVecteur le vecteur qui pointe vers la case qui sert de porte
	 * @param o l'orientation du lien. ie la position de la porte dans la salle origine
	 * @param enabled
	 */
	public Link(SalleAbs origine,Vecteur origVecteur, Orientation o, Boolean enabled){
		this.enabled = enabled;
		
		this.destinationSalle=null;
		
		this.origineSalle = origine;
		this.orientation =o;
		
		this.origineCase=origVecteur;
		
		
		this.rectangleCollision=origine.cases[(int)origineCase.x][(int)origineCase.y].collision;
	}
	
	
	/**
	 * Indique si une salle de destination a �t� sp�cifi�e
	 * @return true si destination n'est pas 'null'. false sinon.
	 */
	public boolean hasDestination(){
		boolean z = true;
		if(this.destinationSalle == null){
			z=false;
		}
		return z;
	}
	
	/**
	 * Methode qui v�rifie si le h�ros a le droit de changer de salle
	 * ie, il marche sur le t�l�porteur et le t�l�porteur est "enabled".
	 * @param p le hero � faire (ou pas) changer de salle
	 * @return true si p est � changer de salle, false sinon.
	 */
	public boolean mustChangeRoom(Heros p){
		return this.enabled && p.enCollision(rectangleCollision);
	}
	
	
	
	/**
	 * M�thode utilis�e quand une salle de destination n'a pas �t� d�finie dans le constructeur
	 * ou que l'on veut chager la destination d'une salle.
	 * @param des salle de destination
	 * @param desVecteur vecteur vers la case de destination dans la salle destination
	 */
	public void setDestination(SalleAbs des, Vecteur desVecteur){
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
}