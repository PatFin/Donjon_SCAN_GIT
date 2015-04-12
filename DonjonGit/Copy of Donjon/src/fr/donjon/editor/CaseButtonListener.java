/**
 * 
 */
package fr.donjon.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Baptiste
 *
 */
public class CaseButtonListener implements ActionListener {

	PanelEdition pan;
	CaseButton button;
	
	/**
	 * 
	 */
	public CaseButtonListener(CaseButton cb, PanelEdition pan) {
		super();
		this.pan = pan;
		this.button = cb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pan.changeCase(button.c);
	}
	
	public void setListener(PanelEdition e){
		this.pan = e;
	}

}
