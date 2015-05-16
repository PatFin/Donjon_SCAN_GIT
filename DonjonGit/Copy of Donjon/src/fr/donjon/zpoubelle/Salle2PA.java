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
public class Salle2PA extends SalleQuatre {

	Orientation o;

	public Salle2PA(Heros hero, Case c, int w, int h, Orientation o){
		this(hero, MapGenerator.fillWith(c, w, h), o);
	}
	
	public Salle2PA(Heros h, Case[][] cs, Orientation o){
		super(h,getCases(cs,o));
		this.o = o;
	}
	
	public Salle2PA(Heros hero,int w, int h, Orientation o){
		this(hero, MapGenerator.randomForet(w, h), o);
	}
	
	
	
	public static Case[][] addDoors(Case[][] c,Orientation o){
		
		int w = c.length;
		int h = c[0].length;
		
		switch(o){
		case NORD:
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			break;
		case SUD:
			c[0][(int)h/2] = new CasePorte(Orientation.OUEST);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;
		case EST:
			c[w-1][(int)h/2] = new CasePorte(Orientation.EST);
			c[(int)w/2][h-1] = new CasePorte(Orientation.SUD);
			break;
		case OUEST:
			c[0][(int)h/2] = new CasePorte(Orientation.OUEST);
			c[(int)w/2][0] = new CasePorte(Orientation.NORD);
			break;

		}

		return c;
	}

	public static Case[][] generateRandomMap(int w, int h, Orientation o){
		
		Case[][] cs = MapGenerator.randomForet(w, h);
		
		cs = addWalls(cs);
		cs = addDoors(cs, o);
		
		return cs;
		
	}
	
	public static Case[][] getCases(Case[][] c, Orientation o){
		
		c = addWalls(c);
		c = addDoors(c, o);
		
		return c;
	}
	
	//TODO remove this as it is now handled in the superclass
	/**
	@Override
	public void passerLaPorte(Vecteur dir) {
		super.passerLaPorte(dir);
		
		//TODO remove this as it is now handled in the superclass
		
		Vecteur pos = new Vecteur(0,0);


		switch(Orientation.getOrientation(dir)){
		case EST:
			pos.setLocation(1, (width/2)+1);
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
	*/

	//TODO changer les constructeurs en correspondance avec le nouveau CollisionPorte
	@Override
	public void activerLesPortes(boolean a) {

		Vecteur dir = Vecteur.vNull;

		switch(o){
		case NORD:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vEst;
					break;
				}
				((CasePorte) c).setCollision( new CollisionPorte(this, dir));
			}
			break;
		case SUD:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vOuest;
					break;
				case 1 :
					dir = Vecteur.vSud;
					break;
				}
				((CasePorte) c).setCollision( new CollisionPorte(this, dir));
			}

			break;
		case EST:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vEst;
					break;
				case 1 :
					dir = Vecteur.vSud;
					break;
				}
				((CasePorte) c).setCollision( new CollisionPorte(this, dir));
			}
			break;
		case OUEST:
			for( Case c : portes){
				switch(portes.indexOf(c)){
				case 0 :
					dir = Vecteur.vNord;
					break;
				case 1 :
					dir = Vecteur.vOuest;
					break;
				}
				((CasePorte) c).setCollision( new CollisionPorte(this, dir));
			}
			break;
		}

	}

}
