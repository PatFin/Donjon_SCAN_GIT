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
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.donjon.classes.Case;
import fr.donjon.classes.Castle_Room;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Linear_Castle;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.classes.Salle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;


/**
 * @author Baptiste
 *
 */

public class JeuLineaireBlac extends GamePanel implements EcouteurClavier {

	
	


	public JeuLineaireBlac(){
		
		super(null);
		

		//TODO at the END !
		this.addKeyListener(new JeuKeyAdapter(this));		
		
	}


	/**
	 * @param g Composant graphique sur lequel dessiner
	 */
	public void paint(Graphics g){

		super.paint(g);

	}

	public void update(){

		super.update();
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
		System.out.println("merde");
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

