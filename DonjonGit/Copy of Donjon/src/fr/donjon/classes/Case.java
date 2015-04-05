package fr.donjon.classes;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;



import javax.imageio.ImageIO;

	/**
	 * Classe Case
	 * @author Vincent
	 *
	 */
public abstract class Case {
	
	public final static int TAILLE=64;
	
	Image image;
	boolean traversable;
	Rectangle collision;
	/**
	 * Constructeur de la Case 
	 * @param ImageName le string contenant l'adresse de l'image.
	 * @param traversable true si la case peut �tre travers�e, false sinon.
	 */
	public Case(String ImageName, boolean traversable){
		try {
			image= ImageIO.read(new File(ImageName));
		     }
		 catch(Exception err) {
		     System.out.println("Failed to read the image "+ImageName);
		     System.out.println(err);
			 System.exit(0);
		}
		this.traversable=traversable;
		this.collision=new Rectangle(Case.TAILLE,Case.TAILLE);
	}
	
	/**
	 * Sets the location of the collision box for the case. It is called in class Salle whenthe array is set. 
	 * @param vertical 
	 * @param horizontal
	 */
	public void setCollisionBoxLocation(int vertical, int horizontal){
		this.collision.setLocation(horizontal*Case.TAILLE, vertical*Case.TAILLE);
	}
}
