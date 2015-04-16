package fr.donjon.start;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.editor.DialogListener;
import fr.donjon.editor.DialogNouveau;
import fr.donjon.utils.Vecteur;


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
		
		// lecture de l'image
		try {
			this.im=ImageIO.read (new File (srcImage));
		}
		catch (IOException e){
			System.out.println ("Could not load image file.");
		}

		this.launcher = l;
		// JPANEL ET BOUTONS
		//Ajout du bouton à notre content pane
		panel.add(boutonStart);
		this.add(panel);
		// Actions listener
		this.addListeners();



	}

	// méthode pour peindre la fenetre 

	public void paint(Graphics g){
		if (im!=null) {
			g.drawImage (im, 0, 0, this);	
		}
		System.out.println (im);



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
