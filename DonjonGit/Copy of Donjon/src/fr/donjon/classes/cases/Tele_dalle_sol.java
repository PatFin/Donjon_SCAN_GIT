package fr.donjon.classes.cases;

import fr.donjon.classes.Personnage;
import fr.donjon.utils.Vecteur;

public class Tele_dalle_sol extends Case_dalle_sol {

	Vecteur destination;		//Contient les coordonnées de la case de destination
	boolean enabled;
	
	/**
	 * Constructeur
	 * @param destination vecteur contenant les coordonnées de la case de destination
	 */
	public Tele_dalle_sol(Vecteur destination, boolean enabled) {
		//On appelle le constructeur de la classe mère
		super();
		
		//On stocke le vecteur qui indique où le héro sera déplacé
		this.destination = destination;
		this.enabled=enabled;
	}
	
	/**
	 * Si le personnage marche sur cette case, alors on le place sur la case de destination
	 */
	@Override
	public void inCollision(Personnage p){
		if(enabled){
			p.setLocation(destination);
		}
		//else, on ne fait rien, le personnage marche sur la case librement.
	}

	/**
	 * Mutateur
	 * @param v Vecteur vers la nouvelle case de destination
	 */
	public void setDestination(Vecteur v){
		this.destination = v;
	}
	
	/**
	 * Autorise le transfer sur la case destination
	 */
	public void enableTeleportaion(){
		this.enabled=true;
	}
	
	public void disableTeleportation(){
		this.enabled=false;
	}
	
}
