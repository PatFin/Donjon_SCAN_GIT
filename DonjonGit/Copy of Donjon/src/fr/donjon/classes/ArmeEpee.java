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
 * @author Baptiste
 *
 */
public class ArmeEpee extends Arme{

	final static int decalageZoneX = 10;
	final static int decalageZoneY = 10;
	final static int DMG = 40;
	final static int DUR = 200;
	
	final static String icSrc = "sword.png";
	final static String src = "hero_map.png";
	final static int zoneHeight = 20;
	final static int zoneWidth = 35; 

	Vecteur c = new Vecteur(0,0);
	private LinkedList<Personnage> persoTouches;
	
	private Rectangle zoneDommages = new Rectangle(0,0, zoneWidth, zoneHeight);

	/**
	 * Constructeur pour objet sans porteur
	 */
	public ArmeEpee(){
		super(icSrc);
	}
	
	/**
	 * Constructeur
	 * @param lanceur
	 */
	public ArmeEpee(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),12,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),13,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),14,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),15,6,DUR);
		animation = animationS;

		this.persoTouches = new LinkedList<Personnage>();
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

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.classes.Arme#updateBounds()
	 */
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

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.classes.Arme#stopAttaquer()
	 */
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
		p.arme = new ArmeEpee(p);
		return 0;
	}

}
