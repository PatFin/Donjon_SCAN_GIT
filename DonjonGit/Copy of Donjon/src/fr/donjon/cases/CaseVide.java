package fr.donjon.cases;


public class CaseVide extends Case {

	final static String ImageName = "Black.png";
	
	public CaseVide() {
		super(ImageName, true, null);
	}
	
	@Override
	public Case clone() {
		return new CaseVide();
	}
	
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		super.setCollisionBoxLocation(x, y);
		this.setCollision(new CollisionObstacle(limites));
	}
	
	
}
