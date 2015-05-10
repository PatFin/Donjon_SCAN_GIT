/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CasePorte;
import fr.donjon.cases2.CollisionTeleportation;
import fr.donjon.classes.Heros;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class Salle3P extends SalleQuatre {


	Orientation o;

	public Salle3P(Heros he, Case c,int w, int h, Orientation o){
		this(he,MapGenerator.fillWith(c, w, h),o);
	}
	
	public Salle3P(Heros h, Case[][] cs, Orientation o){
		super(h,getCases(cs,o));
		this.o = o;
	}
	
	public Salle3P(Heros hero,int w, int h, Orientation o){
		this(hero, MapGenerator.randomForet(w, h),o);
	}
	



	public static Case[][] addDoors(Case[][] c,Orientation o){

		int w = c.length;
		int h = c[0].length;

		
		switch(o){
		case NORD:
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			c[0][(int)h/2] = new CasePorte(Orientation.OUEST);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;
		case SUD:
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			c[0][(int)h/2] = new CasePorte(Orientation.OUEST);
			break;
		case EST:
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			c[0][(int)h/2] = new CasePorte(Orientation.OUEST);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;
		case OUEST:
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;

		}

		return c;
	}

	public static Case[][] getCases(Case[][] c, Orientation o){

		c = addWalls(c);
		c = addDoors(c, o);
		
		return c;
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

	@Override
	public void activerLesPortes(boolean a) {

		Vecteur dir = Vecteur.vNull;

		switch(o){
		case EST:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vOuest;
					break;
				case 2:
					dir = Vecteur.vSud; 
					break;	
				}
				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));
			}
			break;
		case NORD:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vOuest;
					break;
				case 1 :
					dir = Vecteur.vEst;
					break;
				case 2:
					dir = Vecteur.vSud; 
					break;
					
				}
				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));
			}
			break;
		case OUEST:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vEst;
					break;
				case 2:
					dir = Vecteur.vSud; 
					break;
					
				}
				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));
			}
			break;
		case SUD:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vOuest;
					break;
				case 2:
					dir = Vecteur.vEst; 
					break;
					
				}
				((CasePorte) c).setCollision( new CollisionTeleportation(this, dir));
			}
			break;
		}


	}

}
