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

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.classes.Case;
import fr.donjon.classes.Salle;

/**
 * @author Baptiste
 *
 */
public abstract class GamePanel extends JPanel {

	
	static final int LARGEUR = 15;
	static final int HAUTEUR = 9;
	final static int timerTime = 20;

	BufferedImage arrierePlan;
	Graphics buffer;
	Rectangle ecran;
	Timer timer;
	
	long temps;

	Salle salle;

	
	public GamePanel(Salle s){
		
		initialisationFenetre();
		
		timer = new Timer(timerTime, new TimerAction());
		
		this.salle =  new Salle(ecran);
		
	}
	
	private void initialisationFenetre(){

		this.setSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));
		this.setPreferredSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		//Image buffer pour dessiner dessus
		arrierePlan = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);

		//Récuperation de la partie graphique de de l'image pour dessiner dessus
		buffer = arrierePlan.getGraphics();
	}
	
	public void paint(Graphics g){

		salle.draw(temps, g);

	}

	public void update(){

		salle.update(temps);
		repaint();
	}

	public void changeSalle(Salle nSalle){
		
		timer.stop();
		this.salle = nSalle;
		timer.start();
		
	}
	
	public void startGame(){
		this.timer.start();
	}
	
	public class TimerAction implements ActionListener{
		// actions listener appelés toutes les 20ms
		public void actionPerformed(ActionEvent e){
			update();
			temps +=timerTime;
		}

	}
	
	
}
