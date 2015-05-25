package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

public interface CollisionPattern {

	public void persoCollision(Personnage p);
	
	public void persoEnterCase(Personnage p);
	
	public void projEnterCase(Projectile p);
	
	public void projLeaveCase(Projectile p);
	
	public void persoLeaveCase(Personnage p);
	
	public String toString();
}
