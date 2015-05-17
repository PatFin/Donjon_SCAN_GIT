/**
 * 
 */
package fr.donjon.Donjons;

import fr.donjon.classes.Heros;
import fr.donjon.salles.Salle;
import fr.donjon.salles.SalleQuatre;
import fr.donjon.utils.Link;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Patrick
 *
 */
public class DonjonNonLineaire extends GestionnaireSalle{

	private static final int SALLEWIDTH = 15;
	private static final int SALLEHEIGHT = 10;
	
	/**
	 * Constructeur d'un donjon non linéaire
	 * @param width largeur du donjon souhaité
	 * @param height hauteur du donjon souhaité
	 */
	public DonjonNonLineaire(int width, int height){
		super(new SalleQuatre(new Heros(0,0), Salle.addWalls(MapGenerator.randomForet(5, 5))),(int) width/2, height-1, width, height);
		
		//On place le héro au centre de la 1°salle
		this.sActuelle.hero.setLocationCase(sActuelle.getRoomCenter());
		
		//On ajoute une porte vers le nord de la première salle
		this.sActuelle.addDoor(Orientation.NORD, this.smap);
		
		
	}

	@Override
	public void toggleMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		// TODO Auto-generated method stub
		
	}
	
}
