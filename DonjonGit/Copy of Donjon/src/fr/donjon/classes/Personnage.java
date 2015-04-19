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

public abstract class Personnage extends Deplacable{

	public Orientation o;
	EtatPersonnage etat;
	Arme arme;
	public Type type;
	int vie;
	int armure;
	
	Animation animation;
	Animation animationN;
	Animation animationS;
	Animation animationE;
	Animation animationO;
	
	public Vecteur lPos;	//Permet la gestion des collisions
	
	/**
	 * 
	 * @param ax			Position x
	 * @param ay			Position y
	 * @param longueur		Longueur image
	 * @param largeur		Largeur image
	 * @param nom			Nom de l'entité
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
		this.arme = arme;
		this.armure = armure;
		this.vie = vie;
		this.type = t;
		
		this.lPos = new Vecteur(ax, ay);
	}
	
	
	@Override
	public void draw(long t, Graphics g) {
		
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
	}
	
	@Override
	public void update(long t) {
		// TODO Auto-generated method stub
		super.update(t);
		
		this.lPos.setLocation(image.x, image.y); //On met a jour la position précédente
		
		switch(etat){

		case ATTAQUE :
			if(arme != null)arme.update(t);
			Vecteur nexPos = (new Vecteur(image.x,image.y)).ajoute(vvitesse.multiplie(vitDeplacement));
			setLocation( (int) nexPos.x, (int) nexPos.y);
			break;
			
		case DEPLACEMENT :
			//A optimiser, eviter de créer un objets ttes le 40ms
			Vecteur nexPos1 = (new Vecteur(image.x,image.y)).ajoute(vvitesse.multiplie(vitDeplacement));
			setLocation((int) nexPos1.x, (int) nexPos1.y);
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
	 * 	Methode permettant à un personnage d'attaquer
	 * 
	 * @param cibles		Cibles potentielles de l'attaque
	 * @param projectiles	Tableau de projectiles de la salle 
	 * @param o				Orientation de l'attaque
	 */
	public abstract void attaquer(LinkedList<Personnage> cibles, LinkedList<Projectile> projectiles, Orientation o );
	
	/**
	 * A modifier par la suite
	 * @param reference
	 */
	public abstract void utiliserObjet(int reference);
	
	
	
}