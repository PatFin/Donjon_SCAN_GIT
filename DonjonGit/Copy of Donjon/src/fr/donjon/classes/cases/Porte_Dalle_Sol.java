package fr.donjon.classes.cases;

public class Porte_Dalle_Sol extends Case_porte {

	final static String ImageName = "Case_dalle.png";
	
	
	public Porte_Dalle_Sol( boolean enabled) {
		super(ImageName, enabled);
	}

	@Override
	public Case clone() {
		return new Porte_Dalle_Sol(false);
	}
}
