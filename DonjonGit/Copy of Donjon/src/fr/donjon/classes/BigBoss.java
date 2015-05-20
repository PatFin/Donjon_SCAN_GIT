package fr.donjon.classes;

import java.awt.Rectangle;
import java.util.ArrayList;

import fr.donjon.salles.Salle;
import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class BigBoss extends Ennemis {
	
	final static int LNG = 128;
	final static int LRG = 128;
	final static int VIE = 500;
	final static int DEF = 25;
	final static int VIT = 4;
	final static String src = "big_boss_map.png";
	final static double COEFF = 0.3;
	
	final static int TDUR = 15000;
	final static int TFIRE = 5000;
	final static int TRAPR = 8000;
	final static int TMEGA = 15000;
	
	private long tEcoule;
	private long tDecal;
	
	/*
	 * 
	 */
	public BigBoss(int ax, int ay, Personnage cible, int level, Salle room){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(34,30,60,98), new Rectangle(44,96,40,32), true,
				Orientation.SUD, EtatPersonnage.REPOS, Vecteur.vNull,VIT,
				VIE, DEF , null, cible, room);
		
		this.cible = cible;
		
		animationN = new Animation(src, new Vecteur(64, 64),0,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),1,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),2,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),3,9,(long)(VIT/COEFF*100));
		animation = animationS;
		
		NIV = level;
		
		this.inventaire = new Inventaire(2, this);
		
		inventaire.addUtilisable(new BatonDeDark(this, 100));
		
		tEcoule = 0;
		tDecal = -1;
		
		inventaire.useUtilisable(0);
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

		int dx = cible.image.x - this.image.x;
		int dy = cible.image.y - this.image.y;
		
		Vecteur v = new Vecteur(dx, dy).normalise();
		
		if( tEcoule < TFIRE){
			this.vvitesse = Vecteur.vNull;
			attaquer( currentRoom.personnages, currentRoom.projectiles, v);
		}
		else if( tEcoule < TRAPR){
			marcher(v);
		}
		else if( tEcoule < TMEGA){
			
			if(tEcoule%500 != 0)return;
			
			int amount = 20;
			double angle = Math.PI * 2 / amount;
			
			for(double a = 0 ; a < Math.PI*2 ; a += angle){
				attaquer( currentRoom.personnages, currentRoom.projectiles, new Vecteur(Math.cos(a),Math.sin(a)));
			}
			
			marcher(v);
		}
		
	}


	@Override
	public void update(long t) {
		super.update(t);
		
		if(tDecal == -1)tDecal = t;
		
		tEcoule = (t - tDecal)%TDUR ;
		
		pattern();
		
	}

	@Override
	public void attaquer(ArrayList<Personnage> cibles,
			ArrayList<Projectile> projectiles, Vecteur v) {
			
		this.etat = EtatPersonnage.ATTAQUE;

		this.arme.attaquer(cibles, projectiles, v);
		
		this.etat = EtatPersonnage.REPOS;
	}

	@Override
	public void utiliserObjet(int reference) {
		
	}
}
