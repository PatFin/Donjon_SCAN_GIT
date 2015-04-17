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
 * 
 * @author Baptiste
 *
 */
public class Launcher extends JFrame implements EcouteurClavier{

	JPanel panActuel; //LE JPanel a utiliser 

	GamePanel game;	//Le JPanel dessinant le jeu (GamePanel)
	JPanel menu;	//Le JPanel dessinant le menu (EcranAcceuil)

	/**
	 * Lanceur du jeu
	 */
	public Launcher(){

		game = new JeuLineaireBlac(); 	//Ajouter par la suite un gameLin et gameHistoire
		
		menu = new EcranAccueil(this);	//
		
		goToMenu();						//On affiche le menu
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Permet de démarrer le jeu (en linéaire pour le moment)
	 */
	public void startGame(){
		
		this.game.stopGame();		//On arrete le jeu en cours 
		
		try{
			this.remove(panActuel);	//On enleve le JPanel du menu s'il existe
		}
		catch (NullPointerException e){

		}
		this.panActuel = game;		//On change de JPanel d'affichage
		this.add(panActuel);		//Et on l'affiche
		
		panActuel.setFocusable(true);	//Permet la réception des evenements du clavier
		panActuel.requestFocusInWindow(); //Pareil
		
		panActuel.addKeyListener(new JeuKeyAdapter(this));	//On ajoute notre ecouteur de clavier
		
		this.game.startGame();								//On demarre le jeu
		
		this.pack();										//On met a jour la taille de la fenetre
	}

	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		//Voir fonctionnement de startGame()
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
	///INTERFACE D'ECOUTE//////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	
	//On fait passer les evenements au jeu
	
	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		game.attaque(o);
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub
		game.stopAttaque(o);
	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
		game.deplacement(o);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		game.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		game.togglePause();
	}

	@Override
	public void stopDeplacement(Orientation o) {
		// TODO Auto-generated method stub
		game.stopDeplacement(o);
	}
	
	



}
