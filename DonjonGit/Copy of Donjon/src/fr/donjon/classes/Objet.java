/**
 * 
 */
package fr.donjon.classes;
import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.utils.Vecteur;
/**
 * @author Patrick
 *
 */
public abstract class Objet {
	
	
	String nom;					//Nom de l'objet
	Rectangle offArm;			//Offsets du rectangle armes
	Rectangle offCol;			//Offsets du rectangle collisions
	Boolean toDisplay; 			//largeur du rectangle
	public Rectangle collisionArmes;	//Rectangle pour collision avec les armes  
	public Rectangle collisionDecor; 	//Rectangle contenant la boite de collision de l'objet
	public Rectangle image;			//Rectangle contenant l'image
	
	
	/**
	 * Constructor
	 * @param ax Position sur x
	 * @param ay Position sur y
	 * @param nom Nom de l'objet
	 * @param image Rectangle de la taille de l'image
	 * @param offArm Rectangle pour les collisions avec les armes
	 * @param offCol Rectangle pour les collisions avec le décor
	 */
	public Objet(int ax, int ay,int longueur, int largeur, String nom, Rectangle offArm, Rectangle offCol, boolean toDisplay){
		this.image=new Rectangle(ax,ay,longueur,largeur);
		this.nom=nom;
		this.offCol = offCol;
		this.offArm = offArm;
		this.collisionDecor= new Rectangle(image.x +offCol.x, image.y + offCol.y, offCol.width, offCol.height);
		this.collisionArmes= new Rectangle(image.x +offArm.x, image.y + offArm.y, offArm.width, offArm.height);
		this.toDisplay=toDisplay;
	}
	
	public abstract void draw(long  t, Graphics g);
	
	/**
	 * A redéfinir dans les classes filles en fonction de si on s'intéresse au rectangle de collision pour les armes ou le décor
	 * @param o l'autre objet
	 * @return true si en collision, false sinon
	 */
	public boolean enCollision(Rectangle r){
		return r.intersects(this.collisionDecor);	
	}
	
    public Vecteur getCentre(){
    	return new Vecteur(this.image.x + this.image.width/2, this.image.y + this.image.height/2);
    }
    
    public abstract void update(long t);
    
}