package fr.donjon.cases;

import java.awt.Rectangle;


public class CaseRocher extends Case {

	final static String image="Case_rocher.png";


	/**
	 * Constructeur
	 */
	public CaseRocher (){
		super(image, true, null);
		this.limites = new Rectangle(Case.TAILLE/2, Case.TAILLE/2);
		setCollision(new CollisionObstacle(limites));
	}

	@Override
	public Case clone() {
		return new CaseRocher();
	}
	
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		this.limites.setLocation( Case.TAILLE * x + 16, Case.TAILLE * y + 16);
		setCollision(new CollisionObstacle(limites));
	}

	
}
