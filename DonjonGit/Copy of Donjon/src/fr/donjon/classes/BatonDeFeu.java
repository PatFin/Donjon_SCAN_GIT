package fr.donjon.classes;

import java.awt.Graphics;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

public class BatonDeFeu extends Arme {

	final static int DMG = 0;
	final static int DUR = 200;
	final static String src = "skeleton_map.png";
	final static String icSrc = "IceBallIcon.png";


	Vecteur c = new Vecteur(0,0);

	/**
	 * Objet sans porteur
	 */
	public BatonDeFeu(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public BatonDeFeu(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),8,6,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),9,6,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),10,6,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),11,6,DUR);
		
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
		
		projectiles.add(new BouleDeFeu(lanceur.image.x, lanceur.image.y, v));
		
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
		p.arme = new BatonDeFeu(p);
		return 0;
	}
}