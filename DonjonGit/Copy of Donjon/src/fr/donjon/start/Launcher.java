/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Baptiste
 *
 */
public class Launcher extends JFrame {

	JPanel panActuel;
	
	GamePanel game;
	JPanel menu;
	
	public Launcher(){
		
		game = new JeuLineaireBlac(); 
		panActuel = game;
	    add(panActuel);
		
		pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        game.startGame();
        
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher launch = new Launcher();
	}

}
