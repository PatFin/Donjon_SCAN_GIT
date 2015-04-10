/**
 * 
 */
package fr.donjon.editor;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.donjon.classes.Case;

/**
 * @author Baptiste
 *
 */
public class CaseButton extends JButton {

	Case c;
	
	public CaseButton(Case c, final PanelEdition editionPanel){
		super();
		this.c = c;
		this.setSize(20, 20);
		this.setIcon(new ImageIcon(c.image));
		
	}

	public Case getC() {
		return c;
	}

	public void setC(Case c) {
		this.c = c;
	}
	
	
	
}
