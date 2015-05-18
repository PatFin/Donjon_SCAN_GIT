/**
 * 
 */
package fr.donjon.Donjons;

import fr.donjon.cases.Case;
import fr.donjon.salles.Salle;
import fr.donjon.utils.EcouteurChangementSalle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class GestionnaireSalle implements EcouteurChangementSalle, EcouteurClavier{


	public Salle[][] smap;			//le tableau de salles du donjon

	protected Salle sActuelle;		//la salle dans laquelle le h�ros se trouve

	Vecteur position;		//le vecteur contenant les coordonn�es de la salle actuelle

	Vecteur centreCamera; 	//vecteur contenant l'emplacement du personnage dans la salle.
							//Il est utile quand on cherche à peindre la salle.
	
	public EcouteurLauncher ecouteur;

	/**
	 * Constructeur initialisant les dimensions du donjon
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(int w, int h) {
		this.smap = new Salle[w][h];
		this.position = new Vecteur(0,0);
		this.centreCamera = new Vecteur(0,0);
	}

	/**
	 * Constructeur initialisant une premi�re salle et donnant les dimensions du donjon.
	 * Par d�faut la premi�re salle est plac�e en haut � gauche du tableau.
	 * @param s la premi�re salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int w, int h){
		this(s,0,0,w,h);
	}

	
	/**
	 * Constructeur initialisant une premi�re salle et donnant les dimensions du donjon
	 * La premi�re salle est plac�e aux coordonn�es sx et sy donn�es en param�tres
	 * @param s la premi�re salle du donjon
	 * @param sx position x dans le tableau de la premi�re salle
	 * @param sy position y dans le tableau de la premi�re salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int sx, int sy, int w, int h){
		this(w,h);
		smap[sx][sy] = s;
		sActuelle = s;
		//On permet � la salle d'envoyer les �v�nements de changement de salle � this (gestionaire)
		sActuelle.setEcouteur(this);
		position = new Vecteur(sx,sy);
	}

	/**
	 * Méthode raffraichissant la salle actuelle
	 * @param t le temps qui s'est �coul� depuis le d�but du jeu, param�tre n�cessaire aux animations et autres.
	 */
	public void update (long t){

		sActuelle.update(t);	//On raffarichit uniquement la salle actuelle, inutile de faire bouger les ennemis dans tout le donjon.

		centreCamera.setLocation(sActuelle.hero.image.x + sActuelle.hero.image.width/2,
				sActuelle.hero.image.height/2 + sActuelle.hero.image.y);		//On recentre le vecteur centre cam�ra sur la position du personnage.
		this.checkHeroStillAlive();
		this.checkDonjonFini();
	}

	/**
	 * On v�rifie que le h�ro est encore en vie. Si ce n'est pas le cas on retourne au menu.
	 */
	public void checkHeroStillAlive() {
		if(!this.sActuelle.hero.living){
			//TODO afficher un score ou une minifen�tre en interrompant le jeu
			ecouteur.requestBackToMenu();
		}
	}

	/**
	 * V�rifie si le donjon est termin�. C'est � dire:
	 * Si toutes les salles du tableau existent et ont �t� visit�es (bool�en fini == vrai)
	 * Demande � ce que le menu soit rafich� si c'est le cas.
	 */
	public void checkDonjonFini() {
		//We go through the array
		for(int i=0; i<smap.length; i++){
			for(int j=0; j<smap[0].length;j++){
				if(smap[i][j] == null){
					return;
				}else{
					if(!smap[i][j].estFinie()){
						return;
					}
				}
			}
		}
		//TODO afficher un score ou une minifen�tre en interrompant le jeu
		ecouteur.requestBackToMenu();
	}

	/**
	 * Change le personnage de salle vers celle située à la salle située aux coordonn�es actuelles+dir
	 * @param l lien de la salle actuelle vers la nouvelle salle actuelle
	 * @return true si le changement a �t� effectu�, false sinon.
	 */
	public boolean changementSalle(Link l){
		
		sActuelle.update = false;
		
		Vecteur npos = position.ajoute(l.getOrientation().getUnitVector()); //les coordonnées de la prochaine salleActuelle dans le tableau.

		if( !(npos.x >= 0 && npos.x < smap.length && npos.y >= 0  && npos.y < smap[0].length) )return false; //On tombe en dehors du tableau de salle, on renvoi false
		
		if(this.getSalle(npos) == null){
			fournirNouvelleSalle(npos, l, this.smap); //On crée une nouvelle salle et on la met dans le tableau
		}
		
		sActuelle.hero.setLocation(l.getDestinationVecteur().multiplie(Case.TAILLE));
		
		setSActuelle(npos);
		
		sActuelle.update = true;
		
		return true;		//On a bien changé de salle, on renvoie true
	}

	/**
	 * Changes the current room to the one directly located on the (Orientation) of it in the matrix
	 * 
	 * @param o the orientation from the current room to the next one in the matrix.
	 */
	public void setSActuelle(Orientation o){
		position = position.ajoute(Orientation.getUnitVector(o));
		setSActuelle(position);
	}
	
	/**
	 * Change la salle actuelle � celle stu�e dans le tableau aux coordonn�es donn�es dans le vecteur
	 * @param nouvelleSalle les coordonn�es dans le tableau de la nouvelle salle actuelle.
	 */
	public void setSActuelle(Vecteur pos){
		position = pos;
		sActuelle = smap[(int)position.x][(int)position.y];
	}
	
	/**
	 * Méthode utilisée pour générer la salle suivante. 
	 * @param position la position dans le tableau de la nouvelle salle � fournir
	 * @param l le lien de la salle pr�c�dente � la nouvelle salle
	 * @param smap le tableau de salles du donjon
	 */
	public abstract void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap);

	/**
	 * Donne la salle � la position indiqu�e par le vecteur dans le tableau de salle du donjon
	 * @param v vecteur de position de la salle � rendre dans le tableau de salle
	 * @return la salle � la position v. Attention, renvoi null si la salle n'a pas encore �t� cr��.
	 */
	public Salle getSalle(Vecteur v){
		return smap[(int)v.x][(int)v.y];
	}
	
	/**
	 * Accesseur
	 * @return the smap
	 */
	public Salle[][] getSmap() {
		return smap;
	}

	/**
	 * Mutateur
	 * @param smap the smap to set
	 */
	public void setSmap(Salle[][] smap) {
		this.smap = smap;
	}

	/**
	 * Accesseur
	 * @return the sActuelle
	 */
	public Salle getsActuelle() {
		return sActuelle;
	}

	/**
	 * Mutateur
	 * @param sActuelle the sActuelle to set
	 */
	public void setsActuelle(Salle sActuelle) {
		this.sActuelle = sActuelle;
	}

	/**
	 * Accesseur
	 * @return the position
	 */
	public Vecteur getPosition() {
		return position;
	}

	/**
	 * Mutateur
	 * @param position the position to set
	 */
	public void setPosition(Vecteur position) {
		this.position = position;
	}

	/**
	 * Accesseur
	 * @return the centreCamera
	 */
	public Vecteur getCentreCamera() {
		return centreCamera;
	}

	/**
	 * Mutateur
	 * @param centreCamera nouvelle valeur de centreCamera
	 */
	public void setCentreCamera(Vecteur centreCamera) {
		this.centreCamera = centreCamera;
	}
	
	
	///////////////////////////////////////
	//INTERFACE ECOUTEUR CHANGEMENT SALLE//
	///////////////////////////////////////
	
	@Override
	public void changerDeSalle(Link l) {
		this.changementSalle(l);
	}
	
	/////////////////////////////////////
	//INTERFACE ECOUTEUR CLAVIER/////////
	/////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Orientation o) {
		this.sActuelle.attaque(o);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopAttaque()
	 */
	@Override
	public void stopAttaque() {
		this.sActuelle.stopAttaque();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		this.sActuelle.deplacement(v);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		this.sActuelle.utiliseObjet(reference);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {
		//do nothing. This event is handled by the PanelJeu class
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#toggleMap()
	 */
	@Override
	public void toggleMap(){
		//Do nothing, this is handled by the panelJeu class
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		this.sActuelle.stopDeplacement();
	}
}
