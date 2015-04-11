package fr.donjon.start;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EcranAccueil extends JFrame {
	
	// déclaration de l'image
	Image im; // IMAGE
	final static String src="C:/SVN/Background.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=600; // LONGUEUR FENETRE
	final int SCREENY=480; // LARGEUR FENETRE
	
	// déclaration des boutons et du panel
	private JPanel panel = new JPanel();
	private JButton boutonStart = new JButton("Click to Play !! ");
	
		
	public EcranAccueil (){
		
		// lecture de l'image
		try {
			this.im=ImageIO.read (new File (src));
		}
		catch (IOException e){
			System.out.println ("Could not load image file.");
		}
	
		
		
		
		// definition de la fenetre
		this.setSize(SCREENX,SCREENY);
		this.setTitle ("SCAN DONJON 72");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPANEL ET BOUTONS
		//Ajout du bouton à notre content pane
	    panel.add(boutonStart);
	    this.setContentPane(panel);
	    
	    // setVisible à mettre à la fin
	    this.setVisible(true);
	
	}
		
		// méthode pour peindre la fenetre 
		
		public void paint(Graphics g){
			if (im!=null) {
				g.drawImage (im, 0, 0, this);	
			}
			System.out.println (im);
			
			
		}
	
	
	

public static void main (String [] args){
	EcranAccueil start= new EcranAccueil();
}




}
