package fr.donjon.utils;
/**
 * 
 * @author Vincent
 *
 */
public enum Orientation {
	NORD,SUD,OUEST,EST;
	
	
	
	public static Orientation opposite(Orientation o){
		if(o==null){
			return null;
		}
		switch(o){
		case NORD:
			return Orientation.SUD;
		case SUD:
			return Orientation.NORD;
		case EST:
			return Orientation.OUEST;
		case OUEST:
			return Orientation.EST;
		default:
			return null;
		}
	}
	
	public static Orientation getOrientation(Vecteur v){
		
		if(v.x > 0 && v.y == 0)return EST;
		else if (v.x < 0 && v.y == 0)return OUEST;
		else if (v.y > 0 && v.x == 0)return SUD;
		else if (v.y < 0 && v.x == 0)return NORD;
		else return NORD;
		
	}
}
