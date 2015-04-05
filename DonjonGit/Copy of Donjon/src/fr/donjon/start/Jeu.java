/**
 * 
 */
package fr.donjon.start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

import fr.donjon.classes.Heros;
import fr.donjon.classes.TestImage;
import fr.donjon.utils.Orientation;


// A VINCENT NE PAS TOUCHER SVP :)

/**
 * @author Baptiste
 *
 */
public class Jeu extends JFrame {
	
	Graphics buffer;
    Rectangle ecran;
    Image image;
	TestImage timage;
	
	// taille de l'écran de jeu
	
	final static int LARGEUR = 1000;
	final static int LONGUEUR = 800;
    BufferedImage arrierePlan;
    
    
    // touches nécessaires pour jouer
    boolean ToucheZ;
    boolean ToucheQ;
    boolean ToucheS;
    boolean ToucheD;
    
    // Timer

    Timer timer;
    long temps;
   
	
	// PERSONNAGES
	Heros perso;
    
	public Jeu(){
		
        setSize(LARGEUR,LONGUEUR);
        setResizable(false);
        setVisible(true);
        
		
        ecran = new Rectangle(getInsets().left,
        		getInsets().top,
        		getSize().width-getInsets().right-getInsets().left,
        		getSize().height-getInsets().bottom-getInsets().top);
        
       arrierePlan = new BufferedImage(getSize().width,getSize().height, BufferedImage.TYPE_INT_RGB);
        
       buffer = arrierePlan.getGraphics();
       
       timage = new TestImage(0, 0);
       repaint();
       
       //TIMER
       this.temps = 0;
       timer = new Timer(100, new TimerAction());
       timer.start();
       
       // boutons de contôles initialisés à faux
       
       this.ToucheZ = false;
       this.ToucheS = false;
       this.ToucheQ = false;
       this.ToucheD = false;
       
       // Pour permettre les interactions dans la classe Jeu_this_keyAdapter
       
       this.addKeyListener(new Jeu_this_keyAdapter());
       
       // PERSONNAGES
       
       this.perso=new Heros (0,0);
       
      
       
       
	}
	
	// test peinture d'un rectangle vert

	public void paint(Graphics g){
		
        
        buffer.setColor(Color.green);
        buffer.fillRect(ecran.x,ecran.y,ecran.x+ecran.width,ecran.y+ecran.height);
		
		
        timage.draw(0, buffer);
		
        g.drawImage(arrierePlan, 0, 0, this);
        
	}
	
	// méthode timer 
	
		private class TimerAction implements ActionListener{
			// actions listener appelés toutes les 100mms
			public void actionPerformed(ActionEvent e){
				boucle_principale_jeu();
				temps++;
			}
			
		}
		
	// BOUCLE DE JEU 
		
		// Action de bouger le perso  dans toutes les directions ( Z,Q,S,D)
	
    
       public void boucle_principale_jeu(){
    	   
        	if (ToucheQ) { 
        		perso.marcher(Orientation.OUEST);
        	}
        	else if (ToucheD) { 
        		perso.marcher(Orientation.EST); 
        	}
        	else if (ToucheZ) { 
        		perso.marcher(Orientation.NORD);
        	}
        	else if (ToucheS) { 
        		perso.marcher(Orientation.SUD); 
        }else { 
        	perso.stop();
        	}
        	// déplace l'objet sans le dessiner
        	perso.update(temps);
        	// force le rafraîchissement de l'image et le dessin de l'objet
        	
        	repaint();
        	
        }
        
        
      // MAIN
        
    public static void main(String[] args) {
        
        Jeu Monjeu = new Jeu();
        
    }
    
        


   // Classe pour les actions listener     
        
       private class Jeu_this_keyAdapter extends KeyAdapter {
    	   
    	   // Pressions des boutons
    	   
       	 public void keyPressed(KeyEvent e) {
       		    int code = e.getKeyCode();
       		    setTitle("Code clavier :"+Integer.toString(code));
       		    switch (code) {
       		  
       		    // fleche gauche
       		    case KeyEvent.VK_Q   : ToucheQ=true;
       		                            break;
       		    // fleche droit                                         
       		    case KeyEvent.VK_D  : ToucheD=true;
       		                            break;    
       		    // fleche haut
       		    case KeyEvent.VK_Z   : ToucheZ=true;
       		                            break;
       		    // fleche bas                                         
       		    case KeyEvent.VK_S  : ToucheS=true;
       		                            break;    
       		    // Touche ESCAPE pour sortir du programme                                       
       		    case KeyEvent.VK_ESCAPE : System.exit(0);
       		                             break;
       		    // Touche Pause pour arrêter ou relancer le timer
       		    case KeyEvent.VK_ENTER   :  if (timer.isRunning()) timer.stop();
       		                                             else     timer.start();
       		                            break;       
       		    }
       		    
       		    
       		}
       	 
       	 	// relachement des boutons

       		 public void keyReleased(KeyEvent e) {

       		    int code = e.getKeyCode();
       		    switch (code) {
       		    
       		    // fleche gauche
       		    case KeyEvent.VK_Q   : ToucheQ=false;
       		                            break;
       		    // fleche droit                                         
       		    case KeyEvent.VK_D  : ToucheD=false;
       		                            break;    
       		    // fleche haut
       		    case KeyEvent.VK_Z   : ToucheZ=false;
       		                            break;
       		    // fleche bas                                         
       		    case KeyEvent.VK_S  : ToucheS=false;
       		                            break;  
       		    }
       		    // pour tester les écouteurs
       		    setTitle("touche relachée");
       		    }
       		 
       	
       	    }
       	
}


