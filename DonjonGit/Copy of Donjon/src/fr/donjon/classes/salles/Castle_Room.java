package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.CaseLave;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_rocher;
import fr.donjon.classes.cases.Porte_Dalle_Sol;
import fr.donjon.classes.cases.Porte_escalier;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;


public class Castle_Room extends SalleAbs {


	/**
	 * Constructor called when this is the first room of the dungeon
	 * @param ecran contains the space available to the room
	 * @param h the hero controlled by the player
	 * @param o the orientation of the door to be placed
	 */
	public Castle_Room(Rectangle ecran, Heros h, Orientation o) {
		super(ecran, h);
		
		this.addDoor(o, true);
		
		this.generateImage();
		
		//Placing the character in the middle of the room.
		Vecteur v = this.getCenter();
		hero.setLocation((int)v.x*Case.TAILLE, (int)v.y*Case.TAILLE);
	}
	
	/**
	 * @param p Le hero control� par le joueur.
	 * @param lien le lien de lasalle pr�c�dente vers celle-ci.
	 */
	public Castle_Room(Heros h, Link l){
		super(SalleAbs.ecran, h);
		
		this.addDoorToPrevRoom(l);
		
		this.generateEnnemis();
		this.generateImage();
	}
	
	/**
	 * Empty constructor
	 */
	public Castle_Room(){
		super();
	}
	
	@Override
	public void addDoor(Orientation o, boolean enabled){
		Vecteur v = porte.get(o);
		
		//We change the tiles depending on the orientation of the door.
		switch(o){
		case NORD:
			this.cases[(int)v.x][(int)v.y] = new Porte_escalier(enabled);
			this.cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y+2] = new Case_dalle_sol();
			break;
		case SUD:
			this.cases[(int)v.x][(int)v.y] = new Porte_escalier(enabled);
			this.cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
			break;
		case OUEST:
			this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(enabled);
			this.cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y-1] = new Case_mur();
			break;
		case EST:
			this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(enabled);
			this.cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y-1] = new Case_mur();
		}
		
		//The link is created in the superclass (SalleAbs)
		super.addDoor(o, enabled);
	}
	
	@Override
	public void addDoorToPrevRoom(Link l) {
		//We change the tiles of the door.
		Vecteur v = porte.get(Orientation.opposite(l.orientation));
		
		switch(Orientation.opposite(l.orientation)){
		case NORD:
			this.cases[(int)v.x][(int)v.y] = new Porte_escalier(true);
			this.cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y+2] = new Case_dalle_sol();
			break;
		case SUD:
			this.cases[(int)v.x][(int)v.y] = new Porte_escalier(true);
			this.cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
			break;
		case OUEST:
			this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(true);
			this.cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y-1] = new Case_mur();
			break;
		case EST:
			this.cases[(int)v.x][(int)v.y] = new Porte_Dalle_Sol(true);
			this.cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y-1] = new Case_mur();
		}
		
		//Link created in the mother class.
		super.addDoorToPrevRoom(l);
	}
	

	@Override
	protected void generateRoom() {
		
		//Creating the array
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		for(int x=1;x<cases.length-1;x++){
			cases[x][1]=new Case_mur();
		}

		//Filling the room randomly with dalle_fendue / rocher / normale
		for(int y=2;y<cases[0].length-1;y++){
			for(int x=1;x<cases.length-1;x++){
				int random = (int)(Math.round(100*Math.random()));

				if(random <= 5){
					cases[x][y]=new CaseLave();	
				}else if( random <= 15){
					cases[x][y]=new Case_fendue_sol();
				}
				else if(random <= 25){
					cases[x][y]=new Case_rocher();
				}else {						
					cases[x][y]=new Case_dalle_sol();
				}
			}
		}
		
		//Filling the sides with black tiles.
		super.fillEmptyWithVoid();
	}

	@Override
	protected void setDoorPlaces() {
		this.porte = new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		porte.put(Orientation.NORD,new Vecteur(cases.length/2-2, 0));
		porte.put(Orientation.SUD,new Vecteur(cases.length/2+3, cases[0].length-1));
		porte.put(Orientation.EST, new Vecteur(cases.length-1,cases[0].length/2-1));
		porte.put(Orientation.OUEST, new Vecteur(0,cases[0].length/2+2));
		
		this.destination = new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		destination.put(Orientation.NORD, new Vecteur(cases.length/2-2,1));
		destination.put(Orientation.SUD, new Vecteur(cases.length/2+3, cases[0].length-2));
		destination.put(Orientation.OUEST, new Vecteur(1,cases[0].length/2+2));
		destination.put(Orientation.EST, new Vecteur(cases.length-2,cases[0].length/2-1));
	}


	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		return new Castle_Room(ecran, h, o);
	}


	@Override
	public SalleAbs clone(Heros h, Link l) {
		return new Castle_Room(h, l);
	}

}