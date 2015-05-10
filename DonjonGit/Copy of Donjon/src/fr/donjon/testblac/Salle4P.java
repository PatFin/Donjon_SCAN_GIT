/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CasePorte;
import fr.donjon.cases2.CollisionTeleportation;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;



/**
 * @author Baptiste
 *
 */
public class Salle4P extends SalleQuatre {


	
	public Salle4P(Heros h, Case c) {
		super(h,getCases(c));
		this.addEnemy(new Squelette(100,100,hero,1));
	}

	public static Case[][] getCases(Case c){

		Case[][] cs = new Case[W][H];

		for(int x = 0 ; x < W ; x++){
			for(int y = 0 ; y < H ; y++){
				cs[x][y] = c.clone();
				cs[x][y].setCollisionBoxLocation(y, x);
			}
		}
		
		
		cs = addWalls(cs);
		cs = addDoors(cs);
		

		return cs;

	}

	public static Case[][] addDoors(Case[][] c){
		
		c[0][(int)c[0].length/2] = new CasePorte(Orientation.OUEST);
		c[(int)c.length-1][(int)c[0].length/2] = new CasePorte(Orientation.EST);
		c[(int)c.length/2][0] = new CasePorte(Orientation.NORD);
		c[(int)c.length/2][(int)c[0].length-1] = new CasePorte(Orientation.SUD);
		
		return c;
		
	}
	
	
	@Override
	public void passerLaPorte(Vecteur dir) {
		super.passerLaPorte(dir);
		
		Vecteur pos = new Vecteur(0,0);


		switch(Orientation.getOrientation(dir)){
		case EST:
			pos.setLocation(1, (H/2)+1);
			break;
		case NORD:
			pos.setLocation( (W/2)+1, H);
			break;
		case OUEST:
			pos.setLocation( W , (H/2)+1 );
			break;
		case SUD:
			pos.setLocation( (W/2)+1 , 1 );
			break;
		default:
			break;
		}

		hero.setLocation(pos);
	}



	@Override
	public void activerLesPortes(boolean a) {

		Vecteur dir = Vecteur.vNull;

		for( Case c : portes){

			switch(portes.indexOf(c)){
			case 0 :
				dir = Vecteur.vNord;
				break;
			case 1 :
				dir = Vecteur.vOuest;
				break;
			case 2 :
				dir = Vecteur.vEst;
				break;
			case 3 : 
				dir = Vecteur.vSud;
				break;
			}

			((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));

		}

	}


}
