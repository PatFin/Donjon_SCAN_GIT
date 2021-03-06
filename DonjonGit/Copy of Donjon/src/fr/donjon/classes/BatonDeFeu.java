package fr.donjon.classes;


import java.util.ArrayList;

import fr.donjon.utils.Animation;
import fr.donjon.utils.EtatArme;
import fr.donjon.utils.Vecteur;

public class BatonDeFeu extends Arme {

	final static int DMG = 0;
	final static int DUR = 200;
	final static String icSrc = "IceBallIcon.png";
	final static String src = "skeletonfeu_map.png";


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

		animationN = new Animation(src, new Vecteur(64, 64),8,7,DUR); 
		animationO = new Animation(src, new Vecteur(64, 64),9,7,DUR);
		animationS = new Animation(src, new Vecteur(64, 64),10,7,DUR);
		animationE = new Animation(src, new Vecteur(64, 64),11,7,DUR);
		
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
		
		projectiles.add(new BouleDeFeu(lanceur.image.x + lanceur.image.width/2, lanceur.image.y+ lanceur.image.height/2, v));
		
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
		p.arme = new BatonDeFeu(p);
		return 0;
	}
}
