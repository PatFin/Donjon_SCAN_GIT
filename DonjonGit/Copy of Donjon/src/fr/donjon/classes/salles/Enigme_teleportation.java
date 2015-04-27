package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Porte_Dalle_Sol;
import fr.donjon.classes.cases.Tele_dalle_sol;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Enigme_teleportation extends SalleAbs {
	
	
	
	Orientation porteOrigine;			//Contient l'emplacement de la porte d'origine de la salle
	Orientation porteDestination;		//Contient l'emplacement de la porte destination de la salle
	
	Vecteur v0;
	Vecteur v1;
	Vecteur v2;
	Vecteur v3;
	
	
	public Enigme_teleportation(Heros h, Link l) {
		super(SalleAbs.ecran, h);
		
		this.addDoorToPrevRoom(l);
		
		
		this.porteOrigine = Orientation.opposite(l.orientation);
		
		
		//We do not generate enemies in this room
		
	}

	/**
	 * Empty constructor
	 */
	public Enigme_teleportation() {
	}

	/**
	 * creates the tiles of the room.
	 */
	@Override
	protected void generateRoom() {
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];
		
		Vecteur v;
		
		//On initialise la position des centres de téléporteurs
		//TODO
		
		v=new Vecteur(cases.length/4+1, cases[0].length/4);
		
		cases[(int)v.x-1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x-1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
		
		
		v=new Vecteur(3*cases.length/4-1, cases[0].length/4);
		
		cases[(int)v.x-1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x-1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
		
		v=new Vecteur(cases.length/4+1, 3*cases[0].length/4);
		
		cases[(int)v.x-1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x-1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
		
		
		v=new Vecteur(3*cases.length/4-1, 3*cases[0].length/4);
		
		cases[(int)v.x-1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y-1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x-1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x+1][(int)v.y+1] = new Tele_dalle_sol(null, true);
		cases[(int)v.x][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x+1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x-1][(int)v.y] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y+1] = new Case_dalle_sol();
		cases[(int)v.x][(int)v.y-1] = new Case_dalle_sol();
		
		fillEmptyWithVoid();
		
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
		
		Vecteur p1;
		Vecteur p2;
		switch(Orientation.opposite(l.orientation)){
			case NORD:
				p1=new Vecteur(v.x, v.y+1);
				p2=new Vecteur(v.x, v.y+2);
				break;
			case SUD:
				p1=new Vecteur(v.x, v.y-1);
				p2=new Vecteur(v.x, v.y-2);
				break;
			case OUEST:
				p1=new Vecteur(v.x+1, v.y);
				p2=new Vecteur(v.x+2, v.y);
				break;
			default:
				p1=new Vecteur(v.x-1, v.y);
				p2=new Vecteur(v.x-2, v.y);
		}
		this.cases[(int)p1.x][(int)p1.y] = new Case_dalle_sol();
		this.cases[(int)p2.x][(int)p2.y] = new Tele_dalle_sol(null, true);
	
		
		
		//The link is created in the mother class
		super.addDoorToPrevRoom(l);
	}
	
	
	
	@Override
	protected void setDoorPlaces() {
		this.porte=new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.porte.put(Orientation.NORD, new Vecteur(cases.length/2,0));
		this.porte.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-1));
		this.porte.put(Orientation.EST, new Vecteur(cases.length-1,cases[0].length/2));
		this.porte.put(Orientation.OUEST, new Vecteur(0,cases[0].length/2));
		
		this.destination= new EnumMap<Orientation, Vecteur>(Orientation.class);
		
		this.destination.put(Orientation.NORD, new Vecteur(cases.length/2,1));
		this.destination.put(Orientation.SUD, new Vecteur(cases.length/2,cases[0].length-2));
		this.destination.put(Orientation.OUEST, new Vecteur(1,cases[0].length/2));
		this.destination.put(Orientation.EST, new Vecteur(cases.length-2,cases[0].length/2));

	}
	
	/**
	 * We do not generate enemies in this room.
	 * The method is overridden for safety purposes.
	 */
	@Override
	protected void generateEnnemis() {	
	}
	
	

	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		//cette salle ne peut être la première salle du donjon
		//A la place on met donc une salle type "Castle_Room".
		return new Castle_Room(ecran, h, o);
	}

	@Override
	public SalleAbs clone(Heros h, Link l) {
		return new Enigme_teleportation(h,l);
	}

}
