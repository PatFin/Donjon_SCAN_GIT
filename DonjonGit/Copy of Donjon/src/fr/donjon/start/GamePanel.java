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

import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case;
import fr.donjon.test.Gestionnaire;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;

/**
 * La classe mère qui permet de gerer l'affichage et la mise a jour d'une salle
 * cette classe ne se charge que de l'affichage
 * 
 * @author Baptiste
 *
 */
public abstract class GamePanel extends JPanel implements EcouteurClavier{


	static final int LARGEUR = 15;
	static final int HAUTEUR = 9;
	final static int timerTime = 10;

	BufferedImage arrierePlan;
	Graphics buffer;
	Gestionnaire gestionnaire;

	public Rectangle ecran;
	Timer timer;

	long temps;

	public Salle salle; 	//LA salle a afficher

	/**
	 * Permet de creer un JPanel contenant le jeu
	 * @param s	La salle a dessiner
	 */
	public GamePanel(){

		initialisationFenetre();
		timer = new Timer(timerTime, new TimerAction());

		//Le timer sera démarré plus tard grace a la méthode startGame()
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
	 * 
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
	
	///////////////////////////////////////////////////////
	//INTERFACE D'ECOUTE///////////////////////////////////
	///////////////////////////////////////////////////////
	
	//On fait passer les ordres au Gestionnaire et on intercept celui pour stopper le Timer
	
	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		gestionnaire.attaque(o);
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub
		gestionnaire.stopAttaque(o);
	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
		gestionnaire.deplacement(o);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		gestionnaire.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		if(timer.isRunning())timer.stop();
		else timer.start();
	}
	
	@Override
	public void stopDeplacement(Orientation o) {
		// TODO Auto-generated method stub
		gestionnaire.stopDeplacement(o);
	}

	

}
