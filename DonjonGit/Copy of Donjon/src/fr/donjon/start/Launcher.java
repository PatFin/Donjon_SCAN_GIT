/**
 * 
 */
package fr.donjon.start;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class Launcher extends JFrame implements KeyListener{

	JPanel panActuel;

	GamePanel game;	//LE JPanel dessinant le jeu (GamePanel)
	JPanel menu;	//Le JPanel dessinant le menu

	/**
	 * 
	 */
	public Launcher(){

		game = new JeuLineaireBlac();
		menu = new EcranAccueil(this);
		
		goToMenu();
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Affiche le jeu
	 */
	public void startGame(){
		this.game.stopGame();
		try{
			this.remove(panActuel);
		}
		catch (NullPointerException e){

		}
		this.panActuel = game;
		this.add(panActuel);
		panActuel.setFocusable(true);
		panActuel.requestFocusInWindow();
		panActuel.addKeyListener(this);
		this.pack();
	}

	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		this.game.stopGame();
		try{
			this.remove(panActuel);
		}
		catch (NullPointerException e){

		}
		this.panActuel = menu;
		this.add(panActuel);
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
	/*
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
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("kaka");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
