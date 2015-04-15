/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;

/**
 * 
 * Permet demarrer le menu principal du jeu avec le joi du jeu, etc...
 * @author Baptiste
 *
 */
public class Launcher extends JFrame implements EcouteurClavier{

	JPanel panActuel;
	
	JeuLineaireBlac game;	//LE JPanel dessinant le jeu (GamePanel)
	JPanel menu;	//Le JPanel dessinant le menu
	
	/**
	 * 
	 */
	public Launcher(){
		
		game = new JeuLineaireBlac(); 
		startGame();
		
	    add(panActuel);
		
	    this.addKeyListener(new JeuKeyAdapter(this));
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

	
	///////////////////////////////////////////////////////////////
	
	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		game.gestionnaire.listeSalles.getCurrent().getSalle().attaque(o);
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub
		game.gestionnaire.listeSalles.getCurrent().getSalle().stopAttaque(o);
	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
		
		game.gestionnaire.listeSalles.getCurrent().getSalle().deplacement(o);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDeplacement(Orientation o) {
		// TODO Auto-generated method stub
		game.gestionnaire.listeSalles.getCurrent().getSalle().stopDeplacement(o);
	}



}
