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
		this.position = new Vecteur(sx,sy);
	}

	/**
	 * MÃ©thode raffraichissant la salle actuelle
	 * @param t le temps qui s'est ï¿½coulï¿½ depuis le dï¿½but du jeu, paramï¿½tre nï¿½cessaire aux animations et autres.
	 */
	public void update (long t){

		sActuelle.update(t);	//On raffarichit uniquement la salle actuelle, inutile de faire bouger les ennemis dans tout le donjon.

		centreCamera.setLocation(sActuelle.hero.image.x + sActuelle.hero.image.width/2,
				sActuelle.hero.image.height/2 + sActuelle.hero.image.y);		//On recentre le vecteur centre camï¿½ra sur la position du personnage.
		this.checkHeroStillAlive();
		this.checkDonjonFini();
	}

	/**
	 * On vérifie que le héro est encore en vie. Si ce n'est pas le cas on retourne au menu.
	 */
	public void checkHeroStillAlive() {
		if(!this.sActuelle.hero.living){
			//TODO afficher un score ou une minifenêtre en interrompant le jeu
			ecouteur.requestBackToMenu();
		}
	}

	/**
	 * Vérifie si le donjon est terminé. C'est à dire:
	 * Si toutes les salles du tableau existent et ont été visitées (booléen fini == vrai)
	 * Demande à ce que le menu soit rafiché si c'est le cas.
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
		//TODO afficher un score ou une minifenêtre en interrompant le jeu
		ecouteur.requestBackToMenu();
	}

	/**
	 * Change le personnage de salle vers celle situÃ©e Ã  la salle situÃ©e aux coordonnï¿½es actuelles+dir
	 * @param l lien de la salle actuelle vers la nouvelle salle actuelle
	 * @return true si le changement a ï¿½tï¿½ effectuï¿½, false sinon.
	 */
	public boolean changementSalle(Link l){
		
		sActuelle.update = false;
		
		Vecteur npos = position.ajoute(l.getOrientation().getUnitVector()); //les coordonnÃ©es de la prochaine salleActuelle dans le tableau.

		if( !(npos.x >= 0 && npos.x < smap.length && npos.y >= 0  && npos.y < smap[0].length) )return false; //On tombe en dehors du tableau de salle, on renvoi false
		
		if(this.getSalleAt(npos) == null){
			fournirNouvelleSalle(npos, l, this.smap); //On crÃ©e une nouvelle salle et on la met dans le tableau
		}
		
		sActuelle.hero.setLocation(l.getDestinationVecteur().multiplie(Case.TAILLE));
		
		setSActuelle(npos);
		
		sActuelle.update = true;
		
		return true;		//On a bien changÃ© de salle, on renvoie true
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
	private void setSActuelle(Vecteur pos){
		position = pos;
		sActuelle = smap[(int)position.x][(int)position.y];
	}

	/**
	 * donne la salle situÃ©e Ã  la position dans le donjon donnï¿½e par le vecteur.
	 * @param v
	 * @return
	 */
	private Salle getSalleAt(Vecteur v){
		return smap[(int) v.x][(int) v.y];
	}
	
	/**
	 * MÃ©thode utilisÃ©e pour gÃ©nÃ©rer la salle suivante. 
	 * @param position
	 * @param l
	 * @param smap
	 */
	public abstract void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap);

	
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

	@Override
	public void attaque(Orientation o) {
		this.sActuelle.attaque(o);
	}

	@Override
	public void stopAttaque() {
		this.sActuelle.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		this.sActuelle.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		this.sActuelle.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		//Do nothing
	}

	@Override
	public void stopDeplacement() {
		this.sActuelle.stopDeplacement();
	}
}
