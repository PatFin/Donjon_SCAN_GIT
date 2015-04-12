package fr.donjon.editor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
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
import fr.donjon.utils.Vecteur;

/**
 * 
 * Fenêtre principale de l'éditeur
 * 
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

	//Panel Interactions (boutons)
	JPanel panInteractions;

	JSlider sliderThickness;
	JPanel 	panCases; 

	LinkedList<CaseButton> LCButtons;
	LinkedList<Case> listCases;


	//Panel dessin
	PanelEdition panDessin;

	/**
	 * Permet de creer la fenetre
	 * 
	 * @param nom	Titre fenetre
	 */
	public EditorWindow(String nom){
		super(nom);
		
		//Remplissage des cases disponibles
		listCases = new LinkedList<Case>();
		LCButtons = new LinkedList<CaseButton>();

		listCases.add(new Case_dalle_sol());
		listCases.add(new Case_escalier(Orientation.NORD, null, null));
		listCases.add(new Case_fendue_sol());
		listCases.add(new Case_herbe());
		listCases.add(new Case_mur());
		listCases.add(new Case_rocher());
		listCases.add(new Case_void());

		//CADRE PRINCIPAL
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

		//PANEL DESSIN
		panDessin = new PanelEdition(15,10);
		cadre.add(panDessin, BorderLayout.EAST);

		//INTERACTIONS
		panInteractions = new JPanel();
		panInteractions.setLayout(new BoxLayout(panInteractions, BoxLayout.Y_AXIS));
		panInteractions.setPreferredSize(new Dimension(170,600));
		
		
		panCases = new JPanel();
		panCases.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		panCases.setBorder(BorderFactory.createTitledBorder("Cases disponibles :"));
		
		sliderThickness = new JSlider(1,6,1);
		sliderThickness.setBorder(BorderFactory.createTitledBorder("Taille : 1"));
		
		for(int i = 0 ; i < listCases.size() ; i++){
			LCButtons.add(new CaseButton(listCases.get(i))); //Creation des CaseButton grace a la liste de cases
		}

		//REMPLISSAGE INTERACTIONS
		for(int i = 0 ; i < LCButtons.size() ; i++){
			panCases.add(LCButtons.get(i));
		}

		panInteractions.add(sliderThickness);
		panInteractions.add(panCases);

		cadre.add(panInteractions, BorderLayout.WEST);

		//FINALISATION
		this.addListeners();
		this.setContentPane(cadre);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Ajoute tous les listeners des bouttons
	 */
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
				sliderThickness.setBorder(BorderFactory.createTitledBorder("Taille: "+sliderThickness.getValue()));
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

		for(CaseButton bt : LCButtons){
			bt.addActionListener(new CaseButtonListener(bt, panDessin));
		}

	}

	/**
	 * Permet d'afficher le JDialog pour créer un nouveau terrain
	 */
	private void showDialogNew(){

		//Interface d'écoute sur le résultat du JDialog
		DialogListener dl = new DialogListener() {

			@Override
			public void onValidate(Vecteur v) {
				panDessin.reinitialize(v.x, v.y); //Reinitialisation avec la nouvelle taille
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
