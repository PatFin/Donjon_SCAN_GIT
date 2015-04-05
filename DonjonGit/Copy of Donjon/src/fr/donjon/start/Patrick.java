/**
 * 
 */
package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.classes.Case;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;

/**
 * @author Patrick
 * C'est ma classe dans laquelle je fait mes test personnels. 
 * Donc personne n'y touche pig�!
 * lol vousy voyez quelque chose?
 * ta gueule Patrick
 */
public class Patrick extends JFrame {
	
	static final int LARGEUR = 15;
	static final int HAUTEUR = 10;
	
	BufferedImage arrierePlan;
	Graphics buffer1;
	Salle laSalle;
	Timer monTimer;
	Rectangle ecran;
	JPanel monJpanel;
	Heros monHero;
	long time;
	
	public Patrick(){
		super("Mon test � moi");
		
		monJpanel=new JPanel();
		monJpanel.setPreferredSize(new Dimension(LARGEUR*Case.TAILLE,HAUTEUR*Case.TAILLE));
	    add(monJpanel);
	    pack();
	    
	    time = 0;
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		ecran = new Rectangle(3,26,LARGEUR*Case.TAILLE,HAUTEUR*Case.TAILLE); 
        arrierePlan =new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
        buffer1 = arrierePlan.getGraphics();
		
        this.monHero = new Heros(10*Case.TAILLE-3,7*Case.TAILLE-26);
        laSalle = new Salle(ecran, monHero);
        monTimer = new Timer(20,new TimerAction());
        monTimer.start();
	}
	
	public void paint(Graphics g){
		laSalle.draw(time,g);
	}
	
	
	/**
	 * Timer action implement
	 * @author Patrick
	 *
	 */
    private class TimerAction implements ActionListener {
        // ActionListener appelee toutes les 1000 millisecondes
        public void actionPerformed(ActionEvent e) {
        	time+=20;
            repaint();
        }
    }
	
	public static void main(String [] args){
		Patrick monPat = new Patrick();
	}
}
