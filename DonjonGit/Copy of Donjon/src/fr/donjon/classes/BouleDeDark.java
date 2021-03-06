package fr.donjon.classes;
/**
 * 
 */


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
public class BouleDeDark extends Projectile{

	final static int DGT = 15;
	final static String src = "DarkBall.png";
	final static int VITA = 100;
	
	
	int size;
	int speed;
	
	
	public BouleDeDark(int ax, int ay , Vecteur vvitesse, int size, int speed) {
		super(ax - size/2, ay - size/2, size, size, src, new Rectangle(15*(size/40),15*(size/40), size-30*(size/40), size-30*(size/40)), new Rectangle(15*(size/40),15*(size/40), size-30*(size/40), size-30*(size/40)),
				true, vvitesse, speed, Type.ENNEMI, (int)((size/65.0) * 12) );
		
		this.size = size;
		this.speed = speed;
		
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
		
		
		anim.drawAnim(image.x, image.y, size, size, g, t);
	
		
	}


	@Override
	public void inCollision(Personnage p) {
		
		if(!living) return;
		if(p.type == this.type)return;

		p.receiveDammages(degats);
		this.living=false;
	}

	@Override
	public void update(long t) {
		
		if(!living)return;
		
		Vecteur pos = new Vecteur(image.x, image.y).ajoute(vvitesse.multiplie(vitDeplacement));
		
		setLocation( (int)pos.x, (int)pos.y );
		
	}

}
