package fr.donjon.classes;

import java.awt.Rectangle;

import fr.donjon.utils.Orientation;

/**
 * Cette class contient la description d'un t�l�porteur d'une
 * salle � une autre. Chaque salle ontient une LinkedList de link 
 * @author Patrick
 *
 */
public class Link {

	private Salle destination;				//la destination du lien, l'autre salle.
	private int dx, dy;						//coordonn�es de la case de destination.
	
	private Rectangle rectangleCollision;	//Si le h�ro marche sur ce rectangle et que enables vaut true, alors on change de salle.
	public boolean enabled;				//autorise ou pas le changement de salle.
	
	/**
	 * Constructeur de link lorsque tous les �l�ments sont connus d'avance.
	 * @param destination la salle de destination
	 * @param dx coordonn�e horizontale qui indique la case de destination.
	 * @param dy coordonn�e verticale qui indique le case de destination.
	 * @param x coordonn�e horizontale de la case qui contient le t�l�porteur. utilis� pour placer le rectangle de collision
	 * @param y coordonn�e verticale. idem.
	 * @param o l'orientation du lien. Si dans la salle qui contient le lien l'endroit est en haut de la salle, o vaut NORD etc
	 * Ce param�tre sert uniquement � placerle rectangle de collision sur la bonne partie de la case. 
	 * @param enabled bool�en qui autorise ou pas le changlement de salle.
	 */
	public Link(Salle destination, int dx,int dy, int x,int y, Orientation o, boolean enabled) {
		this.destination = destination;
		this.dx = dx;
		this.dy = dy;
		
		this.enabled = enabled;
		
		
		
		/**
		 * On pourra impl�menter un meilleur positionnement du rectangle.
		 */
		switch(o){
		default:
			this.rectangleCollision = new Rectangle(x*Case.TAILLE,y*Case.TAILLE,Case.TAILLE,Case.TAILLE);
		}
	}
	
	/**
	 * Constructeur utilis� quand on ne connait pas la destination du lien
	 * @param x coordonn�e horizontale de la case de transport
	 * @param y coordonn�e verticale de la case de transport
	 * @param o orientation du lien
	 * @param enabled passge autoris� -> enabled vaut true. Sinon false.
	 */
	public Link(int x, int y, Orientation o, boolean enabled){
		this.enabled = enabled;
		
		switch(o){
		default:
			this.rectangleCollision = new Rectangle(x*Case.TAILLE,y*Case.TAILLE,Case.TAILLE,Case.TAILLE);
		}
	}
	
	
	/**
	 * Indique si une salle de destination a �t� sp�cifi�e
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
	 * M�thode utilis�e quand une salle de destination n'a pas �t� d�finie dans le constructeur
	 * ou que l'on veut chager la destination d'une salle.
	 * @param des salle de destination
	 * @param dx Coordonn�e horizontale de la case de destination
	 * @param dy Coordonn�e verticale
	 */
	public void setDestination(Salle des,int dx,int dy){
		this.destination = des;
		this.dx = dx;
		this.dy = dy;
	}
}