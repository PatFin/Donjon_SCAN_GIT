/**
 * 
 */
package fr.donjon.testblac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.Case;

/**
 * @author Baptiste
 *
 */
public class PanelJeu extends JPanel implements EcouteurClavier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int instances = 0;
	int instance;

	final static int timerTime = 30;

	public Rectangle ecran;
	Timer timer;

	GestionnaireSalle gestion;
	DessinateurSalle dessinateur;
	
	
	long temps;
	long ta = -1;
	
	int fps;

	/**
	 * 
	 */
	public PanelJeu() {

		this.setSize(new Dimension( 15*Case.TAILLE, 10*Case.TAILLE));
		this.setPreferredSize(new Dimension( 15*Case.TAILLE, 10*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		timer = new Timer(timerTime, new TimerAction());
		//Le timer sera d�marr� plus tard grace a la m�thode startGame()

		////
		
		//gestion = new GestionnaireBasique();
		//gestion = new GestionnairePatrickBasique(4,3);
		gestion = new DonjonLineaire(10);
		dessinateur = new DessinateurSalle(ecran, gestion.sActuelle);
		
		startGame();
		
		instance = instances;
		instances++;

	}

	public void update(){

		//Shows the fps of the actual game
		
		if(ta == -1)ta = System.currentTimeMillis();
		else{
			if(temps%900==0){//Add comment to hide the message
				fps =  (int) (1000/(System.currentTimeMillis() - ta));
				//System.out.println(""+fps);
			}
			ta = System.currentTimeMillis();
		}
		

		gestion.update(temps);

		repaint();
		
		if(dessinateur.salle != gestion.sActuelle){
			dessinateur.changerSalle(gestion.sActuelle);
		}

	}


	/**
	 * Dessine la salle du donjon actuelle.
	 * On place l'image de la salle de fa�on � ce qu'on voit le personnage au centre de la fen�tre.
	 * On peut donc avoir des salles qui sont plus grandes que la fen�tre.
	 * Toutefois, si salle plus petite que la fen�tre, on centre l'image.
	 */
	@Override
	public void paint(Graphics g) {
		
		//On remplit la salle d'un fond noir (pour les tests mettre du gris)
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//On obtient les dimensions de la salle et de la fen�tre
		Vecteur z = gestion.sActuelle.getPixelSize();
		int Width = getWidth();
		int Height = getHeight();
		
		
		int x; //coordonn�e horizontale de la cam�ra
		if(z.x<Width){
			//on centre la salle dans la fen�tre
			x=(int)(Width/2-z.x/2); 
		}else{
			//on place la cam�ra sur le h�ros
			x=(int)(- gestion.centreCamera.x + Width/2);
		}
		
		//Cf au dessus
		int y; //coordonn�e verticale de la cam�ra
		if(z.y<Height){
			y=(int)(Height/2-z.y/2);
		}else{
			y=(int)(- gestion.centreCamera.y + Height/2);
		}
		
		
		//On dessine la salle 
		g.drawImage(dessinateur.getImage(g, temps),x,y,null);
	}

	/**
	 * Lance le jeu
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
	
	/**
	 * Classe interne du timer
	 * On anime la salle et on incr�mente la variable temps
	 */
	public class TimerAction implements ActionListener{

		public void actionPerformed(ActionEvent e){
			update();
			temps +=timerTime;
		}

	}
	
	

	///////////////////////////////////////////////////////
	//INTERFACE D'ECOUTE///////////////////////////////////
	///////////////////////////////////////////////////////
	
	//On fait passer les ordres au Gestionnaire et on intercepte celui pour stopper le Timer
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Orientation o) {
		gestion.attaque(o);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopAttaque()
	 */
	@Override
	public void stopAttaque() {
		gestion.stopAttaque();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		gestion.deplacement(v);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		gestion.utiliseObjet(reference);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {
		//TODO check this is working
		if(timer.isRunning()){
			this.stopGame();
		}else{
			this.startGame();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		gestion.stopDeplacement();
	}


}
