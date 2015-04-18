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
	public SalleAbs currentRoom;						//La salle actuelle
	
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
	public boolean mustChange() {
		boolean b=false;
		
		
		Link[] l=new Link[4];
		l[0]=currentRoom.link.get(Orientation.NORD);
		l[1]=currentRoom.link.get(Orientation.SUD);
		l[2]=currentRoom.link.get(Orientation.EST);
		l[3]=currentRoom.link.get(Orientation.OUEST);
		
		for(int i=0; i<l.length;i++){
			if(l[i]!=null){
				if(currentRoom.hero.enCollision(l[i].rectangleCollision) && l[i].enabled){
					b=true;
					break;
				}
			}
		}
		
		return b;
	} 
	
	
	
	
	/////////////////////////////////////
	//INTERFACE ECOUTEUR CLAVIER/////////
	/////////////////////////////////////


	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		this.currentRoom.attaque(o);
	}

	@Override
	public void stopAttaque() {
		// TODO Auto-generated method stub
		this.currentRoom.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		this.currentRoom.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		this.currentRoom.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		//Nothing to do, its done in game panel
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		this.currentRoom.stopDeplacement();
	}
	
}
