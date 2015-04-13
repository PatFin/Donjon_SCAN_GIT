/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.utils.JeuKeyAdapter;

/**
 * 
 * Permet demarrer le menu principal du jeu avec le joi du jeu, etc...
 * @author Baptiste
 *
 */
public class Launcher extends JFrame {

	JPanel panActuel;
	
	GamePanel game;	//LE JPanel dessinant le jeu (GamePanel)
	JPanel menu;	//Le JPanel dessinant le menu
	
	/**
	 * 
	 */
	public Launcher(){
		
		game = new JeuLineaireBlac(); 
		startGame();
		
	    add(panActuel);
		
		pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
	}
	
	/**
	 * Affiche le jeu
	 */
	public void startGame(){
		this.panActuel = game;
		game.startGame();
		this.pack();
	}
	
	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		this.game.stopGame();
		this.panActuel = menu;
		this.pack();
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher launch = new Launcher();
	}

}
