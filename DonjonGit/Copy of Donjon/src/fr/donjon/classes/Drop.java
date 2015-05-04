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
	Image imageObjet;
	boolean utilise; // pour savoir si l'objet à été utilisé ou non
	final static int longueur=64;
	final static int largeur=64;

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
		this.imageObjet=ImageManager.getImage(nom, this.getClass().getSimpleName());
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.donjon.utils.Utilisable#utilise(fr.donjon.classes.Personnage)
	 */
	@Override
	public int utilise(Personnage p) {
		// TODO Auto-generated method stub
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.donjon.utils.Utilisable#getIcon()
	 */
	@Override
	public Image getIcon() {
		
		// TODO Auto-generated method stub
		return ImageManager.getImage(nom, this.getClass().getSimpleName());
	}

	/* (non-Javadoc)
	 * @see fr.donjon.classes.Objet#update(long)
	 */
	@Override
	public void update(long t) {
		// TODO Auto-generated method stub

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
		
		// TODO Auto-generated method stub

	}
	

}
