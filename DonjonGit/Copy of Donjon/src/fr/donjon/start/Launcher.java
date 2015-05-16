/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;

import fr.donjon.Donjons.DonjonLineaire;
import fr.donjon.Donjons.GestionnairePatrickBasique;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.MyJPanel;

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

	MyJPanel panActuel; //LE JPanel a utiliser 

	PanelJeu game;	//Le JPanel dessinant le jeu (GamePanel)
	
	MyJPanel menu;	//Le JPanel dessinant le menu (EcranAcceuil)

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
		game.requestFocusInWindow(); //Pareil
		
		game.addKeyListener(new JeuKeyAdapter(this));	//On ajoute notre ecouteur de clavier
		
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


	///////////////////////////////////////////////////////////////
	///INTERFACE D'ECOUTE//////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	
	//On fait passer les evenements au jeu
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Orientation o) {
		game.attaque(o);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopAttaque()
	 */
	@Override
	public void stopAttaque() {
		game.stopAttaque();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		game.deplacement(v);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		game.utiliseObjet(reference);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {
		game.togglePause();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		game.stopDeplacement();
	}
}
