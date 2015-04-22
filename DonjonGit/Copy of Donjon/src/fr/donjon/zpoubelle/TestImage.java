/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.donjon.classes.Objet;

/**
 * @author Baptiste
 *
 */
public class TestImage extends Objet {

	Image im;
	String src = "C:/Users/Baptiste/Documents/2015_Donjon_SC72/Donjon/images/Soleil.png";
	
	final static int LONG = 200;
	final static int LARG = 200;
	/**
	 * @param ax
	 * @param ay
	 * @param nom
	 * @param image
	 * @param collisionArmes
	 * @param collisionDecor
	 * @param toDisplay
	 */
	public TestImage(int ax, int ay) {
		
		super(ax, ay, LONG, LARG, "TEST", null, null , true);
		
		try{
			this.im = ImageIO.read(new File(src));
		}
		catch (IOException e) {
			System.out.println("ZOBI");
		}
	}

	/* (non-Javadoc)
	 * @see donjon.Objet#enCollision(donjon.Objet)
	 */
	@Override
	public boolean enCollision(Objet o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see donjon.Objet#draw(long, java.awt.Graphics)
	 */
	@Override
	public void draw(long t, Graphics g) {
		g.drawImage(im,image.x,image.y,image.width,image.height,null);

	}

	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		
	}

}
