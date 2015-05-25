package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.Donjons.DonjonNonLineaire;
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.editor.EditorWindow;
import fr.donjon.salles.Salle;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.ImageManager;

public class EcranAccueil extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	// déclaration de l'image
	Image im; // IMAGE
	final static String srcImage = "BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE

	// déclaration des boutons et du panel et de l'icone pour le bouton
	private JPanel panel = new JPanel();
	ImageIcon iconePlayer = new ImageIcon(ImageManager.getImage("Player.png", getClass().getSimpleName())); // icone de l'image du joueur
	ImageIcon iconeInfinity = new ImageIcon(ImageManager.getImage("infinity.png", getClass().getSimpleName())); // icone du sigle infini
	ImageIcon iconeEditeur = new ImageIcon(ImageManager.getImage("mapEditor.png", getClass().getSimpleName())); // icone du sigle mapEditor
	
	private JButton boutonJeuLineaire = new JButton("Jeu Lin�aire",iconePlayer);
	private JButton boutonJeuInfini=new JButton ("Jeu Infini",iconeInfinity);
	private JButton boutonJeuTutoriel = new JButton("Tutoriel");
	private JButton boutonEditeur= new JButton("Editeur de map", iconeEditeur);
	
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
		panel.add(boutonJeuTutoriel);
		panel.add(boutonJeuLineaire);
		panel.add(boutonJeuInfini);
		panel.add(boutonEditeur);
		this.add(panel);
		// Actions listener
		this.addListeners();
		
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
				
				ecouteur.requestNewGame(new DonjonLineaire(10));
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
				ecouteur.requestNewGame(new DonjonNonLineaire(5, 8));
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
				ecouteur.requestNewGame(new GestionnairePatrickBasique(4, 3));
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
}
