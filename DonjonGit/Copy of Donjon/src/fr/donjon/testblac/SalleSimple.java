/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class SalleSimple extends Salle {

	final static int W = 15;
	final static int H = 10;
	
	/**
	 * @param w
	 * @param h
	 */
	public SalleSimple(Heros h, Case c) {
		super(h,getCases(c));
	}

	public static Case[][] getCases(Case c){
		
		Case[][] cs = new Case[W][H];
		
		for(int x = 0 ; x < W ; x++){
			for(int y = 0 ; y < H ; y++){
				cs[x][y] = c.clone();
				cs[x][y].setCollisionBoxLocation(y, x);
			}
		}
		
		for(int x = 0 ; x < W ; x++){
			cs[x][0] = new Case_mur();
			cs[x][H-1] = new Case_mur();
		}
		
		for(int y = 0 ; y < H ; y++){
			cs[0][y] = new Case_mur();
			cs[W-1][y] = new Case_mur();
		}
		
		cs[0][(int)H/2] = new CasePorte(Orientation.OUEST);
		cs[W-1][(int)H/2] = new CasePorte(Orientation.EST);
		cs[(int)W/2][0] = new CasePorte(Orientation.NORD);
		cs[(int)W/2][H-1] = new CasePorte(Orientation.SUD);
		
		return cs;
		
	}
	
	@Override
	public void passerLaPorte(Vecteur dir) {
		super.passerLaPorte(dir);
		Vecteur pos = new Vecteur(0,0);
		
		System.out.println("Taking door "+Orientation.getOrientation(dir));
		
		switch(Orientation.getOrientation(dir)){
		case EST:
			pos.setLocation(2, (H/2)+1);
			break;
		case NORD:
			pos.setLocation( (W/2)+1, (H-1));
			break;
		case OUEST:
			pos.setLocation( (W-1), (H/2)+1 );
			break;
		case SUD:
			pos.setLocation( (W/2)+1 , 2 );
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
