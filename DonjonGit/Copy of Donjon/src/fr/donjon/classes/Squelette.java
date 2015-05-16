package fr.donjon.classes;

import java.awt.Rectangle;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Squelette extends Ennemis {
	
	final static int LNG = 64;
	final static int LRG = 64;
	static int VIE = 50;
	static int DEF = 5;
	static int VIT = 3;
	final static String src = "skeleton_map.png";
	final static double COEFF = 0.3;
	public Personnage target;
	
	public Squelette(int ax, int ay, Personnage cible, int level){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(17,15,30,49), new Rectangle(22,48,20,16), true,
				Orientation.SUD, EtatPersonnage.REPOS, Vecteur.vNull,VIT,
				VIE, DEF , null, cible);
		
		target = cible;
		
		
		animationN = new Animation(src, new Vecteur(64, 64),0,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),1,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),2,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),3,9,(long)(VIT/COEFF*100));
		animation = animationS;
		
		NIV = level;
	}
	
	//calculer le vecteur Squellette Personnage (voir methode marche de heros)
	public void marcher(Vecteur v){
		
		//Si le personnage attaque, ne pas le faire se deplacer
		if(this.etat == EtatPersonnage.ATTAQUE)return;
		//Le personnage passe en mode deplacement
		this.etat = EtatPersonnage.DEPLACEMENT;
		
		Orientation dir = v.projectMainDirection();
		this.o = dir;
		
		v = v.normalise();
		this.vvitesse = v;
		
		//Reglage du vecteur vitesse et de l'animation selon la direction de deplacement
		switch (dir){
		case NORD:
			this.animation = animationN;
			break;
		case SUD:
			this.animation = animationS;
			break;
		case EST:
			this.animation = animationE;
			break;
		case OUEST:
			this.animation = animationO;
			break;
		default :
			this.vvitesse = Vecteur.vNull;
			this.animation = animationS;
		}
	}
	
	public void pattern() { // Le squelette se dirige vers sa cible

		int dx = target.image.x - this.image.x;
		int dy = target.image.y - this.image.y;
		
		Vecteur v = new Vecteur(dx, dy);
		
		if (v.getNorm() > 30) {
			
			marcher(v);
		}
		else {
			
			this.etat = EtatPersonnage.REPOS;
		}
	}


	@Override
	public void update(long t) {
		
		super.update(t);
		
		if (stats.vie > 0) {
			
			pattern();
		}
	}

	@Override
	public void attaquer(ArrayList<Personnage> cibles,
			ArrayList<Projectile> projectiles, Orientation o) {
		
	}

	@Override
	public void utiliserObjet(int reference) {
		
	}
}
