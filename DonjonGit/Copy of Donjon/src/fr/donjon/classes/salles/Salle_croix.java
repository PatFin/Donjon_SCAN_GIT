package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Link;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.classes.cases.Porte_Dalle_Sol;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Salle_croix extends SalleAbs {

	/**
	 * Constructor called when this is the first room of the dungeon
	 * @param ecran contains the space available to the room
	 * @param h the hero controlled by the player
	 * @param o the orientation of the door to be placed
	 */
	public Salle_croix(Rectangle ecran, Heros h, Orientation o) {
		super(ecran, h);
		
		//Adding the doors to the next room
		this.addDoor(o, true);
		
		//Generating the image of the room after all the tiles have been set.
		this.generateImage();
	}
	
	/**
	 * Constructor called when the character comes from another room
	 * @param h the hero controlled by the player
	 * @param l the link from the previous room to this one
	 * @param o the orientation of the door to the next room
	 */
	public Salle_croix(Heros h, Link l, Orientation o){
		super(SalleAbs.ecran,h);
		
		//Creating the link to the previous room.
		this.addDoorToPrevRoom(l);
		
		//Adding a door to the next room
		this.addDoor(o, true);
		
		this.generateImage();
	}

	/**
	 * This method fills the room with the standards
	 * tiles of the room.
	 * It does not add the links to other rooms
	 * nor does it modify the tiles accordingly.
	 */
	@Override
	protected void generateRoom() {
		//Creating the array
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		//Filling the empty bits with black tiles
		super.fillEmptyWithVoid();			
	}
	
	/**
	 * Creates the tiles for the door and sort of a "bridge" 
	 * to the center of the room
	 */
	@Override
	public void addDoor(Orientation o, boolean enabled){
		Vecteur v = porte.get(o);
		
		int x = cases.length;
		int height = cases[0].length;
		int i;
		
		//We change the tiles accordingly.
		switch(o){
		case NORD:
			for(i=(int)v.y;i<height/2;i++){
				cases[(int)v.x][i] = new Case_dalle_sol();
			}
			if(cases[(int)v.x][i] instanceof Case_void){
				cases[(int)v.x][i]=new Case_mur();
			}
			break;
		case SUD:
			for(i=(int)v.y;i>height/2;i--){
				cases[width][i] = new Case_dalle_sol();
			}
			break;
		case OUEST:
			for(i=(int)v.x;i<width/2;i++){
				cases[i][height] = new Case_dalle_sol();
				cases[i][height+1] = new Case_mur();
			}
			break;
		case EST:
			for(i=(int)v.x;i>width/2;i++){
				cases[i][height] = new Case_dalle_sol();
				cases[i][height+1] = new Case_mur();
			}
		}
		cases[(int)v.x][(int)v.y]=new Porte_Dalle_Sol(enabled);
		super.addDoor(o, enabled);
	}
	
	/**
	 * Adds the door to the prev room and a bridge
	 * to the center of the room.
	 */
	protected void addDoorToPrevRoom(Link l){
		Vecteur v = porte.get(Orientation.opposite(l.orientation));
		int width = (int)v.x;
		int height = (int)v.y;
		int i;
		
		//We change the tiles accordingly.
		switch(Orientation.opposite(l.orientation)){
		case NORD:
			for(i=(int)v.y;i<height;i++){
				cases[width][i] = new Case_dalle_sol();
			}
			if(cases[width][i] instanceof Case_void){
				cases[width][i]=new Case_mur();
			}
			break;
		case SUD:
			for(i=(int)v.y;i>height;i--){
				cases[width][i] = new Case_dalle_sol();
			}
			break;
		case OUEST:
			for(i=(int)v.x;i<width;i++){
				cases[i][height] = new Case_dalle_sol();
				cases[i][height+1] = new Case_mur();
			}
			break;
		case EST:
			for(i=(int)v.x;i>width;i++){
				cases[i][height] = new Case_dalle_sol();
				cases[i][height+1] = new Case_mur();
			}
		}
		cases[(int)v.x][(int)v.y]=new Porte_Dalle_Sol(true);
	}
	
	
	
	/**
	 * This method sets the different places where the character
	 * will be placed when it comes from another room to this one
	 */
	@Override
	protected void setDestinationPlaces() {
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
	protected void setDoorPlaces() {
		this.porte=new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.porte.put(Orientation.NORD, new Vecteur(cases.length/2,0));
		this.porte.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-1));
		this.porte.put(Orientation.EST, new Vecteur(cases.length-1,cases[0].length/2));
		this.porte.put(Orientation.OUEST, new Vecteur(0,cases[0].length/2));
	}

}
