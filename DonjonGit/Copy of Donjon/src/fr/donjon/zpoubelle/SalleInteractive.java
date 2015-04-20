package fr.donjon.zpoubelle;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case_escalier;
import fr.donjon.utils.EcouteurChangementSalle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 * Cette classe est une Salle qui possède un ecouteur de changement de salle, lorsque le joueur marche
 * sur une case escalier l'ecouteur (Gestionnaire) est prévenu qu'il faut changer de salle
 * 
 * @author Baptiste
 *
 */
public class SalleInteractive extends Salle implements EcouteurClavier{

	
	public EcouteurChangementSalle ecouteur; 
	
	
	public SalleInteractive(Heros hero, Rectangle ecran, EcouteurChangementSalle ecouteur) {
		super(hero, ecran);
		this.ecouteur = ecouteur;
		this.ajoutDeuxEscalier();
	}
	
	/**
	 * Ajoute deux escaliers, un au NORD et un au SUD et rafraichit les cases du jeu
	 */
	public void ajoutDeuxEscalier(){
		
		this.cases[cases.length/2][1] = new Case_escalier(Orientation.NORD);
		this.cases[cases.length/2][8] = new Case_escalier(Orientation.SUD);
		refreshRoomCases(cases);
	}
	
	@Override
	public void update(long temps) {
		// TODO Auto-generated method stub
		super.update(temps);
		
		//Gère les collisions avec les escaliers
		//A amélieorer !
		
		if(this.hero.collisionDecor.intersects(cases[cases.length/2][1].collision)){
			ecouteur.changerDeSalle(Orientation.NORD, this.hero);
			hero.setLocation(64*cases.length/2 - 32, 64*7 - 3);
		}if(this.hero.collisionDecor.intersects(cases[cases.length/2][8].collision)){
			ecouteur.changerDeSalle(Orientation.SUD, this.hero);
			hero.setLocation(64*cases.length/2 - 32, 64*2 - (Heros.LNG - hero.collisionDecor.height) +3  );
		}
		
	}
	
	

	/////////////////////////////////////
	//INTERFACE D'ECOUTE/////////////////
	/////////////////////////////////////


	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		hero.attaquer(null, null, o);
	}

	@Override
	public void stopAttaque() {
		hero.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		hero.marcher(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		hero.utiliserObjet(reference);
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		//Nothing to do, its done in game panel
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		hero.stop();
	}
	
	

}
