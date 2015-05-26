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

import fr.donjon.utils.Vecteur;


/**
 * La boite de dialogue permettant de choisir la taille de grile a creer
 * 
 * @author Baptiste
 *
 */
public class DialogNouveau extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton BAnnuler;

	JButton BCreer;
	Box content; //Le contenu graphique du JDialog

	DialogListener listener;			//La classe attendant un r�sultat du JDialog (et implementant DialogListener)	
	JSlider sHeight;//Slider hauteur

	JSlider sWidth;	//Slider largeur

	String titleText = "Grille 15x10"; //Texte a afficher dans la fen�tre

	/**
	 * 
	 * Permet de creer la boite de dialogue et de l'afficher
	 * 
	 * @param parent	Le panel qui contient le JDialog
	 * @param title		Le titre a afficher
	 * @param modal		Modalit� de la boite	
	 * @param l			La classe ecoutant le r�sultat
	 */
	public DialogNouveau(JFrame parent, String title, boolean modal, DialogListener l){
		super(parent,title,modal);

		//Centrage
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		listener = l;

		//Sliders
		sWidth = new JSlider(1,30,15);
		sHeight = new JSlider(1,30,10);
		sWidth.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// MAJ du texte a afficher
				updateText();
			}
		});
		sHeight.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				updateText();
			}
		});

		//Buttons
		BCreer = new JButton("Cr�er");
		BAnnuler = new JButton("Annuler");
		BCreer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Boutton valider
				validateBox();
			}
		});
		BAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Boutton annuler
				cancelBox();
			}
		});


		//Les deux slider cote a cote
		Box box1 = Box.createHorizontalBox();
		box1.add(sWidth);
		box1.add(sHeight);
		box1.add(Box.createVerticalStrut(30));	//Espace vide

		//Les deux bouttons cote a cote
		Box box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(5));	//Expace vide
		box2.add(BAnnuler);
		box2.add(Box.createHorizontalGlue());	//Boutons ecart�s au maximum
		box2.add(BCreer);
		box2.add(Box.createHorizontalStrut(5)); //Espace vide

		//Les deux composants precedents a la verticale
		content = Box.createVerticalBox();
		content.add(box1);
		content.add(box2);
		content.add(Box.createVerticalStrut(5));
		content.setBorder(BorderFactory.createTitledBorder(titleText)); //Texte a afficher
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

	}

	private void cancelBox(){
		
		listener.onCancel(); 	//On previent
		setVisible(false);		//On d�saffiche
	}

	/**
	 * Met a jout le texte de la bordure en fonction de la valeur des Sliders
	 */
	private void updateText(){
		titleText = "Grille " + sWidth.getValue() + "x" + sHeight.getValue() ;
		content.setBorder(BorderFactory.createTitledBorder(titleText));

	}

	/**
	 * G�re l'appui sur le boutton valider
	 */
	private void validateBox(){
		
		listener.onValidate(new Vecteur(sWidth.getValue(), sHeight.getValue())); 	//On previent l'�couteur
		setVisible(false);															//On fait disparaitre le JDialog
	}

}
