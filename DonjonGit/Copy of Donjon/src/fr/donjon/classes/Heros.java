package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;


/**
 * 
 * @author Vincent
 *
 */
public class Heros extends Personnage{

	final static int LNG = 64;
	final static int LRG = 64;
	final static String src = "C:/SVN/map.png";

	final static Rectangle offC = new Rectangle(17,52,30,12); 	//Les offsets du rectangle de collisions
																//par rapport a celui de l'image
	boolean key;

	Animation animation;
	Animation animationN;
	Animation animationS;
	Animation animationE;
	Animation animationO;

	/**
	 * 
	 * @param ax Position x
	 * @param ay Position y
	 */
	public Heros(int ax, int ay){
		super(ax, ay, LNG, LRG, src,
				new Rectangle(ax,ay,LNG,LRG), new Rectangle(ax+offC.y,ay+offC.x,offC.width,offC.height), true,
				Vecteur.vNull, 6, Orientation.SUD, EtatPersonnage.REPOS, 100, 0 , null);

		key = false;

		animationN = new Animation(src, new Vecteur(64, 64),8,9,550); 
		animationO = new Animation(src, new Vecteur(64, 64),9,9,550);
		animationS = new Animation(src, new Vecteur(64, 64),10,9,550);
		animationE = new Animation(src, new Vecteur(64, 64),11,9,500);
		animation = animationS;

		this.arme = new ArmeLance(this);

	}


	public void draw(long t, Graphics g) {

		switch(etat){
		case ATTAQUE:
			arme.paint(g, t);
			break;
		case DEPLACEMENT:
			animation.drawAnim(this.image.x, this.image.y, LNG, LRG, g, t);	//Dessin de l'animation
			break;
		case REPOS:
			animation.drawImage(this.image.x, this.image.y, LNG, LRG, g, 0);//Dessin de l'image 0 seulement
		}

	}

	/**
	 * Arrete le mouvement du personnage s'il allait dans la direction 'or'
	 * @param or Orientation de l'arret
	 */
	public void stop(Orientation or){
		if(etat==EtatPersonnage.DEPLACEMENT && o==or)
			this.etat = EtatPersonnage.REPOS;
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
	public void update(long t) {
		// TODO Auto-generated method stub

		switch(etat){

		case ATTAQUE :
			arme.update(t);
			break;
			
		case DEPLACEMENT :
			//A optimiser, eviter de créer un objets ttes le 40ms
			Vecteur nexPos = (new Vecteur(image.x,image.y)).ajoute(vvitesse.multiplie(vitDeplacement)) ;
			image.setLocation(nexPos.x,nexPos.y);
			//collisionArmes.setLocation(nexPos.x,nexPos.y);
			collisionDecor.setLocation(nexPos.x+offC.x,nexPos.y+offC.y);
			break;

		case REPOS :
			//Ben on fait rien ^_^
			break;
		}

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