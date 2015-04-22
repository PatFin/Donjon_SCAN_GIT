package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Utilisable;

/**
 * 
 * @author Baptiste
 *
 */
public abstract class Arme implements Utilisable{

	Personnage lanceur; //Permet la syncro avec le personnage lanceur 
	Orientation o;		//Orientation de l'attaque
	EtatArme etat;		//Etat de l'attaque (finie ou pas)

	String iconSrc;
	
	Animation animationN;
	Animation animationS;
	Animation animationE;
	Animation animationO;
	Animation animation;	//Animation utilisée courrament

	int degats;			//Degats bruts ou par seconde selon le type d'attaque
	long duree;			//Duree de l'attaque

	protected LinkedList<Personnage> cibles; //Liste des cibles potentielles
	protected LinkedList<Projectile> projectiles;	//Listes des projectiles

	
	public Arme(String iconSrc){
			this.iconSrc = iconSrc;
	}
	
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

	/**
	 * 
	 * Cette methode dessine l'animation du héro lors de l'attaque
	 * 
	 * @param g
	 * @param t
	 */
	public void paint(Graphics g, long t){
		
		//Si l'annimation est terminee le personnage passe au repos et on redemarre l'anim de l'arme
		if(animation.drawOnce(this.lanceur.image.x, this.lanceur.image.y, Heros.LNG, Heros.LRG, g, t)){
			animation.drawImage(this.lanceur.image.x, this.lanceur.image.y, Heros.LNG, Heros.LRG, g, 0);
			this.stopAttaquer();
			animation.restart();
		}
		
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
	
	protected abstract void giveDammages();
	
	protected abstract void updateBounds();

	/**
	 * 
	 * @param cibles		La fonction ataque de l'arme définit les cibles touchées
	 * @param projectiles	Si l'arme lance des projectiles elle l'ajoute a cette liste
	 */
	public void attaquer(LinkedList<Personnage> cib,
			LinkedList<Projectile> proj, Orientation o){
		
		//L'attaque demarre et on regle la bonne animation selon la direction
		this.etat = EtatArme.RUNNING;
		this.o = o;
		this.projectiles = proj;
		this.cibles = cib;
		this.lanceur.o = o;

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

	public void stopAttaquer(){
		this.etat = EtatArme.FINISHING;
	}
	
	
	@Override
	public Image getIcon() {
		return ImageManager.getImage(iconSrc, this.getClass().getSimpleName());
	}

}
