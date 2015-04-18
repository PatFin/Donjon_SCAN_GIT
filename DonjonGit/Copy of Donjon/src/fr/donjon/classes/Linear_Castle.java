package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import fr.donjon.classes.cases.Case;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Linear_Castle {
	
	public Salle firstRoom;
	public Salle currentRoom;
	public Heros hero;
	
	/**
	 * We start the castle with a hero and the size of the screen.
	 * @param p
	 * @param ecran
	 */
	public Linear_Castle(Heros p, Rectangle ecran) {
		this.firstRoom = new  Castle_Room(p, ecran,null, 1, Orientation.OUEST);	
		this.currentRoom=firstRoom;
		this.hero = p;
	}
	
	/**
	 * Changes the current room.
	 * The new current room will be the room s.
	 * @param s The new current room.
	 */
	public void changeRoom(Link l){
		hero.setLocation(l.dx*Case.TAILLE, l.dy*Case.TAILLE);
		this.currentRoom = l.destination;
		System.out.println("You are now in room number: "+this.currentRoom.roomNumber);
		System.out.println("Your character coordinates are: "+hero.collisionDecor.x/Case.TAILLE+","+hero.collisionDecor.y/Case.TAILLE);
	}
	
	/**
	 * Method called in the game class that updates ONLY the current room.
	 * @param temps
	 */
	public void update(long temps){
		//On update la salle.
		currentRoom.update(temps);
		//On vérifie si le héro doit être changé de salle ou pas
		for(int i=0; i<currentRoom.liens.size(); i++){
			Link z = currentRoom.liens.get(i);
			
			if(z.mustChangeRoom(hero)){
				System.out.println("Some fancy stuff happens.");
				//Send the hero to the next room ...
				
				//Create a new room and send the hero to it!
				if(!z.hasDestination()){
					System.out.println("Creation of a new room!");
					
					Salle s = new Castle_Room(hero, currentRoom.ecran, z, 1, Orientation.NORD);
					Vecteur v = s.portes.get(Orientation.SUD);
					
					z.setDestination(s, v.x, v.y-1);
				}
				
				//Send the hero
				this.changeRoom(z);
				break;
			}
		}
	}
	
	/**
	 * Draws the current Room.
	 * @param t time parameter.
	 * @param g graphical destination.
	 */
	public void draw(long t, Graphics g){
		this.currentRoom.draw(t, g);
	}
}
