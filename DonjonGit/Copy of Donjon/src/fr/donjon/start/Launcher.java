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

	GamePanel game;	//LE JPanel dessinant le jeu (GamePanel)
	JPanel menu;	//Le JPanel dessinant le menu

	/**
	 * 
	 */
	public Launcher(){

		game = new JeuLineaireBlac();
		menu = new EcranAccueil(this);

		goToMenu();

		pack();
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
