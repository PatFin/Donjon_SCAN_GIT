package fr.donjon.classes;


import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

public class ArcSquelette extends Arme {

	final static int DMG = 0;
	final static int DUR = 400;
	final static String icSrc = null;
	final static String src = "skeleton_arc.png";


	Vecteur c = new Vecteur(0,0);

	/**
	 * Objet sans porteur
	 */
	public ArcSquelette(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public ArcSquelette(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),13,13,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),14,13,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),15,13,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),16,13,DUR);
		
		animation = animationS;
	}


	@Override
	protected void giveDammages() {

	}

	@Override
	protected void updateBounds() {

	}

	@Override
	public void attaquer(ArrayList<Personnage> personnage,
			ArrayList<Projectile> proj, Vecteur v) {
		
		super.attaquer(personnage, proj, v);
		
		projectiles.add(new Fleche(lanceur.image.x + lanceur.image.width/2, lanceur.image.y+ lanceur.image.height/2, v));
		
	}

	@Override
	public void stopAttaquer() {
		this.etat = EtatArme.FINISHED;
	}

	////////////////////////////////
	///INTERFACE UTILISABLE/////////
	////////////////////////////////


	@Override
	public int utilise(Personnage p) {
		p.arme = new ArcSquelette(p);
		return 0;
	}
}
