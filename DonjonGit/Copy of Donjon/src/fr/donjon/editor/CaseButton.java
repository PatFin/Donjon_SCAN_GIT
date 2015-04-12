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
 * @author Baptiste
 *
 */
public class CaseButton extends JButton {

	final static int size = 70;
	Case c;

	public CaseButton(Case c){
		super();
		this.c = c;

		this.setPreferredSize(new Dimension(size,size));
	
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
