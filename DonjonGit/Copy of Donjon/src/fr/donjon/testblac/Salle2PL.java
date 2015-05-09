/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CasePorte;
import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class Salle2PL extends Salle {


	Orientation o;

	public Salle2PL(Heros h, Case c, Orientation o){
		super(h,getCases(c,o));
		this.o = o;
	}
	
	public Salle2PL(Heros h, Case[][] cs, Orientation o){
		super(h,getCases(cs,o));
		this.o = o;
	}
	
	
	
	public static Case[][] addDoors(Case[][] c,Orientation o){
		
		int w = c.length;
		int h = c[0].length;
		
		switch(o){
		case NORD:
		case SUD:
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;

		case EST:
		case OUEST:
			c[0][(int)w/2] = new CasePorte(Orientation.OUEST);
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			break;

		}

		return c;
	}

	public static Case[][] getCases(Case c, Orientation o){

		Case[][] cs = new Case[W][H];

		for(int x = 0 ; x < W ; x++){
			for(int y = 0 ; y < H ; y++){
				cs[x][y] = c.clone();
				cs[x][y].setCollisionBoxLocation(y, x);
			}
		}

		cs = addWalls(cs);
		cs = addDoors(cs, o);

		return cs;
	}

	public static Case[][] getCases(Case[][] c, Orientation o){
		
		addWalls(c);
		addDoors(c, o);
		
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


		switch(o){
		case NORD:
		case SUD:

			for( Case c : portes){

				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vSud;
					break;
				}

				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));

			}

			break;

		case EST:
		case OUEST:

			for( Case c : portes){

				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vOuest;
					break;
				case 1 :
					dir = Vecteur.vEst;
					break;
				}

				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));

			}

			break;

		}

	}

}
