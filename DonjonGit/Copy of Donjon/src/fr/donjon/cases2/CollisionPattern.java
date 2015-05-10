package fr.donjon.cases2;

import fr.donjon.classes.Personnage;

public interface CollisionPattern {

	public void enCollision(Personnage p);
	
	public void nonCollision(Personnage p);
}
