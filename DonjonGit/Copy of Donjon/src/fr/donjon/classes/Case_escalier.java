// /!\ Classe Ã  complÃ©ter

package fr.donjon.classes;

import java.awt.Rectangle;

import fr.donjon.utils.Orientation;


public class Case_escalier extends Case {
	
	final static String image="Ressources/Images/Case_escalier.png";
	
	Orientation o; 	//Describes if the case is at top (north) of the room or the bottom (south)
	Salle destination;		//Salle vers lasuelle l'escalier va pointer.
	Salle appartient;		//Salle qui contient l'escalier
	
	/**
	 * Constructeur de la classe Case_Escalier
	 * La position du rectangle de collision est standard.
	 * @param o l'endroit ou se trouve l'escalier dans la salle.
	 * @param s La salle vers laquelle l'escalier va pointer.
	 */
	public Case_escalier(Orientation o, Salle destination, Salle appartient) {
		super(image);
		this.o=o;
		this.destination=destination;
		this.appartient=appartient;
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_escalier(o, destination, appartient);
	}
	

	/**
	 * Overrides the generic Case method.
	 */
	public void setCollisionBoxLocation(int vertical, int horizontal){
		switch(o){
		case NORD:
			this.collision = new Rectangle(horizontal*Case.TAILLE, vertical*Case.TAILLE, Case.TAILLE, Case.TAILLE/2);
			break;
		default:
			this.collision = new Rectangle(horizontal*Case.TAILLE, vertical*Case.TAILLE+(1/2)*Case.TAILLE, Case.TAILLE, Case.TAILLE/2);
		}
	}
	
	
	/**
	 * Comportement spécifique. Il faut envoyer le héro dans une autre salle.
	 */
	public void inCollision(Personnage z) {
		System.out.println("Changement de salle!");
		if(this.destination==null){
			
			destination=new Castle_Room(appartient.hero, appartient.ecran, appartient, appartient.castle);
			System.out.println("On crée une salle");
		}
		
		appartient.castle.changeRoom(this.destination);
	}
}
