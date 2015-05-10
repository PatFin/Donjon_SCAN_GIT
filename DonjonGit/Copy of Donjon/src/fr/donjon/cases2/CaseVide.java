package fr.donjon.cases2;


public class CaseVide extends Case {

	final static String ImageName = "Black.png";
	
	public CaseVide() {
		super(ImageName, true, new CollisionObstacle());
	}
	
	@Override
	public Case clone() {
		return new CaseVide();
	}
	
	
}
