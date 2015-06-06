package fr.donjon.salles;

import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.classes.Heros;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;

/**
 * Salle d'�nigme
 * Pour passer � la salle suivante, il faut trouver la bonne combinaison d'interrupteurs au sol.
 * On pourrait augmenter la difficult� en changeant le nombre d'interrupteurs. 
 * Note personnelle: 5 interrupteurs est d�j� beaucoup, en mettre plus rendrait le jeu d�sagrable.
 * @author Patrick
 * 
 */
public class EnigmeSwitch extends SalleQuatre {


	private static final int HEIGHT = 3; 	//hauteur de la salle
	private static int NUMBERSWITCHES = 3;	//nombre d'interrupteur à mettre dans la salle
	private static final int WIDTH = 7; 	//largeur de la salle
	
	boolean[] code;							//contient la combinaison qui autorise le passage
	CaseSwitch[] interrupteur;				//contient les interrupteurs situ�s dans la salle
	
	/**
	 * Constructeur
	 * @param h le h�ro contr�l� par le joueur
	 */
	public EnigmeSwitch(Heros h) {
		super(h, Salle.addWalls(MapGenerator.fillWith(new CaseDalle(), WIDTH, HEIGHT)));
		
		//On place NUMBERSWITCHES interrupteurs dans la salle pr�s du centre
		Vecteur c = this.getRoomCenter();
		interrupteur = new CaseSwitch[NUMBERSWITCHES];
		
		for(int i=0; i<NUMBERSWITCHES; i++){
			CaseSwitch s = new CaseSwitch();
			this.cases[(int)(c.x-NUMBERSWITCHES/2)+i][(int)c.y] = s;
			interrupteur[i] = s;
		}
		//On g�n�re un code
		generateCode();
		
	}

	/**
	 * V�rifie si la combinaison d'interrupteur correspond au code.
	 * M�thode utilis�e dans chaeckFinie() de cette classe
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
	 * G�n�re al�atoirement un code pour la salle.
	 * Le code se compose d'un tableau de bool�en de la taille du nombre d'interrupteurs.
	 * La probabilit� qu'il faille que chaque interrupteurs soit "true" est de 50% 
	 */
	private void generateCode(){
		this.code = new boolean[NUMBERSWITCHES];
		
		for(int i=0; i<code.length; i++){
			code[i] = (Math.random()<0.5);
		}
		
	}
	
	/**
	 * V�rifie si le h�ro a le droit de changer de salle. 
	 * On v�rifie que le h�ros est bien seul dans la salle (comme dans la m�thode m�re).
	 * On v�rifie si la combianison d'interrupteurs est bien correcte. 
	 * Si les deux conditions sont true, on autorise le passage des portes, sinon, on l'emp�che.
	 */
	@Override
	public void checkFinie(){
		boolean b = (personnages.size() == 1 && codeCorrect());
		finie = b;
		activerLesPortes(b);
	}
	
}
