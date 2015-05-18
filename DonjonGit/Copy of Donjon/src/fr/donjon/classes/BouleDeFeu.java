/**
 * 
 */
package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class BouleDeFeu extends Projectile{

	final static int DGT = 20;
	final static int VIT = 12;
	final static int DIM = 64;
	final static int VITA = 100;
	
	final static String src = "FireBall.png";
	

	public BouleDeFeu(int ax, int ay , Vecteur vvitesse) {
		super(ax, ay, DIM, DIM, src, new Rectangle(20,20, DIM-40, DIM-40), new Rectangle(20,20, DIM-40, DIM-40),
				true, vvitesse, VIT, Type.ENNEMI, DGT);
		
		Orientation ori = Vecteur.projectMainDirection(vvitesse);
		
		switch (ori) {
		case EST:
			anim = new Animation(src,  new Vecteur(64,64), 2, 3, VITA);
			break;
		case NORD:
			anim = new Animation(src,  new Vecteur(64,64), 3, 3, VITA);
			break;
		case OUEST:
			anim = new Animation(src,  new Vecteur(64,64), 1, 3, VITA);
			break;
		case SUD:
			anim = new Animation(src,  new Vecteur(64,64), 0, 3, VITA);
			break;
		default:
			break;
		
		}
		
	}
	
	@Override
	public void draw(long t, Graphics g) {
		
		if(!living)return;
		
		
		anim.drawAnim(image.x, image.y, DIM, DIM, g, t);
	
		
	}


	@Override
	public void update(long t) {
		
		if(!living)return;
		
		Vecteur pos = new Vecteur(image.x, image.y).ajoute(vvitesse.multiplie(vitDeplacement));
		
		setLocation( (int)pos.x, (int)pos.y );
		
	}

	@Override
	public void inCollision(Personnage p) {
		
		if(!living) return;
		if(p.type == this.type)return;


		p.receiveDammages(degats);
	}

}
