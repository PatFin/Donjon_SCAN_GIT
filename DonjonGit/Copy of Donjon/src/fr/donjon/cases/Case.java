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
 * Cette classe abstraite d�finit 
 * @author Vincent
 *
 */
public abstract class Case {

	public final static int TAILLE=64;

	boolean enabled;
	ArrayList<Personnage> persos;

	ArrayList<Projectile> projs;
	public CollisionPattern collision;
	
	public Image image;
	public Rectangle limites;;

	/**
	 * Constructeur de la Case sans comportement sp�ciaux
	 * @param ImageName le string contenant l'adresse de l'image.
	 */
	public Case(String src){
		this(src, true, null);
	}

	/**
	 * Constructeur 
	 * @param src string contenant l'adresse de l'image
	 * @param e bool�en n�cessaire pour certaines cases
	 * @param cp le comportement assoc� � la case
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
	 * Renvoi une nouvelle case du m�me genre que celle this.
	 * Elle est impl�ment�e dans les case qui en h�rite (case sp�cifiques ...)
	 */
	@Override
	public abstract Case clone();
	
	/**
	 * Permet de dessiner la case dans l'image de la salle 
	 * Certaines cases aux comportements plus sp�cifiques peuvent override cette m�thode
	 * @param g l� o� on veut dessiner la case
	 * @param t param�tre n�cessaire � certaines case (animations ...)
	 * @param x position horizontale de la case dans le tableau de case qui compose la salle.
	 * @param y position verticale de la case dans le tableau de case qui compose la salle.
	 */
	public void draw(Graphics g, long t, int x, int y){
		
		if(image == null)return;
		
		g.drawImage(image, TAILLE*x, TAILLE*y , TAILLE, TAILLE, null);
		
	}

	/**
	 * M�thode qui s'�x�cute quand un personnage marche sur la case
	 * Si la case est enabled et qu'elle poss�de un comportement particulier, on effectue ce comportement particulier sur le personnage en question
	 * On ajoute le personnage � la liste des perrsonnage qui sont en collision avec la case.
	 * @param p le personnage qui marche sur la case
	 * @see fr.donjon.cases.CollisionPattern#persoEnterCase(Personnage)
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
	
	/**
	 * M�thode qui s'�x�cute quand un projectile se trouve sur la case
	 * Si la case est enabled et qu'elle poss�de un comportement particulier, on effectue ce comportement particulier sur le projectile en question
	 * On ajoute le projectile � la liste des projectiles en collisions avec la case
	 * @param p le projectile qui se trouve sur la case
	 * @see fr.donjon.cases.CollisionPattern#projEnterCase(Projectile)
	 */
	public void inCollision(Projectile p){
		if(enabled && collision != null){
			if(!projs.contains(p)){
				collision.projEnterCase(p);
				projs.add(p);
			}
		}
	}
	
	
	/**
	 * M�thode qui s'effectue quand un personnage quitte la case. 
	 * Si la case est enabled, on effectue le comportement sp�cifique de la case lorsqu'un personnage quitte celle-ci,
	 * On retire le personnage en question de la liste des personnage en collision avec la case.
	 * @param p le personnage qui quitte la case.
	 * @see fr.donjon.cases.CollisionPattern#persoLeaveCase(Personnage)
	 */
	public void nonCollision(Personnage p){
		if(enabled && collision != null){
			if(persos.contains(p)){
				collision.persoLeaveCase(p);
				persos.remove(p);
			}
		}
		
	}
	
	/**
	 * M�thode qui s'�x�cute quand un projectile quitte la case.
	 * On effectue le comportement sp�cifique de la case quand un projectile la quitte.
	 * On retire le projectile de la liste des projectiles en collision avec cette case.
	 * @param p le projectile qui quitte la case.
	 * @see fr.donjon.cases.CollisionPattern#projLeaveCase(Projectile)
	 */
	public void nonCollision(Projectile p){
		if(enabled && collision != null){
			if(projs.contains(p)){
				collision.projLeaveCase(p);
				projs.remove(p);
			}
		}
		
	}

	/**
	 * Mutateur
	 * Permet de changer le comportement de la case en fournissant un nouveau CollisionPattern.
	 * @param cp contient le nouveau comportement de la case.
	 */
	public void setCollision(CollisionPattern cp){
		this.collision = cp;
	}

	
	/**
	 * Positionne le rectangle de collision qui va d�clencher la methode inCollision.
	 * Par d�fault le rectangle de collision recouvre toute la case.
	 * Certianes cases peuvent override cette m�thode afin de placer leur collision � un autre endroit.
	 * @param vertical index de l'array 2D de la salle o� se trouve la case.
	 * @param horizontal index de l'array 2D de la salle o� se trouve la case.
	 */
	public void setCollisionBoxLocation(int x, int y){
		this.limites.setLocation(Case.TAILLE*x, Case.TAILLE*y);
	}

	/**
	 * Mutateur
	 * Permet d'activer ou de d�sactiver les collisions de la case avec les personnages et le projectiles.
	 * @param b la nouvelle valeur de enabled. Si true, les collisions ont lieu. Si false les collsions sont ignor�es.
	 */
	public void setEnabled(boolean b){
		this.enabled = b;
	}

}
