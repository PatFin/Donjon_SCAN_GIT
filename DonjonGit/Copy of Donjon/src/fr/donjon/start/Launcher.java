/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.utils.JeuKeyAdapter;

/**
 * 
 * Permet demarrer le menu principal du jeu avec le choix du jeu, etc...
 * 
 * @author Baptiste
 *
 */
public class Launcher extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	PanelJeu game;	//Le JPanel dessinant le jeu (GamePanel)
	
	EcranAccueil menu;	//Le JPanel dessinant le menu (EcranAcceuil)

	/**
	 * Constructeur
	 */
	public Launcher() {

		menu = new EcranAccueil(this);
		
		goToMenu();						//On affiche le menu
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Permet de dï¿½marrer le jeu
	 * @param mode le mode de jeu: 0 Pour jeu lineaire, default jeu non linï¿½aire
	 */
	public void startGame(int mode){
		
		
		try{
			this.game.stopGame();		//On arrete le jeu possiblement en cours
			this.remove(game);	//On enleve le JPanel en cours d'utilisation
		}
		catch (NullPointerException e){

		}
		
		//On change de JPanel d'affichage
		switch(mode){
		case 0:
			//On met un jeu linéaire
			game = new PanelJeu(new DonjonLineaire(10)); 
			break;
		default:
			game = new PanelJeu(new GestionnairePatrickBasique(4, 3));
		}
		
		this.add(game);		//Et on l'affiche
		
		game.setFocusable(true);	//Permet la rÃ©ception des evenements du clavier
		game.requestFocusInWindow(); //Idem
		game.addKeyListener(new JeuKeyAdapter(game));	//On ajoute notre ecouteur de clavier personnalisé à notre PanelJeu
		
		this.game.startGame();								//On demarre le jeu
		
		this.pack();										//On met a jour la taille de la fenetre
	}

	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		
		try{
			this.game.stopGame();
			this.remove(game);
		}
		catch (NullPointerException e){

		}
		this.add(menu);
		this.pack();
	}

	/**
	 * Main method which launches the program
	 * @param args nothing required 
	 */
	public static void main(String[] args)  {
		new Launcher();
	}

}
