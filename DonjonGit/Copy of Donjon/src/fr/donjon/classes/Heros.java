package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;


/**
 * 
 * @author Vincent
 *
 */
public class Heros extends Personnage{

	final static int LNG = 64;
	final static int LRG = 64;
	final static int VIE = 100;
	final static int DEF = 10;
	final static int VIT = 6;
	final static String src = "C:/SVN/map.png";
	final static double COEFF = 2.5;
	
	boolean key;


	/**
	 * 
	 * @param ax Position x
	 * @param ay Position y
	 */
	public Heros(int ax, int ay){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(17,15,30,49), new Rectangle(17,52,30,12), true,
				Vecteur.vNull, VIT, Orientation.SUD, EtatPersonnage.REPOS, VIE, DEF , null, Type.HERO);

		key = false;

		animationN = new Animation(src, new Vecteur(64, 64),8,9,(long)(VIT/COEFF*100));
		animationO = new Animation(src, new Vecteur(64, 64),9,9,(long)(VIT/COEFF*100));
		animationS = new Animation(src, new Vecteur(64, 64),10,9,(long)(VIT/COEFF*100));
		animationE = new Animation(src, new Vecteur(64, 64),11,9,(long)(VIT/COEFF*100));
		animation = animationS;
		
		this.arme = new ArmeLance(this);

	}


	public void draw(long t, Graphics g) {
		super.draw(t, g);
	}

	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	/**
	 * Demarre le deplacement du personnage dans une direction
	 * @param dir Direction de deplacement du personnage
	 */
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

	/**
	 * Permet de mettre a jour le personnage (position, arme...)
	 */
	

	@Override
	public boolean enCollision(Rectangle r) {
		// TODO Auto-generated method stub
		return super.enCollision(r);
	}
	
	/**
	 * Methode qui lance l'attaque du héros sur une/des cible(s)
	 * L'attaque pourra ajouter des projectiles sur le terrain
	 */
	public void attaquer(LinkedList<Personnage> cibles,
			LinkedList<Projectile> projectiles, Orientation o) {
		
		//S'il attaque deja on ne fait rien
		if(this.etat == EtatPersonnage.ATTAQUE)return;
		
		//Lancement de l'attaque
		this.etat = EtatPersonnage.ATTAQUE;
		this.arme.attaquer(cibles, projectiles,o);

	}


	@Override
	public void utiliserObjet(int reference) {
		// TODO Auto-generated method stub
		
	}




}