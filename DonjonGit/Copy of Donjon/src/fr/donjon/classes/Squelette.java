package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.*;

public class Squelette extends Ennemis {
	
	final static int LNG = 64;
	final static int LRG = 64;
	final static int VIE = 50;
	final static int DEF = 5;
	final static int VIT = 4;
	final static String src = "Ressources/Images/skeleton_sprites.png";
	final static double COEFF = 2.5;
	
	public Squelette(int ax, int ay, String aNom, Personnage p){
		super(ax, ay, LNG, LRG, aNom,new Rectangle(LRG,LNG), new Rectangle(17,15,30,49), new Rectangle(22,48,20,16), true, Orientation.SUD, EtatPersonnage.REPOS, Vecteur.vNull, VIT, VIE, DEF, null, p);
		
		animationN = new Animation(src, new Vecteur(64, 64),1,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),2,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),3,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),4,9,(long)(VIT/COEFF*100));
		animation = animationS;
	}
	
	public void marcher(Orientation dir){
		//Si le personnage attaque, ne pas le faire se deplacer
		if(this.etat == EtatPersonnage.ATTAQUE)return;
		//Le personnage passe en mode deplacement
		this.etat = EtatPersonnage.DEPLACEMENT;
		this.o = dir;
		//Reglage du vecteur vitesse et de l'animation selon la direction de deplacement
		switch (dir){
		case NORD:
			this.vvitesse = Vecteur.vNord;
			this.animation = animationN;
			break;
		case SUD:
			this.vvitesse = Vecteur.vSud;
			this.animation = animationS;
			break;
		case EST:
			this.vvitesse = Vecteur.vEst;
			this.animation = animationE;
			break;
		case OUEST:
			this.vvitesse = Vecteur.vOuest;
			this.animation = animationO;
			break;
		default :
			this.vvitesse = Vecteur.vNull;
			this.animation = animationS;
		}
	}
	
	public void pattern(Personnage p) { // Le squelette se dirige vers le personnage p
		
		Orientation dir = Orientation.SUD;
		
		if (this.image.x > p.image.x) {
			
			dir = Orientation.EST;
		}
		
		if (this.image.x < p.image.x) {
			
			dir = Orientation.OUEST;
		}
		
		if (this.image.y > p.image.y) {
			
			dir = Orientation.SUD;
		}
		
		if (this.image.y < p.image.y) {
			
			dir = Orientation.NORD;
		}
		
		marcher(dir);
	}

	@Override
	public void draw(long t, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attaquer(LinkedList<Personnage> cibles,
			LinkedList<Projectile> projectiles, Orientation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void utiliserObjet(int reference) {
		// TODO Auto-generated method stub
		
	}
}
