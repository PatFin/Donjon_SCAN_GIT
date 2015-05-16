package fr.donjon.zpoubelle;

import java.awt.Image;
import java.awt.Rectangle;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.ImageManager;

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
	 * @param traversable true si la case peut �tre travers�e, false sinon.
	 */
	public Case(String ImageName){
		
		image = ImageManager.getImage(ImageName,this.getClass().getSimpleName());
		
		this.collision=new Rectangle(Case.TAILLE,Case.TAILLE);
	}

	/**
	 * Positionne le rectangle de collision qui va d�clencher la methode inCollision.
	 * Par d�fault le rectangle de collision recouvre toute la case.
	 * @param vertical index de l'array 2D de la salle.
	 * @param horizontal index de l'array 2D de la salle.
	 */
	public void setCollisionBoxLocation(int horizontal, int vertical){
		this.collision.setLocation(Case.TAILLE*horizontal, Case.TAILLE*vertical);
	}

	/**
	 * Methode vide. Les cases qui d�rivent de Case peuvent l'override selon leur besoin.
	 * @param z Le personnage qui marche sur la case.
	 */
	public void inCollision(Personnage p) {
	}
	
	public Case clone(){
		return null;
	}
	
	/**
	 * Empty method 
	 * It's purpose is to change the value of a boolean that determines whether a tile can be stepped on or not.
	 * The class Case_porte overrides this method.
	 */
	public void enablePassage(){
		
	}
	
}
