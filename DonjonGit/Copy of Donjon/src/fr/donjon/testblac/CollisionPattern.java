package fr.donjon.testblac;

import fr.donjon.classes.Personnage;

public interface CollisionPattern {

	public void enCollision(Personnage p);
	
	public void nonCollision();
}
