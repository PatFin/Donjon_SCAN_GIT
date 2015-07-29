/**
 * 
 */
package fr.donjon.start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.Donjons.GestionnaireSalle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.Vecteur;
import fr.donjon.cases.Case;


public class PanelJeu extends JPanel implements EcouteurClavier, EcouteurLauncher{
	
	/**
	 * Classe interne du timer
	 * On anime la salle et on incr�mente la variable temps
	 */
	public class TimerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			tempsTimer +=timerTime;
			if(!enPause){
				update();
				temps = tempsTimer;
			}
			repaint();
		}

	}
	
	static int instances = 0;
	final static int timerTime = 30;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean enPause;
	
	DessinateurSalle dessinateur;
	EcouteurLauncher ecouteur;

	int fps;
	GestionnaireSalle gestion;
	int instance;
	
	DessinateurGestionnaire map;
	boolean showMap;
	
	long ta = -1;
	long temps;
	long tempsTimer;
	
	Timer timer;

	public Rectangle ecran;

	public PanelJeu(GestionnaireSalle gestion) {
		
		this.setPreferredSize(new Dimension( 15*Case.TAILLE, 10*Case.TAILLE));
		this.setSize(new Dimension( 15*Case.TAILLE, 10*Case.TAILLE));
		
		ecran = new Rectangle(0,0,getWidth(),getHeight());
		
		
		timer = new Timer(timerTime, new TimerAction());
		
		this.gestion = gestion;
		gestion.ecouteur = this;
		
		dessinateur = new DessinateurSalle(ecran, gestion.getsActuelle());
		
		map = new DessinateurGestionnaire(ecran,gestion);
		
		showMap = false ;
		enPause = false;
		
		instance = instances;
		instances++;
		this.timer.start();
		startGame();
	}


	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Vecteur v) {
		gestion.attaque(v);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		gestion.deplacement(v);
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
		Vecteur z = gestion.getsActuelle().getPixelSize();
		
		int Width = getWidth();
		int Height = getHeight();
		
		
		int x; //coordonnee horizontale de la camera
		if(z.x<Width){
			//on centre la salle dans la fenetre
			x=(int)(Width/2-z.x/2); 
		}else{
			//on place la camera sur le heros
			x=(int)(- gestion.getCentreCamera().x + Width/2);
		}
		
		//Cf au dessus
		int y; //coordonnee verticale de la camera
		if(z.y<Height){
			y=(int)(Height/2-z.y/2);
		}else{
			y=(int)(- gestion.getCentreCamera().y + Height/2);
		}
		
		
		//On dessine la salle 
		g.drawImage(dessinateur.getImage(g, temps),x,y,null);
		
		//Dessin de la map si le bool�en showMap est true (voir toggleMap dans cette classe).
		if(showMap){
			g.drawImage(map.getImage(gestion.smap, gestion.getsActuelle()), 0, 0, getHeight(), getHeight(), null);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurLauncher#requestBackToMenu()
	 */
	@Override
	public void requestBackToMenu() {
		ecouteur.requestBackToMenu();
	}
	
	

	///////////////////////////////////////////////////////
	//INTERFACE D'ECOUTE///////////////////////////////////
	///////////////////////////////////////////////////////
	
	//On fait passer les ordres au Gestionnaire et on intercepte celui pour stopper le Timer
	
	@Override
	public void requestGameOver(Boolean win) {
		ecouteur.requestGameOver(win);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurLauncher#requestNewGame(fr.donjon.Donjons.GestionnaireSalle)
	 */
	@Override
	public void requestNewGame(GestionnaireSalle g) {
		ecouteur.requestNewGame(g);
	}

	/**
	 * Lance le jeu
	 */
	public void startGame(){
		this.enPause = false;
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
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		gestion.stopDeplacement();
	}
	
	/**
	 * Met le jeu en pause
	 */
	public void stopGame(){
		this.enPause=true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#toggleMap()
	 */
	@Override
	public void toggleMap() {
		
		if(showMap){
			showMap = false;
			this.startGame();
		}
		else {
			showMap = true;
			this.stopGame();
		}
		
	}
	
	//On capte les demandes du GestionnaireSalle et on les transmets au launcher
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {
		if(timer.isRunning()){
			this.stopGame();
		}else{
			this.startGame();
		}
	}

	/**
	 * Cette m�thode appel�e par le timer r�p�titivement appelle la m�thode update du gestionnaire.
	 */
	public void update(){
		
		//TODO remove this bit?
		
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
		
		if(dessinateur.salle != gestion.getsActuelle()){
			dessinateur.changerSalle(gestion.getsActuelle());
		}

	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		gestion.utiliseObjet(reference);
	}


}
