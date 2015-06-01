/**
 * 
 */
package fr.donjon.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * @author Alexandre
 *
 */
public class ArmePoingSquelette extends Arme{

	final static int decalageZoneX = 0;
	final static int decalageZoneY = 0;
	
	final static int DUR = 500;

	final static String icSrc = null;
	final static String src = "skeleton_map.png";
	final static int zoneHeight = 45;
	final static int zoneWidth = 45; 
	
	Vecteur c = new Vecteur(0,0);
	private LinkedList<Personnage> persoTouches;

	private Rectangle zoneDommages = new Rectangle(0,0, zoneWidth, zoneHeight);

	/**
	 * Objet sans porteur
	 */
	public ArmePoingSquelette(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public ArmePoingSquelette(Personnage lanceur, int dommages) {
		super(lanceur, dommages, DUR);

		Vecteur v = new Vecteur(64, 64);

		animationN = new Animation(src, v, 4, 6, DUR); 
		animationO = new Animation(src, v, 5, 6, DUR);
		animationS = new Animation(src, v, 6, 6, DUR);
		animationE = new Animation(src, v, 7, 6, DUR);
		animation = animationS;

		this.persoTouches = new LinkedList<Personnage>();
	}

	@Override
	protected void giveDammages() {

		for( Personnage p :  cibles){
			if( p.collisionArmes.intersects(zoneDommages) && p.type == Type.HERO && !persoTouches.contains(p)){
				p.receiveDammages(degats);
				persoTouches.add(p);
			}
			
		}
		


	}

	@Override
	protected void updateBounds() {

		c = lanceur.getCentre();

		zoneDommages.setLocation( (int) (c.x - zoneDommages.width/2)  , (int) (c.y - zoneDommages.height/2) ); 


	}
	

	@Override
	public void stopAttaquer() {
		//Pas d'anim de finition
		this.etat = EtatArme.FINISHED;
		this.persoTouches.clear();
	}

	////////////////////////////////
	///INTERFACE UTILISABLE/////////
	////////////////////////////////


	@Override
	public int utilise(Personnage p) {
		p.arme = new ArmePoingSquelette(p,12);
		return 0;
	}

}
