/**
 * 
 */
package fr.donjon.start;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

	MyJPanel panActuel; //LE JPanel a utiliser 

	GamePanel gameLin;	//Le JPanel dessinant le jeu (GamePanel)
	GamePanel gameInf;  //Un autre JPanel qui va contenir le jeu non linéaire
	
	MyJPanel menu;	//Le JPanel dessinant le menu (EcranAcceuil)

	/**
	 * Lanceur du jeu
	 */
	public Launcher(){

		gameLin = new JeuLineaire(); 	//Ajouter par la suite un gameLin et gameHistoire
		gameInf = new JeuNonLineaire();
		
		menu = new EcranAccueil(this);	//
		
		goToMenu();						//On affiche le menu
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Permet de démarrer le jeu
	 * @param mode le mode de jeu: 0 Pour jeu lineaire, default jeu non linéaire
	 */
	public void startGame(int mode){
		
		this.gameLin.stopGame();		//On arrete les jeux possiblement en cours
		this.gameInf.startGame();
		
		try{
			this.remove(panActuel);	//On enleve le JPanel du menu s'il existe
		}
		catch (NullPointerException e){

		}
		
		//On change de JPanel d'affichage
		switch(mode){
		case 0:
			//On met un jeu linéaire
			this.panActuel = gameLin;
			break;
		default:
			this.panActuel = gameInf;
		}
		
		
		this.add(panActuel);		//Et on l'affiche
		
		panActuel.setFocusable(true);	//Permet la réception des evenements du clavier
		panActuel.requestFocusInWindow(); //Pareil
		
		panActuel.addKeyListener(new JeuKeyAdapter(this));	//On ajoute notre ecouteur de clavier
		
		this.panActuel.startGame();								//On demarre le jeu
		
		this.pack();										//On met a jour la taille de la fenetre
	}

	/**
	 * Affiche le menu
	 */
	public void goToMenu(){
		//Voir fonctionnement de startGame()
		
		
		try{
			this.panActuel.stopGame();
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
		panActuel.attaque(o);
	}

	@Override
	public void stopAttaque() {
		// TODO Auto-generated method stub
		panActuel.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		panActuel.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		panActuel.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		panActuel.togglePause();
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		panActuel.stopDeplacement();
	}
	
	



}
