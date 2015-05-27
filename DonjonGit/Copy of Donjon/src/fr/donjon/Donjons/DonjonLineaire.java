package fr.donjon.Donjons;

import fr.donjon.cases.Case;
import fr.donjon.classes.BigBoss;
import fr.donjon.classes.Heros;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.salles.EnigmeSwitch;
import fr.donjon.salles.EnigmeTeleporteur;
import fr.donjon.salles.Salle;
import fr.donjon.salles.SalleQuatre;
import fr.donjon.utils.EnnemyGenerator;
import fr.donjon.utils.Link;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;



public class DonjonLineaire extends GestionnaireSalle{

	private static final int SALLEHEIGHT = 10;
	private static final int SALLEWIDTH = 15;

	int nombreDeSalle;
	/**
	 * Constructeur d'un donjon linï¿½aire
	 * @param nbSalles le nombre de salles que le contient le donjon
	 */
	public DonjonLineaire(int nbSalles)  {
		super(new SalleQuatre(new Heros(0,0),Salle.addWalls(MapGenerator.randomForet(5, 5))) , 0,nbSalles-1,1,nbSalles);

		this.nombreDeSalle = nbSalles;

		Salle start = this.getsActuelle();

		start.createPorteSalleVoisines(this.getSmap());

		//On place le héro au centre de la salle
		start.hero.setLocationCase(start.getRoomCenter());;

	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.start.GestionnaireSalle#fournirNouvelleSalle(fr.donjon.utils.Vecteur, fr.donjon.utils.Link, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		//create the new room

		
		Salle s;
		
		if(Salle.instances+1 != nombreDeSalle){
			switch(Salle.instances%6){
			case 1:
				s= new EnigmeTeleporteur(l.getSalleOrigine().hero);
				break;
			case 4:
				s= new EnigmeSwitch(l.getSalleOrigine().hero);
				break;
			default:
				s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT )));
				s.personnages.addAll(EnnemyGenerator.generateCircle(s.hero, s, 20, 100 ));
			}
		}else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapFileHandler.getSalleDescriptionFromFile(1000).getMatrix()));
			s.addEnemy(new BigBoss( (int)s.getRoomCenter().x*Case.TAILLE, (int)s.getRoomCenter().y*Case.TAILLE , s.hero, 1, s) );
		}
		
		
		
		

		s.setEcouteur(l.getSalleOrigine().ecouteur);

		//put it in the array of rooms
		smap[(int) position.x][(int) position.y] = s;

		//we create all the possible doors to the neighbors
		s.createPorteSalleVoisines(smap);

	}

}