/**
 * 
 */
package fr.donjon.utils;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Baptiste
 *
 *	Cette classe permet de gérer le defilement des animations
 *	a partir d'une image contenant les images successives a la suite (voir map.png par exemple)
 */

public class Animation{

	Image im; //L'image globale

	long duration; //La durée de l'animation
	long frameDuration; //La duree d'affichage d'une image
	int nb; //Le nombre d'images composant l'animation
	int line; //La ligne correspondant à l'animation sur l'image globale
	boolean isFinished; //Definit si l'anim est terminee ou non
	long timeStart; //Temps auquel a demarree l'anim
	
	Vecteur taille; //La taille d'une image dans l'animation
	Vecteur temp; //Vecteur temporaire se deplacant sur l'image globale

	/**
	 * 
	 * @param source 	Image source
	 * @param taille 	Taille de l'image animée
	 * @param line 		Ligne sur laquelle se trouve l'anim sur l'image globale	
	 * @param nb		Le nombre d'images composant l'animation
	 * @param duration	Durée d'affichage de l'animation
	 */
	public Animation(String source, Vecteur taille, int line, int nb, long duration){

		//Lecture image globale
		im = ImageManager.getImage(source, this.getClass().getSimpleName());

		this.taille = taille;
		this.line = line;
		this.nb = nb;
		this.duration = duration;
		this.isFinished = false;
		this.timeStart = -1;		//-1 si l'animation n'as jamais été demarree

		this.frameDuration = (long) duration/nb;

		//On se place en x=0 et au y correspondant à la ligne de l'animation a afficher
		temp = new Vecteur(0, line*taille.y);

	}

	/**
	 * 
	 * Cette methode permet de jouer une animation en boucle
	 * 
	 * @param x Coordonnée x
	 * @param y	Coordonnée y
	 * @param w	Largeur image
	 * @param h	Longueur image
	 * @param g	Graphics associé au buffer
	 * @param t	Temps écoulé
	 */
	public void drawAnim(int x, int y, int w, int h, Graphics g, long t){

		//Si l'animation est terminee on regle timeStart pour la redemarrer
		if( (t-timeStart)>=duration )timeStart = t;
		
		//Si l'anim n'as jamais ete demaree on regle timeStart
		if(timeStart == -1)timeStart = t;

		//Calcul coordonnée x de l'image
		temp.x = (int) (((t-timeStart)/(double)duration )*nb)*taille.x ;

		//Dessin image
		g.drawImage(im, (int) x, (int) y,
				(int) x+w,(int) y+h, 
				(int) temp.x,(int) temp.y,
				(int) (temp.x+taille.x), (int) (temp.y+taille.y), null);


	}

	/**
	 * 
	 * Cette methode permet d'afficher une seule fois une Animation
	 * 
	 * @param x	Coordonnee x
	 * @param y	Coordonnee y
	 * @param w	Largeur
	 * @param h	Hauteur
	 * @param g	Buffer sur lequel dessiner
	 * @param t	Temps ecoule
	 * @return	Renvoie True si l'animation est terminee, sinon renvoie False
	 */
	public boolean drawOnce(int x, int y, int w, int h, Graphics g, long t){

		//Renvoie true si terminee
		if(isFinished)return true;
		
		//Premier demarrage
		if(timeStart == -1)timeStart = t;
		
		//L'animation est terminee
		if( (t-timeStart)>=duration ){
			timeStart = -1;
			isFinished = true;
		}

		//Calcul coordonnée x de l'image
		temp.x = (int) (((t-timeStart)/(double)duration )*nb)*taille.x ;

		//Dessin image
		g.drawImage(im, (int) x, (int) y,
				(int) x+w,(int) y+h, 
				(int) temp.x,(int) temp.y,
				(int) (temp.x+taille.x), (int) (temp.y+taille.y), null);

		//Renvoi par defaut
		return false;
	}

	/**
	 * Reinitialise l'animation
	 */
	public void restart(){
		
		isFinished = false;
		timeStart = -1;
	}

	/**
	 * 
	 * Permet de dessiner une seule image de l'animation
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param g
	 * @param ima Image a dessiner 
	 */
	public void drawImage(int x, int y, int w, int h, Graphics g, int ima){

		//Calcul coordonnée x de l'image
		temp.x = ima*taille.x;

		//Dessin image
		g.drawImage(im, (int) x, (int) y,
				(int) x+w,(int) y+h, 
				(int) temp.x,(int) temp.y,
				(int) (temp.x+taille.x), (int) (temp.y+taille.y), null);

	}

}
