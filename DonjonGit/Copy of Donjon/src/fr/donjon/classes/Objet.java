/**
 * 
 */
package fr.donjon.classes;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * @author Patrick
 *
 */
public abstract class Objet {
	
	
	String nom;					//Nom de l'objet
	Rectangle image;			//Rectangle contenant l'image
	Rectangle collisionArmes;	//Rectangle pour collision avec les armes  
	Rectangle collisionDecor; 	//Rectangle contenant la boite de collision de l'objet
	Boolean toDisplay; 			//largeur du rectangle
	
	
	/**
	 * Constructor
	 * @param ax Position sur x
	 * @param ay Position sur y
	 * @param nom Nom de l'objet
	 * @param image Rectangle de la taille de l'image
	 * @param collisionArmes Rectangle pour les collisions avec les armes
	 * @param collisionDecor Rectangle pour les collisions avec le décor
	 */
	public Objet(int ax, int ay,int longueur, int largeur, String nom, Rectangle collisionArmes, Rectangle collisionDecor, boolean toDisplay){
		this.image=new Rectangle(ax,ay,longueur,largeur);
		this.nom=nom;
		this.collisionArmes=collisionArmes;
		this.collisionDecor=collisionDecor;
		this.toDisplay=toDisplay;
	}
	
	/**
	 * A redéfinir dans les classes filles en fonction de si on s'intéresse au rectangle de collision pour les armes ou le décor
	 * @param o l'autre objet
	 * @return true si en collision, false sinon
	 */
	public boolean enCollision(Rectangle r){
		return r.intersects(this.collisionDecor);
		
	}
	
	public abstract void update(long t);
	
    public abstract void draw( long  t, Graphics g);
}