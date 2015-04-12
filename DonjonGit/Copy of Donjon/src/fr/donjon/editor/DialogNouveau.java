/**
 * 
 */
package fr.donjon.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.donjon.utils.DialogListener;
import fr.donjon.utils.Vecteur;


/**
 * @author Baptiste
 *
 */
public class DialogNouveau extends JDialog {

	boolean valid = false;

	int dWith;
	int dHeight;

	Box content;

	JSlider sWidth;
	JSlider sHeight;

	JButton BCreer;
	JButton BAnnuler;

	String titleText = "Grille 15x10";

	//

	DialogListener listener;

	public DialogNouveau(JFrame parent, String title, boolean modal, DialogListener l){
		super(parent,title,modal);

		this.setLocationRelativeTo(null);
		this.setResizable(false);

		listener = l;

		//Sliders
		sWidth = new JSlider(1,30,15);
		sHeight = new JSlider(1,30,10);
		sWidth.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				updateText();
			}
		});
		sHeight.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				updateText();
			}
		});

		//Buttons
		BCreer = new JButton("Créer");
		BAnnuler = new JButton("Annuler");
		BCreer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				validateBox();
			}
		});
		BAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelBox();
			}
		});


		//Add
		Box box1 = Box.createHorizontalBox();
		box1.add(sWidth);
		box1.add(sHeight);
		box1.add(Box.createVerticalStrut(30));

		Box box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(5));
		box2.add(BAnnuler);
		box2.add(Box.createHorizontalGlue());
		box2.add(BCreer);
		box2.add(Box.createHorizontalStrut(5));


		content = Box.createVerticalBox();
		content.add(box1);
		content.add(box2);
		content.add(Box.createVerticalStrut(5));
		content.setBorder(BorderFactory.createTitledBorder(titleText));
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

	}

	private void updateText(){
		titleText = "Grille " + sWidth.getValue() + "x" + sHeight.getValue() ;
		content.setBorder(BorderFactory.createTitledBorder(titleText));

	}


	private void validateBox(){
		
		listener.onValidate(new Vecteur(sWidth.getValue(), sHeight.getValue()));
		setVisible(false);
	}

	private void cancelBox(){
		
		listener.onCancel();
		setVisible(false);
	}

}
