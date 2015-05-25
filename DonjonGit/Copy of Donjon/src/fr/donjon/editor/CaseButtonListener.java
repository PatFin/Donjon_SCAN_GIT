/**
 * 
 */
package fr.donjon.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface permettant de gerer le clik sur un CaseButton (JButton)
 * afin de changer la brosse du PanelEdition associ�
 * 
 * @author Baptiste
 *
 */
public class CaseButtonListener implements ActionListener {

	CaseButton button;	//Le CaseButton associ�
	PanelEdition pan;	//Le PanelEdition associ�
	
	/**
	 * 
	 */
	public CaseButtonListener(CaseButton cb, PanelEdition pan) {
		super();
		this.pan = pan;
		this.button = cb;
	}

	/**
	 * Redefinition de la methode de ActionListener (clik sur le boutton)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//On change la case dessin�e par la brosse
		pan.changeCase(button.c);
	}
	

}
