package fr.donjon.Donjons;

import fr.donjon.cases.Case;
import fr.donjon.classes.BigBoss;
import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Squelette;
import fr.donjon.classes.SqueletteArc;
import fr.donjon.classes.SqueletteFeu;
import fr.donjon.editor.MapFileHandler;
import fr.donjon.salles.EnigmeSwitch;
import fr.donjon.salles.EnigmeTeleporteur;
import fr.donjon.salles.Salle;
import fr.donjon.salles.SalleQuatre;
import fr.donjon.utils.EnnemyGenerator;
import fr.donjon.utils.Link;
import fr.donjon.utils.MapGenerator;
import fr.donjon.utils.Vecteur;

/**
 * Gestion d'un tutoriel.
 * @author Patrick
 * Le donjon "tutoriel" est un donjon lin�aire vertical.
 * On y traverse touts les genres de salle qu'on peut trouver dans un donjon.
 * Touts les genres d'ennemis y sont pr�sent�s,seuls d'abord dans une salle.
 * Le boss est pr�sent dans la derni�re salle.
 * Les salles �nigmes sont aussi pr�sentes.
 */
public class Tutoriel extends GestionnaireSalle {

	private static final int SALLEHEIGHT = 7;
	private static final int SALLEWIDTH = 10;

	private static final int nombreDeSalle = 8;
	
	/**
	 * Constructeur
	 * Aucun argument n�cessaire.
	 */
	public Tutoriel(){
		super(new SalleQuatre(new Heros(0,0), Salle.addWalls(MapGenerator.randomForet(5, 5))),nombreDeSalle-1, 0, nombreDeSalle, 1);
		
		//On place le h�ro au centre de la 1�salle
		this.sActuelle.hero.setLocationCase(sActuelle.getRoomCenter());

		//On ajoute une porte vers le nord de la premi�re salle
		this.sActuelle.createPorteSalleVoisines(smap);


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

		Heros h = l.getSalleOrigine().hero;
		
		//On choisit un genre de salle et on le place dans le tableau de salle du donjon
		SalleQuatre s;
		Ennemis e;
		
		if(Salle.instances+1 != nombreDeSalle){
			//Salle.instances +1 est la ni�me la salle qu'on va cr�er.
			switch(Salle.instances+1){
			case 4:
				//La deuxi�me salle aura 1 seul squelette
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomChateauRochers(SALLEWIDTH, SALLEHEIGHT)));
				e = new Squelette(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			case 5:
				//La troisi�me salle contiendra un squelette qui lance des boules de feu.
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomChateauLave(SALLEWIDTH, SALLEHEIGHT)));
				e = new SqueletteFeu(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			case 2:
				//La quatri�me salle sera une �nigme switch
				s= new EnigmeSwitch(h);
				break;
			case 3:
				//La cinqui�me salle sera une �nigme t�l�porteur
				s= new EnigmeTeleporteur(h);
				break;
			case 6:
				//La sixième salle aura un squelette archer
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomForet(SALLEWIDTH, SALLEHEIGHT)));
				e = new SqueletteArc(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			default:
				//Pour les salles suivantes on g�n�re des ennemis al�atoirement dans une salle al�atoire.
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT)));
				s.personnages.addAll(EnnemyGenerator.generateCircle(s.hero, s, 20, 100 ));
			}
		}else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapFileHandler.getSalleDescriptionFromFile(1000).getMatrix()));
			s.addEnemy(new BigBoss( (int)s.getRoomCenter().x*Case.TAILLE, (int)s.getRoomCenter().y*Case.TAILLE , s.hero, 1, s) );
		}
		
		
		s.setEcouteur(this);
		smap[(int)position.x][(int)position.y] = s;

		//On cr�� les portes vers les autres salles
		s.createPorteSalleVoisines(smap);

		
	}

}
