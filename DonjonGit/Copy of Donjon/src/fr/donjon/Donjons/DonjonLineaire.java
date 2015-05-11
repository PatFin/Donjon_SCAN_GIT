package fr.donjon.Donjons;

import fr.donjon.testblac.GestionnaireSalle;
import fr.donjon.testblac.Salle;
import fr.donjon.utils.Vecteur;

public class DonjonLineaire extends GestionnaireSalle{

	public DonjonLineaire(int nbSalles)  {
		super(1,nbSalles);
		
		}

	@Override
	public void changerDeSalle(Vecteur dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Salle fournirNouvelleSalle(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
