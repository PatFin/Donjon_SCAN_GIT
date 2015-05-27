package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	ImageIcon iconeEditeur = new ImageIcon(ImageManager.getImage("mapEditor.png", getClass().getSimpleName())); 		// icone du sigle mapEditor
	ImageIcon iconeInfinity = new ImageIcon(ImageManager.getImage("infinity.png", getClass().getSimpleName())); 		// icone du sigle infini
	ImageIcon iconePlayer = new ImageIcon(ImageManager.getImage("Player.png", getClass().getSimpleName())); 			// icone de l'image du joueur
	ImageIcon iconePlayerKneeling = new ImageIcon(ImageManager.getImage("Player_Dead_Alternative.png", getClass().getSimpleName()));
	ImageIcon iconePlayerDead = new ImageIcon(ImageManager.getImage("Player_Dead.png", getClass().getSimpleName()));
	
	// declaration de l'image
	Image im; // IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	private JButton boutonEditeur= new JButton("Editeur de map", iconeEditeur);
	
	private JButton boutonJeuInfini=new JButton ("Labyrinthe",iconePlayerDead);
	private JButton boutonJeuLineaire = new JButton("Jeu Lineaire",iconePlayerKneeling);
	private JButton boutonJeuTutoriel = new JButton("Tutoriel", iconePlayer);
	
	private JSlider taille = new JSlider(5,15,10);
	private JLabel tailleMessage = new JLabel("Donjon Size: 10");
	
	private JPanel panel1 = new JPanel(); //Will contain the buttons
	private JPanel panel2 = new JPanel(); //Will contain the slider giving the size of the donjon and the JLabel indicating its value
	
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
		
		//Ajout d'un slider pour choisir la taille du donjon
		panel2.add(tailleMessage);
		panel2.add(taille);
		this.add(panel2);
		
		// Actions listener
		this.addListeners();
		
	}

	/**
	 * Cette methode rassemble les operations à faire lorsqu'on clique sur chacun des boutons ou qu'on fais varier le slider.
	 * Cette methode n'est appelée que dans le constructeur pour initialiser les actions à effectuer lorsqu'on touche aux composants.
	 */
	private void addListeners(){

		boutonJeuLineaire.addActionListener(new ActionListener() {
			/**
			 * Si on a appuyï¿½ sur le bouton, on commande au lancher un nouveau jeu du type correspondant
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//On rï¿½initialise le nombre de salles
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
				//On rï¿½initialise le nombre de salles
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
				//On reinitialise le nombre de salles qui ont été créées.
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
				//On commande l'editeur de map
				new EditorWindow("Editeur de cartes");
			}
		});
		
		//On update le texte du JLabel à chaque fois que le JSLider change de valeur.
		taille.addChangeListener(new ChangeListener(){

			/*
			 * (non-Javadoc)
			 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
			 */
			@Override
			public void stateChanged(ChangeEvent arg0) {
				tailleMessage.setText("Donjon Size: "+taille.getValue());
			}
		});
	}

	/**
	 * On affiche les composants du JPanel this et on rajoute une image de fond.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (im!=null) {
			g.drawImage (im, 0, 0, this);	
		}
	}
}
