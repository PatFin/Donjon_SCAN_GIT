package fr.donjon.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.sound.SoundLoop;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

public class CaseSwitch extends CaseSource {

	static Vecteur pos1 = new Vecteur(9,0);
	static Vecteur pos2 = new Vecteur(10,0);
	private static SoundLoop clic = new SoundLoop(SoundLoop.CLIC);
	/**
	 * Constructeur
	 */
	public CaseSwitch() {
		super(pos1);
	
		setCollision(new CollisionPattern() {
			
			@Override
			public void persoCollision(Personnage p) {
			}
			
			@Override
			public void persoEnterCase(Personnage p) {
				clic.playOnce();
				
				if(p.type != Type.HERO)return;
				positionInMap = positionInMap == pos2 ? pos1 : pos2;
			}
			
			@Override
			public void persoLeaveCase(Personnage p) {
			}
			
			@Override
			public void projEnterCase(Projectile p) {
			}
			
			@Override
			public void projLeaveCase(Projectile p) {
			}
		});
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.CaseSource#clone()
	 */
	@Override
	public Case clone(){
		return new CaseSwitch();
	}
	
	/**
	 * Renvoie la valeur de l'interrupteur
	 * @return true/false 
	 */
	public boolean isActivated(){
		return positionInMap == pos1 ? false : true;
	}
	
}
