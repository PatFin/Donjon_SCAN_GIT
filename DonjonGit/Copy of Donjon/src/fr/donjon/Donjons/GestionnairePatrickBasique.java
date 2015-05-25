/**
 * 
 */
package fr.donjon.Donjons;

import java.util.ArrayList;

import fr.donjon.cases.Case;
import fr.donjon.classes.BigBoss;
import fr.donjon.classes.Heros;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.salles.EnigmeSwitch;
import fr.donjon.salles.EnigmeTeleporteur;
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
public class GestionnairePatrickBasique extends GestionnaireSalle{

	private static final int SALLEHEIGHT = 7;
	private static final int SALLEWIDTH = 10;

	/**
	 * Constructeur d'un donjon non lin�aire
	 * @param width largeur du donjon souhait�
	 * @param height hauteur du donjon souhait�
	 */
	public GestionnairePatrickBasique(int width, int height){
		super(new SalleQuatre(new Heros(0,0), Salle.addWalls(MapGenerator.randomForet(5, 5))),width/2, height-1, width, height);

		//On place le h�ro au centre de la 1�salle
		this.sActuelle.hero.setLocationCase(sActuelle.getRoomCenter());

		//On ajoute une porte vers le nord de la premi�re salle
		this.sActuelle.addDoor(Orientation.NORD, this.smap);


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
		ecouteur.requestGameOver(true);
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

		if(r<15){ //TODO change back to 15%
			s= new EnigmeSwitch(l.getSalleOrigine().hero);
		}
		else if(r<30){
			s=new EnigmeTeleporteur(l.getSalleOrigine().hero);
		}
		else if(r < 100){
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapFileHandler.getSalleDescriptionFromFile(1000).getMatrix()));
			s.addEnemy(new BigBoss( (int)s.getRoomCenter().x*Case.TAILLE, (int)s.getRoomCenter().y*Case.TAILLE , s.hero, 1, s) );
		}
		else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT )));
			//s.personnages.addAll(EnnemyGenerator.generateCircle(s.hero, s, 20, 200));
		}
		
		s.setEcouteur(this);
		smap[(int)position.x][(int)position.y] = s;

		//On cr�� les portes vers les autres salles
		//On d�cide des portes � ouvrir ou pas pour la suite

		ArrayList<Orientation> mustCreateDoor = new ArrayList<Orientation>();
		ArrayList<Orientation> canCreateDoor = new ArrayList<Orientation>();

		//D'abord la porte vers la salle pr�c�dente
		Orientation o = l.getOrientation().opposite();
		mustCreateDoor.add(o);

		//On regarde autour de la salle si des salles existent d�j� et ont une porte vers la nouvelle.
		for(Orientation z : Orientation.getOrientationList()){
			if(z!=o){
				try{
					Vecteur voisin = s.detecteSalleDansTableau(smap).ajoute(z.getUnitVector());
					if(this.getSalle(voisin).hasDoorInOrientation(z.opposite())){
						mustCreateDoor.add(z);
					}
				}catch(NullPointerException e){
					//La salle n'a pas encore �t� cr�� auparavant
					canCreateDoor.add(z);
				}catch(ArrayIndexOutOfBoundsException e){
					//On est sur le bord du donjon, il n'y a plus de salles sur ce c�t� 
				}
			}
		}

		//On d�termine combien de portes et lesquelles parmi celles possibles on va cr�er

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

		//On cr�� les portes
		for(Orientation y: mustCreateDoor){
			s.addDoor(y, smap);
		}

		
	}

}
