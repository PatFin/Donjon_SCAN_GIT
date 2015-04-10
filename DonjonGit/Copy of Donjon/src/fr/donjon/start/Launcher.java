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
	
	JPanel jeuLineaire;
	JPanel menu;
	
	public Launcher(){
		
		jeuLineaire = new JeuLineaireBlac(); 
		panActuel = jeuLineaire;
		
	    add(panActuel);
		
		pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher launch = new Launcher();
	}

}
