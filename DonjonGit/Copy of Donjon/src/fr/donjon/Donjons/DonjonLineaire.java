package fr.donjon.Donjons;

import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.testblac.GestionnaireSalle;
import fr.donjon.testblac.MapGenerator;
import fr.donjon.testblac.Salle;
import fr.donjon.testblac.SalleQuatre;
import fr.donjon.utils.Link;
import fr.donjon.utils.Vecteur;

public class DonjonLineaire extends GestionnaireSalle{

	private static final int SALLEWIDTH = 15;
	private static final int SALLEHEIGHT = 10;

	public DonjonLineaire(int nbSalles)  {
		super(new SalleQuatre(new Heros(0,0),Salle.addWalls(MapGenerator.randomForet(5, 5))) , 0,nbSalles-1,1,nbSalles);
		Salle start = this.getsActuelle();
		start.setEcouteur(this);
		
		start.createPorteSalleVoisines(this.getSmap());
		
		//On place le héros au centre de la salle
		start.hero.setLocationCase(start.getRoomCenter());;
		
		}

	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		//create the new room
		SalleQuatre s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT )));
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
