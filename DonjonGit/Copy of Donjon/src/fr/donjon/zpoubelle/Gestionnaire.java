/**
 * 
 */
package fr.donjon.zpoubelle;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.SalleAbs;

/**
 * Permet la gestion des salles
 * 
 * @author Baptiste
 *
 */
public abstract class Gestionnaire implements EcouteurClavier {

	GamePanel game;									//Le jeu qui g�re l'affichage de la Salle.
	public LinkedList<SalleAbs> listeSalles;		//La liste des salles du donjon.
	public LinkedList<SalleAbs> sallesDisponibles;	//La liste des salles dans lesquelles on peut piocher pour g�n�rer la salle suivante.
	public SalleAbs currentRoom;					//La salle actuelle.
	
	public Gestionnaire(GamePanel game) {
		this.game = game;
	}
	
	
	/**
	 * Method called when the link has no destination set yet.
	 * It creates a new room selected randomly in the available rooms.
	 * @param l the link which destination is the new room.
	 */
	public abstract void createNextRoom(Link l);
	
	/**
	 * Renvoie la salle dans laquelle le h�ro se trouve actuellement
	 * @return 
	 */
	public SalleAbs getCurrentRoom(){
		return currentRoom;
	}
	
	/**
	 * Selects randomly a room in the available list of rooms and returns a clone of it.
	 * @param h the hero the player controls
	 * @param l the link to the previous room
	 * @param o the orientation to the next room.
	 * @return
	 * @throws CustomException 
	 */
	protected SalleAbs createRandomNewRoom(Heros h, Link l) throws CustomException{
		int r = (int) (42*Math.random()*sallesDisponibles.size())%sallesDisponibles.size();
		return sallesDisponibles.get(r).clone(h, l);
	}
	
	
	
	public void changerDeSalle(Link l){
		if(!l.hasDestination()){
			createNextRoom(l);
		}
		Vecteur v = l.destinationSalle.destination.get(Orientation.opposite(l.orientation));
		l.destinationSalle.hero.setLocation(v);
		this.currentRoom = l.destinationSalle;
	}
	
	/**
	 * Gives the orientation of the door on which the player is stepping on (if door availabe)
	 * @return null if we don't have to change of room, the orientation of the door in the room otherwise.
	 */
	public Orientation mustChange() {
		
		Link[] l=new Link[4];
		l[0]=currentRoom.link.get(Orientation.NORD);
		l[1]=currentRoom.link.get(Orientation.SUD);
		l[2]=currentRoom.link.get(Orientation.EST);
		l[3]=currentRoom.link.get(Orientation.OUEST);
		
		int i;
		for(i=0; i<l.length;i++){
			if(l[i]!=null){
				if(currentRoom.hero.enCollision(l[i].rectangleCollision) && l[i].enabled){
					break;
				}
			}
		}
		
		switch(i){
		case 0:
			return Orientation.NORD;
		case 1:
			return Orientation.SUD;
		case 2:
			return Orientation.EST;
		case 3:
			return Orientation.OUEST;
		default:
			return null;
		}
		
		
	} 
	
	
	
	
	/**
	 * Updates the current room and checks the need for change of Room
	 * @param temps parameter used for the objects in the room.
	 */
	public void update(long temps) {
		this.currentRoom.update(temps);
		
		//tests if all the enemies have been killed. If so enable all the doors of the room.
		if(allEnnemiesKilled()){
			enableAllDoors();
		}
		
		//test if the current room needs to be changed.
		Orientation o = this.mustChange();
		if(o != null){
			changerDeSalle(currentRoom.link.get(o));
		}
		
	}
	
	/**
	 * This method checks if all the enemies in the room have been killed or not.
	 * @return true if all the enemies are dead (they don't appear in the list of characters of the current room), false otherwise.
	 */
	protected boolean allEnnemiesKilled(){
		boolean b=true;
		for(int i=0; i<currentRoom.personnage.size();i++){
			if(currentRoom.personnage.get(i).type == Type.ENNEMI){
				b=false;
				//System.out.println("There are "+currentRoom.personnage.size()+" characters in the room.");
				break;
			}
		}
		
		return b;
	}
	
	/**
	 * Enables all the door of the current room
	 */
	protected void enableAllDoors(){
		enableDoor(Orientation.NORD);
		enableDoor(Orientation.SUD);
		enableDoor(Orientation.EST);
		enableDoor(Orientation.OUEST);
	}
	
	/**
	 * Enables the door of the current room as the orientation given in parameter
	 * @param o the position of the door to be enabled in the current room.
	 */
	protected void enableDoor(Orientation o){
		//we check if the link exists
		Link l = this.currentRoom.link.get(o);
		if(l!=null){
			
			//The link is enabled.
			l.enabled=true;
			
			//The tile is enabled.
			Vecteur v = currentRoom.porte.get(o);
			currentRoom.getCase(v).enablePassage();
		}
		
		
	}
	
	/////////////////////////////////////
	//INTERFACE ECOUTEUR CLAVIER/////////
	/////////////////////////////////////


	@Override
	public void attaque(Orientation o) {
		this.currentRoom.attaque(o);
	}

	@Override
	public void stopAttaque() {
		this.currentRoom.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		this.currentRoom.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		this.currentRoom.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		//Nothing to do, its done in game panel
	}

	@Override
	public void stopDeplacement() {
		this.currentRoom.stopDeplacement();
	}






	
}
