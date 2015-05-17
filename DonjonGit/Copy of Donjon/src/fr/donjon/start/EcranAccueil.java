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
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.ImageManager;

public class EcranAccueil extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	// dÃ©claration de l'image
	Image im; // IMAGE
	final static String srcImage = "BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE

	// dÃ©claration des boutons et du panel et de l'icone pour le bouton
	private JPanel panel = new JPanel();
	ImageIcon iconePlayer = new ImageIcon(ImageManager.getImage("Player.png", getClass().getSimpleName())); // icone de l'image du joueur
	ImageIcon iconeInfinity = new ImageIcon(ImageManager.getImage("infinity.png", getClass().getSimpleName())); // icone du sigle infini
	
	private JButton boutonJeuLineaire = new JButton("Jeu LinÃ©aire",iconePlayer);
	private JButton boutonJeuInfini=new JButton ("Jeu Infini",iconeInfinity);
	
	public EcouteurLauncher ecouteur;

	//Constructeur de la classe EcranAccueil

	public EcranAccueil (){

		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		boutonJeuInfini.setPreferredSize(new Dimension(180, 60));
		boutonJeuLineaire.setPreferredSize(new Dimension(180, 60));
		
		// lecture de l'image
		this.im = ImageManager.getImage(srcImage, this.getClass().getSimpleName());


		// JPANEL ET BOUTONS
		//Ajout du bouton ï¿½ notre content pane
		panel.add(boutonJeuLineaire);
		panel.add(boutonJeuInfini);
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
	 * Cette méthode décrit les opérations à faire lorsqu'on clique sur chacun des boutons.
	 */
	private void addListeners(){

		boutonJeuLineaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ecouteur.requestNewGame(new DonjonLineaire(10));
			}
		});
		
		boutonJeuInfini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ecouteur.requestNewGame(new GestionnairePatrickBasique(4, 3));

			}
		});

	}
}
