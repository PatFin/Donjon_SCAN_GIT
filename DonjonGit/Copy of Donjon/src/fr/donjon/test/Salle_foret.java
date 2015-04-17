package fr.donjon.test;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Salle_foret extends SalleAbs {
	
	/**
	 * Constructor called when this is the first room
	 * of the dungeon
	 * @param ecran contains the space available to the room
	 * @param h the hero controlled by the player
	 * @param o the orientation of the door to be placed
	 */
	public Salle_foret(Rectangle ecran, Heros h, Orientation o) {
		super(ecran,h);
		
		//Standard room creation
		generateRoomImage();
		setDestinationPlaces();
		setDoorPlaces();
		
		//Setting up the door
		
		
		
		
	}
	
	/**
	 * Overrides the SalleAbs method.
	 * It also modifies the tiles that are concerned by this door.
	 * Then calls the mother class method to create the link
	 */
	protected void addDoor(Orientation o){
		// TODO taper cette classe.
	}
	
	
	/**
	 * This method sets the different places where the character
	 * will be placed when it comes from another room to this one
	 */
	private void setDestinationPlaces(){
		this.destination= new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.destination.put(Orientation.NORD, new Vecteur(cases.length/2,1));
		this.destination.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-2));
		this.destination.put(Orientation.OUEST, new Vecteur(1,cases[0].length/2));
		this.destination.put(Orientation.EST, new Vecteur(cases.length-2,cases[0].length/2));
	}
	
	
	/**
	 * This method sets the positions of the potential doors of the room.
	 */
	private void setDoorPlaces(){
		this.porte=new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.porte.put(Orientation.NORD, new Vecteur(cases.length/2,0));
		this.porte.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-1));
		this.porte.put(Orientation.EST, new Vecteur(cases.length-1,cases[0].length/2));
		this.porte.put(Orientation.OUEST, new Vecteur(0,cases[0].length/2));
	}

	/**
	 * This method fills the room with the standards
	 * tiles of the room.
	 * It does not add the links to other rooms
	 * nor does it modify the tiles accordingly.
	 */
	private void generateRoomImage(){
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		//We fill the array with grass tiles
		for(int x=1; x<cases.length-1;x++){
			for(int y=1;y<cases[0].length-1;y++){
				cases[x][y]=new Case_herbe();
			}
		}
		
		//The sides are filled with black tiles.
		fillEmptyWithVoid();
		
	}
}
