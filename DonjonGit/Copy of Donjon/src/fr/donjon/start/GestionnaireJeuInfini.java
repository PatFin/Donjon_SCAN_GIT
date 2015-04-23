package fr.donjon.start;

import java.util.EnumMap;
import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.salles.Salle_foret;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuInfini extends Gestionnaire {

	/**
	 * Constructeur
	 * @param game
	 */
	public GestionnaireJeuInfini(GamePanel game) {
		super(game);
		
		//On créé la liste des salles à utiliser dans le donjon
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		
		//On créé la première salle
		SalleAbs firstRoom = new Salle_croix(game.ecran, new Heros(0,0), Orientation.random());
		currentRoom = firstRoom;
		//On initialise la liste des salles avec la première
		this.listeSalles = new LinkedList<SalleAbs>();
		this.listeSalles.add(firstRoom);
	}

	@Override
	public void createNextRoom(Link l) {
		// TODO Auto-generated method stub
		
		//On créé la salle
		Orientation a=Orientation.opposite(l.orientation);
		SalleAbs s = createRandomNewRoom(l.origineSalle.hero, l);
		l.setDestination(s, s.destination.get(a));
		
		//On ajoute les portes
		
		//On vérifie combien de liens vers d'autres salles sont disponibles
		LinkedList<Link> nord=getAvailableDoors(Orientation.NORD);
		LinkedList<Link> sud=getAvailableDoors(Orientation.SUD);
		LinkedList<Link> est=getAvailableDoors(Orientation.EST);
		LinkedList<Link> ouest=getAvailableDoors(Orientation.OUEST);
		
		EnumMap<Orientation, LinkedList<Link>> d = new EnumMap<Orientation, LinkedList<Link>>(Orientation.class);
		
		int total = nord.size()+est.size()+ouest.size()+sud.size();
		//On décide du nombre de porte à créé et combien vers des salles qui existent déjà.
		if(total==0){
			//We create three links to new rooms.
			switch(a){
			case NORD:
				s.addDoor(Orientation.EST, true);
				s.addDoor(Orientation.SUD,true);
				s.addDoor(Orientation.OUEST,true);
				break;
			case SUD:
				s.addDoor(Orientation.EST, true);
				s.addDoor(Orientation.NORD,true);
				s.addDoor(Orientation.OUEST,true);
				break;
			case EST:
				s.addDoor(Orientation.NORD, true);
				s.addDoor(Orientation.SUD,true);
				s.addDoor(Orientation.OUEST,true);
				break;
			case OUEST:
				s.addDoor(Orientation.EST, true);
				s.addDoor(Orientation.SUD,true);
				s.addDoor(Orientation.NORD,true);
			}
			
		}else if(total >6){
			//We create a dead end, no doors added
		}else{
			//We add a random number of rooms.
			//the first one to a new room.
			//the next one(s) to an existing room.
			int r=(int)(3*Math.random());
			
			switch(r){
			case 0:
				break;
			case 1:
				s.addDoor(Orientation.random(a), true);
				break;
			case 2:
				//Adding an available door in the direction of the least number of available ones.
				
				
				//Now door to existing room in the second but least number of available rooms. 
				
				break;
			case 3:
				//Adding an available door in the direction of the least number of available ones.
				
				//Now door to existing room in the the two other directions. 
			}
		}
	}

	/**
	 * Gives the list of all the available doors in the orientation given.
	 * @param o the orientation of the doors
	 * @return linkedlist of links that have the orientation given in parameter.
	 */
	private LinkedList<Link> getAvailableDoors(Orientation o){
		LinkedList<Link> a = new LinkedList<Link>();
		
		//we go through all the rooms ans count the available doors that have no destination yet.
		for(int i=0;i<listeSalles.size();i++){
			
			Link z=listeSalles.get(i).link.get(o);
			
			//We avoid a nullPointerException
			if(z!=null){
				//If the link has no destination yet.
				if(!z.hasDestination()){
					a.add(z);
				}
			}
		}
		
		return a;
	}
	
}
