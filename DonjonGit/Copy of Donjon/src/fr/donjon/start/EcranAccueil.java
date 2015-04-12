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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class EcranAccueil extends JFrame implements MouseListener, MouseMotionListener{ 
	
	// déclaration de l'image
	Image im; // IMAGE
	final static String src="C:/SVN/Background.jpg"; // FICHIER SOURCE POUR CHARGER L'IMAGE
	final int SCREENX=600; // LONGUEUR FENETRE
	final int SCREENY=480; // LARGEUR FENETRE
	
	// déclaration des boutons et du panel
	private JPanel panel = new JPanel();
	private JButton boutonStart = new JButton("Click to Play !! ");
	
	
		// constructeur de la classe EcranAccueil
	
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
	    
	    // Actions listener
	    this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	
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
	    	System.out.println("clique");
	    	
	    }
	    public void mouseExited( MouseEvent e ) { }        
	    public void mousePressed( MouseEvent e ) { }
	
	

public static void main (String [] args){
	EcranAccueil start= new EcranAccueil();
}




}
