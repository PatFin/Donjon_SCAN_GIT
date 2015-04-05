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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.classes.Case;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */
public class JeuTest extends JFrame implements EcouteurClavier {
	
	final static int LARGEUR = 700;
	final static int LONGUEUR = 480;
	final static int timerTime = 20;
    BufferedImage arrierePlan;
    Graphics buffer;
    Rectangle ecran;
    JPanel monJpanel;

    long temps;
    Timer timer;
    Heros hero;
    

	public JeuTest(){
		
		
       initialisationFenetre();
       
       timer = new Timer(timerTime, new TimerAction());
       
       
       hero = new Heros(0,0);
       
       
       this.addKeyListener(new JeuKeyAdapter(this));
       
       timer.start();
       
       
	}
	
	private void initialisationFenetre(){
		
		setSize(LARGEUR,LONGUEUR);
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
		
        //On dessine sur le buffer
        buffer.setColor(Color.RED);
        buffer.fillRect(ecran.x,ecran.y,ecran.x+ecran.width,ecran.y+ecran.height);
		
        hero.draw(temps, buffer);
		
        //On affiche le buffer à l'écran
        g.drawImage(arrierePlan, 0, 0, this);
	}
	
 	public void update(){
 		
 		hero.update(temps);
 		
 		repaint();
 	}
 	
	public static void main(String[] args) {
        
        JeuTest Monjeu = new JeuTest();
        
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

