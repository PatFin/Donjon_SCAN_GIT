package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EcranAccueil extends JPanel{ 

	// déclaration de l'image
	Image im; // IMAGE
	final static String srcImage="Ressources/Images/BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE



	// déclaration des boutons et du panel et de l'icone pour le bouton
	private JPanel panel = new JPanel();
	ImageIcon icone= new ImageIcon("Ressources/Images/Player.png"); // icone de l'image
	private JButton boutonStart = new JButton("Click to Play !! ",icone);

	private Launcher launcher;

	// constructeur de la classe EcranAccueil

	public EcranAccueil (Launcher l){

		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		this.launcher = l;
		
		// lecture de l'image
		try {
			this.im=ImageIO.read (new File (srcImage));
		}
		catch (IOException e){
			System.out.println ("Could not load image file.");
		}


		// JPANEL ET BOUTONS
		//Ajout du bouton à notre content pane
		panel.add(boutonStart);
		this.add(panel);
		// Actions listener
		this.addListeners();

	}

	// méthode pour peindre l'image de fond
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if (im!=null) {
			g.drawImage (im, 0, 0, this);	
		}
	}
	//les actions de la souris

	private void addListeners(){

		boutonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launcher.startGame();

			}
		});

	}





}
