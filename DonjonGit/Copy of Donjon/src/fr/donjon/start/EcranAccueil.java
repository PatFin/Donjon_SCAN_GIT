package fr.donjon.start;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class EcranAccueil extends JFrame implements MouseListener, MouseMotionListener{ 
	
	// déclaration de l'image
	Image im; // IMAGE
	final static String srcImage="Ressources/Images/BackgroundMenu.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	
	
	
	// déclaration des boutons et du panel et de l'icone pour le bouton
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
		//Ajout du bouton à notre content pane
	    panel.add(boutonStart);
	    this.setContentPane(panel);
	    
	    // Actions listener
	    boutonStart.addMouseListener(this);
		boutonStart.addMouseMotionListener(this);
		
	
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
	
		//methodes pour les actions de la souris
		
		public void mouseReleased( MouseEvent e ) { }
	    public void mouseDragged( MouseEvent e ) {
	    }
	    public void mouseEntered( MouseEvent e ) { }
	    public void mouseMoved( MouseEvent e ) {}
	    public void mouseClicked( MouseEvent e )  {
	    	if(e.getSource()==boutonStart){
	    	System.out.println("clique");
	    	JeuLineaireVincent lanceJeu= new JeuLineaireVincent();
	    	}
	    	
	    }
	    public void mouseExited( MouseEvent e ) { }        
	    public void mousePressed( MouseEvent e ) { }
	
	

public static void main (String [] args){
	EcranAccueil start= new EcranAccueil();
}




}
