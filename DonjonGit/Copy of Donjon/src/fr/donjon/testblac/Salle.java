package fr.donjon.testblac;

import java.util.ArrayList;
import fr.donjon.cases2.Case;
import fr.donjon.cases2.CaseMur;
import fr.donjon.cases2.CasePorte;
import fr.donjon.cases2.CaseVide;
import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public abstract class Salle implements EcouteurClavier{

	int width;
	int height;
	
	public static int instances = 0;
	public int roomNumber;

	public Case[][] cases;
	public ArrayList<CasePorte> portes;
	public ArrayList <Personnage> personnages;
	public ArrayList<Projectile> projectiles;
	public Heros hero;	

	public EcouteurChangementSalle ecouteur;

	boolean finie;
	

	/**
	 * Constructeur
	 * ATTENTION, les cases qui composent la salle n'ont pas �t� d�finies!
	 * @param w largeur de la salle
	 * @param h hauteur de la salle
	 */
	public Salle(int w, int h) {

		this.cases = new Case[w][h];

		this.width = w;
		this.height = h;
		
		fillEmptyWithVoid();

		this.personnages = new ArrayList<Personnage>();

		this.portes = new ArrayList<CasePorte>();

		this.projectiles = new ArrayList<Projectile>();
		
		this.hero = new Heros(0, 0);

		this.roomNumber = instances++;

		finie = false;
	}

	/**
	 * Constructeur
	 * Il est possible d'afficher cette salle apr�s avoir utilis� ce constructeur 
	 * @param h le h�ros contr�l� par le personnage
	 * @param persos liste des personnages de la salle
	 * @param c tableau de cases de la salle
	 */
	public Salle(Heros h, ArrayList<Personnage> persos, Case[][] c){
		this(c.length, c[0].length);

		this.cases = c;

		this.personnages = persos;
		this.hero = h;
		this.finie = false;

		personnages.add(hero);

		this.portes = new ArrayList<CasePorte>();

		trouverLesPortes();
	}

	/**
	 * Constructeur
	 * On initialise les cases de la salle et le heros que le joueur contr�le
	 * @param h h�ros contr�l� par le joueur
	 * @param cases le tableau de cases de la salle
	 */
	public Salle(Heros h , Case[][] cases){
		this(h, new ArrayList<Personnage>(), cases);
	}

	/**
	 * Methode appel�e par les cases portes quand le h�ros marche dessus.
	 * Cela va appeler la m�thode correspondante dans le gestionnaire de salle auquel la salle appartient.
	 * @param l le lien de la porte sur laquelle le h�ro marche
	 */ 
	public void passerLaPorte(Link l){
		ecouteur.changerDeSalle(l);
	}

	/**
	 * Permet de d�tecter les portes et de les stocker dans une liste.
	 * Il est n�cessaire d'appeler cette m�thode apr�s l'ajout des portes.
	 * Sans quoi il ne sera pas possible de changer de salle car le bool�en 
	 * passageAutorise des casePorte ne pourra pas �tre chang� en true par la
	 * m�thode activerLesPortes
	 */
	public void trouverLesPortes(){

		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				if(cases[x][y].getClass().getSimpleName().equals("CasePorte")){
					portes.add((CasePorte) cases[x][y]);
				}
			}
		}

	}

	/**
	 * On met la valeur a dans l'autorisation de passage de toutes les portes de la salle.
	 * @param a true si on veut rendre le passage possible, false sinon.
	 */
	public void activerLesPortes(boolean a){
		for(int i=0; i<portes.size(); i++){
			portes.get(i).setPassageAutorise(a);
		}
	}
	
	
	/**
	 * Cr�� toutes les portes dans la salle s'il peut exister une salle � c�t� de cete instance.
	 * Voir dans la classe SalleQuatre pour plus de pr�cisions
	 * @param sMap le tableau de salles
	 */
	public abstract void createPorteSalleVoisines(Salle[][] sMap);

	/**
	 * Prends le tableau de cases et en cr�� un nouveau entour� de murs
	 * @param c tableau de cases
	 * @return le m�me tableau mais de dimensions +2� entour� de cases murs
	 */
	public static Case[][] addWalls(Case[][] c){
		
		Case[][] cs = new Case[c.length+2][c[0].length+2];

		for(int x = 0 ; x < cs.length ; x++){
			cs[x][0] = new CaseMur();
			cs[x][cs[0].length - 1] = new CaseMur();
		}

		for(int y = 0 ; y < cs[0].length ; y++){
			cs[0][y] = new CaseMur();
			cs[cs.length - 1][y] = new CaseMur();
		}
		
		for(int y = 0 ; y < c[0].length ; y++){
			for(int x = 0 ; x < c.length ; x++){
				cs[x+1][y+1] = c[x][y];
			}
		}

		return cs;
	}

	/**
	 * Mutateur
	 * @param e l'�couteur qui doit �tre le m�me que celui du gestionnaire
	 */
	public void setEcouteur(EcouteurChangementSalle e){
		this.ecouteur = e;
	}

	/**
	 * Acceseur
	 * @param v position de la case dans la salle
	 * @return la case � la position v dans le salle
	 */
	public Case getCase(Vecteur v){
		return cases[(int)v.x][(int)v.y];
	}

	/**
	 * Anime les objets de la salle
	 * @param temps indispensable pour les animations des objets
	 */
	public void update(long temps){


		Personnage z;

		//Update des projectiles
		for( Projectile p : projectiles){
			p.update(temps);
		}
		
		//Parcours des personnages
		for(int i=0;i<personnages.size();i++){
			z=personnages.get(i);

			if(z!=null){

				z.update(temps);	//Le personnage agit.

				//Collisions inter-personnages
				for(int j = i+1 ; j < personnages.size(); j ++){
					Personnage p2 = personnages.get(j);
					if(z.collisionDecor.intersects(p2.collisionDecor)){
						z.setLocation((int)z.lPos.x, (int) z.lPos.y);
						p2.setLocation((int)p2.lPos.x, (int) p2.lPos.y);
					}
				}

				//Collisions avec les cases
				for(int x = 0 ; x < width ; x++){
					for(int y = 0 ; y < height ; y++){
						
						//Collision case personnage
						if(z.collisionDecor.intersects(cases[x][y].limites)) cases[x][y].inCollision(z);
						else cases[x][y].nonCollision(z);
						
						//Collision case-projectile
						for(Projectile p : projectiles){
							if(p.collisionDecor.intersects(cases[x][y].limites)) cases[x][y].inCollision(p);
							else cases[x][y].nonCollision(p);
						}
					}
				}
				
				//Collision projectiles-persos
				for( Projectile p : projectiles){
					if(p.collisionArmes.intersects(z.collisionArmes))p.inCollision(z);
				}
				

			}//Fin de la boucle sur les personnages

			if(!z.living)personnages.remove(z); //(on enterre les morts)
		}
		
		for(int i = 0 ; i< projectiles.size() ; i++){
			if(!projectiles.get(i).living)projectiles.remove(projectiles.get(i));
		}

		checkFinie();

		//We sort the list of characters such that they superimpose correctly in the room
		sortCharacters();
	}

	/**
	 * Permet d'ajouter un ennemis � la salle
	 * @param e l'ennemi � ajouter
	 */
	public void addEnemy(Ennemis e) {
		personnages.add(e);
	}

	/**
	 * M�thode de tri
	 * Pour que les images des personnages se superposent correctement, il faut qu'ils soient tri�s par coordonn�es y croissante.
	 * Cette m�thode est donc appel�e dans update()
	 */
	private void sortCharacters(){
		//Putting the elements in an array
		Personnage [] a = new Personnage[personnages.size()];

		for(int i=0; i<a.length;i++){
			a[i] = personnages.get(i);
		}

		//Bubble sort algorithm
		for(int j=0; j<a.length-1;j++){
			for(int i=0;i<a.length-j-1;i++){
				if(a[i].collisionDecor.y>a[i+1].collisionDecor.y){
					Personnage temp = a[i];
					a[i]=a[i+1];
					a[i+1]=temp;
				}
			}
		}

		//Putting the element back into the list.
		this.personnages = new ArrayList<Personnage>();

		for(int i=0;i<a.length;i++){
			this.personnages.add(a[i]);
		}

	}

	/**
	 * Rempli les cases non d�finies de la salle par des cases noires/void
	 */
	public void fillEmptyWithVoid(){
		for(int x=0; x<width;x++){
			for(int y=0;y<height;y++){

				if(cases[x][y]==null){
					cases[x][y]=new CaseVide();
					cases[x][y].setCollisionBoxLocation(y, x);
				}
			}
		}
	}
	
	/**
	 * Donne une case porte de l'orientation donn�e en parametre
	 * @param o l'orientation de la porte souhait�es
	 * @return une CasePorte de la bonne orientation
	 */
	public CasePorte getPorte(Orientation o){
		for(CasePorte c : this.portes){
			if(c.collisionPorte.lien.getOrientation() == o){
				return c;
			}
		}
		return null;
	}
	

	/**
	 * On v�rifie si le nombre d'ennemis est nul et que seul le h�ros contr�l� par le joueur reste dans la salle.
	 * Si c'est le cas on rends le passage vers les autres salles possible
	 */
	public void checkFinie(){
		if(personnages.size() == 1 && !finie){
			finie = true;
			activerLesPortes(true);
			System.out.println("Les portes sont maintenant ouvertes");
		}
	}
	
	/**
	 * Donne la taille de l'image de la salle
	 * @return vecteur. Coordonn�e x taille horizontale, y taille verticale.
	 */
	public Vecteur getPixelSize(){
		return new Vecteur(this.cases.length*Case.TAILLE, this.cases[0].length*Case.TAILLE);
	}

	/////////////////////////////////////
	///INTERFACE ECOUTE//////////////////
	/////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#attaque(fr.donjon.utils.Orientation)
	 */
	@Override
	public void attaque(Orientation o) {
		this.hero.attaquer(personnages, projectiles, o);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopAttaque()
	 */
	@Override
	public void stopAttaque() {

	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#deplacement(fr.donjon.utils.Vecteur)
	 */
	@Override
	public void deplacement(Vecteur v) {
		this.hero.marcher(v);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#utiliseObjet(int)
	 */
	@Override
	public void utiliseObjet(int reference) {
		this.hero.utiliserObjet(reference);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#togglePause()
	 */
	@Override
	public void togglePause() {

	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.utils.EcouteurClavier#stopDeplacement()
	 */
	@Override
	public void stopDeplacement() {
		this.hero.stop();
	}
}
