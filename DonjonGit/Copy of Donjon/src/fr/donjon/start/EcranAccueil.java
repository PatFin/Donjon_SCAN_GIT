package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class EcranAccueil extends MyJPanel{ 

	// déclaration de l'image
	Image im; // IMAGE
	final static String srcImage = "BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE

	// déclaration des boutons et du panel et de l'icone pour le bouton
	private JPanel panel = new JPanel();
	ImageIcon iconePlayer = new ImageIcon(ImageManager.getImage("Player.png", getClass().getSimpleName())); // icone de l'image du joueur
	ImageIcon iconeInfinity = new ImageIcon(ImageManager.getImage("infinity.png", getClass().getSimpleName())); // icone du sigle infini
	
	private JButton boutonJeuLineaire = new JButton("Jeu Linéaire",iconePlayer);
	private JButton boutonJeuInfini=new JButton ("Jeu Infini",iconeInfinity);

	private Launcher launcher;

	//Constructeur de la classe EcranAccueil

	public EcranAccueil (Launcher l){

		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		boutonJeuInfini.setPreferredSize(new Dimension(180, 60));
		boutonJeuLineaire.setPreferredSize(new Dimension(180, 60));
		
		this.launcher = l;
		
		// lecture de l'image
		this.im = ImageManager.getImage(srcImage, this.getClass().getSimpleName());


		// JPANEL ET BOUTONS
		//Ajout du bouton � notre content pane
		panel.add(boutonJeuLineaire);
		panel.add(boutonJeuInfini);
		this.add(panel);
		// Actions listener
		this.addListeners();

	}

	// méthode pour peindre l'image de fond
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (im!=null) {
			g.drawImage (im, 0, 0, this);	
		}
	}
	//les actions de la souris

	private void addListeners(){

		boutonJeuLineaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launcher.startGame(0);

			}
		});
		
		boutonJeuInfini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launcher.startGame(1);

			}
		});

	}

	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAttaque() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		
	}





}
