/**
 * 
 */
package fr.donjon.classes;

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

	final static int decalageZoneX = 10;
	final static int decalageZoneY = 10;
	final static int DMG = 20;
	final static int DUR = 200;
	
	final static String icSrc = null;
	final static String src = "skeleton_map.png";
	final static int zoneHeight = 20;
	final static int zoneWidth = 35; 

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
	public ArmePoingSquelette(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		Vecteur v = new Vecteur(64, 64);
		
		animationN = new Animation(ArmePoingSquelette.src, v,4,6,DUR); 
		animationO = new Animation(ArmePoingSquelette.src, v,5,6,DUR);
		animationS = new Animation(ArmePoingSquelette.src, v,6,6,DUR);
		animationE = new Animation(ArmePoingSquelette.src, v,7,6,DUR);
		animation = animationS;

		this.persoTouches = new LinkedList<Personnage>();
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
		p.arme = new ArmePoingSquelette(p);
		return 0;
	}

}
