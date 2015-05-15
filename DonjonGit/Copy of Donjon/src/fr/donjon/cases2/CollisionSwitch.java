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

	

	@Override
	public void persoCollision(Personnage p) {

	}

	@Override
	public void persoEnterCase(Personnage p) {
		if(on){
			on=false;
			return;
		}else{				
			on=true;
		}
		
	}

	@Override
	public void projEnterCase(Projectile p) {
		
	}

	@Override
	public void projLeaveCase(Projectile p) {
	}

	@Override
	public void persoLeaveCase(Personnage p) {
	}

}
