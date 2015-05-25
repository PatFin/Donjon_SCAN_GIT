/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.Donjons.GestionnaireSalle;
import fr.donjon.salles.Salle;
import fr.donjon.sound.SoundLoop;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.GameOverListener;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Vecteur;

/**
 * 
 * Permet demarrer le menu principal du jeu avec le choix du jeu, etc...
 * 
 * @author Baptiste
 *
 */
public class Launcher extends JFrame implements EcouteurClavier, EcouteurLauncher, GameOverListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	JPanel actuel;
	
	PanelJeu game;	//Le JPanel dessinant le jeu (GamePanel)
	EcranAccueil menu;	//Le JPanel dessinant le menu (EcranAcceuil)
	SoundLoop music;
	
	/**
	 * Constructeur
	 */
	public Launcher() {

		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new JeuKeyAdapter(this));	//On ajoute notre ecouteur de clavier personnalis� � notre PanelJeu
		
		menu = new EcranAccueil();
		menu.ecouteur = this;
		
		music = new SoundLoop(SoundLoop.GOT);
		
		goToMenu();						//On affiche le menu
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Permet de d�marrer le jeu
	 * @param mode le mode de jeu: 0 Pour jeu lineaire, default jeu non lin�aire
	 */
	public void startGame(GestionnaireSalle g){
		
		
		try{
			this.remove(actuel);			//On enleve le JPanel en cours d'utilisation
			this.game.stopGame();			//On arrete le jeu possiblement en cours
		}
		catch (NullPointerException e){

		}
		game = new PanelJeu(g);				//On cr�� le nouveau jeu
		game.ecouteur = this;
		
		actuel=game;
		
		this.add(actuel);									//Et on l'affiche
		game.startGame();								//On demarre le jeu
		this.pack();									//On adapte la taille de la fenetre
		music.loop();
		this.setResizable(true);
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
		this.setResizable(false);
		music.stop();
		repaint();
	}
	
	public void goToGameOver(Boolean win) {
		try{
			this.remove(actuel);
			this.game.stopGame();
		}
		catch (NullPointerException e){

		}
		GameOver g = new GameOver(win);
		g.listener = this;
		actuel =  g;
		this.add(actuel);
		this.pack();
		this.setResizable(false);
		music.stop();
		repaint();
	}

	/**
	 * Main method which launches the program
	 * @param args nothing required 
	 */
	public static void main(String[] args)  {
		new Launcher();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Vecteur v) {
		if(game!=null){
			game.attaque(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopAttaque()
	 */
	@Override
	public void stopAttaque() {
		if(game!=null){
			game.stopAttaque();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		if(game!=null){
			game.deplacement(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		if(game!=null){
			game.utiliseObjet(reference);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {
		if(game!=null){
			game.togglePause();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		if(game!=null){
			game.stopDeplacement();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#toggleMap()
	 */
	@Override
	public void toggleMap() {
		if(game!=null){
			game.toggleMap();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurLauncher#requestBackToMenu()
	 */
	@Override
	public void requestBackToMenu() {
		goToMenu();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurLauncher#requestNewGame(fr.donjon.Donjons.GestionnaireSalle)
	 */
	@Override
	public void requestNewGame(GestionnaireSalle g) {
		startGame(g);
	}

	@Override
	public void requestGameOver(Boolean win) {
		goToGameOver(win);
	}

	@Override
	public void quit() {
		System.exit(0);
	}

	@Override
	public void retourMenu() {
		goToMenu();
	}

}
