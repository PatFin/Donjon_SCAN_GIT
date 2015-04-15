package fr.donjon.test;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Salle;
import fr.donjon.classes.cases.Case_escalier;
import fr.donjon.utils.Orientation;

public class SalleInteractive extends Salle{

	public EcouteurChangementSalle ecouteur; 
	
	
	public SalleInteractive(Heros hero, Rectangle ecran, EcouteurChangementSalle ecouteur) {
		super(hero, ecran);
		this.ecouteur = ecouteur;
		this.ajoutDeuxEscalier();
	}
	
	public void ajoutDeuxEscalier(){
		
		this.cases[cases.length/2][1] = new Case_escalier(Orientation.NORD);
		this.cases[cases.length/2][8] = new Case_escalier(Orientation.SUD);
		refreshRoomCases(cases);
	}
	
	@Override
	public void update(long temps) {
		// TODO Auto-generated method stub
		super.update(temps);
		
		if(this.hero.collisionDecor.intersects(cases[cases.length/2][1].collision)){
			ecouteur.changerDeSalle(Orientation.NORD, this.hero);
			hero.setLocation(64*cases.length/2 - 32, 64*7 - 3);
		}if(this.hero.collisionDecor.intersects(cases[cases.length/2][8].collision)){
			ecouteur.changerDeSalle(Orientation.SUD, this.hero);
			hero.setLocation(64*cases.length/2 - 32, 64*2 - (Heros.LNG - hero.collisionDecor.height) +3  );
		}
		
	}
	
	

}
