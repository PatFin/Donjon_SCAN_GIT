package fr.donjon.classes;

import java.awt.Image;
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
	}
}
