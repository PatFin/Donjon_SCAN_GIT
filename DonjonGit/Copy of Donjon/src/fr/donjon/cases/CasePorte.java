/**
 * 
 */
package fr.donjon.cases;

import fr.donjon.salles.Salle;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CasePorte extends Case {

	final static String src = "CaseTP.png";

	public CollisionPorte collisionPorte;


	public CasePorte() {
		super(src);
		this.setCollision(new CollisionObstacle());
	}


	/**
	 * This constructor should be prefered.
	 * @param s the room containing the door
	 * @param palier the vector indicating the available case in front of the door.
	 * @param o Orientation of the door
	 */
	public CasePorte(Salle s, Vecteur palier, Orientation o) {
		super(src);
		this.setCollision(new CollisionObstacle());
		this.collisionPorte = new CollisionPorte(s, palier, o);
	}

	/**
	 * Constructeur utilisé quand on crée le lien réciproque de la nouvelle salleActuelle vers la salle précédente.
	 * On connait alors le lien de A->B. 
	 * @param l le lien reciproque de B->A (voir méthode static de Link)
	 */
	public CasePorte(Link l){
		super(src);
		this.setCollision(new CollisionObstacle());
		this.collisionPorte = new CollisionPorte(l);
	}


	/**
	 * Methode utilis�e pour cr�er le lien vers la salle suivante.
	 * @param des
	 */
	public void setDestination(CasePorte des){
		this.collisionPorte.lien.setDestination(des.collisionPorte.lien.getSalleOrigine(), des.collisionPorte.lien.getPalier());
	}

	public void setDestination(Salle s, Vecteur des){
		this.collisionPorte.lien.setDestination(s, des);
	}


	/**
	 * Permet de rendre possible le passage � la salle suivante.
	 * @param b true si on veut rendre le passage possible, false sinon.
	 */
	public void setPassageAutorise(boolean b){
		collision = b ? collisionPorte : new CollisionObstacle() ;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases.Case#clone()
	 */
	@Override
	public Case clone() {
		return new CasePorte();
	}


}
