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
 * @author Baptiste
 *
 */
public class Heros extends Personnage{

	public final static int LNG = 64;
	final static int LRG = 64;
	final static int VIE = 100;
	final static int DEF = 10;
	final static int VIT = 2;
	final static String src = "Ressources/Images/hero_map.png";
	final static double COEFF = 2.5;
	


	/**
	 * 
	 * @param ax Position x
	 * @param ay Position y
	 */
	public Heros(int ax, int ay){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(17,15,30,49), new Rectangle(22,48,20,16), true,
				Vecteur.vNull, VIT, Orientation.SUD, EtatPersonnage.REPOS, VIE, DEF , null, Type.HERO);


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
	public void marcher(Vecteur v){
		
		
		if(this.etat == EtatPersonnage.ATTAQUE){
			this.vvitesse = v;
		}
		
		else if(this.etat == EtatPersonnage.REPOS){
			this.etat = EtatPersonnage.DEPLACEMENT;
			this.vvitesse = v;

			if(v.y > 0)			this.animation = animationS;
			else if ( v.y < 0)	this.animation = animationN;
			else if ( v.x < 0)	this.animation = animationO;
			else if ( v.x > 0 ) this.animation = animationE;
			else				this.animation = animationS;
			
		}
		
		else if (this.etat == EtatPersonnage.DEPLACEMENT){
			this.vvitesse = v;
			if(v.y > 0)			this.animation = animationS;
			else if ( v.y < 0)	this.animation = animationN;
			else if ( v.x < 0)	this.animation = animationO;
			else if ( v.x > 0 ) this.animation = animationE;
			else				this.animation = animationS;
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