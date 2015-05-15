/**
 * 
 */
package fr.donjon.cases2;

import fr.donjon.classes.Personnage;
import fr.donjon.testblac.Salle;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CasePorte extends Case {

	final static String src = "CaseTP.png";
	
	boolean passageAutorise;
	public CollisionPorte collisionPorte;
	
	//TODO remove this constructor?
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
	 * Constructeur utilis� quand on cr�� le lien r�ciproque de la nouvelle salleActuelle vers la salle pr�c�dente.
	 * On connait alors le lien de A->B. 
	 * @param l le lien reciproque de B->A (voir m�thode static de Link)
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
	
	
	public void setPassageAutorise(boolean b){
		this.passageAutorise=b;
	}
	
	@Override
	public void inCollision(Personnage p){
		System.out.println(passageAutorise);
		if(passageAutorise){
			//Alors on change de salle
			collisionPorte.enCollision(p);
		}else{
			//La porte se comporte comme un obstacle
			super.inCollision(p);
		}
	}

}
