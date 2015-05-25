package fr.donjon.zpoubelle;

import fr.donjon.utils.Orientation;

/**
 * 
 * Permet la gestion d'une liste de SalleElement qui repr�sente une Salle
 * ainsi que le liens avec les autres Salles
 * 
 * @author Baptiste
 *
 */
public class ListeSalle {

	private SalleElement current;	//La salle actuelle 
	private SalleElement root;		//La premi�re salle cr�e

	/**
	 * Initialisation avec une Salle
	 * @param s
	 */
	public ListeSalle(Salle s){
		this(new SalleElement(s));
	}

	/**
	 * Initialisation avec un SalleElement
	 * @param salleE
	 */
	public ListeSalle(SalleElement salleE) {
		this.root = salleE;
		this.current = root;
	}

	/**
	 * Ajoute une Salle dans une direction et bouge vers cette salle getCurrent() renvoie la nouvelle Salle
	 * 
	 * @param se	L'�l�ment a ajouter
	 * @param o		La direction de la nouvelle salle
	 * 
	 * @return 		Renvoie true si il n'y avait pas de salle a l'endroit ou l'on ins�re la nouvelle salle, renvoie false dans le cas contraire, dans ca cas la salle n'est pas ajout�e;
	 */
	public boolean addElement(SalleElement se, Orientation o){

		Boolean hasWorked = false;

		switch(o){
		case NORD :
			if(!this.current.hasHaut()){
				this.current.setHaut(se);
				hasWorked = true;
			}
			break;
		case SUD :
			if(!this.current.hasBas()){
				this.current.setBas(se);
				hasWorked = true;
			}
			break;
		case EST :
			if(!this.current.hasDroite()){
				this.current.setDroite(se);
				hasWorked = true;
			}
			break;
		case OUEST:
			if(!this.current.hasGauche()){
				this.current.setGauche(se);
				hasWorked = true;
			}
			break;
		}

		moveTo(o);

		return hasWorked;
	}

	public SalleElement getCurrent() {
		return current;
	}
	
	public SalleElement getRoot() {
		return root;
	}

	/**
	 * 
	 * Change la salle courrante en bougeant dans une direction
	 * 
	 * @param o	Direction dans laquelle aller
	 * @return	Renvoie null si la salle n'existe pas !
	 */
	public SalleElement moveTo(Orientation o){

		SalleElement c = null;

		switch(o){
		case NORD :
			c = current.getHaut();
			break;
		case SUD :
			c = current.getBas();
			break;
		case EST :
			c = current.getDroite();
			break;
		case OUEST:
			c = current.getGauche();
			break;
		}

		this.current = c;

		return c;
	}

	/**
	 * Permet de modifier un element tout en gardant ses liens a jout
	 * @param newSE
	 */
	public void setCurrent(SalleElement newSE) {
		this.current.setSalle(newSE.getSalle());
	}

	/**
	 * Permet de modifier un element tout en gardant ses liens a jour
	 * @param newSE
	 */
	public void setRoot(SalleElement newSE) {
		this.root.setSalle(newSE.getSalle());
	}

	

	

}
