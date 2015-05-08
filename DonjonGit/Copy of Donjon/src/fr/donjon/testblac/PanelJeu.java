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

import fr.donjon.classes.cases.Case;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class PanelJeu extends JPanel implements EcouteurClavier{

	static int instances = 0;
	int instance;

	final static int timerTime = 30;

	public Rectangle ecran;
	Timer timer;

	GestionnaireBasique gestion;
	DessinateurSalle dessinateur;
	
	long temps;
	long ta = -1;
	
	int fps;

	/**
	 * 
	 */
	public PanelJeu() {

		this.setSize(new Dimension(15*Case.TAILLE, 10*Case.TAILLE));
		this.setPreferredSize(new Dimension(15*Case.TAILLE, 10*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		timer = new Timer(timerTime, new TimerAction());
		//Le timer sera d�marr� plus tard grace a la m�thode startGame()

		////
		
		gestion = new GestionnaireBasique();
		dessinateur = new DessinateurSalle(ecran, gestion.getsActuelle());
		
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
				System.out.println(""+fps);
			}
			ta = System.currentTimeMillis();
		}

		gestion.update(temps);

		repaint();
		
		if(dessinateur.salle != gestion.sActuelle){
			dessinateur.changerSalle(gestion.sActuelle);
		}

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.GRAY);
		
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		Vecteur d = new Vecteur( - gestion.centreCamera.x + getWidth()/2 , 
				 					- gestion.centreCamera.y + getHeight()/2);
		
		
		g.drawImage(dessinateur.getImage(g, temps),(int)d.x,(int)d.y,null);
		
		
	}

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
		if(timer.isRunning())timer.stop();
		else timer.start();
	}
	
	@Override
	public void stopDeplacement() {
		gestion.stopDeplacement();
	}


}
