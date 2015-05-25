/**
 * 
 */
package fr.donjon.zpoubelle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * La classe m�re qui permet de gerer l'affichage et la mise a jour d'une salle
 * cette classe ne se charge que de l'affichage
 * @author Baptiste
 */
public abstract class GamePanel extends MyJPanel implements EcouteurClavier{

	public class TimerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			update();
			temps +=timerTime;
		}

	}
	static final int HAUTEUR = 9;

	static int instances = 0;
	static final int LARGEUR = 15;
	final static int timerTime = 30;

	BufferedImage arrierePlan;
	Graphics buffer;
	int instance;

	long ta = -1;
	long temps;

	Timer timer;

	protected Gestionnaire gestionnaire;
	public Rectangle ecran;

	/**
	 * Permet de creer un JPanel contenant le jeu
	 * @param s	La salle a dessiner
	 */
	public GamePanel(){

		initialisationFenetre();
		timer = new Timer(timerTime, new TimerAction());
		//Le timer sera d�marr� plus tard grace a la m�thode startGame()
		
		instance = instances;
		instances++;
	}

	private void initialisationFenetre(){

		this.setSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));
		this.setPreferredSize(new Dimension(LARGEUR*Case.TAILLE, HAUTEUR*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		//Image buffer pour dessiner dessus
		arrierePlan = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);

		//R�cuperation de la partie graphique de de l'image pour dessiner dessus
		buffer = arrierePlan.getGraphics();
	}

	@Override
	public void attaque(Orientation o) {
		gestionnaire.attaque(o);
	}


	@Override
	public void deplacement(Vecteur v) {
		gestionnaire.deplacement(v);
	}

	/**
	 * Dessin de la salle dans le JPanel
	 */
	@Override
	public void paint(Graphics g){

		this.gestionnaire.currentRoom.draw(temps, g);
	}

	/**
	 * Permet de d�marrer l'actualisation du jeu (par d�faut en pause)
	 */
	@Override
	public void startGame(){
		this.timer.start();
	}
	
	///////////////////////////////////////////////////////
	//INTERFACE D'ECOUTE///////////////////////////////////
	///////////////////////////////////////////////////////
	
	//On fait passer les ordres au Gestionnaire et on intercepte celui pour stopper le Timer
	
	@Override
	public void stopAttaque() {
		gestionnaire.stopAttaque();
	}

	@Override
	public void stopDeplacement() {
		gestionnaire.stopDeplacement();
	}

	/**
	 * Met en pause le jeu
	 */
	@Override
	public void stopGame(){
		this.timer.stop();
	}

	@Override
	public void togglePause() {
		if(timer.isRunning())timer.stop();
		else timer.start();
	}

	/**
	 * Permet de mettre a jour et de dessiner la salle
	 */
	public void update(){
		
		//Shows the fps of the actual game
		if(ta == -1)ta = System.currentTimeMillis();
		else{
			if(temps%900==0){//Add comment to hide the message
				System.out.println("FPS:" + 1000/(System.currentTimeMillis() - ta)+ " from GamPanel "+instance+ " (GamePanel l.90)");
			}
			ta = System.currentTimeMillis();
		}
		
		
		this.gestionnaire.update(temps);
		repaint();
	}
	
	@Override
	public void utiliseObjet(int reference) {
		gestionnaire.utiliseObjet(reference);
	}

	

}
