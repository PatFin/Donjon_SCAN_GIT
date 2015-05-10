package fr.donjon.testblac;
import java.util.ArrayList;

import fr.donjon.cases2.Case;
import fr.donjon.cases2.CaseMur;
import fr.donjon.cases2.CasePorte;
import fr.donjon.cases2.CaseVide;
import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public abstract class Salle implements EcouteurClavier{

	int width;
	int height;
	
	public static int instances=0;
	public int roomNumber;

	public Case[][] cases;
	public ArrayList<CasePorte> portes;
	public ArrayList <Personnage> personnages;
	public Heros hero;	

	public EcouteurChangementSalle ecouteur;

	boolean finie;
	

	public Salle(int w, int h) {

		this.cases = new Case[w][h];

		this.width = w;
		this.height = h;
		
		fillEmptyWithVoid();

		this.personnages = new ArrayList<Personnage>();

		this.portes = new ArrayList<CasePorte>();

		this.hero = new Heros(0, 0);

		this.roomNumber = instances++;

		finie = false;
	}

	public Salle(Heros h, ArrayList<Personnage> persos, Case[][] c){
		this(c.length, c[0].length);

		this.cases = c;

		this.personnages = persos;
		this.hero = h;
		this.finie = false;

		personnages.add(hero);

		this.portes = new ArrayList<CasePorte>();

		trouverLesPortes();

	}

	public Salle(Heros h , Case[][] cases){
		this(h, new ArrayList<Personnage>(), cases);
	}

	
	public void passerLaPorte(Vecteur dir){
		ecouteur.changerDeSalle(dir);
	}

	public void trouverLesPortes(){


		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				if(cases[x][y].getClass().getSimpleName().equals("CasePorte")){
					portes.add((CasePorte) cases[x][y]);
				}
			}
		}

	}

	public abstract void activerLesPortes(boolean a);

	public static Case[][] addWalls(Case[][] c){

		Case[][] cs = new Case[c.length+2][c[0].length+2];

		for(int x = 0 ; x < cs.length ; x++){
			cs[x][0] = new CaseMur();
			cs[x][cs[0].length - 1] = new CaseMur();
		}

		for(int y = 0 ; y < cs[0].length ; y++){
			cs[0][y] = new CaseMur();
			cs[cs.length - 1][y] = new CaseMur();
		}
		
		for(int y = 0 ; y < c[0].length ; y++){
			for(int x = 0 ; x < c.length ; x++){
				cs[x+1][y+1] = c[x][y];
			}
		}

		return cs;
	}

	public void setEcouteur(EcouteurChangementSalle e){
		this.ecouteur = e;
	}

	public Case getCase(Vecteur v){
		return cases[(int)v.x][(int)v.y];
	}

	public void update(long temps){

		Personnage z;

		//Parcours des personnages
		for(int i=0;i<personnages.size();i++){
			z=personnages.get(i);

			if(z!=null){

				z.update(temps);	//Le personnage agit.

				//Collisions inter-personnages
				for(int j = i+1 ; j < personnages.size(); j ++){
					Personnage p2 = personnages.get(j);
					if(z.collisionDecor.intersects(p2.collisionDecor)){
						z.setLocation((int)z.lPos.x, (int) z.lPos.y);
						p2.setLocation((int)p2.lPos.x, (int) p2.lPos.y);
					}
				}

				//Collisions avec les cases
				for(int x = 0 ; x < width ; x++){
					for(int y = 0 ; y < height ; y++){ 
						if(z.collisionDecor.intersects(cases[x][y].limites))cases[x][y].inCollision(z);
						else cases[x][y].nonCollision(z);
					}
				}

			}//Fin de la boucle sur les personnages

			if(!z.living)personnages.remove(z); //(on enterre les morts)
		}

		checkFinie();

		//We sort the list of characters such that they superimpose correctly in the room
		sortCharacters();
	}

	public void addEnemy(Ennemis e) {
		personnages.add(e);
	}

	private void sortCharacters(){
		//Putting the elements in an array
		Personnage [] a = new Personnage[personnages.size()];

		for(int i=0; i<a.length;i++){
			a[i] = personnages.get(i);
		}

		//Bubble sort algorithm
		for(int j=0; j<a.length-1;j++){
			for(int i=0;i<a.length-j-1;i++){
				if(a[i].collisionDecor.y>a[i+1].collisionDecor.y){
					Personnage temp = a[i];
					a[i]=a[i+1];
					a[i+1]=temp;
				}
			}
		}

		//Putting the element back into the list.
		this.personnages = new ArrayList<Personnage>();

		for(int i=0;i<a.length;i++){
			this.personnages.add(a[i]);
		}

	}

	private void fillEmptyWithVoid(){
		for(int x=0; x<width;x++){
			for(int y=0;y<height;y++){

				if(cases[x][y]==null){
					cases[x][y]=new CaseVide();
					cases[x][y].setCollisionBoxLocation(y, x);
				}
			}
		}
	}

	public void checkFinie(){
		if(personnages.size() == 1 && !finie){
			finie = true;
			activerLesPortes(true);
		}
	}
	
	/**
	 * Donne la taille de l'image de la salle
	 * @return vecteur. Coordonn�e x taille horizontale, y taille verticale.
	 */
	public Vecteur getPixelSize(){
		return new Vecteur(this.cases.length*Case.TAILLE, this.cases[0].length*Case.TAILLE);
	}

	/////////////////////////////////////
	///INTERFACE ECOUTE//////////////////
	/////////////////////////////////////

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void attaque(Orientation o) {
		this.hero.attaquer(personnages, null, o);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void stopAttaque() {

	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void deplacement(Vecteur v) {
		this.hero.marcher(v);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void utiliseObjet(int reference) {
		this.hero.utiliserObjet(reference);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void togglePause() {

	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void stopDeplacement() {
		this.hero.stop();
	}
}
