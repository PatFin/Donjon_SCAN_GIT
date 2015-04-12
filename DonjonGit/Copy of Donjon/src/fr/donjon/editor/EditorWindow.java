package fr.donjon.editor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import fr.donjon.utils.DialogListener;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class EditorWindow extends JFrame{

	//Cadre principal
	JPanel cadre;

	//Panel menu
	JPanel panMenu;

	JButton BNouveau;
	JButton BSauvegarder;
	JButton BDelete;
	JButton BFill;

	//Panel Interactions
	JPanel panInteractions;

	JSlider sliderThickness;
	JPanel 	panCases; 

	LinkedList<CaseButton> LCButtons;
	LinkedList<Case> listCases;


	//Panel dessin
	PanelEdition panDessin;

	public EditorWindow(String nom){
		super(nom);
		
		//Init cases
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

		//MENU

		panMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));

		BNouveau = new JButton("Nouveau");
		BSauvegarder = new JButton("Sauvegarder");
		BDelete = new JButton("Delete");
		BFill = new JButton("Remplir");

		panMenu.add(BNouveau);
		panMenu.add(BSauvegarder);
		panMenu.add(BFill);
		panMenu.add(BDelete);


		cadre.add(panMenu,BorderLayout.NORTH);

		//DESSIN
		panDessin = new PanelEdition(15,10);
		cadre.add(panDessin, BorderLayout.EAST);

		//INTERACTIONS
		panInteractions = new JPanel();
		panInteractions.setLayout(new BoxLayout(panInteractions, BoxLayout.Y_AXIS));
		panInteractions.setPreferredSize(new Dimension(170,600));

		panCases = new JPanel();
		panCases.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

		sliderThickness = new JSlider(1,6,1);

		for(int i = 0 ; i < listCases.size() ; i++){
			CaseButton bt = new CaseButton(listCases.get(i));
			LCButtons.add(bt);
		}

		//REMPLISSAGE INTER


		for(int i = 0 ; i < LCButtons.size() ; i++){
			panCases.add(LCButtons.get(i));
		}

		panInteractions.add(sliderThickness);
		panInteractions.add(panCases);

		cadre.add(panInteractions, BorderLayout.WEST);

		//FIN
		this.addListeners();
		this.setContentPane(cadre);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void addListeners(){


		BDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panDessin.fill(new Case_void());
			}
		});

		sliderThickness.addChangeListener( new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				panDessin.setThickness(sliderThickness.getValue());
			}
		});

		BNouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BNouveau.setEnabled(false);
				showDialogNew();
				BNouveau.setEnabled(true);
			}
		});

		BSauvegarder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		BFill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panDessin.fill(panDessin.caseT);
			}
		});

		for( CaseButton bt : LCButtons){
			bt.addActionListener(new CaseButtonListener(bt, panDessin));
		}

	}

	private void showDialogNew(){

		DialogListener dl = new DialogListener() {

			@Override
			public void onValidate(Vecteur v) {
				//cadre.remove(panDessin);
				panDessin.reinitialize(v.x, v.y);
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		};

		DialogNouveau d = new DialogNouveau(null, "Taille de la grille :", true,dl);



	}

	public static void main(String[] a){

		EditorWindow window = new EditorWindow("Editeur de cartes");

	}




}
