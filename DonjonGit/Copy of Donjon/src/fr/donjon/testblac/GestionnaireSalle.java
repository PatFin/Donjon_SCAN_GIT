/**
 * 
 */
package fr.donjon.testblac;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public abstract class GestionnaireSalle implements EcouteurChangementSalle, EcouteurClavier{


	Salle[][] smap;

	Salle sActuelle;

	Vecteur position;

	Vecteur centreCamera;

	public GestionnaireSalle(int w, int h) {
		this.smap = new Salle[w][h];
		this.position = new Vecteur(0,0);
		this.centreCamera = new Vecteur(0,0);
	}

	public GestionnaireSalle(Salle s, int w, int h){
		this(s,0,0,w,h);
	}

	public GestionnaireSalle(Salle s, int sx, int sy, int w, int h){
		this(w,h);
		smap[sx][sy] = s;
		sActuelle = s;
		this.position = new Vecteur(sx,sy);
	}

	public void update (long t){

		sActuelle.update(t);

		centreCamera.setLocation(sActuelle.hero.image.x + sActuelle.hero.image.width/2,
				sActuelle.hero.image.height/2 + sActuelle.hero.image.y);
	}


	public boolean changementSalle(Vecteur dir){
		
		Vecteur npos = position.ajoute(dir);

		if( !(npos.x >= 0 && npos.x < smap.length && npos.y >= 0  && npos.y < smap[0].length) )return false;

		if(smap[(int)npos.x][(int)npos.y] != null ){
			setSActuelle( smap[(int)npos.x][(int)npos.y] , dir );
		}
		else{
			Salle s = fournirNouvelleSalle((int)npos.x, (int)npos.y);
			setSActuelle( s, dir);
			smap[(int)npos.x][(int)npos.y] = s;
		}
		
		return true;

	}

	public void setSActuelle(Salle s, Vecteur nPos){
		sActuelle = s;
		position = position.ajoute(nPos);
	}

	public abstract Salle fournirNouvelleSalle(int x, int y);

	

	//MUT
	
	/**
	 * @return the smap
	 */
	public Salle[][] getSmap() {
		return smap;
	}

	/**
	 * @param smap the smap to set
	 */
	public void setSmap(Salle[][] smap) {
		this.smap = smap;
	}

	/**
	 * @return the sActuelle
	 */
	public Salle getsActuelle() {
		return sActuelle;
	}

	/**
	 * @param sActuelle the sActuelle to set
	 */
	public void setsActuelle(Salle sActuelle) {
		this.sActuelle = sActuelle;
	}

	/**
	 * @return the position
	 */
	public Vecteur getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vecteur position) {
		this.position = position;
	}

	/**
	 * @return the centreCamera
	 */
	public Vecteur getCentreCamera() {
		return centreCamera;
	}

	/**
	 * @param centreCamera the centreCamera to set
	 */
	public void setCentreCamera(Vecteur centreCamera) {
		this.centreCamera = centreCamera;
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
		//Nothing to do, its done in game panel
	}

	@Override
	public void stopDeplacement() {
		this.sActuelle.stopDeplacement();
	}


	
	
}
