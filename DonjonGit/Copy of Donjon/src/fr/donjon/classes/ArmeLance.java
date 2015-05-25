package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;
/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public class ArmeLance extends Arme {

	final static int DMG = 30;
	final static int DUR = 350;
	final static String src = "hero_map.png";
	final static String icSrc = "spear.png";

	final static int zoneWidth = 20;
	final static int zoneHeight = 60;
	final static int decalageZoneX = 0;
	final static int decalageZoneY = 10; 

	private Rectangle zoneDommages = new Rectangle(0,0, zoneWidth, zoneHeight);
	private LinkedList<Personnage> persoTouches;

	Vecteur c = new Vecteur(0,0);
	/**
	 * Objet sans porteur
	 */
	public ArmeLance(){
		super(icSrc);
	}
	
	/**
	 * 
	 * @param lanceur
	 */
	public ArmeLance(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),4,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),5,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),6,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),7,6,DUR);
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
			if(c.collisionArmes.intersects(zoneDommages) && c.type==Type.ENNEMI && !persoTouches.contains(c)){
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
			zoneDommages.setBounds((int)c.x - zoneWidth/2, (int)c.y  - zoneHeight - decalageZoneX, zoneWidth, zoneHeight);
			break;
		case OUEST:
			zoneDommages.setBounds( (int)c.x - zoneHeight - decalageZoneX, (int)c.y  - zoneWidth/2 + decalageZoneY, zoneHeight, zoneWidth);
			break;
		case SUD:
			zoneDommages.setBounds((int)c.x - zoneWidth/2, (int)c.y + decalageZoneX, zoneWidth, zoneHeight);
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
		p.arme = new ArmeLance(p);
		return 0;
	}

}
