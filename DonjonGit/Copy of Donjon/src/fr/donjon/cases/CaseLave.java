/**
 * 
 */
package fr.donjon.cases;

import java.awt.Graphics;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Animation;
import fr.donjon.utils.Vecteur;


/**
 * @author Baptiste
 *
 */
public class CaseLave extends Case {

	final static int DPS = 10;
	final static String src = "AnimLave.png";
	
	Animation anim;
	
	CollisionPattern collisionE;
	
	
	/**
	 * @param ImageName
	 */
	public CaseLave() {
		super(src,true, new CollisionDegats(DPS));
		this.anim = new Animation(src, new Vecteur(64,64), 0, 3, 500);
		collisionE = new CollisionObstacle(limites);
	}
	
	@Override
	public Case clone() {
		return new CaseLave();
	}
	
	@Override
	public void draw(Graphics g, long t, int x, int y) {
		anim.drawAnim(x*TAILLE, y*TAILLE, TAILLE, TAILLE, g, t);
	}
	
	@Override
	public void inCollision(Personnage p) {

		switch(p.type){
		case ALIE:
			super.inCollision(p);
			break;
		case ENNEMI:
			collisionE.persoCollision(p);
			break;
		case HERO:
			super.inCollision(p);
			break;
		case UNDEFINED:
			break;
		default:
			break;
		
		
		
		}
		
	}
	
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		super.setCollisionBoxLocation(x, y);
		collisionE = new CollisionObstacle(limites);
	}

}
