package fr.donjon.classes;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_escalier;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_rocher;
import fr.donjon.test.SalleAbs;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;


public class Castle_Room extends SalleAbs {


	/**
	 * @param p Le hero controlé par le joueur.
	 * @param ecran L'écran de jeu.
	 * @param lien le lien de lasalle précédente vers celle-ci.
	 */
	public Castle_Room(Rectangle ecran, Heros h, Orientation o) {
		super(ecran, h);
		
		this.addDoor(o, true);
		
		this.generateImage();
		
	}
	
	
	public Castle_Room(Heros h, Link l, Orientation o){
		super(SalleAbs.ecran, h);
		
		this.addDoorToPrevRoom(l);
		
		this.addDoor(o, true);
		
		this.generateImage();
	}
	
	
	@Override
	public void addDoor(Orientation o, boolean enabled){
		Vecteur v = porte.get(o);
		
		//We change the tiles depending on the orientation of the door.
		switch(o){
		case NORD:
			this.cases[(int)v.x][(int)v.y] = new Case_escalier();
			this.cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
			this.cases[(int)v.x][(int)v.y+2] = new Case_dalle_sol();
			break;
		case SUD:
			this.cases[(int)v.x][(int)v.y] = new Case_escalier();
			this.cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
			break;
		case OUEST:
			this.cases[(int)v.x][(int)v.y] = new Case_fendue_sol();
			this.cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
			break;
		case EST:
			this.cases[(int)v.x][(int)v.y] = new Case_fendue_sol();
			this.cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
		}
		
		//The link is created in the superclass (SalleAbs)
		super.addDoor(o, enabled);
	}
	
	@Override
	protected void addDoorToPrevRoom(Link l) {
		//We change the tiles of the door.
		Vecteur v = porte.get(Orientation.opposite(l.orientation));
		
		switch(Orientation.opposite(l.orientation)){
		case NORD:
			this.cases[(int)v.x][(int)v.y] = new Case_escalier();
			this.cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
			break;
		case SUD:
			this.cases[(int)v.x][(int)v.y] = new Case_escalier();
			this.cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
			break;
		case OUEST:
			this.cases[(int)v.x][(int)v.y] = new Case_fendue_sol();
			this.cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
			break;
		case EST:
			this.cases[(int)v.x][(int)v.y] = new Case_fendue_sol();
			this.cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
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
				int random = (int)(Math.round(6*Math.random()));

				if(random == 0){
					cases[x][y]=new Case_rocher();	
				}else if(random == 1){
					cases[x][y]=new Case_fendue_sol();
				}else {						
					cases[x][y]=new Case_dalle_sol();
				}
			}
		}
		
		//Filling the sides with black tiles.
		super.fillEmptyWithVoid();
	}

	@Override
	protected void setDestinationPlaces() {
		// TODO Auto-generated method stub
		this.destination = new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		destination.put(Orientation.NORD, new Vecteur(cases.length/2-2,1));
		destination.put(Orientation.SUD, new Vecteur(cases.length/2+3, cases[0].length-2));
		destination.put(Orientation.OUEST, new Vecteur(1,cases[0].length/2+2));
		destination.put(Orientation.EST, new Vecteur(cases.length-2,cases[0].length/2-1));
	}

	@Override
	protected void setDoorPlaces() {
		this.porte = new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		porte.put(Orientation.NORD,new Vecteur(cases.length/2-2, 0));
		porte.put(Orientation.SUD,new Vecteur(cases.length/2+3, cases[0].length-1));
		porte.put(Orientation.EST, new Vecteur(cases.length-1,cases[0].length/2-1));
		porte.put(Orientation.OUEST, new Vecteur(0,cases[0].length/2+2));
	}

}