package fr.donjon.classes.cases;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

import fr.donjon.classes.Personnage;

/**
 * Classe Case
 * @author Vincent
 *
 */
public class Case {

	public final static int TAILLE=64;
	
	
	public Image image;
	public Rectangle collision;
	/**
	 * Constructeur de la Case 
	 * @param ImageName le string contenant l'adresse de l'image.
	 * @param traversable true si la case peut être traversée, false sinon.
	 */
	public Case(String ImageName){
		try {
			image= ImageIO.read(new File(ImageName));
		}
		catch(Exception err) {
			System.out.println("Failed to read the image "+ImageName);
			System.out.println(err);
			System.exit(0);
		}
		this.collision=new Rectangle(Case.TAILLE,Case.TAILLE);
	}

	/**
	 * Positionne le rectangle de collision qui va déclencher la methode inCollision.
	 * Par défault le rectangle de collision recouvre toute la case.
	 * @param vertical index de l'array 2D de la salle.
	 * @param horizontal index de l'array 2D de la salle.
	 */
	public void setCollisionBoxLocation(int vertical, int horizontal){
		this.collision.setLocation(Case.TAILLE*horizontal, Case.TAILLE*vertical);
	}

	/**
	 * Methode vide. Les cases qui dérivent de Case peuvent l'override selon leur besoin.
	 * @param z Le personnage qui marche sur la case.
	 */
	public void inCollision(Personnage z) {
	}
	
	public Case clone(){
		return null;
	}
	
}
