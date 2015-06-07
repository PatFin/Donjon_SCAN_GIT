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
 * Le donjon "tutoriel" est un donjon linï¿½aire vertical.
 * On y traverse touts les genres de salle qu'on peut trouver dans un donjon.
 * Touts les genres d'ennemis y sont prï¿½sentï¿½s,seuls d'abord dans une salle.
 * Le boss est prï¿½sent dans la derniï¿½re salle.
 * Les salles ï¿½nigmes sont aussi prï¿½sentes.
 */
public class Tutoriel extends GestionnaireSalle {

	private static final int SALLEHEIGHT = 7;
	private static final int SALLEWIDTH = 10;

	private static final int nombreDeSalle = 8;
	
	/**
	 * Constructeur
	 * Aucun argument nécessaire
	 */
	public Tutoriel(){
		super(new SalleQuatre(new Heros(0,0), Salle.addWalls(MapGenerator.randomForet(5, 5))),nombreDeSalle-1, 0, nombreDeSalle, 1);
		
		//On place le hï¿½ro au centre de la 1ï¿½salle
		this.sActuelle.hero.setLocationCase(sActuelle.getRoomCenter());

		//On ajoute une porte vers le nord de la premiï¿½re salle
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
			//Salle.instances +1 est la niï¿½me la salle qu'on va crï¿½er.
			switch(Salle.instances+1){
			case 2:
				//La deuxième salle aura 1 seul squelette
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomChateauRochers(SALLEWIDTH, SALLEHEIGHT)));
				e = new Squelette(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			case 3:
				//La troisiï¿½me salle contiendra un squelette qui lance des boules de feu.
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomChateauLave(SALLEWIDTH, SALLEHEIGHT)));
				e = new SqueletteFeu(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			case 6:
				//La quatriï¿½me salle sera une ï¿½nigme switch
				s= new EnigmeSwitch(h);
				break;
			case 5:
				//La cinquiï¿½me salle sera une ï¿½nigme tï¿½lï¿½porteur
				s= new EnigmeTeleporteur(h);
				break;
			case 4:
				//La sixiÃ¨me salle aura un squelette archer
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomForet(SALLEWIDTH, SALLEHEIGHT)));
				e = new SqueletteArc(0,0,h,1,s);
				e.setLocationCase(s.getRoomCenter());
				s.addEnemy(e);
				break;
			default:
				//Pour les salles suivantes on gï¿½nï¿½re des ennemis alï¿½atoirement dans une salle alï¿½atoire.
				s = new SalleQuatre(h, Salle.addWalls(MapGenerator.randomMap(SALLEWIDTH, SALLEHEIGHT)));
				s.personnages.addAll(EnnemyGenerator.generateCircle(s.hero, s, 20, 100 ));
			}
		}else{
			s = new SalleQuatre(l.getSalleOrigine().hero, Salle.addWalls(MapFileHandler.getSalleDescriptionFromFile(1000).getMatrix()));
			s.addEnemy(new BigBoss( (int)s.getRoomCenter().x*Case.TAILLE, (int)s.getRoomCenter().y*Case.TAILLE , s.hero, 1, s) );
		}
		
		
		s.setEcouteur(this);
		smap[(int)position.x][(int)position.y] = s;

		//On créé les portes vers les autres salles
		s.createPorteSalleVoisines(smap);

		
	}

}
