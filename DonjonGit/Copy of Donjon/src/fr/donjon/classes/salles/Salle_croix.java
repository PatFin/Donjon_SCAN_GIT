package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.classes.cases.Porte_Dalle_Sol;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Salle_croix extends SalleAbs {

	/**
	 * Constructor called when this is the first room of the dungeon
	 * @param ecran contains the space available to the room
	 * @param h the hero controlled by the player
	 * @param o the orientation of the door to be placed
	 * @throws CustomException 
	 */
	public Salle_croix(Rectangle ecran, Heros h, Orientation o) throws CustomException {
		super(ecran, h);
		
		generateRoom();
		setDoorPlaces();
		
		//Adding the doors to the next room
		this.addDoor(o, true);
				
		//Generating the image of the room after all the tiles have been set.
		this.generateImage();
		
		//Placing the character in the middle of the room.
		Vecteur v = this.getCenter();
		hero.setLocation((int)v.x*Case.TAILLE, (int)v.y*Case.TAILLE);

	}
	
	/**
	 * Constructor called when the character comes from another room
	 * @param h the hero controlled by the player
	 * @param l the link from the previous room to this one
	 * @throws CustomException 
	 */
	public Salle_croix(Heros h, Link l) throws CustomException{
		super(SalleAbs.ecran,h);
		
		generateRoom();
		setDoorPlaces();
		
		//Creating the link to the previous room.
		this.addDoorToPrevRoom(l);
		
		
		this.generateEnnemis();
		
		this.generateImage();
	}
	
	/**
	 * Empty constructor
	 */
	public Salle_croix(){
		super();
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
		changeTilesTo(o);
		
		Vecteur v = this.porte.get(o);
		cases[(int)v.x][(int)v.y]=new Porte_Dalle_Sol(true);
		super.addDoor(o, enabled);
	}
	
	/**
	 * Intermediate method which makes it easier to call in the addDoor
	 * and addDoorToPrevRoom methods.
	 * @param o The orientation of the door to be created.
	 */
	private void changeTilesTo(Orientation o){
		switch(o){
		case NORD:
			this.doorNORD();
			break;
		case SUD:
			this.doorSUD();
			break;
		case EST:
			this.doorEST();
			break;
		case OUEST:
			this.doorOUEST();
		}
	}
	
	
	/**
	 * Adds the door to the prev room and a bridge
	 * to the center of the room.
	 */
	public void addDoorToPrevRoom(Link l){
		changeTilesTo(Orientation.opposite(l.orientation));
		
		Vecteur v = this.porte.get(Orientation.opposite(l.orientation));
		cases[(int)v.x][(int)v.y]=new Porte_Dalle_Sol(true);
		super.addDoorToPrevRoom(l);
	}
	

	/**
	 * This method sets the positions of the potential doors of the room.
	 */
	@Override
	protected void setDoorPlaces() {
		this.porte=new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		int x = 1+(int)(Math.random()*(cases.length-2));
		int y = 1+(int)(Math.random()*(cases[0].length-2));
		
		this.porte.put(Orientation.NORD, new Vecteur(x,0));
		this.porte.put(Orientation.SUD, new Vecteur(x,cases[0].length-1));
		this.porte.put(Orientation.EST, new Vecteur(cases.length-1,y));
		this.porte.put(Orientation.OUEST, new Vecteur(0,y));
		
		this.destination= new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.destination.put(Orientation.NORD, new Vecteur(x,1));
		this.destination.put(Orientation.SUD, new Vecteur(x,cases[0].length-2));
		this.destination.put(Orientation.OUEST, new Vecteur(1,y));
		this.destination.put(Orientation.EST, new Vecteur(cases.length-2,y));
	}
	
	
	/**
	 * This method changes the tiles to the northern door.
	 */
	private void doorNORD(){
		Vecteur c = getCenter();
		int i;
		for(i=(int)c.y; i>porte.get(Orientation.NORD).y; i--){
			cases[(int)c.x][i] = new Case_dalle_sol();
		}
		if(cases[(int)c.x][i] instanceof Case_void){
			cases[(int)c.x][i] = new Case_mur();
		}
	}
	
	/**
	 * This method changes the tiles to the southern door.
	 */
	private void doorSUD(){
		Vecteur c = getCenter();
		
		for(int i=(int)c.y; i<porte.get(Orientation.SUD).y; i++){
			cases[(int)c.x][i] = new Case_dalle_sol();
		}
	}
	
	
	/**
	 * This method changes the tiles to the eastern door.
	 */
	private void doorEST(){
		Vecteur c = getCenter();
		int i;
		for(i=(int)c.x; i<porte.get(Orientation.EST).x; i++){
			cases[i][(int)c.y] = new Case_dalle_sol();
			
			if(cases[i][(int)c.y+1] instanceof Case_void){
				cases[i][(int)c.y+1] = new Case_mur();
			}
		}
		if(cases[i][(int)c.y+1] instanceof Case_void){
			cases[i][(int)c.y+1] = new Case_mur();
		}
	}
	
	/**
	 * This method changes the tiles to the western door.
	 */
	private void doorOUEST(){
		Vecteur c = getCenter();
		
		int i;
		for(i= (int)c.x; i>porte.get(Orientation.OUEST).x; i--){
			cases[i][(int)c.y] = new Case_dalle_sol();
			
			if(cases[i][(int)c.y+1] instanceof Case_void){
				cases[i][(int)c.y+1] = new Case_mur();
			}
		}
		if(cases[i][(int)c.y+1] instanceof Case_void){
			cases[i][(int)c.y+1] = new Case_mur();
		}
	}

	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) throws CustomException {
		return new Salle_croix(ecran, h, o);
	}

	@Override
	public SalleAbs clone(Heros h, Link l) throws CustomException {
		return new Salle_croix(h, l);
	}

}
