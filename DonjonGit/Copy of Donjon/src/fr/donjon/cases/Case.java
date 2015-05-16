package fr.donjon.cases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.ImageManager;

/**
 * Classe Case
 * @author Vincent
 *
 */
public abstract class Case {

	public final static int TAILLE=64;

	public Image image;
	public Rectangle limites;

	boolean enabled;
	public CollisionPattern collision;
	
	ArrayList<Personnage> persos;
	ArrayList<Projectile> projs;;

	/**
	 * Constructeur de la Case sans comportement spéciaux
	 * @param ImageName le string contenant l'adresse de l'image.
	 */
	public Case(String src){
		this(src, true, null);
	}

	/**
	 * Constructeur 
	 * @param src string contenant l'adresse de l'image
	 * @param e booléen nécessaire pour certaines cases
	 * @param cp lecomportement associé à la case
	 */
	public Case( String src, boolean e, CollisionPattern cp){

		image = ImageManager.getImage(src,this.getClass().getSimpleName());

		this.enabled = e;

		this.collision = cp;

		this.limites = new Rectangle(Case.TAILLE,Case.TAILLE);
		
		persos = new ArrayList<Personnage>();
		projs = new ArrayList<Projectile>();
	}

	/**
	 * Permet de dessiner la case dans l'image de la salle 
	 * Certaines cases aux comportements plus spécifiques peuvent override cette méthode
	 * @param g là où on veut dessiner la case
	 * @param t paramètre nécessaire à certaines case (animations ...)
	 * @param x position horizontale de la case dans le tableau de case qui compose la salle.
	 * @param y position verticale de la case dans le tableau de case qui compose la salle.
	 */
	public void draw(Graphics g, long t, int x, int y){
		
		if(image == null)return;
		
		g.drawImage(image, TAILLE*x, TAILLE*y , TAILLE, TAILLE, null);
		
	}
	
	/**
	 * Positionne le rectangle de collision qui va dï¿½clencher la methode inCollision.
	 * Par dï¿½fault le rectangle de collision recouvre toute la case.
	 * @param vertical index de l'array 2D de la salle.
	 * @param horizontal index de l'array 2D de la salle.
	 */
	public void setCollisionBoxLocation(int x, int y){
		this.limites.setLocation(Case.TAILLE*x, Case.TAILLE*y);
	}

	/**
	 * Methode vide. Les cases qui dï¿½rivent de Case peuvent l'override selon leur besoin.
	 * @param z Le personnage qui marche sur la case.
	 */
	public void inCollision(Personnage p) {
		
		if(enabled && collision != null){
			collision.persoCollision(p);
			if(!persos.contains(p)){
				collision.persoEnterCase(p);
				persos.add(p);
			}
		}
		
	}
	
	public void nonCollision(Projectile p){
		
		if(enabled && collision != null){
			if(projs.contains(p)){
				collision.projLeaveCase(p);
				projs.remove(p);
			}
		}
		
	}
	
	public void inCollision(Projectile p){
		if(enabled && collision != null){
			if(!projs.contains(p)){
				collision.projEnterCase(p);
				projs.add(p);
			}
		}
	}
	
	
	public void nonCollision(Personnage p){
		
		if(enabled && collision != null){
			if(persos.contains(p)){
				collision.persoLeaveCase(p);
				persos.remove(p);
			}
		}
		
	}

	/**
	 * Renvoi une nouvelle case du même genre que celle dont on appelle la méthode clone.
	 * Elle est redéfinie dans les classes dérivant de Case.
	 */
	public abstract Case clone();

	
	public void setEnabled(boolean b){
		this.enabled = b;
	}

	/**
	 * Mutateur
	 * Permet de changer le comportement de la case en fournissant un nouveau CollisionPattern.
	 * @param cp contient le nouveau comportement de la case.
	 */
	public void setCollision(CollisionPattern cp){
		this.collision = cp;
	}

}
