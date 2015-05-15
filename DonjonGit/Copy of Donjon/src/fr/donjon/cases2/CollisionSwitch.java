package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;

public class CollisionSwitch implements CollisionPattern {

	public boolean on;
	boolean wasSteppedOn;
	
	public CollisionSwitch() {
		this.on=false;
		this.wasSteppedOn=false;
	}

	//TODO put a time parameter in method enCollision
	@Override
	public void enCollision(Personnage p) {
		if(on){
			on=false;
			return;
		}else{				
			on=true;
		}
	}

	@Override
	public void enCollision(Projectile p) {
		//Do nothing
	}

	@Override
	public void nonCollision(Personnage p) {
		//Do nothing
	}

}
