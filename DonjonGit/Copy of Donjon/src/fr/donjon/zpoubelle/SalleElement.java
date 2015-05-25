package fr.donjon.zpoubelle;


/**
 * Cette classe repr�sente une Salle est ses liens vers les autres Salles
 * 
 * @author Baptiste
 *
 */
public class SalleElement {

	private SalleElement bas;		//
	private SalleElement droite;	//			  droite
	private SalleElement gauche;	//L'�l�ment a gauche
	private SalleElement haut;		//
	
	private Salle salle;			//Salle contenue dans le SalleElement

	public SalleElement(Salle s) {
		this.salle = s;
	}

	//GETTERS SETTERS
	
	public SalleElement getBas() {
		return bas;
	}

	public SalleElement getDroite() {
		return droite;
	}

	public SalleElement getGauche() {
		return gauche;
	}

	public SalleElement getHaut() {
		return haut;
	}

	public Salle getSalle() {
		return salle;
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

	/**
	 * Permet de v�rifier s'il y a une salle au dessus de la salle actuelle
	 * @return
	 */
	public boolean hasHaut(){
		return ( this.getHaut() == null ? false : true );
	}

	public boolean hasSalle(){
		return this.getSalle() == null ? false : true;
	}

	public void setBas(SalleElement bas) {
		this.bas = bas;
		bas.haut = this;
	}
	public void setDroite(SalleElement droite) {
		this.droite = droite;
		droite.gauche = this;
	}
	/**
	 * Met a jour le SalleElement a gauche et met a jour le lien
	 * 
	 * @param gauche
	 */
	public void setGauche(SalleElement gauche) {
		this.gauche = gauche;
		gauche.droite = this;
	}
	public void setHaut(SalleElement haut) {
		this.haut = haut;
		haut.bas = this;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	
	

}