package fr.donjon.classes;

import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

public class ArcHeros extends Arme {

	final static int DMG = 0;
	final static int DUR = 400;
	final static String icSrc = "bow.png";
	final static String src = "hero_map.png";


	Vecteur c = new Vecteur(0,0);

	/**
	 * Objet sans porteur
	 */
	public ArcHeros(){
		super(icSrc);
	}

	/**
	 * 
	 * @param lanceur
	 */
	public ArcHeros(Personnage lanceur) {
		super(lanceur, DMG, DUR);

		animationN = new Animation(src, new Vecteur(64, 64),16,13,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),17,13,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),18,13,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),19,13,DUR);
		
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
		
		projectiles.add(new FlecheHeros(lanceur.image.x + lanceur.image.width/2, lanceur.image.y+ lanceur.image.height/2, v));
		
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
		p.arme = new ArcHeros(p);
		return 0;
	}
}