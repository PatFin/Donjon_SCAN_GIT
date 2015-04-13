package fr.donjon.classes;

public class Case_void extends Case_Obstacle {

	final static String ImageName = "Ressources/Images/Black.png";
	
	public Case_void() {
		super(ImageName);
	}
	
	@Override
	public Case clone() {
		// TODO Auto-generated method stub
		return new Case_void();
	}
	
	
}
