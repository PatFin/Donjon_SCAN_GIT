/**
 * 
 */
package fr.donjon.test;

import java.util.LinkedList;

import fr.donjon.classes.Link;
import fr.donjon.start.GamePanel;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * Permet la gestion des salles
 * 
 * @author Baptiste
 *
 */
public abstract class Gestionnaire implements EcouteurChangementSalle, EcouteurClavier {

	GamePanel game;								//Le jeu qui gère l'affichage de la Salle
	public LinkedList<SalleAbs> listeSalles;	//La liste des salles du donjon
	public SalleAbs currentRoom;				//La salle actuelle
	
	public Gestionnaire(GamePanel game) {
		this.game = game;
		
	}
	
	
	
	
	
	/**
	 * Method called when the link has no destination set yet.
	 * It creates a new room or makes a link to an existing one.
	 * @param l the link which needs a destination
	 */
	public abstract void createNextRoom(Link l);
	
	/**
	 * Renvoie la salle dans laquelle le héro se trouve actuellement
	 * @return 
	 */
	public SalleAbs getCurrentRoom(){
		return currentRoom;
	}
	
	
	/////////////////////////////////////
	//INTERFACE CHANGEMENT SALLE/////////
	/////////////////////////////////////
	
	
	@Override
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
		
		//TODO écrire le test "si doit changer de salle alors changer de salle".
		
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
		// TODO Auto-generated method stub
		this.currentRoom.stopDeplacement();
	}






	
}
