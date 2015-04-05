package fr.donjon.classes;

import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Vincent
 *
 */

public abstract class Personnage extends Deplacable{

	Orientation o;
	EtatPersonnage etat;
	Arme arme;
	int vie;
	int armure;
	
	
	/**
	 * 
	 * @param ax			Position x
	 * @param ay			Position y
	 * @param longueur		Longueur image
	 * @param largeur		Largeur image
	 * @param nom			Nom de l'entité
	 * @param collisionArmes	Rectangle de collisions armes 
	 * @param collisionDecor	Rectangle de collisions avec le decor
	 * @param toDisplay			Affichage de l'objet
	 * @param vvitesse			Vecteur vitesse
	 * @param vitd				Vitesse de deplacement
	 * @param o					Orientation du personage
	 * @param etat				Etat du personnage
	 * @param vie				Vie du personnage
	 * @param armure			Armure du personnage
	 * @param arme				Arme portee par le personnage
	 */
	public Personnage(int ax, int ay,int longueur,int largeur,String nom,  Rectangle collisionArmes, Rectangle collisionDecor,boolean toDisplay,
			Vecteur vvitesse, int  vitd, 										//Attributs de Deplacable
			Orientation o, EtatPersonnage etat,int vie, int armure, Arme arme){ //Attributs de Personnage
		
		super(ax, ay, longueur, largeur, nom, collisionArmes, collisionDecor, toDisplay, vvitesse, vitd);
		
		this.o=o;
		this.etat=etat;
		this.arme = arme;
		this.armure = armure;
		this.vie = vie;
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
	
	/**
	 * Implémentation de la méthode de la classe mère
	 */
	public boolean enCollision(Rectangle r) {
		return super.enCollision(r);
	}
	
	@Override
	public abstract void update(long t);
}