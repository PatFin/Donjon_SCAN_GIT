package fr.donjon.salles;

import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseSwitch;
import fr.donjon.classes.Heros;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;

public class EnigmeSwitch extends SalleQuatre {


	private static final int HEIGHT = 3; 	//hauteur de la salle
	private static final int WIDTH = 7; 	//largeur de la salle
	private static int NUMBERSWITCHES = 3;	//nombre d'interrupteur Ã  mettre dans la salle
	
	boolean[] code;							//contient la combinaison qui autorise le passage
	CaseSwitch[] interrupteur;				//contient les interrupteurs situï¿½s dans la salle
	
	/**
	 * Constructeur
	 * @param h le hï¿½ros controlï¿½ par le joueur
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
		//On génère un code
		generateCode();
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.salles.Salle#checkFinie()
	 */
	@Override
	public void checkFinie(){
		boolean b = (personnages.size() == 1 && codeCorrect());
		finie = b;
		activerLesPortes(b);
	}

	/**
	 * Vï¿½rifie si la combinaison d'interrupteur correspond au code.
	 * @return true si la combinaison est correcte, false sinon
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
	 * Randomly generates a code for the room
	 */
	private void generateCode(){
		this.code = new boolean[NUMBERSWITCHES];
		
		for(int i=0; i<code.length; i++){
			code[i] = (Math.random()<0.5);
		}
		
	}
	
}
