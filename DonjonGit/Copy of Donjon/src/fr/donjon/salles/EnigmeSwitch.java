package fr.donjon.salles;

import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.classes.Heros;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;

/**
 * Salle d'énigme
 * Pour passer à la salle suivante, il faut trouver la bonne combinaison d'interrupteurs au sol.
 * On pourrait augmenter la difficulté en changeant le nombre d'interrupteurs. 
 * Note personnelle: 5 interrupteurs est déjà beaucoup, en mettre plus rendrait le jeu désagrable.
 * @author Patrick
 * 
 */
public class EnigmeSwitch extends SalleQuatre {


	private static final int HEIGHT = 3; 	//hauteur de la salle
	private static int NUMBERSWITCHES = 3;	//nombre d'interrupteur Ã  mettre dans la salle
	private static final int WIDTH = 7; 	//largeur de la salle
	
	boolean[] code;							//contient la combinaison qui autorise le passage
	CaseSwitch[] interrupteur;				//contient les interrupteurs situï¿½s dans la salle
	
	/**
	 * Constructeur
	 * @param h le héro contrôlé par le joueur
	 */
	public EnigmeSwitch(Heros h) {
		super(h, Salle.addWalls(MapGenerator.fillWith(new CaseDalle(), WIDTH, HEIGHT)));
		
		//On place NUMBERSWITCHES interrupteurs dans la salle prï¿½s du centre
		Vecteur c = this.getRoomCenter();
		interrupteur = new CaseSwitch[NUMBERSWITCHES];
		
		for(int i=0; i<NUMBERSWITCHES; i++){
			CaseSwitch s = new CaseSwitch();
			this.cases[(int)(c.x-NUMBERSWITCHES/2)+i][(int)c.y] = s;
			interrupteur[i] = s;
		}
		//On gï¿½nï¿½re un code
		generateCode();
		
	}

	/**
	 * Vérifie si la combinaison d'interrupteur correspond au code.
	 * Méthode utilisée dans chaeckFinie() de cette classe
	 * @return true si la combinaison est correcte, false sinon
	 * @see #checkFinie()
	 */
	private boolean codeCorrect() {
		
		for(int i=0; i<this.code.length; i++){
			if(code[i]!= interrupteur[i].isActivated()){
				return false;
			}
		}
		
		return true;
		
	}

	/**
	 * Génère aléatoirement un code pour la salle.
	 * Le code se compose d'un tableau de booléen de la taille du nombre d'interrupteurs.
	 * La probabilité qu'il faille que chaque interrupteurs soit "true" est de 50% 
	 */
	private void generateCode(){
		this.code = new boolean[NUMBERSWITCHES];
		
		for(int i=0; i<code.length; i++){
			code[i] = (Math.random()<0.5);
		}
		
	}
	
	/**
	 * Vérifie si le héro a le droit de changer de salle. 
	 * On vérifie que le héros est bien seul dans la salle (comme dans la méthode mère).
	 * On vérifie si la combianison d'interrupteurs est bien correcte. 
	 * Si les deux conditions sont true, on autorise le passage des portes, sinon, on l'empêche.
	 */
	@Override
	public void checkFinie(){
		boolean b = (personnages.size() == 1 && codeCorrect());
		finie = b;
		activerLesPortes(b);
	}
	
}
