package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Linear_Castle {
	
	public Salle firstRoom;
	public Salle currentRoom;
	
	/**
	 * We start the castle with a hero and the size of the screen.
	 * @param p
	 * @param ecran
	 */
	public Linear_Castle(Heros p, Rectangle ecran) {
		this.firstRoom = new  Castle_Room(p, ecran, null, this);	
		this.currentRoom=firstRoom;
	}
	
	/**
	 * Changes the current room.
	 * The new current room will be the room s.
	 * @param s The new current room.
	 */
	public void changeRoom(Salle s){
		currentRoom=s;
	}
	
	/**
	 * Method called in the game class that updates ONLY the current room.
	 * @param temps
	 */
	public void update(long temps){
		currentRoom.update(temps);
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
