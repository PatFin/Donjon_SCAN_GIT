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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		this.setSize(new Dimension((Salle.W+2)*Case.TAILLE, (Salle.H+2)*Case.TAILLE));
		this.setPreferredSize(new Dimension((Salle.W+2)*Case.TAILLE, (Salle.H+2)*Case.TAILLE));

		//Partie utile de la fenetre
		ecran = new Rectangle(0,0,getWidth(),getHeight());

		timer = new Timer(timerTime, new TimerAction());
		//Le timer sera dï¿½marrï¿½ plus tard grace a la mï¿½thode startGame()

		////
		
		gestion = new GestionnaireBasique();
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

	@Override
	public void paint(Graphics g) {
		
		//TODO I changed the color from grey to black
		//On remplit la salle d'un fond noir
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//vecteur d place l'image de la salle de façon à ce qu'on voit le personnage au centre de la fenêtre.
		//On peut donc avoir des salles qui sont plus grandes que la fenêtre.
		
		//si salle plus petite que la fenêtre, on centre l'image.
		//Sinon la caméra suit le héros
		Vecteur z = gestion.sActuelle.getPixelSize();
		int Width = getWidth();
		int Height = getHeight();
		
		
		int x;
		if(z.x<Width){
			x=(int)(Width/2-z.x/2);
		}else{
			x=(int)(- gestion.centreCamera.x + Width/2);
		}
		
		int y;
		if(z.y<Height){
			y=(int)(Height/2-z.y/2);
		}else{
			y=(int)(- gestion.centreCamera.y + Height/2);
		}
		
		Vecteur d = new Vecteur(x,y);
		
		
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
