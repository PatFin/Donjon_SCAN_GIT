package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import fr.donjon.utils.EtatPersonnage;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Vincent
 *
 */
public abstract class Ennemis extends Personnage{
	
	Personnage cible;
	
	static int NIV;
	
	// Ajouter des Animations ici directement ?
	
	public Ennemis(int ax, int ay,int longueur, int largeur, String nom, Rectangle offArm, Rectangle offCol, boolean toDisplay,
			Orientation o, EtatPersonnage etat, Vecteur vvitesst, int vitd, //Attributs de Deplacable,
			int vie, int armure, Arme arme, //Attributs de Personnage
			Personnage cible){ //Attribut d'Ennemi
		
		super(ax, ay, longueur, largeur, nom, offArm, offCol, toDisplay, 
				vvitesst, vitd, o, etat,
				vie, armure, arme,Type.ENNEMI);
		
		this.cible = cible;
	}

	@Override
	public void draw(long t, Graphics g){
		super.draw(t, g);
	}

	@Override
	public void update(long t) {
		super.update(t);
	}

	@Override
	abstract public void attaquer(ArrayList<Personnage> cibles,
			LinkedList<Projectile> projectiles, Orientation o);
}
