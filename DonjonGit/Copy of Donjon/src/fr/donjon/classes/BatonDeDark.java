package fr.donjon.classes;


import java.awt.Graphics;
import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

public class BatonDeDark extends Arme {

	final static int DMG = 0;
	final static int DUR = 500;
	final static String src = "big_boss_map.png";
	final static String icSrc = "DarkBall.png";


	Vecteur c = new Vecteur(0,0);

	int size;
	int speed;
	
	/**
	 * Objet sans porteur
	 */
	public BatonDeDark(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public BatonDeDark(Personnage lanceur, int size, int speed) {
		super(lanceur, DMG, DUR);

		this.size = size;
		this.speed = speed;
		
		animationN = new Animation(src, new Vecteur(64, 64),8,7,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),9,7,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),10,7,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),11,7,DUR);
		
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
		
		projectiles.add(new BouleDeDark(lanceur.image.x + lanceur.image.width/2,
				lanceur.image.y + lanceur.image.height/2,
				v, size,speed));
		
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
		p.arme = new BatonDeDark(p, size,speed);
		return 0;
	}
}
