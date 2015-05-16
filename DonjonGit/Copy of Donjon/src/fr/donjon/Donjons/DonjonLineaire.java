package fr.donjon.Donjons;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.salles.EnigmeSwitch;
import fr.donjon.salles.Salle;
import fr.donjon.salles.SalleQuatre;
import fr.donjon.utils.Link;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;



public class DonjonLineaire extends GestionnaireSalle{

	private static final int SALLEWIDTH = 15;
	private static final int SALLEHEIGHT = 10;

	int nombreDeSalle;
	/**
	 * Constructeur d'un donjon linéaire
	 * @param nbSalles le nombre de salles que le contient le donjon
	 */
	public DonjonLineaire(int nbSalles)  {
		super(new SalleQuatre(new Heros(0,0),Salle.addWalls(MapGenerator.randomForet(5, 5))) , 0,nbSalles-1,1,nbSalles);
		
		this.nombreDeSalle = nbSalles;
		
		Salle start = this.getsActuelle();
		start.setEcouteur(this);
		
		start.createPorteSalleVoisines(this.getSmap());
		
		//On place le hï¿½ros au centre de la salle
		start.hero.setLocationCase(start.getRoomCenter());;
		
		}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.start.GestionnaireSalle#fournirNouvelleSalle(fr.donjon.utils.Vecteur, fr.donjon.utils.Link, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		//create the new room
		SalleQuatre s;
		
		if((l.getSalleOrigine().roomNumber)%(int)(nombreDeSalle/2) == 1){
			s= new EnigmeSwitch(l.getSalleOrigine().hero);
		}else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT )));
		}
		
		s.setEcouteur(l.getSalleOrigine().ecouteur);

		//put it in the array of rooms
		smap[(int) position.x][(int) position.y] = s;
				
		//we create all the possible doors to the neighbors
		s.createPorteSalleVoisines(smap);
		
		//We add a skeleton to each room, its level being the room number
		Squelette e=new Squelette(0, 0, s.hero, s.roomNumber);
		e.setLocationCase(s.getRoomCenter());
		s.addEnemy(e);
		
	}

}