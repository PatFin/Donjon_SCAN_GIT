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
 * @author Alexandre
 *
 */
public class Fleche extends Projectile{

	final static int DGT = 11;
	final static int DIM = 25;
	final static String src = "Arrow.png";
	final static int VIT = 12;
	
	final static int VITA = 100;
	

	public Fleche(int ax, int ay , Vecteur vvitesse) {
		super(ax, ay, DIM, DIM, src, new Rectangle(15,15, DIM-18, DIM-18), new Rectangle(15,15, DIM-18, DIM-18),
				true, vvitesse, VIT, Type.ENNEMI, DGT);
		
		Orientation ori = Vecteur.projectMainDirection(vvitesse);
		
		switch (ori) {
		case EST:
			anim = new Animation(src,  new Vecteur(32,32), 2, 1, VITA);
			break;
		case NORD:
			anim = new Animation(src,  new Vecteur(32,32), 3, 1, VITA);
			break;
		case OUEST:
			anim = new Animation(src,  new Vecteur(32,32), 1, 1, VITA);
			break;
		case SUD:
			anim = new Animation(src,  new Vecteur(32,32), 0, 1, VITA);
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
