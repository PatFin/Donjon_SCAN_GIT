/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.donjon.Donjons.GestionnaireSalle;
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
	 * On initialise les keyListeners, la musique.
	 * On affiche le menu et on rend la fenetre visible.
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
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		if(game!=null){
			game.deplacement(v);
		}
	}
	
	/**
	 * Affiche un ecran de GameOver
	 * @param win true si le joueur a termine le donjon avec succes, false sinon. Ce parametre influe sur le message et l'image qui s'affiche
	 */
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
	 * Affiche le menu
	 * On retire le JPanel actuel de la fenetre (s'il existe)
	 * On le remplace par le panel de menu qui a ete cree dans le constructeur de launcher
	 * On radapte la taille de la fenetre a son contenu et on en empeche son redimensionement.
	 * On arrete la musique qui pouvait etre en cours de lecture a cause d'un precedent jeu.
	 * On appelle repaint() pour bien repeindre la fenetre apr�s le changement de panel. 
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

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.GameOverListener#quit()
	 */
	@Override
	public void quit() {
		System.exit(0);
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
	 * @see fr.donjon.utils.EcouteurLauncher#requestGameOver(java.lang.Boolean)
	 */
	@Override
	public void requestGameOver(Boolean win) {
		goToGameOver(win);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurLauncher#requestNewGame(fr.donjon.Donjons.GestionnaireSalle)
	 */
	@Override
	public void requestNewGame(GestionnaireSalle g) {
		startGame(g);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.GameOverListener#retourMenu()
	 */
	@Override
	public void retourMenu() {
		goToMenu();
	}

	/**
	 * Permet de lancer un nouveau jeu.
	 * On retire le panel actuel de la fenetre launcher.
	 * On cr�� une nouvelle instance de PanelJeu dont le gestionnaire est celui donn� en parametre.
	 * On affiche ce nouveau panel qui devient le panel actuel.
	 * On lance la musique de jeu et on autorise le changement de taille de la fenetre.
	 * @param g le gestionnaire de jeu qui contient les m�canismes du jeu.
	 */
	public void startGame(GestionnaireSalle g){
		
		
		try{
			this.remove(actuel);			//On enleve le JPanel en cours d'utilisation
			this.game.stopGame();			//On arrete le jeu possiblement en cours
		}
		catch (NullPointerException e){

		}
		game = new PanelJeu(g);				//On cree le nouveau jeu
		game.ecouteur = this;
		
		actuel=game;
		
		this.add(actuel);								//Et on l'affiche
		game.startGame();								//On demarre le jeu
		this.pack();									//On adapte la taille de la fenetre
		music.loop();									//On lance la musique en boucle
		this.setResizable(true);						//On autorise le changement de taille de la fenetre
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
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		if(game!=null){
			game.utiliseObjet(reference);
		}
	}

	/**
	 * Main method which launches the program
	 * It creates a new window containing a "EcranAccueil"
	 * @param args nothing required 
	 */
	public static void main(String[] args)  {
		new Launcher();
	}

}
