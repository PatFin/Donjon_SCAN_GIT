package fr.donjon.Donjons;

import fr.donjon.cases.CaseTeleportation;
import fr.donjon.cases.CollisionTeleportation;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.salles.Salle;
import fr.donjon.salles.SalleQuatre;
import fr.donjon.utils.Link;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;

public class GestionnairePatrickBasique extends GestionnaireSalle {

	final static int SALLEWIDTH = 8;
	final static int SALLEHEIGHT = 7;
	
	/**
	 * handles a new donjon. This one is just a grid of size w*h
	 * the first room is by default placed in 0;0 coordinates
	 * @param w width of the donjon
	 * @param h height of the donjon
	 */
	public GestionnairePatrickBasique(int w, int h) {
		this(w, h,0,0);
	}

	/**
	 * Constructor
	 * @param w width of the donjon
	 * @param h height of the donjon
	 * @param sX x position of the 1st room
	 * @param sY y position of the 1st room
	 */
	public GestionnairePatrickBasique(int w, int h, int sX, int sY){
		super(new SalleQuatre(new Heros(0, 0), Salle.addWalls(MapGenerator.randomForet(SALLEWIDTH, SALLEHEIGHT))),sX,sY,w, h);
		
		this.getsActuelle().hero.setLocation(new Vecteur(100,100));
		
		this.sActuelle.createPorteSalleVoisines(smap);
		
		//TODO : remove
		sActuelle.cases[3][5] = new CaseTeleportation();
		sActuelle.cases[7][5] = new CaseTeleportation();
		
		sActuelle.cases[3][5].setCollision(new CollisionTeleportation(sActuelle.cases[3][5],sActuelle.cases[7][5]));
		sActuelle.cases[7][5].setCollision(new CollisionTeleportation(sActuelle.cases[7][5],sActuelle.cases[3][5]));
		
		
		//TODO create a proper enemy generation algorithm. Maybe in fournirNouvelleSalle?
		this.sActuelle.addEnemy(new Squelette(400, 400, sActuelle.hero, 1, sActuelle));
		
		this.sActuelle.activerLesPortes(false);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.Donjons.GestionnaireSalle#fournirNouvelleSalle(fr.donjon.utils.Vecteur, fr.donjon.utils.Link, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		//create the new room
		SalleQuatre s = new SalleQuatre(l.getSalleOrigine().hero,
				Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT)));
		
		s.setEcouteur(this);
		
		//put it in the array of rooms
		smap[(int) position.x][(int) position.y] = s;
		
		//we create all the possible doors to the neighbors
		s.createPorteSalleVoisines(smap);
		
	}

}
