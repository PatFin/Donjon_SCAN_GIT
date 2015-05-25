/**
 * 
 */
package fr.donjon.Donjons;

import fr.donjon.cases.Case;
import fr.donjon.cases.CasePorte;
import fr.donjon.salles.Salle;
import fr.donjon.sound.SoundLoop;
import fr.donjon.utils.EcouteurChangementSalle;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.EcouteurLauncher;
import fr.donjon.utils.GameOverListener;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class GestionnaireSalle implements EcouteurChangementSalle, EcouteurClavier, GameOverListener{


	private static SoundLoop doorSound = new SoundLoop(SoundLoop.DOOR);
	public Salle[][] smap;			//le tableau de salles du donjon

	protected Salle sActuelle;		//la salle dans laquelle le hï¿½ros se trouve

	Vecteur position;		//le vecteur contenant les coordonnï¿½es de la salle actuelle

	Vecteur centreCamera; 	//vecteur contenant l'emplacement du personnage dans la salle.
							//Il est utile quand on cherche Ã  peindre la salle.
	
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
	 * Constructeur initialisant une premiï¿½re salle et donnant les dimensions du donjon.
	 * Par dï¿½faut la premiï¿½re salle est placï¿½e en haut ï¿½ gauche du tableau.
	 * @param s la premiï¿½re salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int w, int h){
		this(s,0,0,w,h);
	}

	
	/**
	 * Constructeur initialisant une premiï¿½re salle et donnant les dimensions du donjon
	 * La premiï¿½re salle est placï¿½e aux coordonnï¿½es sx et sy donnï¿½es en paramï¿½tres
	 * @param s la premiï¿½re salle du donjon
	 * @param sx position x dans le tableau de la premiï¿½re salle
	 * @param sy position y dans le tableau de la premiï¿½re salle
	 * @param w la largeur du donjon (nombres de salles sur axe horizontal)
	 * @param h la hauteur du donjon (nombres de salles sur axe vertical)
	 */
	public GestionnaireSalle(Salle s, int sx, int sy, int w, int h){
		this(w,h);
		smap[sx][sy] = s;
		sActuelle = s;
		//On permet ï¿½ la salle d'envoyer les ï¿½vï¿½nements de changement de salle ï¿½ this (gestionaire)
		sActuelle.setEcouteur(this);
		position = new Vecteur(sx,sy);
	}

	/**
	 * MÃ©thode raffraichissant la salle actuelle
	 * @param t le temps qui s'est ï¿½coulï¿½ depuis le dï¿½but du jeu, paramï¿½tre nï¿½cessaire aux animations et autres.
	 */
	public void update (long t){

		sActuelle.update(t);	//On raffarichit uniquement la salle actuelle, inutile de faire bouger les ennemis dans tout le donjon.

		for(CasePorte c : sActuelle.portes){
			System.out.println((this.sActuelle.roomNumber)+""+(c.collision));
		}
		
		centreCamera.setLocation(sActuelle.hero.image.x + sActuelle.hero.image.width/2,
				sActuelle.hero.image.height/2 + sActuelle.hero.image.y);		//On recentre le vecteur centre camï¿½ra sur la position du personnage.
		this.checkHeroStillAlive();
		this.checkDonjonFini();
	}

	/**
	 * On vï¿½rifie que le hï¿½ro est encore en vie. Si ce n'est pas le cas on retourne au menu.
	 */
	public void checkHeroStillAlive() {
		if(!this.sActuelle.hero.living){
			ecouteur.requestGameOver(false);
		}
	}

	/**
	 * Vï¿½rifie si le donjon est terminï¿½. C'est ï¿½ dire:
	 * Si toutes les salles du tableau existent et ont ï¿½tï¿½ visitï¿½es (boolï¿½en fini == vrai)
	 * Demande ï¿½ ce que le menu soit rafichï¿½ si c'est le cas.
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
		ecouteur.requestGameOver(true);
	}

	/**
	 * Change le personnage de salle vers celle situÃ©e Ã  la salle situÃ©e aux coordonnï¿½es actuelles+dir
	 * @param l lien de la salle actuelle vers la nouvelle salle actuelle
	 * @return true si le changement a ï¿½tï¿½ effectuï¿½, false sinon.
	 */
	public boolean changementSalle(Link l){
		
		sActuelle.update = false;
		
		Vecteur npos = position.ajoute(l.getOrientation().getUnitVector()); //les coordonnées de la prochaine salleActuelle dans le tableau.

		if( !(npos.x >= 0 && npos.x < smap.length && npos.y >= 0  && npos.y < smap[0].length) )return false; //On tombe en dehors du tableau de salle, on renvoi false. Il s'agit d'une sécurité supplémentaire par desuus celle de la création des portes.
		
		//On créé une nouvelle salle et on la met dans le tableau si elle n'existe pas déjà.
		if(this.getSalle(npos) == null){
			fournirNouvelleSalle(npos, l, this.smap); 
		}
		
		setSActuelle(npos);
		sActuelle.hero.setLocation(l.getDestinationVecteur().multiplie(Case.TAILLE));
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
	 * Change la salle actuelle ï¿½ celle stuï¿½e dans le tableau aux coordonnï¿½es donnï¿½es dans le vecteur
	 * @param nouvelleSalle les coordonnï¿½es dans le tableau de la nouvelle salle actuelle.
	 */
	public void setSActuelle(Vecteur pos){
		position = pos;
		sActuelle = smap[(int)position.x][(int)position.y];
	}
	
	/**
	 * MÃ©thode utilisÃ©e pour gÃ©nÃ©rer la salle suivante. 
	 * @param position la position dans le tableau de la nouvelle salle ï¿½ fournir
	 * @param l le lien de la salle prï¿½cï¿½dente ï¿½ la nouvelle salle
	 * @param smap le tableau de salles du donjon
	 */
	public abstract void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap);

	/**
	 * Donne la salle ï¿½ la position indiquï¿½e par le vecteur dans le tableau de salle du donjon
	 * @param v vecteur de position de la salle ï¿½ rendre dans le tableau de salle
	 * @return la salle ï¿½ la position v. Attention, renvoi null si la salle n'a pas encore ï¿½tï¿½ crï¿½ï¿½.
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
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurChangementSalle#changerDeSalle(fr.donjon.utils.Link)
	 */
	@Override
	public void changerDeSalle(Link l) {
		doorSound.playOnce();
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
	public void attaque(Vecteur v) {
		this.sActuelle.attaque(v);
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
	
	/////////////////////////////////////
	//INTERFACE ECOUTEUR LAUNCHER////////
	/////////////////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.GameOverListener#quit()
	 */
	@Override
	public void quit(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.GameOverListener#retourMenu()
	 */
	@Override
	public void retourMenu(){
		ecouteur.requestBackToMenu();
	}
	
}
