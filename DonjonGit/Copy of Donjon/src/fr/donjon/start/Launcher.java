/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 * Permet demarrer le menu principal du jeu avec le choix du jeu, etc...
 * 
 * @author Baptiste
 *
 */
public class Launcher extends JFrame implements EcouteurClavier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	JPanel actuel;
	
	PanelJeu game;	//Le JPanel dessinant le jeu (GamePanel)
	EcranAccueil menu;	//Le JPanel dessinant le menu (EcranAcceuil)

	/**
	 * Constructeur
	 */
	public Launcher() {

		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new JeuKeyAdapter(this));	//On ajoute notre ecouteur de clavier personnalisé à notre PanelJeu
		
		
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
			this.remove(actuel);			//On enleve le JPanel en cours d'utilisation
			this.game.stopGame();			//On arrete le jeu possiblement en cours
		}
		catch (NullPointerException e){

		}
		
		//On créé le JPanel de jeu
		switch(mode){
		case 0:
			//On met un jeu linéaire
			game = new PanelJeu(new DonjonLineaire(10)); 
			break;
		default:
			game = new PanelJeu(new GestionnairePatrickBasique(4, 3));
		}
		
		actuel=game;
		
		this.add(actuel);									//Et on l'affiche
		game.startGame();								//On demarre le jeu
		this.pack();									//On adapte la taille de la fenetre
	}

	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		
		try{
			this.remove(actuel);
			this.game.stopGame();
		}
		catch (NullPointerException e){

		}
		actuel = menu;
		this.add(actuel);
		this.pack();
	}

	/**
	 * Main method which launches the program
	 * @param args nothing required 
	 */
	public static void main(String[] args)  {
		new Launcher();
	}

	@Override
	public void attaque(Orientation o) {
		game.attaque(o);
	}

	@Override
	public void stopAttaque() {
		game.stopAttaque();
		
	}

	@Override
	public void deplacement(Vecteur v) {
		game.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		game.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		game.togglePause();
	}

	@Override
	public void stopDeplacement() {
		game.stopDeplacement();
	}

}
