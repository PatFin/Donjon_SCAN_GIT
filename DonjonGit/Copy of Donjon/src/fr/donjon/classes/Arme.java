package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Utilisable;
import fr.donjon.utils.Vecteur;

/**
 * 
 * @author Baptiste
 *
 */
public abstract class Arme implements Utilisable{

	Animation animation;	//Animation utilisée courrament
	Animation animationE;
	Animation animationN;

	Animation animationO;
	
	Animation animationS;
	int degats;			//Degats bruts ou par seconde selon le type d'attaque
	long duree;			//Duree de l'attaque
	EtatArme etat;		//Etat de l'attaque (finie ou pas)
	String iconSrc;

	Personnage lanceur; //Permet la syncro avec le personnage lanceur 
	Orientation o;		//Orientation de l'attaque

	protected ArrayList<Personnage> cibles; //Liste des cibles potentielles
	protected ArrayList<Projectile> projectiles;	//Listes des projectiles

	
	/**
	 * 
	 * @param lanceur 	Lanceur de l'attaque
	 * @param degats	Degats de l'arme
	 * @param duree		Durée de l'attaque
	 */
	public Arme(Personnage lanceur, int degats, long duree)
	{	
		this.lanceur = lanceur;
		this.degats = degats;
		this.etat = EtatArme.FINISHED; //On cree juste l'arme sans attaquer
		this.duree = duree;
	}
	
	public Arme(String iconSrc){
			this.iconSrc = iconSrc;
	}

	protected abstract void giveDammages();

	protected abstract void updateBounds();
	
	/**
	 * 
	 * @param cibles		La fonction ataque de l'arme définit les cibles touchées
	 * @param projectiles	Si l'arme lance des projectiles elle l'ajoute a cette liste
	 */
	public void attaquer(ArrayList<Personnage> personnage,
			ArrayList<Projectile> proj, Vecteur v){
		
		//L'attaque demarre et on regle la bonne animation selon la direction
		this.etat = EtatArme.RUNNING;
		this.o = v.projectMainDirection();
		this.projectiles = proj;
		this.cibles = personnage;
		this.lanceur.o = v.projectMainDirection();

		switch(o){
		case NORD :
			animation = animationN;
			break;
		case SUD :
			animation = animationS;
			break;
		case EST :
			animation = animationE;
			break;
		case OUEST :
			animation = animationO;
			break;
		}

	}
	
	/**
	 * 
	 * Cette methode dessine l'animation du personnage lors de l'attaque
	 * 
	 * @param g
	 * @param t
	 */
	public void draw(Graphics g, long t){
		
		
		int T = lanceur.image.width;
		
		//Si l'annimation est terminee le personnage passe au repos et on redemarre l'anim de l'arme
		if(animation.drawOnce(this.lanceur.image.x, this.lanceur.image.y, T, T, g, t)){
			
			animation.drawImage(this.lanceur.image.x, this.lanceur.image.y,T, T, g, 0);
			this.stopAttaquer();
			animation.restart();
		}
		
	}

	@Override
	public Image getIcon() {
		return ImageManager.getImage(iconSrc, this.getClass().getSimpleName());
	}

	public void stopAttaquer(){
		this.etat = EtatArme.FINISHING;
	}
	
	
	/**
	 * 
	 * Mise a jour de l'arme, coordonnée etc
	 * 
	 * @param t
	 */
	public void update(long t){

		switch(etat){

		case FINISHED: 
			lanceur.stopAttaque();
			break;
		case FINISHING : 
			updateBounds();
			giveDammages();
			break;
		case RUNNING :
			updateBounds();
			giveDammages();
			break;

		}

		
	}

}
