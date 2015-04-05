package fr.donjon.classes;

import java.awt.Graphics;
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
public abstract class Ennemis extends Personnage{
	
	Personnage cible;
	// Ajouter des Animations ici directement ?
	
	public Ennemis(int ax, int ay,int longueur, int largeur, String nom, Rectangle image, Rectangle collisionArmes, Rectangle collisionDecor, boolean toDisplay,
			Orientation o, EtatPersonnage etat, Vecteur vvitesst, int vitd, //Attributs de Deplacable,
			int vie, int armure, Arme arme, //Attributs de Personnage
			Personnage cible){ //Attribut d'Ennemi
		
		super(ax, ay, longueur, largeur, nom, collisionArmes, collisionDecor, toDisplay, 
				vvitesst, vitd, o, etat,
				vie, armure, arme);
		
		this.cible = cible;
	}

	@Override
	abstract public void draw(long t, Graphics g);

	@Override
	abstract public void update(long t);

	@Override
	abstract public void attaquer(LinkedList<Personnage> cibles,
			LinkedList<Projectile> projectiles, Orientation o);
}
