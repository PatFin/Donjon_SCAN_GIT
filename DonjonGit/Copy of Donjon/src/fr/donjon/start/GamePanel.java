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

import fr.donjon.classes.Heros;
import fr.donjon.classes.Linear_Castle;
import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case;

/**
 * La classe mère qui permet de gerer l'affichage et la mise a jour d'une salle
 * 
 * @author Baptiste
 *
 */
public abstract class GamePanel extends JPanel {

	
	static final int LARGEUR = 15;
	static final int HAUTEUR = 9;
	final static int timerTime = 10;

	BufferedImage arrierePlan;
	Graphics buffer;
	
	Rectangle ecran;
	Timer timer;
	
	long temps;

	public Salle salle; 	//The room to update and draw
	
	/**
	 * Permet de creer un JPanel contenant le jeu
	 * @param s	La salle a dessiner
	 */
	public GamePanel(Salle s){
		
		initialisationFenetre();
		
		timer = new Timer(timerTime, new TimerAction());
		
		this.salle = s;
		
	}
	
	private void initialisationFenetre(){

		this.setSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));
		this.setPreferredSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		//Image buffer pour dessiner dessus
		arrierePlan = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);

		//Récuperation de la partie graphique de de l'image pour dessiner dessus
		buffer = arrierePlan.getGraphics();
	}
	
	/**
	 * Dessin de la salle dans le JPanel
	 */
	public void paint(Graphics g){

		salle.draw(temps, g);

	}

	/**
	 * Permet de mettre a jour et de dessiner la salle
	 */
	public void update(){

		salle.update(temps);
		repaint();
	}

	/**
	 * Permet de changer la salle active 
	 * @param nSalle La nouvelle salle a dessiner
	 */
	public void changeSalle(Salle nSalle){
		
		timer.stop();
		this.salle = nSalle;
		timer.start();
		
	}
	
	/**
	 * Permet de démarrer l'actualisation du jeu (par défaut en pause)
	 */
	public void startGame(){
		this.timer.start();
	}
	
	/**
	 * Met en pause le jeu
	 */
	public void stopGame(){
		this.timer.stop();
	}
	
	public class TimerAction implements ActionListener{
		// actions listener appelés toutes les 20ms
		public void actionPerformed(ActionEvent e){
			update();
			temps +=timerTime;
		}

	}
	
	
}
