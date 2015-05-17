/**
 * 
 */
package fr.donjon.Donjons;

import java.util.ArrayList;

import fr.donjon.classes.Heros;
import fr.donjon.salles.EnigmeSwitch;
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

	private static final int SALLEWIDTH = 10;
	private static final int SALLEHEIGHT = 7;
	
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

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.Donjons.GestionnaireSalle#fournirNouvelleSalle(fr.donjon.utils.Vecteur, fr.donjon.utils.Link, fr.donjon.salles.Salle[][])
	 */
	@Override
	public void fournirNouvelleSalle(Vecteur position, Link l, Salle[][] smap) {
		
		//On choisit un genre de salle et on le place dans le tableau de salle du donjon
		SalleQuatre s;
		int r =(int) (Math.random()*100);
		
		if(r<15){
			s= new EnigmeSwitch(l.getSalleOrigine().hero);
		}else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT )));
		}
		s.setEcouteur(this);
		smap[(int)position.x][(int)position.y] = s;
		
		//On créé les portes vers les autres salles
		//On décide des portes à ouvrir ou pas pour la suite
		
		ArrayList<Orientation> mustCreateDoor = new ArrayList<Orientation>();
		ArrayList<Orientation> canCreateDoor = new ArrayList<Orientation>();
		
		//D'abord la porte vers la salle précédente
		Orientation o = l.getOrientation().opposite();
		mustCreateDoor.add(o);
		
		//On regarde autour de la salle si des salles existent déjà et ont une porte vers la nouvelle.
		for(Orientation z : Orientation.getOrientationList()){
			if(z!=o){
				try{
					Vecteur voisin = s.detecteSalleDansTableau(smap).ajoute(z.getUnitVector());
					if(this.getSalle(voisin).hasDoorInOrientation(z.opposite())){
						mustCreateDoor.add(z);
					}
				}catch(NullPointerException e){
					//La salle n'a pas encore été créé auparavant
					canCreateDoor.add(z);
				}catch(ArrayIndexOutOfBoundsException e){
					//On est sur le bord du donjon, il n'y a plus de salles sur ce côté 
				}
			}
		}
		
		//On détermine combien de portes et lesquelles parmi celles possibles on va créer
		
		if(!canCreateDoor.isEmpty()){
			r=(int)(Math.random()*6*43);
			
			switch(r%6){
			case 0:
			case 1:
			case 2:
				mustCreateDoor.add(canCreateDoor.get(r%canCreateDoor.size()));
				break;
			case 3:
			case 4:
				
				Orientation g = canCreateDoor.get(r%canCreateDoor.size());
				mustCreateDoor.add(g);
				canCreateDoor.remove(g);
				if(!canCreateDoor.isEmpty()){
					mustCreateDoor.add(canCreateDoor.get(r%canCreateDoor.size()));
				}
			
				break;
			default:
				mustCreateDoor.addAll(canCreateDoor);
			}
		}
		
		//On créé les portes
		for(Orientation y: mustCreateDoor){
			s.addDoor(y, smap);
		}
		
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.donjon.Donjons.GestionnaireSalle#checkDonjonFini()
	 */
	@Override
	public void checkDonjonFini(){
		//We go through the array
		for(int i=0; i<smap.length; i++){
			for(int j=0; j<smap[0].length;j++){
				if(!(smap[i][j] == null)){
					if(!smap[i][j].estFinie() || !smap[i][j].allDoorsHaveDestination()){
						return;
					}
				}
			}
		}
		//TODO afficher un score ou une minifenêtre en interrompant le jeu
		ecouteur.requestBackToMenu();
	}
	
}
