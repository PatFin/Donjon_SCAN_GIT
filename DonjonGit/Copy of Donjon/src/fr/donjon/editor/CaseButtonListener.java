/**
 * 
 */
package fr.donjon.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface permettant de gerer le clik sur un CaseButton (JButton)
 * afin de changer la brosse du PanelEdition associé
 * 
 * @author Baptiste
 *
 */
public class CaseButtonListener implements ActionListener {

	PanelEdition pan;	//Le PanelEdition associé
	CaseButton button;	//Le CaseButton associé
	
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
	public void actionPerformed(ActionEvent e) {
		//On change la case dessinée par la brosse
		pan.changeCase(button.c);
	}
	

}
