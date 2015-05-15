package fr.donjon.testblac;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.utils.Link;
import fr.donjon.utils.Vecteur;

public class gestionnairePatrickBasique extends GestionnaireSalle {

	final static int SALLEWIDTH = 10;
	final static int SALLEHEIGHT = 10;
	
	/**
	 * handles a new donjon. This one is just a grid of size w*h
	 * the first room is by default placed in 0;0 coordinates
	 * @param w width of the donjon
	 * @param h height of the donjon
	 */
	public gestionnairePatrickBasique(int w, int h) {
		this(w, h,0,0);
	}

	/**
	 * Constructor
	 * @param w width of the donjon
	 * @param h height of the donjon
	 * @param sX x position of the 1st room
	 * @param sY y position of the 1st room
	 */
	public gestionnairePatrickBasique(int w, int h, int sX, int sY){
		super(new SalleQuatre(new Heros(0, 0), Salle.addWalls(MapGenerator.randomForet(SALLEWIDTH, SALLEHEIGHT))),sX,sY,w, h);
		
		this.sActuelle.hero.setLocation(new Vecteur(1,1));
		this.sActuelle.createPorteSalleVoisines(smap);
		this.sActuelle.setEcouteur(this);
		
		//TODO create a proper ennemy generation algorythm. Maybe in fournirNouvelleSalle?
		this.sActuelle.addEnemy(new Squelette(400, 400, sActuelle.hero, 1));
		
		this.sActuelle.activerLesPortes(false);
	}

	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		//create the new room
		SalleQuatre s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT)));
		s.setEcouteur(l.getSalleOrigine().ecouteur);
		
		//put it in the array of rooms
		smap[(int) position.x][(int) position.y] = s;
		
		//we create all the possible doors to the neighbors
		s.createPorteSalleVoisines(smap);
		
	}

}
