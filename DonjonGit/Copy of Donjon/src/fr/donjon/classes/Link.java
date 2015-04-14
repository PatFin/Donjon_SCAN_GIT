package fr.donjon.classes;

import java.awt.Rectangle;

import fr.donjon.utils.Orientation;

/**
 * Cette class contient la description d'un téléporteur d'une
 * salle à une autre. Chaque salle ontient une LinkedList de link 
 * @author Patrick
 *
 */
public class Link {

	public Salle destination;				//la destination du lien, l'autre salle.
	public int dx, dy;						//coordonnées de la case de destination.
	
	public Salle origine;
	public int x,y;
	
	public Orientation orientation;
	private Rectangle rectangleCollision;	//Si le héro marche sur ce rectangle et que enabled vaut true, alors on change de salle.
	public boolean enabled;				//autorise ou pas le changement de salle.
	
	/**
	 * Constructeur de link lorsque tous les éléments sont connus d'avance.
	 * @param destination la salle de destination
	 * @param dx coordonnée horizontale qui indique la case de destination.
	 * @param dy coordonnée verticale qui indique le case de destination.
	 * @param x coordonnée horizontale de la case qui contient le téléporteur. utilisé pour placer le rectangle de collision
	 * @param y coordonnée verticale. idem.
	 * @param o l'orientation du lien. Si dans la salle qui contient le lien l'endroit est en haut de la salle, o vaut NORD etc
	 * Ce paramètre sert uniquement à placerle rectangle de collision sur la bonne partie de la case. 
	 * @param enabled booléen qui autorise ou pas le changlement de salle.
	 */
	public Link(Salle destination, int dx,int dy, Salle origine, int x,int y, Orientation o, boolean enabled) {
		this.destination = destination;
		this.dx = dx;
		this.dy = dy;
		
		this.origine = origine;
		this.x=x;
		this.y=y;
		
		this.orientation = o;
		this.enabled = enabled;
		
		
		
		/**
		 * On pourra implémenter un meilleur positionnement du rectangle.
		 */
		switch(o){
		default:
			this.rectangleCollision = new Rectangle(x*Case.TAILLE,y*Case.TAILLE,Case.TAILLE,Case.TAILLE);
		}
	}
	
	/**
	 * Constructeur utilisé quand on ne connait pas la destination du lien
	 * @param x coordonnée horizontale de la case de transport
	 * @param y coordonnée verticale de la case de transport
	 * @param o orientation du lien
	 * @param enabled passge autorisé -> enabled vaut true. Sinon false.
	 */
	public Link(Salle origine, int x, int y, Orientation o, boolean enabled){
		this.enabled = enabled;
		this.destination=null;
		
		this.origine = origine;
		this.orientation =o;
		
		this.x=x;
		this.y=y;
		
		switch(orientation){
		case NORD:
			
		default:
			this.rectangleCollision = new Rectangle(x*Case.TAILLE,y*Case.TAILLE,Case.TAILLE,Case.TAILLE);
		}
	}
	
	
	/**
	 * Indique si une salle de destination a été spécifiée
	 * @return true si destination n'est pas 'null'. false sinon.
	 */
	public boolean hasDestination(){
		boolean z = true;
		if(this.destination == null){
			z=false;
		}
		return z;
	}
	
	
	
	
	
	/**
	 * Méthode utilisée quand une salle de destination n'a pas été définie dans le constructeur
	 * ou que l'on veut chager la destination d'une salle.
	 * @param des salle de destination
	 * @param dx Coordonnée horizontale de la case de destination
	 * @param dy Coordonnée verticale
	 */
	public void setDestination(Salle des,int dx,int dy){
		this.destination = des;
		this.dx = dx;
		this.dy = dy;
	}
	
	
	public String toString(){
		if(destination!=null){
			return ("Lien desalle "+origine.roomNumber+" vers salle "+destination.roomNumber);
		}else{
			return ("Lien de salle "+origine.roomNumber+" vers null");
		}
	}
}