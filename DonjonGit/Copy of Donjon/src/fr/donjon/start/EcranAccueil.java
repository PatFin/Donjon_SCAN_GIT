package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.Donjons.DonjonNonLineaire;
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.editor.EditorWindow;
import fr.donjon.salles.Salle;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.ImageManager;

public class EcranAccueil extends JPanel{ 

	final static String srcImage = "BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	
	private static final long serialVersionUID = 1L;
	ImageIcon iconeEditeur = new ImageIcon(ImageManager.getImage("mapEditor.png", getClass().getSimpleName())); // icone du sigle mapEditor
	ImageIcon iconeInfinity = new ImageIcon(ImageManager.getImage("infinity.png", getClass().getSimpleName())); // icone du sigle infini
	ImageIcon iconePlayer = new ImageIcon(ImageManager.getImage("Player.png", getClass().getSimpleName())); // icone de l'image du joueur

	// déclaration de l'image
	Image im; // IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	private JButton boutonEditeur= new JButton("Editeur de map", iconeEditeur);
	
	private JButton boutonJeuInfini=new JButton ("Jeu Infini",iconeInfinity);
	private JButton boutonJeuLineaire = new JButton("Jeu Lin�aire",iconePlayer);
	private JButton boutonJeuTutoriel = new JButton("Tutoriel");
	
	private JSlider taille = new JSlider(5,15,5);
	
	private JPanel panel1 = new JPanel(); //Will contain the buttons
	private JPanel panel2 = new JPanel(); //Will contain the slider giving the size of the donjon
	
	public EcouteurLauncher ecouteur;

	//Constructeur de la classe EcranAccueil

	public EcranAccueil (){

		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		Dimension d = new Dimension(180,60);
		boutonJeuInfini.setPreferredSize(d);
		boutonJeuLineaire.setPreferredSize(d);
		boutonJeuTutoriel.setPreferredSize(d);
		boutonEditeur.setPreferredSize(d);
		
		// lecture de l'image
		this.im = ImageManager.getImage(srcImage, this.getClass().getSimpleName());


		// JPANEL ET BOUTONS
		//Ajout des boutons dans notre panel
		panel1.add(boutonJeuTutoriel);
		panel1.add(boutonJeuLineaire);
		panel1.add(boutonJeuInfini);
		panel1.add(boutonEditeur);
		this.add(panel1);
		
		panel2.add(taille);
		this.add(panel2);
		
		// Actions listener
		this.addListeners();
		
	}

	/**
	 * Cette m�thode d�crit les op�rations � faire lorsqu'on clique sur chacun des boutons.
	 */
	private void addListeners(){

		boutonJeuLineaire.addActionListener(new ActionListener() {
			/**
			 * Si on a appuy� sur le bouton, on commande au lancher un nouveau jeu du type correspondant
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//On r�initialise le nombre de salles
				Salle.instances = 0;
				
				ecouteur.requestNewGame(new DonjonLineaire(taille.getValue()));
			}
		});
		
		boutonJeuInfini.addActionListener(new ActionListener() {
			/**
			 * Idem
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//On r�initialise le nombre de salles
				Salle.instances = 0;
				
				//On commande un jeu infini
				ecouteur.requestNewGame(new DonjonNonLineaire(taille.getValue(), taille.getValue()));
			}
		});
		boutonJeuTutoriel.addActionListener(new ActionListener(){
			/**
			 * Idem
			 */
			@Override
			public void actionPerformed(ActionEvent e){
				//On r�initialise le nombre de salles
				Salle.instances = 0;
				
				//On commande un jeu test
				ecouteur.requestNewGame(new GestionnairePatrickBasique());
			}
		});
		
		boutonEditeur.addActionListener(new ActionListener() {
			/**
			 * Idem
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//On commande l'�diteur de map
				new EditorWindow("Editeur de cartes");
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (im!=null) {
			g.drawImage (im, 0, 0, this);	
		}
	}
	//les actions de la souris
}
