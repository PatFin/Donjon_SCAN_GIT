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
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.Case;


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
	public PanelJeu(GestionnaireSalle gestion) {

		
		this.setPreferredSize(new Dimension( 15*Case.TAILLE, 10*Case.TAILLE));
		
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		timer = new Timer(timerTime, new TimerAction());
		
		this.gestion = gestion;
		
		dessinateur = new DessinateurSalle(ecran, gestion.getsActuelle());
		
		startGame();
		
		instance = instances;
		instances++;

	}

	/**
	 * Cette méthode appelée par le timer répétitivement appelle la méthode update du gestionnaire.
	 */
	public void update(){

		//Shows the fps of the actual game
		
		//TODO remove this bit?
		
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
		
		if(dessinateur.salle != gestion.getsActuelle()){
			dessinateur.changerSalle(gestion.getsActuelle());
		}

	}


	/**
	 * Dessine la salle du donjon actuelle.
	 * On place l'image de la salle de faï¿½on ï¿½ ce qu'on voit le personnage au centre de la fenï¿½tre.
	 * On peut donc avoir des salles qui sont plus grandes que la fenï¿½tre.
	 * Toutefois, si salle plus petite que la fenï¿½tre, on centre l'image.
	 */
	@Override
	public void paint(Graphics g) {
		
		//On remplit la salle d'un fond noir (pour les tests mettre du gris)
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//On obtient les dimensions de la salle et de la fenï¿½tre
		Vecteur z = gestion.getsActuelle().getPixelSize();
		
		int Width = getWidth();
		int Height = getHeight();
		
		
		int x; //coordonnï¿½e horizontale de la camï¿½ra
		if(z.x<Width){
			//on centre la salle dans la fenï¿½tre
			x=(int)(Width/2-z.x/2); 
		}else{
			//on place la camï¿½ra sur le hï¿½ros
			x=(int)(- gestion.getCentreCamera().x + Width/2);
		}
		
		//Cf au dessus
		int y; //coordonnï¿½e verticale de la camï¿½ra
		if(z.y<Height){
			y=(int)(Height/2-z.y/2);
		}else{
			y=(int)(- gestion.getCentreCamera().y + Height/2);
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
	 * Met le jeu en pause
	 */
	public void stopGame(){
		this.timer.stop();
	}
	
	/**
	 * Classe interne du timer
	 * On anime la salle et on incrï¿½mente la variable temps
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
	
	
	@Override
	public void attaque(Orientation o) {
		gestion.attaque(o);
	}

	@Override
	public void stopAttaque() {
		gestion.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		gestion.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		gestion.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		if(timer.isRunning()){
			this.stopGame();
		}else{
			this.startGame();
		}
	}
	
	@Override
	public void stopDeplacement() {
		gestion.stopDeplacement();
	}


}
