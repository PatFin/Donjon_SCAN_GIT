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
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.classes.Castle_Room;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.classes.Squelette;
import fr.donjon.classes.cases.Case;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireAlex extends JFrame implements EcouteurClavier {
	
	static final int LARGEUR = 15;
	static final int HAUTEUR = 9;
	final static int timerTime = 20;
	
    public BufferedImage arrierePlan;
    public Graphics buffer;
    public Rectangle ecran;
    public JPanel monJpanel;

    public long temps;
    
    public Timer timer;
    public Heros hero;
    public Linear_Castle castle;
    

	public JeuLineaireAlex(){
		
       initialisationFenetre();
       
       //General declarations
       timer = new Timer(timerTime, new TimerAction());
       hero = new Heros(447,430);
       castle = new Linear_Castle(hero, ecran);
       
       //Ajout d'un squelette
       
       castle.currentRoom.ajouterEnnemi(new Squelette(200, 300, hero));
       
       //TODO at the END !
       this.addKeyListener(new JeuKeyAdapter(this));
       timer.start();
       
	}
	
	private void initialisationFenetre(){
		
		monJpanel=new JPanel();
		monJpanel.setPreferredSize(new Dimension(LARGEUR*Case.TAILLE,HAUTEUR*Case.TAILLE));
	    add(monJpanel);
	    pack();

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        //Partie utile de la fenetre
        ecran = new Rectangle(getInsets().left,
        		getInsets().top,
        		getSize().width-getInsets().right-getInsets().left,
        		getSize().height-getInsets().bottom-getInsets().top);
       
       //Image buffer pour dessiner dessus
       arrierePlan = new BufferedImage(getSize().width,getSize().height, BufferedImage.TYPE_INT_RGB);
       
       //Récuperation de la partie graphique de de l'image pour dessiner dessus
       buffer = arrierePlan.getGraphics();
	}

	/**
	 * @param g Composant graphique sur lequel dessiner
	 */
 	public void paint(Graphics g){
		
        
        castle.draw(temps, g);
        setTitle(String.valueOf(hero.image.x) + ", " + String.valueOf(hero.image.y) + ", " + castle.currentRoom.personnage.size());
	}
	
 	public void update(){
 		
 		//UPDATE
 		castle.update(temps);
 		repaint();
 	}
 	
	public static void main(String[] args) {
        
        JeuLineaireAlex Monjeu = new JeuLineaireAlex();
        
    }

	private class TimerAction implements ActionListener{
		// actions listener appelés toutes les 20ms
		public void actionPerformed(ActionEvent e){
			update();
			temps +=timerTime;
		}
		
	}
	
	
	//INTERFACE D'ECOUTE

	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		hero.attaquer(new LinkedList<Personnage>(), new LinkedList<Projectile>(),o);
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
		hero.marcher(o);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		
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
		hero.stop(o);
	}
	
	  
    
}

