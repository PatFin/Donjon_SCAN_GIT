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

	final static int DMG = 20;
	final static int DUR = 200;
	final static String src = "skeleton_map.png";
	final static String icSrc = null;
	
	final static int zoneWidth = 35;
	final static int zoneHeight = 20;
	final static int decalageZoneX = 10;
	final static int decalageZoneY = 10; 

	private Rectangle zoneDommages = new Rectangle(0,0, zoneWidth, zoneHeight);
	private LinkedList<Personnage> persoTouches;
	
	Vecteur c = new Vecteur(0,0);

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
	public ArmePoingSquelette(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		Vecteur v = new Vecteur(64, 64);
		
		animationN = new Animation(src, v,4,6,DUR); 
		animationO = new Animation(src, v,5,6,DUR);
		animationS = new Animation(src, v,6,6,DUR);
		animationE = new Animation(src, v,7,6,DUR);
		animation = animationS;

		this.persoTouches = new LinkedList<Personnage>();
	}

	@Override
	public void stopAttaquer() {
		//Pas d'anim de finition
		this.etat = EtatArme.FINISHED;
		this.persoTouches.clear();
	}

	@Override
	protected void giveDammages() {
		//On evite les dmg en continu
		for( Personnage c : cibles){
			if(c.collisionArmes.intersects(zoneDommages)&& c.type==Type.HERO && !persoTouches.contains(c)){
				persoTouches.add(c);
				c.receiveDammages(DMG);
			}
		}

	}

	@Override
	protected void updateBounds() {

		c = lanceur.getCentre();

		switch(o){
		case EST:
			zoneDommages.setBounds( (int)c.x + decalageZoneX, (int)c.y  - zoneWidth/2 + decalageZoneY, zoneHeight, zoneWidth);
			break;
		case NORD:
			zoneDommages.setBounds((int)c.x - zoneWidth/2, (int)c.y  - zoneHeight - decalageZoneX + 15, zoneWidth, zoneHeight);
			break;
		case OUEST:
			zoneDommages.setBounds( (int)c.x - zoneHeight - decalageZoneX, (int)c.y  - zoneWidth/2 + decalageZoneY, zoneHeight, zoneWidth);
			break;
		case SUD:
			zoneDommages.setBounds((int)c.x - zoneWidth/2, (int)c.y + decalageZoneX + 15, zoneWidth, zoneHeight);
			break;
		default:
			zoneDommages.setBounds((int)c.x, (int)c.y, 0, 0);
			break;


		}

	}

	////////////////////////////////
	///INTERFACE UTILISABLE/////////
	////////////////////////////////


	@Override
	public int utilise(Personnage p) {
		p.arme = new ArmePoingSquelette(p);
		return 0;
	}

}