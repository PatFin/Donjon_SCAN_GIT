package fr.donjon.classes;

import java.awt.Rectangle;



public class Castle_Room extends Salle {

	
	public Castle_Room(Heros p, Rectangle ecran) {
		super(p, null, ecran);
		
		Case[][] casesSalle;
		casesSalle = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		for(int x=0;x<cases.length;x++){
			casesSalle[x][0]=new Case_rocher();
			casesSalle[x][1]=new Case_mur();
		}
		
		//Le reste du tableau est rempli aléatoirement de dalles (fendue ou non)
		for(int y=2;y<casesSalle[0].length;y++){
			for(int x=0;x<casesSalle.length;x++){
				int random = (int)3*Math.round(Math.random());
				
				if(random == 0){
					casesSalle[x][y]=new Case_rocher();	
				}else{
					casesSalle[x][y]=new Case_dalle_sol();
				}
			}
		}
		refreshRoomCases(casesSalle);
	}
	
	
	
}