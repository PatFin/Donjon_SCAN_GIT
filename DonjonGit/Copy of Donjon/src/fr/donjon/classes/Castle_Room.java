package fr.donjon.classes;

import java.awt.Rectangle;

import fr.donjon.utils.Orientation;


public class Castle_Room extends Salle {
	
	Linear_Castle castle;
	
	/**
	 * @param p Le hero controlé par le joueur.
	 * @param ecran L'écran de jeu.
	 * @param lien le lien de lasalle précédente vers celle-ci.
	 */
	public Castle_Room(Heros p, Rectangle ecran, Link lien) {
		super(ecran, p);
		
		
		//On génèrel'apparence de la Salle.
		Case[][] casesSalle;
		casesSalle = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		for(int x=0; x<casesSalle.length;x++){
			for(int y=0; y<casesSalle[0].length; y++){
				casesSalle[x][y]  = new Case_void();
			}
		}
		
		for(int x=1;x<cases.length-1;x++){
			casesSalle[x][1]=new Case_mur();
		}
		
		//Le reste du tableau est rempli aléatoirement de dalles (fendue / rocher / normale)
		for(int y=2;y<casesSalle[0].length-1;y++){
			for(int x=1;x<casesSalle.length-1;x++){
				int random = (int)(Math.round(6*Math.random()));
				
				if(random == 0){
					casesSalle[x][y]=new Case_rocher();	
				}else if(random == 1){
					casesSalle[x][y]=new Case_fendue_sol();
				}else {
					casesSalle[x][y]=new Case_dalle_sol();
				}
			}
		}
		
		/**
		 * On ajoute des escaliers vers une autre salle. On s'assure qu'il est possible d'y rentrer en plaçant une dalle normale 
		 */
		
		
	}
	/**
	 * Methode utilisée juste après l'initialisation de la salle.
	 * Elle crée les lien vers la salle précédente s'il y a lieu
	 * elle en créé un vers une prochaine salle pas encore définie.
	 * @param lien
	 */
	public void initializeLlinks(Link lien){
		//Si on a une salle précédente
				if(lien != null){
					switch(lien.orientation){
					case NORD:
					cases[cases.length/2][cases[0].length-1] = new Case_escalier();
					cases[cases.length/2][cases[0].length-2] = new Case_dalle_sol();
					this.liens.add(new Link(lien.destination, lien.x, lien.y+1, this, cases.length/2, cases[0].length-1, Orientation.SUD, true));
						break;
					default:	
					}
				}
				//L'escalier vers une autre future salle
				cases[cases.length/2][0] = new Case_escalier();
				cases[cases.length/2][1] = new Case_dalle_sol();
				this.liens.add(new Link(lien.destination, cases.length/2, 0,Orientation.NORD, true));
				
				refreshRoomCases(cases);
	}
	
	
}