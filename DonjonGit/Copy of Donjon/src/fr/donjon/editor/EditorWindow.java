package fr.donjon.editor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseBloquante;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseEscalier;
import fr.donjon.cases.CaseFendue;
import fr.donjon.cases.CaseGlass;
import fr.donjon.cases.CaseHerbe;
import fr.donjon.cases.CaseLave;
import fr.donjon.cases.CaseLiane;
import fr.donjon.cases.CaseMur;
import fr.donjon.cases.CaseRocher;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.cases.CaseTeleportation;
import fr.donjon.cases.CaseVide;
import fr.donjon.cases.CaseWater;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.SalleEssai;
import fr.donjon.zpoubelle.SimplePanel;


/**
 * 
 * Fen�tre principale de l'�diteur
 * 
 * @author Baptiste
 *
 */
public class EditorWindow extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton BDelete;

	JButton BEssayer;

	JButton BFill;
	JButton BNouveau;
	JButton BOuvrir;
	JButton BSauvegarder;
	//Cadre principal
	JPanel cadre;
	LinkedList<CaseButton> LCButtons;

	LinkedList<Case> listCases;

	JPanel 	panCases;
	//Panel dessin
	PanelEdition panDessin; 

	//Panel Interactions (boutons)
	JPanel panInteractions;
	//Panel menu
	JPanel panMenu;


	JSlider sliderThickness;

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

		listCases.add(new CaseDalle());
		listCases.add(new CaseEscalier());
		listCases.add(new CaseFendue());
		listCases.add(new CaseGlass());	
		listCases.add(new CaseHerbe());
		listCases.add(new CaseMur());
		listCases.add(new CaseLiane());
		listCases.add(new CaseRocher());
		listCases.add(new CaseSwitch());
		listCases.add(new CaseTeleportation());
		listCases.add(new CaseLave());
		listCases.add(new CaseWater());
		listCases.add(new CaseBloquante());	
		listCases.add(new CaseVide());
		
		//TODO
		/*
		CaseMap map = ImageManager.map1;
		for(int x= 0 ; x < map.w ; x++){
			for(int y = 0 ; y < map.h ; y++){
				listCases.add(new CaseSource(new Vecteur(x,y)));
			}
		}
		*/

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
		this.setVisible(true);

	}

	/**
	 * Ajoute tous les listeners des bouttons
	 */
	private void addListeners(){ 
		
		BDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panDessin.fill(new CaseVide());
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
				//Interface d'écoute sur le résultat du JDialog
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

				new DialogNouveau(null, "Taille de la grille :", true,dl);

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
						int finalIndex = MapFileHandler.createMapFile(sd, false);
						if(index != finalIndex){
							
							JOptionPane.showMessageDialog(null, "L'index existe déja, nouveau index: "+finalIndex, "Index deja existant :", JOptionPane.INFORMATION_MESSAGE);
						}
					}

					@Override
					public void onValidate(Vecteur v) {

					}
				};

				new DialogSauvegarder(null, "Sauvegarde de carte", true, l);
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
				
				SalleDescription sd = null;
				
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(MapFileHandler.absolutePath+"/"+MapFileHandler.pathMaps));
				fc.setFileFilter(new FileFilter() {

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						}

						String extension = null;
						try{
							extension = f.getName().split("\\.")[1];
						}
						catch(ArrayIndexOutOfBoundsException e){
						}
						
						if (extension != null) {
							if (extension.equals(MapFileHandler.extension)){
								return true;
							} else {
								return false;
							}
						}

						return false;
					}

					@Override
					public String getDescription() {
						return null;
					}
				});
				int returnVal = fc.showOpenDialog(cadre);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sd = MapFileHandler.getFileToDescription(fc.getSelectedFile());
					panDessin.reinitialize(sd.matrix);
				}
				
				
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

				SalleEssai essai = null;
				

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

	public static void main(String[] a){

		new EditorWindow("Editeur de cartes");
	}




}
