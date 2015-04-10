package fr.donjon.editor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.donjon.classes.Case;
import fr.donjon.classes.Case_dalle_sol;
import fr.donjon.classes.Case_escalier;
import fr.donjon.classes.Case_fendue_sol;
import fr.donjon.classes.Case_herbe;
import fr.donjon.classes.Case_mur;
import fr.donjon.classes.Case_rocher;
import fr.donjon.classes.Case_void;
import fr.donjon.utils.Orientation;

/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public class EditorWindow extends JFrame {

	/**
	 * 
	 */


	JPanel cadre;

	//Interactions
	JPanel panInteractions;
	JButton BDelete;
	JSlider sliderThickness;
	JPanel 	panCases; 

	LinkedList<CaseButton> LCButtons;
	LinkedList<Case> listCases;


	PanelEdition panDessin;
	JPanel panMenuBar;

	public EditorWindow(String nom){
		super(nom);

		listCases = new LinkedList<Case>();
		LCButtons = new LinkedList<CaseButton>();

		listCases.add(new Case_dalle_sol());
		listCases.add(new Case_escalier(Orientation.NORD, null, null));
		listCases.add(new Case_fendue_sol());
		listCases.add(new Case_herbe());
		listCases.add(new Case_mur());
		listCases.add(new Case_rocher());
		listCases.add(new Case_void());

		//CADRE
		cadre = new JPanel();
		cadre.setLayout(new BorderLayout());
		cadre.setBackground(Color.RED);

		//DESSIN
		panDessin = new PanelEdition(25,15);
		cadre.add(panDessin, BorderLayout.EAST);

		//INTERACTIONS
		panInteractions = new JPanel();
		panInteractions.setLayout(new BoxLayout(panInteractions, BoxLayout.Y_AXIS));

		panCases = new JPanel();
		panCases.setLayout(new GridLayout(3, 2));
		panCases.setSize(100, 200);
		panCases.setMaximumSize(new Dimension(200,300));

		BDelete = new JButton("Delete");

		sliderThickness = new JSlider(1,6,1);

		for(int i = 0 ; i < listCases.size() ; i++){

			CaseButton bt = new CaseButton(listCases.get(i),panDessin);
			bt.addActionListener(new CaseButtonListener(bt, panDessin));
			LCButtons.add(bt);

		}

		//Remplissage

		for(int i = 0 ; i < LCButtons.size() ; i++){
			panCases.add(LCButtons.get(i));
		}

		panInteractions.add(sliderThickness);
		panInteractions.add(BDelete);
		panInteractions.add(panCases);

		cadre.add(panInteractions, BorderLayout.WEST);

		this.addListeners();
		this.setContentPane(cadre);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void addListeners(){

		BDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panDessin.changeCase(null);
			}
		});

		sliderThickness.addChangeListener( new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				panDessin.setThickness(sliderThickness.getValue());
			}
		});


	}

	public static void main(String[] a){

		EditorWindow window = new EditorWindow("Editeur de cartes");

	}

}
