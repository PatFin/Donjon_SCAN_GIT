package fr.donjon.utils;
/**
 * 
 * @author Vincent
 *
 */
public enum Orientation {
	NORD,SUD,OUEST,EST;
	
	
	/**
	 * Generates a random orientation
	 * @return a random orientation
	 */
	public static Orientation random(){
		Orientation[] o = {NORD,SUD,EST,OUEST};
		
		return o[(int)(Math.random()*4)];
	}
	
	/**
	 * Generates a random orientation which is not that of the one given in parameter
	 * @param o the non wanted Orientation
	 * @return any orientation but o
	 */
	public static Orientation random(Orientation o){
		Orientation a;
		do{
			a=random();
		}while(a==o);
		return a;
	}
	
	
	/**
	 * Gives the opposite Orientation to that given
	 * @param o 
	 * @return the opposite of o
	 */
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
	
	/**
	 * Gives back the unit vector corresponding to an orientation
	 * @param o the orientation to be converted into a vector
	 * @return a unit vector in the orientation given
	 */
	public static Vecteur getUnitVector(Orientation o){
		switch(o){
		case NORD:
			return Vecteur.vNord;
		case SUD:
			return Vecteur.vSud;
		case EST:
			return Vecteur.vEst;
		case OUEST:
			return Vecteur.vOuest;
		default:
			return Vecteur.vNull;
		}
	}
}
