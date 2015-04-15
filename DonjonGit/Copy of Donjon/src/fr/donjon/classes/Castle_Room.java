package fr.donjon.classes;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;


public class Castle_Room extends Salle {
	
	
	
	
	
	/**
	 * @param p Le hero controlé par le joueur.
	 * @param ecran L'écran de jeu.
	 * @param lien le lien de lasalle précédente vers celle-ci.
	 */
	public Castle_Room(Heros p, Rectangle ecran, Link lien) {
		super(ecran, p, null);
		
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
		this.portes = new EnumMap<Orientation, Vecteur>(Orientation.class);
		portes.put(Orientation.NORD,new Vecteur(casesSalle.length/2, 0));
		portes.put(Orientation.SUD,new Vecteur(casesSalle.length/2, cases[0].length-1));
		
		
		
		//Si on a une salle précédente
		if(lien != null){
			switch(lien.orientation){
			case NORD:										//If the character hit the north door of the previous room, 
															//we need to create a link from the south of this room to 
															//the north of the previous room
			Vecteur z= portes.get(Orientation.SUD);
			casesSalle[z.x][z.y] = new Case_escalier();
			casesSalle[z.x][z.y-1] = new Case_dalle_sol();
			this.liens.add(new Link(lien.origine, lien.x, lien.y+1, this, z.x, z.y, Orientation.SUD, true));
				break;
				
			}
		}
		//L'escalier vers une autre future salle
		Vecteur z = portes.get(Orientation.NORD);
		casesSalle[z.x][z.y] = new Case_escalier();
		casesSalle[z.x][z.y+1] = new Case_dalle_sol();
		this.liens.add(new Link(this, casesSalle.length/2, 0,Orientation.NORD, true));
		
		refreshRoomCases(casesSalle);
	}
	
}