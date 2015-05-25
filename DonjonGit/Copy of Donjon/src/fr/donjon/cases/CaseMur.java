package fr.donjon.cases;



/**
 * 
 * @author Vincent
 *
 */

public class CaseMur extends Case{
	
	final static String image="Case_mur.png";
	
	
	/**
	 * Constructeur
	 */
	public CaseMur(){
		super(image,true, null);
	}
	
	@Override
	public Case clone() {
		return new CaseMur();
	}
	
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		super.setCollisionBoxLocation(x, y);
		this.setCollision(new CollisionObstacle(limites));
	}
	
	

}