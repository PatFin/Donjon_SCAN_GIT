package fr.donjon.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.Effet;
import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Vincent
 *
 */

public abstract class Personnage extends Deplacable{

	public Orientation o;
	EtatPersonnage etat;
	Arme arme;
	Inventaire inventaire;

	public Effet stats;		//les stats du personnage (vie, atk,...)

	public Type type;
	public boolean living;	//Etat vivant/mort du perso

	Animation animation;
	Animation animationN;
	Animation animationS;
	Animation animationE;
	Animation animationO;

	public Vecteur lPos;
	Vecteur tPos = new Vecteur(0,0);

	/**
	 * 
	 * @param ax			Position x
	 * @param ay			Position y
	 * @param longueur		Longueur image
	 * @param largeur		Largeur image
	 * @param nom			Nom de l'entit�
	 * @param offArm			Offsets rectangle armes
	 * @param offCol			Offsets rectangle collisions
	 * @param toDisplay			Affichage de l'objet
	 * @param vvitesse			Vecteur vitesse
	 * @param vitd				Vitesse de deplacement
	 * @param o					Orientation du personage
	 * @param etat				Etat du personnage
	 * @param vie				Vie du personnage
	 * @param armure			Armure du personnage
	 * @param arme				Arme portee par le personnage
	 */
	public Personnage(int ax, int ay,int longueur,int largeur,String nom,  Rectangle offArm, Rectangle offCol,boolean toDisplay,
			Vecteur vvitesse, int  vitd, 										//Attributs de Deplacable
			Orientation o, EtatPersonnage etat,int vie, int armure, Arme arme,Type t){ //Attributs de Personnage

		super(ax, ay, longueur, largeur, nom, offArm, offCol, toDisplay, vvitesse, vitd);

		this.o=o;
		this.etat=etat;

		this.stats = new Effet(this,vie,armure,vitd,0);

		this.arme = arme;
		this.type = t;
		this.living = true;

		this.inventaire = new Inventaire(0, this);

		this.lPos = new Vecteur(ax, ay);
	}

	
	@Override
	public void draw(long t, Graphics g) {

		if(!living)return;
		
		//Draws the character
		switch(etat){
		case ATTAQUE:
			arme.paint(g, t);
			break;
		case DEPLACEMENT:
			animation.drawAnim(image.x, image.y, image.width, image.height, g, t);	//Dessin de l'animation
			break;
		case REPOS:
			animation.drawImage(image.x, image.y, image.width, image.height, g, 0);//Dessin de l'image 0 seulement
		}

		//Draws the life of the personnage
		switch(type){
		case ALIE:
			g.setColor(new Color(0, 255, 0, 120));
			break;
		case ENNEMI:
			g.setColor(new Color(255, 0, 0, 120));
			break;
		case HERO:
			g.setColor(new Color(0, 0, 255, 120));
			break;
		case UNDEFINED:
			break;
		default:
			break;

		}
		
		int pourcent = (stats.vie * 112) /100;
		
		g.fillRect( (int)getCentre().x - pourcent/2, (int)getCentre().y - image.height/2 - 15, pourcent, 10);
		
		
	}

	@Override
	public void update(long t) {

		if(!living)return;
		
		super.update(t); 

		this.lPos.setLocation(image.x, image.y); //On met a jour la position pr�c�dente
		
		tPos.setLocation(image.x, image.y);
		tPos = tPos.ajoute(vvitesse.multiplie(stats.vit));
		
		//Deplacement
		switch(etat){

		case ATTAQUE :
			if(arme != null)arme.update(t);
			setLocation( (int) tPos.x, (int) tPos.y);
			break;

		case DEPLACEMENT :
			setLocation((int) tPos.x, (int) tPos.y);
			break;

		case REPOS :
			//Ben on fait rien ^_^
			break;
		}

		
	}

	public void stop(){
		if(etat==EtatPersonnage.DEPLACEMENT){
			this.etat = EtatPersonnage.REPOS;
			this.vvitesse = Vecteur.vNull;
		}
		else if( etat == EtatPersonnage.ATTAQUE){
			this.vvitesse = Vecteur.vNull;
		}
	}

	public void stopAttaque(){
		if(this.vvitesse == Vecteur.vNull){
			this.etat = EtatPersonnage.REPOS;
		}
		else this.etat = EtatPersonnage.DEPLACEMENT;

		if(arme != null)arme.stopAttaquer();
	}

	/**
	 * 
	 * 	Methode permettant � un personnage d'attaquer
	 * 
	 * @param cibles		Cibles potentielles de l'attaque
	 * @param projectiles	Tableau de projectiles de la salle 
	 * @param o				Orientation de l'attaque
	 */
	public abstract void attaquer(ArrayList<Personnage> cibles, ArrayList<Projectile> projectiles, Vecteur v);

	/**
	 * A modifier par la suite
	 * @param reference
	 */
	public abstract void utiliserObjet(int reference);

	/**
	 * Permet au perso de recevoir un certain nombre de dommage,
	 * calcule les dommages subits en fonction de l'armure et met a jour
	 * l'état du personnage.
	 * 
	 * @param amount Nombre de dommages a distribuer
	 */
	public void receiveDammages(int amount){
		stats.vie -= amount <= stats.def ? 0 : amount - stats.def ;
		if(stats.vie <= 0) living = false;
	}

	public abstract void collide(Personnage p);
	
}