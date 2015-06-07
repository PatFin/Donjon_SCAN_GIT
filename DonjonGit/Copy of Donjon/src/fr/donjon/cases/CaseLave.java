/**
 * 
 */
package fr.donjon.cases;

import java.awt.Graphics;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Animation;
import fr.donjon.utils.Vecteur;


/**
 * 
 * @author Baptiste
 *
 */
public class CaseLave extends Case {

	final static int DPS = 10;
	final static String src = "AnimLave.png";
	
	Animation anim;
	
	CollisionPattern collisionE;
	
	
	/**
	 * Constructeur
	 * Pas de paramètre requis
	 */
	public CaseLave() {
		super(src,true, new CollisionDegats(DPS));
		this.anim = new Animation(src, new Vecteur(64,64), 0, 3, 500);
		collisionE = new CollisionObstacle(limites);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CaseLave();
	}
	
	/**
	 * La caseLave bénéficie d'une animation. Son apparence change d'un dessin de la salle à l'autre.
	 */
	@Override
	public void draw(Graphics g, long t, int x, int y) {
		anim.drawAnim(x*TAILLE, y*TAILLE, TAILLE, TAILLE, g, t);
	}
	
	/**
	 * La case lave se comporte différament selon le type de personnage.
	 * Si le héro marche dessus, il subi des dégats.
	 * Si un ennemi marche dessus, il est repoussé (genre d'obstacle)
	 * @param p le personnage en collision avec la case  
	 */
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
		default:
			break;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#setCollisionBoxLocation(int, int)
	 */
	@Override
	public void setCollisionBoxLocation(int x, int y) {
		super.setCollisionBoxLocation(x, y);
		collisionE = new CollisionObstacle(limites);
	}

}
