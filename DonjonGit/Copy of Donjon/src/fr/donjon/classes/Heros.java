package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	final static int VIT = 7;
	final static String src = "hero_map.png";
	final static double COEFF = 1;
	

	/**
	 * Constructeur
	 * 
	 * @param ax Position x position horizontale du hÈro dans la salle (en pixels)
	 * @param ay Position y position verticale du hÈro dans la salle (en pixels)
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
		
		this.inventaire = new Inventaire(5, this);
		
		inventaire.addUtilisable(new ArmeEpee());
		inventaire.addUtilisable(new ArmeLance());
		inventaire.addUtilisable(new BatonDeGlace());
		
		inventaire.useUtilisable(0);
	}


	/*
	 * (non-Javadoc)
	 * @see fr.donjon.classes.Personnage#draw(long, java.awt.Graphics)
	 */
	public void draw(long t, Graphics g) {

		//Only the Heros has to draw the inventaire
		this.inventaire.draw(g);
		
		//We draw the hero just like any other character.
		super.draw(t, g);
		
		
	}

	/**
	 * Demarre le deplacement du personnage dans une direction
	 * @param v Direction de deplacement du personnage
	 */
	
	public void marcher(Vecteur v){
		//We store the new displacement value
		this.vvitesse = v;
		
		//If the character was'nt moving we change its state to moving
		if(this.etat == EtatPersonnage.REPOS){
			this.etat = EtatPersonnage.DEPLACEMENT;
		}
		
		//We pick the correct animation. If the character is attacking, we don't change the animation
		//In this case, the image drawn will be that of the character attacking.
		if(this.etat != EtatPersonnage.ATTAQUE){
			if(v.y > 0)			this.animation = animationS;
			else if ( v.y < 0)	this.animation = animationN;
			else if ( v.x < 0)	this.animation = animationO;
			else if ( v.x > 0 ) this.animation = animationE;
			else				this.animation = animationS;
		}
	}

	
	/**
	 * Methode qui lance l'attaque du h√©ros sur une/des cible(s)
	 * L'attaque pourra ajouter des projectiles sur le terrain
	 */
	public void attaquer(ArrayList<Personnage> personnage,
			ArrayList<Projectile> projectiles, Vecteur v) {
		
		//S'il attaque deja on ne fait rien
		if(this.etat == EtatPersonnage.ATTAQUE)return;
		
		//Lancement de l'attaque
		this.etat = EtatPersonnage.ATTAQUE;
		this.arme.attaquer(personnage, projectiles,v);

	}


	
	@Override
	public void utiliserObjet(int reference) {
		inventaire.useUtilisable(reference);
	}


	/**
	 * If the hero is colliding with another character, we want to put it back to its previous position
	 */
	@Override
	public void collide(Personnage p) {
		this.setLocation(lPos);
	}

}