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
	 * @param traversable true si la case peut être traversée, false sinon.
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

	/**
	 * 
	 * @param p
	 * @return
	 */
	public void inCollision(Personnage z){

		System.out.println("Hep t'as pas le droit d'être ici!");
		/**
		 * Replace the character to the limit of the collision box
		 */
		switch(z.o){

		case NORD:
			z.setLocation(z.image.x, this.collision.y+this.collision.height+z.collisionDecor.height-z.image.height);
			break;
		case EST:
			z.setLocation(this.collision.x+z.collisionDecor.width-z.image.width, z.image.y);
			break;
		case OUEST:
			z.setLocation(this.collision.x+this.collision.width-z.image.width+2*z.collisionDecor.width+2, z.image.y);
			break;
		case SUD:
			z.setLocation(z.image.x, this.collision.y-z.image.height);

		}
	}

}
