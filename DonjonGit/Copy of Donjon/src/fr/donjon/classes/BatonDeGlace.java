/**
 * 
 */
package fr.donjon.classes;

import java.awt.Graphics;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class BatonDeGlace extends Arme{

	final static int DMG = 0;
	final static int DUR = 200;
	final static String src = "hero_map.png";
	final static String icSrc = "IceStaff.png";


	Vecteur c = new Vecteur(0,0);

	/**
	 * Objet sans porteur
	 */
	public BatonDeGlace(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public BatonDeGlace(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),0,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),1,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),2,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),3,6,DUR);
		
		animation = animationS;

	}

	@Override
	public void paint(Graphics g, long t) {
		super.paint(g, t);
	}

	@Override
	public void stopAttaquer() {
		this.etat = EtatArme.FINISHED;
	}

	@Override
	public void attaquer(ArrayList<Personnage> personnage,
			ArrayList<Projectile> proj, Vecteur v) {
		
		super.attaquer(personnage, proj, v);
		
		projectiles.add(new BouleDeGlace(lanceur.image.x, lanceur.image.y, v));

		
	}

	@Override
	protected void giveDammages() {

	}

	@Override
	protected void updateBounds() {

	}

	////////////////////////////////
	///INTERFACE UTILISABLE/////////
	////////////////////////////////


	@Override
	public int utilise(Personnage p) {
		p.arme = new BatonDeGlace(p);
		return 0;
	}

}
