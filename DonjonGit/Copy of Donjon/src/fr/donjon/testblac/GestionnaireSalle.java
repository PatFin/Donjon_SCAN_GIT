/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class GestionnaireSalle implements EcouteurChangementSalle, EcouteurClavier{


	Salle[][] smap;			//le tableau de salles du donjon

	Salle sActuelle;		//la salle dans laquelle le héros se trouve

	Vecteur position;		//le vecteur contenant les coordonnées de la salle actuelle

	Vecteur centreCamera; 	//vecteur contenant l'emplacement du personnage dans la salle.
							//Il est utile quand on cherche à peindre la salle.

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
	 * Constructeur initialisant une première salle et donnant les dimensions du donjon.
	 * Par défaut la première salle est placée en haut à gauche du tableau.
	 * @param s la première salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int w, int h){
		this(s,0,0,w,h);
	}

	
	/**
	 * Constructeur initialisant une première salle et donnant les dimensions du donjon
	 * La première salle est placée aux coordonnées sx et sy données en paramètres
	 * @param s la première salle du donjon
	 * @param sx position x dans le tableau de la première salle
	 * @param sy position y dans le tableau de la première salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int sx, int sy, int w, int h){
		this(w,h);
		smap[sx][sy] = s;
		sActuelle = s;
		this.position = new Vecteur(sx,sy);
	}

	/**
	 * Méthode raffraichissant la salle actuelle
	 * @param t le temps qui s'est écoulé depuis le début du jeu, paramètre nécessaire aux animations et autres.
	 */
	public void update (long t){

		sActuelle.update(t);	//On raffarichit uniquement la salle actuelle, inutile de faire bouger les ennemis dans tout le donjon.

		centreCamera.setLocation(sActuelle.hero.image.x + sActuelle.hero.image.width/2,
				sActuelle.hero.image.height/2 + sActuelle.hero.image.y);		//On recentre le vecteur centre caméra sur la position du personnage.
	}


	/**
	 * Change le personnage de salle vers celle située à la salle située aux coordonnées actuelles+dir
	 * @param l lien de la salle actuelle vers la nouvelle salle actuelle
	 * @return true si le changement a été effectué, false sinon.
	 */
	public boolean changementSalle(Link l){
		
		Vecteur npos = position.ajoute(l.getOrientation().getUnitVector()); //les coordonnées de la prochaine salleActuelle dans le tableau.

		if( !(npos.x >= 0 && npos.x < smap.length && npos.y >= 0  && npos.y < smap[0].length) )return false; //On tombe en dehors du tableau de salle, on renvoi false

		if(this.getSalleAt(npos) == null){
			fournirNouvelleSalle(npos, l, this.smap); //On créé une nouvelle salle et on la met dans le tableau
		}
		
		setSActuelle(npos);
		sActuelle.hero.setLocation(l.getDestinationVecteur());
		
		return true;		//On a bien changé de salle, on renvoie true
	}

	/**
	 * Changes the current room to the one directly located on the (Orientation) of it in the matrix
	 * @param o the orientation from the current room to the next one in the matrix.
	 */
	public void setSActuelle(Orientation o){
		position = position.ajoute(Orientation.getUnitVector(o));
		setSActuelle(position);
	}
	
	/**
	 * Change la salle actuelle à celle stuée dans le tableau aux coordonnées données dans le vecteur
	 * @param nouvelleSalle les coordonnées dans le tableau de la nouvelle salle actuelle.
	 */
	private void setSActuelle(Vecteur Salle){
		position = Salle;
		this.setSalleActuelleOn(position);
	}

	/**
	 * donne la salle située à la position dans le donjon donnée par le vecteur.
	 * @param v
	 * @return
	 */
	private Salle getSalleAt(Vecteur v){
		return smap[(int) v.x][(int) v.y];
	}
	
	/**
	 * Méthode utilisée pour générer la salle suivante. 
	 * @param position
	 * @param l
	 * @param smap
	 */
	public abstract void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap);

	

	/**
	 * Mutateur
	 * change la salle actuelle pour celle aux coordonnées indiqués.
	 * @param v
	 */
	private void setSalleActuelleOn(Vecteur v){
		sActuelle = smap[(int)v.x][(int)v.y];
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
	
	
	/////////////////////////////////////
	//INTERFACE ECOUTEURChangementSalle//
	/////////////////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.testblac.EcouteurChangementSalle#changerDeSalle(fr.donjon.utils.Link)
	 */
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
		//Nothing to do, its done in game panel
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
