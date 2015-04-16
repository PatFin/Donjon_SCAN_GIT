package fr.donjon.start;

import java.awt.Cursor;
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
	
	// d�claration de l'image
	Image im; // IMAGE
	final static String srcImage="Ressources/Images/BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	
	
	
	// d�claration des boutons et du panel et de l'icone pour le bouton
	private JPanel panel = new JPanel();
	ImageIcon icone= new ImageIcon("Ressources/Images/Player.png"); // icone de l'image
	private JButton boutonStart = new JButton("Click to Play !! ",icone);
	
	
	
		// constructeur de la classe EcranAccueil
	
	public EcranAccueil (){
		
		// lecture de l'image
		try {
			this.im=ImageIO.read (new File (srcImage));
		}
		catch (IOException e){
			System.out.println ("Could not load image file.");
		}
		
		
	
		
		
		
		// definition de la fenetre
		this.setSize(SCREENX,SCREENY);
		this.setTitle ("SCAN DONJON 72");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JPANEL ET BOUTONS
		//Ajout du bouton � notre content pane
	    panel.add(boutonStart);
	    this.setContentPane(panel);
	    
	    // Actions listener
	    this.addListeners();
		
	
	    // setVisible � mettre � la fin
	    this.setVisible(true);
	   
	
	}
		
		// m�thode pour peindre la fenetre 
		
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
				System.out.println("clique");
		    	Launcher lanceJeu= new Launcher();
				
					}
				});

		}
			
	
	

public static void main (String [] args){
	EcranAccueil depart= new EcranAccueil();
}




}
