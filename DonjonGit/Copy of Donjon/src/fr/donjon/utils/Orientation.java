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
}
