package fr.donjon.editor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
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

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_escalier;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_rocher;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.classes.salles.SalleEssai;
import fr.donjon.start.SimplePanel;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Vecteur;

/**
 * 
 * Fen�tre principale de l'�diteur
 * 
 * @author Baptiste
 *
 */
public class EditorWindow extends JFrame{

	public static void main(String[] a){

		EditorWindow window = new EditorWindow("Editeur de cartes");

	}

	//Cadre principal
	JPanel cadre;

	//Panel menu
	JPanel panMenu;
	JButton BNouveau;
	JButton BSauvegarder;
	JButton BDelete;
	JButton BFill;
	JButton BOuvrir;
	
	JButton BEssayer;

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
		listCases.add(new Case_escalier());
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
		BDelete = new JButton("Supprimer");
		BFill = new JButton("Remplir");
		BOuvrir = new JButton("Ouvrir");
		BEssayer = new JButton("Essayer");

		panMenu.add(BNouveau);
		panMenu.add(BOuvrir);
		panMenu.add(BSauvegarder);
		panMenu.add(BFill);
		panMenu.add(BDelete);
		panMenu.add(BEssayer);

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
				panDessin.fill(new Case_void());
			}
		});

		sliderThickness.addChangeListener( new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panDessin.setThickness(sliderThickness.getValue());
				sliderThickness.setBorder(BorderFactory.createTitledBorder("Taille: "+sliderThickness.getValue()));
			}
		});

		BNouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Interface d'�coute sur le r�sultat du JDialog
				DialogListener dl = new DialogListener() {

					@Override
					public void onCancel() {
					}

					@Override
					public void onValidate(String name, int index) {
						
					}

					@Override
					public void onValidate(Vecteur v) {
						panDessin.reinitialize((int)v.x, (int)v.y); //Reinitialisation avec la nouvelle taille
					}
				};

				DialogNouveau d = new DialogNouveau(null, "Taille de la grille :", true,dl);

			}
		});

		BSauvegarder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DialogListener l = new DialogListener() {
					
					@Override
					public void onCancel() {
						
					}
					
					@Override
					public void onValidate(String name, int index) {
						SalleDescription sd = new SalleDescription(panDessin.cases, panDessin.width, panDessin.height, index, name);
						MapFileHandler.createMapFile(sd, true);
					}

					@Override
					public void onValidate(Vecteur v) {
						
					}
				};
				
				DialogSauvegarder dl = new DialogSauvegarder(null, "Sauvegarde de carte", true, l);
			}
		});

		BFill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panDessin.fill(panDessin.caseT);
			}
		});

		BOuvrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		BEssayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int y = 0 ; y < panDessin.height ; y++){
					for(int x = 0 ; x < panDessin.width ; x++){
						panDessin.cases[x][y].setCollisionBoxLocation(y, x);
					}
				}
				
				JFrame frame = new JFrame("Essai de carte");
				
				SalleEssai essai = new SalleEssai(new Rectangle(panDessin.width*Case.TAILLE, panDessin.height*Case.TAILLE), new Heros(200,200)
				, panDessin.cases);
				
				SimplePanel gpanel = new SimplePanel(essai);
				gpanel.addKeyListener(new JeuKeyAdapter(gpanel));
				
				frame.setContentPane(gpanel);
				frame.pack();
				frame.setResizable(false);
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setVisible(true);
				gpanel.startGame();
				
			}
		});
		
		for(CaseButton bt : LCButtons){
			bt.addActionListener(new CaseButtonListener(bt, panDessin));
		}

	}




}
