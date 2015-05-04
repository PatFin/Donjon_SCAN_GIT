package fr.donjon.start;

import java.util.EnumMap;
import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.Castle_Room;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.classes.salles.Salle_croix;
import fr.donjon.classes.salles.Salle_foret;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class GestionnaireJeuInfini extends Gestionnaire {

	/**
	 * Constructeur
	 * @param game
	 * @throws CustomException 
	 */
	public GestionnaireJeuInfini(GamePanel game) throws CustomException {
		super(game);
		
		//On cr�� la liste des salles � utiliser dans le donjon
		this.sallesDisponibles = new LinkedList<SalleAbs>();
		sallesDisponibles.add(new Salle_foret());
		sallesDisponibles.add(new Castle_Room());
		sallesDisponibles.add(new Salle_croix());
		
		//On cr�� la premi�re salle
		SalleAbs firstRoom = new Salle_croix(game.ecran, new Heros(0,0), Orientation.random());
		currentRoom = firstRoom;
		//On initialise la liste des salles avec la premi�re
		this.listeSalles = new LinkedList<SalleAbs>();
		this.listeSalles.add(firstRoom);
	}

	@Override
	public void createNextRoom(Link l) {
		
		//On cr�� la salle
		Orientation a=Orientation.opposite(l.orientation);
		SalleAbs s = null;
		try {
			s = createRandomNewRoom(l.origineSalle.hero, l);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		l.setDestination(s, s.destination.get(a));
		
		//On ajoute les portes
		
		//On v�rifie combien de liens vers d'autres salles sont disponibles
		LinkedList<Link> nord=getAvailableDoors(Orientation.NORD);
		LinkedList<Link> sud=getAvailableDoors(Orientation.SUD);
		LinkedList<Link> est=getAvailableDoors(Orientation.EST);
		LinkedList<Link> ouest=getAvailableDoors(Orientation.OUEST);
		
		EnumMap<Orientation, LinkedList<Link>> d = new EnumMap<Orientation, LinkedList<Link>>(Orientation.class);
		
		//This array contains the orientation of increasing number of doors.
		Orientation[] order = new Orientation[4];
		order[0] = Orientation.NORD;
		order[1] = Orientation.SUD;
		order[2] = Orientation.EST;
		order[3] = Orientation.OUEST;
		
			//Quicksort algorithm
		for(int j=0; j<order.length-1;j++){
            for(int i=0;i<order.length-j-1;i++){
                
                try{
                	if(d.get(order[i]).size()>d.get(order[i+1]).size()){
                        Orientation temp = order[i];
                        order[i]=order[i+1];
                        order[i+1]=temp;
                    }
                }catch(Exception e){
                	i++;
                }
            	
                
            }
        }
		
		
		int total = nord.size()+est.size()+ouest.size()+sud.size();
		System.out.println("Le nombre total de portes dispo est: "+total);
		System.out.println("NORD:"+nord.size());
		System.out.println("SUD:"+sud.size());
		System.out.println("EST:"+est.size());
		System.out.println("OUEST:"+ouest.size());
		
		
		//On d�cide du nombre de porte � cr�� et combien vers des salles qui existent d�j�.
		if(total==0){
			//We create three links to new rooms.
			switch(a){
			case NORD:
				s.addDoor(Orientation.EST, false);
				s.addDoor(Orientation.SUD,false);
				s.addDoor(Orientation.OUEST,false);
				break;
			case SUD:
				s.addDoor(Orientation.EST, false);
				s.addDoor(Orientation.NORD,false);
				s.addDoor(Orientation.OUEST,false);
				break;
			case EST:
				s.addDoor(Orientation.NORD, false);
				s.addDoor(Orientation.SUD,false);
				s.addDoor(Orientation.OUEST,false);
				break;
			case OUEST:
				s.addDoor(Orientation.EST, false);
				s.addDoor(Orientation.SUD,false);
				s.addDoor(Orientation.NORD,false);
			}
			
		}else if(total >10){
			//We create a dead end, no doors added
		}else{
			//TODO correct the link to prev room as it is not currently working.
			//We add a random number of rooms.
			//the first one to a new room.
			//the next one(s) to an existing room.
			int r=(int)(3*Math.random());
			int z;
			
			switch(r){
			case 0:
				break;
			case 1:
				z=0;
				if(order[z]==a){
					z++;
				}
				s.addDoor(order[z], false);
				break;
			case 2:
				//Adding a door in the direction of the least number of available ones.
				z=0;
				if(order[z]==a){
					z++;
				}
				s.addDoor(order[z], false);
				
				//Now door to existing room in the second but least number of available rooms.
				//We always take the eldest room to create the link (get(0))
				if(order[z+1]==a){
					z++;
				}
				
				if(d.get(Orientation.opposite(order[z+1]))!=null){
					s.addDoorToPrevRoom(d.get(order[z+1]).get(0));
				}else{
					s.addDoor(order[z+1], false);
				}
				
				break;
			case 3:
				//Adding an available door in the direction of the least number of available ones.
				//first door
				z=0;
				if(order[z]==a){
					z++;
				}
				s.addDoor(order[z], false);
				
				//Now 2 doors to existing room in the the two other directions.
				
				//second door
				if(order[z+1]==a){
					z++;
				}
				
				if(d.get(Orientation.opposite(order[z+1]))!=null){
					s.addDoorToPrevRoom(d.get(order[z+1]).get(0));
				}else{
					s.addDoor(order[z+1], false);
				}
				//third door
				if(order[z+2]==a){
					z++;
				}
				if(d.get(Orientation.opposite(order[z+2]))!=null){
					s.addDoorToPrevRoom(d.get(order[z+2]).get(0));
				}else{
					s.addDoor(order[z+2], false);
				}
				
				
			}//end switch
			
		}//end adding random number of room
		
		//we create the image an add the room to the list of rooms.
		s.generateImage();
		listeSalles.add(s);
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
