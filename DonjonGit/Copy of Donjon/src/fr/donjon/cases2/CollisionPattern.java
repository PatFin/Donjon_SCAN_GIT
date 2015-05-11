package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

public interface CollisionPattern {

	public void enCollision(Personnage p);
	
	public void enCollision(Projectile p);
	
	public void nonCollision(Personnage p);
}
