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

	private int phase;
	private long tEcoule;
	private long tDecal;

	/*
	 * 
	 */
	public BigBoss(int ax, int ay, Personnage cible, int level, Salle room){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(34*LNG/128,30*LNG/128,60*LNG/128,98*LNG/128), new Rectangle(44*LNG/128,96*LNG/128,40*LNG/128,32*LNG/128), true,
				Orientation.SUD, EtatPersonnage.REPOS, Vecteur.vNull,VIT,
				VIE, DEF , null, cible, room);

		this.cible = cible;
		this.phase = 3;

		animationN = new Animation(src, new Vecteur(64, 64),0,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),1,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),2,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),3,9,(long)(VIT/COEFF*100));
		animation = animationS;

		NIV = level;

		this.inventaire = new Inventaire(2, this);

		inventaire.addUtilisable(new BatonDeDark(this, 65, 6));

		tEcoule = 0;
		tDecal = -1;

		inventaire.useUtilisable(0);
	}


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

	public void pattern(long t) { // Le squelette se dirige vers sa cible

		int dx = cible.image.x + cible.image.width/2 - (this.image.x  + this.image.width/2) ;
		int dy = cible.image.y + cible.image.height/2 - (this.image.y + this.image.height/2) ;

		Vecteur v = new Vecteur(dx, dy).normalise();

		if( tEcoule < TFIRE){
			this.vvitesse = Vecteur.vNull;
			
			if(t%120 != 0)return;
			
			attaquer( currentRoom.personnages, currentRoom.projectiles, v);
		}
		else if( tEcoule < TRAPR){
			marcher(v);
		}
		else if( tEcoule < TMEGA){

			marcher(v);
			
			if(t%500 != 0)return;

			int amount = 20;
			double angle = Math.PI * 2 / amount;

			for(double a = 0 ; a < Math.PI*2 ; a += angle){
				attaquer( currentRoom.personnages, currentRoom.projectiles, new Vecteur(Math.cos(a),Math.sin(a)));
			}

		}

	}


	@Override
	public void update(long t) {
		super.update(t);

		if(tDecal == -1)tDecal = t;

		tEcoule = (t - tDecal)%TDUR ;

		if( phase !=  (int)stats.vie/(VIE/3)  ){
			phase = (int)stats.vie/(VIE/3);
			
			switch(phase){
			case 2:
				arme = new BatonDeDark(this, 75, 7);
				break;
			case 1:
				arme = new BatonDeDark(this, 85, 8);
				break;
			case 0:
				arme = new BatonDeDark(this, 100, 10);
				stats.vit+=3;
				stats.def+=2;
				break;
			}
		}

		pattern(t);

	}

	@Override
	public void attaquer(ArrayList<Personnage> cibles,
			ArrayList<Projectile> projectiles, Vecteur v) {

		this.etat = EtatPersonnage.ATTAQUE;

		this.arme.attaquer(cibles, projectiles, v);

		//this.etat = EtatPersonnage.REPOS;
	}

	@Override
	public void utiliserObjet(int reference) {

	}
}
