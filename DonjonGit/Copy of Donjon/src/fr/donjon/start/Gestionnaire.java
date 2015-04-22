/**
 * 
 */
package fr.donjon.start;

import java.util.LinkedList;

import fr.donjon.classes.Heros;
import fr.donjon.classes.salles.SalleAbs;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * Permet la gestion des salles
 * 
 * @author Baptiste
 *
 */
public abstract class Gestionnaire implements EcouteurClavier {

	GamePanel game;									//Le jeu qui gère l'affichage de la Salle.
	public LinkedList<SalleAbs> listeSalles;		//La liste des salles du donjon.
	public LinkedList<SalleAbs> sallesDisponibles;	//La liste des salles dans lesquelles on peut piocher pour générer la salle suivante.
	public SalleAbs currentRoom;					//La salle actuelle.
	
	public Gestionnaire(GamePanel game) {
		this.game = game;
	}
	
	
	/**
	 * Method called when the link has no destination set yet.
	 * It creates a new room selected randomly in the rooms available.
	 * @param l the link which destination is the new room.
	 */
	public void createNextRoom(Link l){
		Orientation a = Orientation.opposite(l.orientation);
		SalleAbs s = createRandomNewRoom(l.origineSalle.hero, l, Orientation.random(a));
		l.setDestination(s, s.destination.get(a));
		listeSalles.add(s);
	}
	
	/**
	 * Renvoie la salle dans laquelle le héro se trouve actuellement
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
	 */
	protected SalleAbs createRandomNewRoom(Heros h, Link l,Orientation o){
		int r = (int) (42*Math.random()*sallesDisponibles.size())%sallesDisponibles.size();
		return sallesDisponibles.get(r).clone(h, l, o);
	}
	
	
	
	public abstract void changerDeSalle(Link l);
	
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
		
		//test if the current room needs to be changed.
		Orientation o = this.mustChange();
		if(o != null){
			changerDeSalle(currentRoom.link.get(o));
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
