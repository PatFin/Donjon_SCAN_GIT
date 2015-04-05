/**
 * 
 */
package fr.donjon.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Baptiste
 *
 */
public class JeuKeyAdapter extends KeyAdapter {

	private EcouteurClavier ecouteur;	


	/**
	 * @param ecouteur La classe implementant l'ecouteur
	 */
	public JeuKeyAdapter(EcouteurClavier ecouteur) {
		// TODO Auto-generated constructor stub
		this.ecouteur = ecouteur;
	}


	//METHODES HERITEES DE KeyAdapter
	//Elles doivent appeler les methodes de l'ecouteur
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {

		case KeyEvent.VK_Q   : 
			ecouteur.deplacement(Orientation.OUEST);
			break;

		case KeyEvent.VK_D  : 
			ecouteur.deplacement(Orientation.EST);
			break;    

		case KeyEvent.VK_Z   : 
			ecouteur.deplacement(Orientation.NORD);
			break;

		case KeyEvent.VK_S  : 
			ecouteur.deplacement(Orientation.SUD);
			break; 

		case KeyEvent.VK_LEFT   : 
			ecouteur.attaque(Orientation.OUEST);
			break;

		case KeyEvent.VK_RIGHT  : 
			ecouteur.attaque(Orientation.EST);
			break;    

		case KeyEvent.VK_UP   : 
			ecouteur.attaque(Orientation.NORD);
			break;

		case KeyEvent.VK_DOWN  : 
			ecouteur.attaque(Orientation.SUD);
			break; 

		case KeyEvent.VK_ESCAPE : 
			System.exit(0);
			break;

		case KeyEvent.VK_ENTER   : 
			ecouteur.togglePause();
			break;       
		}


	}

	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		switch (code) {

		case KeyEvent.VK_Q   : 
			ecouteur.stopDeplacement(Orientation.OUEST);
			break;
                                        
		case KeyEvent.VK_D  : 
			ecouteur.stopDeplacement(Orientation.EST);
			break;    

		case KeyEvent.VK_Z   : 
			ecouteur.stopDeplacement(Orientation.NORD);
			break;
                                     
		case KeyEvent.VK_S  : 
			ecouteur.stopDeplacement(Orientation.SUD);
			break; 
			
		case KeyEvent.VK_LEFT   : 
			ecouteur.stopAttaque(Orientation.OUEST);
			break;
                                       
		case KeyEvent.VK_RIGHT  : 
			ecouteur.stopAttaque(Orientation.EST);
			break;    

		case KeyEvent.VK_UP   : 
			ecouteur.stopAttaque(Orientation.NORD);
			break;
                                      
		case KeyEvent.VK_DOWN  : 
			ecouteur.stopAttaque(Orientation.SUD);
			break; 
                                   
		case KeyEvent.VK_ESCAPE : 
			System.exit(0);
			break;

		case KeyEvent.VK_ENTER   : 
			//Nothing to do here !
			break;       
		}
	
	}

}
