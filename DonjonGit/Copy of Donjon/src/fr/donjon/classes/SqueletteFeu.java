package fr.donjon.classes;


import java.awt.Rectangle;
import java.util.ArrayList;

import fr.donjon.salles.Salle;
import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

public class SqueletteFeu extends Ennemis {

	final static double COEFF = 0.3;
	static int DEF = 5;
	final static int LNG = 64;
	final static int LRG = 64;
	final static String src = "skeletonfeu_map.png";
	static int VIE = 75;
	static int VIT = 2;


	public SqueletteFeu(int ax, int ay, Personnage cible, int level, Salle room){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(17,15,30,49), new Rectangle(22,48,20,16), true,
				Orientation.SUD, EtatPersonnage.REPOS, Vecteur.vNull,VIT,
				VIE, DEF , null, cible, room);

		this.cible = cible;


		animationN = new Animation(src, new Vecteur(64, 64),0,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),1,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),2,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),3,9,(long)(VIT/COEFF*100));
		animation = animationS;


		this.inventaire = new Inventaire(2, this);

		inventaire.addUtilisable(new ArmeEpee());
		inventaire.addUtilisable(new BatonDeFeu(this));

		inventaire.useUtilisable(1);
	}

	@Override
	public void attaquer(ArrayList<Personnage> cibles,
			ArrayList<Projectile> projectiles, Vecteur v) {

		this.etat = EtatPersonnage.ATTAQUE;

		this.arme.attaquer(currentRoom.personnages, projectiles, v);

		this.etat = EtatPersonnage.REPOS;
	}

	@Override
	public void collide(Personnage p) {
		if(p.type == Type.HERO)this.setLocation(lPos);
		else {
			Vecteur axis = new Vecteur( this.image.x + this.image.width/2 - (p.image.x + p.image.width/2),
					this.image.y + this.image.height/2 - (p.image.y + p.image.height/2) );

			this.marcher(axis.normalise());
		}
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

	public void pattern(long t) {

		int dx = cible.image.x - this.image.x;
		int dy = cible.image.y - this.image.y;

		Vecteur v = new Vecteur(dx, dy);

		if (v.getNorm() > 300) {

			v = v.normalise();

			if(t %240 == 0) attaquer(currentRoom.personnages, currentRoom.projectiles, v);

		}else if (v.getNorm() < 301) {

			v = v.multiplie(-1);

			v= v.normalise();
			
			marcher(v);
			
		}else { 
			
			v = v.normalise();
			
			marcher(v);
		}
		
	}

	@Override
	public void update(long t) {

		super.update(t);

		if (stats.vie > 0) {

			pattern(t);
		}
	}

	@Override
	public void utiliserObjet(int reference) {

	}
}
