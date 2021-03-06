/**
 * 
 */
package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Utilisable;

/**
 * @author Vincent
 *
 */
public class Drop extends Objet implements Utilisable {
	final static int largeur=64;
	final static int longueur=64;
	Image imageObjet;
	boolean utilise; // pour savoir si l'objet � �t� utilis� ou non

	/**
	 * @param ax
	 * @param ay
	 * @param longueur
	 * @param largeur
	 * @param nom
	 * @param offArm
	 * @param offCol
	 * @param toDisplay
	 
	 */
	public Drop(int ax, int ay, String nom,Rectangle offArm, Rectangle offCol, boolean toDisplay){
		super(ax, ay, longueur, largeur, nom, offArm, offCol, true);
		this.utilise=false;
		this.imageObjet=getIcon();
	}

	/* (non-Javadoc)
	 * @see fr.donjon.classes.Objet#draw(long, java.awt.Graphics)
	 */
	@Override
	public void draw(long t, Graphics g) {
		if(utilise){
			return;
			
		}
		g.drawImage(imageObjet, image.x, image.y, image.width, image.height, null);
		

	}

	/* (non-Javadoc)
	 * @see fr.donjon.utils.Utilisable#getIcon()
	 */
	@Override
	public Image getIcon() {
		return ImageManager.getImage(nom, this.getClass().getSimpleName());
	}

	/* (non-Javadoc)
	 * @see fr.donjon.classes.Objet#update(long)
	 */
	@Override
	public void update(long t) {

	}

	/* (non-Javadoc)
	 * @see fr.donjon.utils.Utilisable#utilise(fr.donjon.classes.Personnage)
	 */
	@Override
	public int utilise(Personnage p) {
		return 1;
	}
	

}
