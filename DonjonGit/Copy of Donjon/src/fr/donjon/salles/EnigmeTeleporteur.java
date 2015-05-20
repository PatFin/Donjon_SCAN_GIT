/**
 * 
 */
package fr.donjon.salles;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CasePorte;
import fr.donjon.cases.CaseTeleportation;
import fr.donjon.cases.CaseVide;
import fr.donjon.cases.CollisionPorte;
import fr.donjon.cases.CollisionTeleportation;
import fr.donjon.classes.Heros;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Patrick
 *
 */
public class EnigmeTeleporteur extends SalleQuatre {

	private static final int HEIGHT = 11; 	//hauteur de la salle
	private static final int WIDTH = 11; 	//largeur de la salle
	

	private TeleSet[] blocs;
	
	
	/**
	 * Constructeur de cette salle
	 * @param h le héros contrôlé par le joueur
	 */
	public EnigmeTeleporteur (Heros h){
		super(h, generateRoomTiles());
		
		this.blocs = new TeleSet[4];
		int k=0;
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				blocs[k] = (new TeleSet(new Vecteur(3+i*4,3+j*4)));
				k++;
			}
		}
	
		//On créé un chemin de bloc en blocs
		for(int z=0; z<3; z++){
			int r = (int) (Math.random()*4);
			
			blocs[z].teleporteur[r].collision = new CollisionTeleportation(blocs[z].teleporteur[r], blocs[z+1].tele[1][1]);
		}
		
		
		//On change les cases de la salle
		for(TeleSet t: blocs){
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					this.cases[(int)t.topLeft.x+i][(int)t.topLeft.y+j] = t.tele[i][j];
				}
			}
		}
		
	}

	/**
	 * Generates room tiles
	 * @return an 2D array of Case.
	 */
	public static Case[][] generateRoomTiles(){
		Case[][] tiles = Salle.addWalls(MapGenerator.fillWith(new CaseVide(), HEIGHT, WIDTH));
		return tiles;
	}

	
	private class TeleSet {
		public Case[][] tele;
		public CaseTeleportation[] teleporteur;
		
		Vecteur topLeft;
		public TeleSet(Vecteur v){
			topLeft = v;
			
			tele = new Case[3][3];
			teleporteur = new CaseTeleportation[4]; 
			
			for(int i=0; i<tele.length;i++){
				for(int j=0; j<tele[0].length; j++){
					if((i+j)%2==1 && (i!=j)){
						tele[i][j] = new CaseDalle();
					}else{
						tele[i][j] = new CaseTeleportation();
					}
				}
			}
			teleporteur[0] = (CaseTeleportation) tele[0][0];
			teleporteur[1] = (CaseTeleportation) tele[0][2];
			teleporteur[2] = (CaseTeleportation) tele[2][0];
			teleporteur[3] = (CaseTeleportation) tele[2][2];
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.salles.SalleQuatre#addDoor(fr.donjon.utils.Orientation, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void addDoor(Orientation o, Salle[][] sMap){
		super.addDoor(o, sMap);
		int w = cases.length;
		int h = cases[0].length;
		
		CaseTeleportation c = new CaseTeleportation();
		c.collision = new CollisionTeleportation(c, blocs[0].tele[1][1]);
		switch(o){
		case NORD:
			cases[(int)w/2][2] = c;
			break;
		case SUD:
			cases[(int)w/2][h-3] = c;
			break;
		case EST:
			cases[w-3][(int)h/2] = c;
			break;
		case OUEST:
			cases[2][(int)h/2] = c;
			break;
		}
		
		
		//On met un téléporteur vers chaque palier de porte de la salle dans le dernier bloc.
		int i=0;
		for(CasePorte p :  this.portes){
			this.blocs[3].teleporteur[i].collision = new CollisionTeleportation(this.blocs[3].teleporteur[i], this.getCase(p.collisionPorte.lien.getPalier()));
			i++;
		}
		
		for(i=0; i<4; i++){
			for(int x=0; x<4; x++){
				if(this.blocs[i].teleporteur[x].collision == null){
					this.blocs[i].teleporteur[x].collision = new CollisionTeleportation(this.blocs[i].teleporteur[x], this.getCase(portes.get(0).collisionPorte.lien.getPalier()));
				}
			}
		}
		
	}
}
