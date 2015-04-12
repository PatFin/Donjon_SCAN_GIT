/**
 * 
 */
package fr.donjon.editor;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.donjon.classes.Case;

/**
 * 
 * Permet d'associer une JButton a une Case afin de dessiner l'image de la case dans le JButton
 * 
 * @author Baptiste
 *
 */
public class CaseButton extends JButton {

	final static int size = 70;
	Case c;

	/**
	 * Permet de creer un boutton avec l'image d'une case
	 * @param c	La case a afficher
	 */
	public CaseButton(Case c){
		super();
		this.c = c;

		this.setPreferredSize(new Dimension(size,size));
	
		//Redimensionne l'image pour qu'elle prenne toute la place dans le boutton
		Image newimg = c.image.getScaledInstance( size, size,  java.awt.Image.SCALE_SMOOTH ) ;  
		Icon icon = new ImageIcon( newimg );
		this.setIcon(icon);
		
		
	}

	public Case getC() {
		return c;
	}

	public void setC(Case c) {
		this.c = c;
	}



}
