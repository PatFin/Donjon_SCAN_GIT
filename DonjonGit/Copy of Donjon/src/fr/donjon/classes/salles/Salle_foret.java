package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_herbe;
import fr.donjon.classes.cases.Porte_Dalle_Sol;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Salle_foret extends SalleAbs {
	
	/**
	 * Constructor called when this is the first room of the dungeon
	 * @param ecran contains the space available to the room
	 * @param h the hero controlled by the player
	 * @param o the orientation of the door to be placed
	 */
	public Salle_foret(Rectangle ecran, Heros h, Orientation o) {
		super(ecran,h);
		
		//Adding the doors to the next room
		this.addDoor(o,true);
		
		//generating the image of the room after all the tiles have been set
		this.generateImage();
		
		//Placing the character in the middle of the room.
		Vecteur v = this.getCenter();
		hero.setLocation((int)v.x*Case.TAILLE, (int)v.y*Case.TAILLE);
	}
	
	
	/**
	 * Constructor called when the character comes from another room
	 * @param h the hero controlled by the player
	 * @param l the link from the previous room to this one
	 * @param o the orientation of the door to the next room
	 */
	public Salle_foret(Heros h, Link l, Orientation o){
		super(SalleAbs.ecran, h);
		
		//Creating the link to the previous room
		this.addDoorToPrevRoom(l);
		
		//Adding a door to the next room
		this.addDoor(o, true);
		
		this.generateImage();
	}
	
	/**
	 * Empty constructor
	 */
	public Salle_foret(){
		super();
	}
	
	/**
	 * Overrides the SalleAbs method.
	 * It also modifies the tiles that are concerned by this door.
	 * Then calls the mother class method to create the link
	 */
	@Override
	public void addDoor(Orientation o, boolean enabled){
		//We change the tiles of the door
		Vecteur v = porte.get(o);
		this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(enabled);
		
		//The link is created in the mother class
		super.addDoor(o, enabled);
	}
	
	
	/**
	 * Overrides the SalleAbs method.
	 * It also modifies the tiles that are concerned by this door.
	 * Then calls the mother class method to create the link
	 */
	@Override
	public void addDoorToPrevRoom(Link l){
		//We change the tiles of the door
		Vecteur v = porte.get(Orientation.opposite(l.orientation));
		this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(true);
		
		
		//The link is created in the mother class
		super.addDoorToPrevRoom(l);
	}
	
		
	/**
	 * This method sets the different places where the character
	 * will be placed when it comes from another room to this one
	 */
	@Override
	protected void setDestinationPlaces(){
		this.destination= new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.destination.put(Orientation.NORD, new Vecteur(cases.length/2,1));
		this.destination.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-2));
		this.destination.put(Orientation.OUEST, new Vecteur(1,cases[0].length/2));
		this.destination.put(Orientation.EST, new Vecteur(cases.length-2,cases[0].length/2));
	}

	/**
	 * This method sets the positions of the potential doors of the room.
	 */
	@Override
	protected void setDoorPlaces(){
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
	@Override
	protected void generateRoom(){
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		
		
		//We fill the array with grass tiles
		for(int x=1; x<cases.length-1;x++){
			for(int y=1;y<cases[0].length-1;y++){
				int random = (int)(Math.round(100*Math.random()));

				if(random >= 10){
					cases[x][y]=new Case_herbe();	
				}else {
					cases[x][y]=new Case_fendue_sol();
				}
			}
		}
		
		//The sides are filled with black tiles.
		super.fillEmptyWithVoid();
		
	}


	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		return new Salle_foret(ecran, h, o);
	}


	@Override
	public SalleAbs clone(Heros h, Link l, Orientation o) {
		return new Salle_foret(h, l, o);
	}

}
