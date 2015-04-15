package fr.donjon.test;

import fr.donjon.classes.Salle;

public class SalleElement {

	private SalleElement gauche;
	private SalleElement droite;
	private SalleElement bas;
	private SalleElement haut;
	private Salle salle;

	public SalleElement(Salle s) {
		this.salle = s;
	}

	

	//GETTERS SETTERS
	
	public SalleElement getGauche() {
		return gauche;
	}

	public void setGauche(SalleElement gauche) {
		this.gauche = gauche;
		gauche.droite = this;
	}

	public SalleElement getDroite() {
		return droite;
	}

	public void setDroite(SalleElement droite) {
		this.droite = droite;
		droite.gauche = this;
	}

	public SalleElement getBas() {
		return bas;
	}

	public void setBas(SalleElement bas) {
		this.bas = bas;
		bas.haut = this;
	}

	public SalleElement getHaut() {
		return haut;
	}

	public void setHaut(SalleElement haut) {
		this.haut = haut;
		haut.bas = this;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public boolean hasHaut(){
		return ( this.getHaut() == null ? false : true );
	}
	public boolean hasBas(){
		return ( this.getBas() == null ? false : true );
	}
	public boolean hasDroite(){
		return ( this.getDroite() == null ? false : true );
	}
	public boolean hasGauche(){
		return ( this.getGauche() == null ? false : true );
	}
	public boolean hasSalle(){
		return this.getSalle() == null ? false : true;
	}
	
	
	

}