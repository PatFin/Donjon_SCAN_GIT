/**
 * 
 */
package fr.donjon.zpoubelle;

import fr.donjon.cases.Case;
import fr.donjon.cases.CasePorte;
import fr.donjon.cases.CollisionPorte;
import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class Salle2PL extends SalleQuatre {


	Orientation o;

	public Salle2PL(Heros he, Case c,int w, int h, Orientation o){
		this(he,MapGenerator.fillWith(c, w, h),o);
	}
	
	public Salle2PL(Heros h, Case[][] cs, Orientation o){
		super(h,getCases(cs,o));
		this.o = o;
	}
	
	public Salle2PL(Heros hero,int w, int h, Orientation o){
		this(hero, MapGenerator.randomForet(w, h),o);
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

				((CasePorte) c).setCollision( new CollisionPorte(this, dir));

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

				((CasePorte) c).setCollision( new CollisionPorte(this, dir));

			}

			break;

		}

	}


	@Override
	public void passerLaPorte(Vecteur dir) {
		super.passerLaPorte(dir);
		Vecteur pos = new Vecteur(0,0);


		switch(Orientation.getOrientation(dir)){
		case EST:
			pos.setLocation(1, (height/2)+1);
			break;
		case NORD:
			pos.setLocation( (width/2)+1, height);
			break;
		case OUEST:
			pos.setLocation( width , (height/2)+1 );
			break;
		case SUD:
			pos.setLocation( (width/2)+1 , 1 );
			break;
		default:
			break;
		}
		hero.setLocation(pos);
	}
	
	public static Case[][] addDoors(Case[][] c,Orientation o){
		
		int w = c.length;
		int h = c[0].length;
		
		switch(o){
		case NORD:
		case SUD:
			c[w/2][0] = new CasePorte(Orientation.NORD);
			c[w/2][h-1] = new CasePorte(Orientation.SUD);
			break;

		case EST:
		case OUEST:
			c[0][w/2] = new CasePorte(Orientation.OUEST);
			c[w-1][h/2] = new CasePorte(Orientation.EST);
			break;

		}

		return c;
	}

	public static Case[][] getCases(Case[][] c, Orientation o){
		
		c = addWalls(c);
		c = addDoors(c, o);
		
		return c;
	}

}
