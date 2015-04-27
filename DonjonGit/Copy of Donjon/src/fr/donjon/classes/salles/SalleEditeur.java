package fr.donjon.classes.salles;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class SalleEditeur extends SalleAbs {

	public SalleEditeur(Rectangle ecran, Heros h) {
		super(ecran, h);
		// TODO Auto-generated constructor stub
	}

	public SalleEditeur() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateRoom() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDoorPlaces() {
		// TODO Auto-generated method stub

	}

	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalleAbs clone(Heros h, Link l) {
		// TODO Auto-generated method stub
		return null;
	}

}
