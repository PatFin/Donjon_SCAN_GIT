package fr.donjon.salles;

import java.util.ArrayList;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseMur;
import fr.donjon.cases.CasePorte;
import fr.donjon.cases.CaseVide;
import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.EcouteurChangementSalle;
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
	public boolean update;

	/**
	 * Construit une salle vide
	 * 
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
		
		this.update = true;

		finie = false;
	}

	/**
	 * Constructeur
	 * Il est possible d'afficher cette salle apr�s avoir utilis� ce constructeur 
	 * @param h le héros controlé par le personnage
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
	 * Methode appell�e par les cases portes quand le h�ros marche dessus.
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
	 * méthode activerLesPortes
	 */
	public void trouverLesPortes(){
		
		ArrayList<CasePorte> a = new ArrayList<CasePorte>();
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				if(cases[x][y].getClass().getSimpleName().equals("CasePorte")){
					a.add((CasePorte) cases[x][y]);
				}
			}
		}
		this.portes = a;
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
	 * Cr�� toutes les portes possibles vers les salles voisines dans le tableau de salles du donjon.
	 * 
	 * @param sMap le tableau de salles
	 */
	public abstract void createPorteSalleVoisines(Salle[][] sMap);

	/**
	 * Cr�� une case sur un c�t� de la salle selon l'orientation
	 * @param o Orientation de la porte � cr��
	 * @param smap le tableau de salles du donjon
	 */
	public abstract void addDoor(Orientation o, Salle[][] sMap);
	
	/**
	 * Prends le tableau de cases et en cr�e un nouveau entour� de murs
	 * 
	 * @param c tableau de cases
	 * @return le même tableau mais de dimensions +2 entouré de cases murs
	 */
	public static Case[][] addWalls(Case[][] c){
		
		Case[][] cs = new Case[c.length+2][c[0].length+2];

		//Murs horizontaux
		for(int x = 0 ; x < cs.length ; x++){
			cs[x][0] = new CaseMur();
			cs[x][cs[0].length - 1] = new CaseMur();
		}

		//Murs verticaux
		for(int y = 0 ; y < cs[0].length ; y++){
			cs[0][y] = new CaseMur();
			cs[cs.length - 1][y] = new CaseMur();
		}
		
		//Remplissage du nouveau tableau
		for(int y = 0 ; y < c[0].length ; y++){
			for(int x = 0 ; x < c.length ; x++){
				cs[x+1][y+1] = c[x][y];
			}
		}

		return cs;
	}
	
	/**
	 * Detecte les coordonnées d'une salle dans un tableau
	 * @param sMap le tableau de salle
	 * @return Vecteur indiquant la position dans le tableau, (-1,-1) si non trouvée
	 */
	public Vecteur detecteSalleDansTableau(Salle[][] sMap){
		Vecteur v = new Vecteur(-1,-1);
		
		for(int i=0; i<sMap.length; i++){
			for(int j=0; j<sMap[0].length; j++){
				if(sMap[i][j] == this){
					v.setLocation(i, j);
				}
			}
		}
		
		return v;
	}
	
	/**
	 * Donne un vecteur pointant sur la case centrale de la salle
	 * @return vecteur vers le centre de la salle
	 */
	public Vecteur getRoomCenter(){
		return new Vecteur(cases.length/2, cases[0].length/2);
	}

	/**
	 * 
	 * Permet de définir quel écouteur la salle prévient lorsqu'on veut changer
	 * de salle
	 * 
	 * @param e Classe attendant un résultat et implémentant EcouteurChangementSalle
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

		if(!update) return ;
		
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
						if(!update)return;
						z.setLocation((int)z.lPos.x, (int) z.lPos.y);
						p2.setLocation((int)p2.lPos.x, (int) p2.lPos.y);
					}
				}

				//Collisions avec les cases
				for(int x = 0 ; x < width ; x++){
					for(int y = 0 ; y < height ; y++){
						if(!update) return;
						//Collision case personnage
						if(z.collisionDecor.intersects(cases[x][y].limites))cases[x][y].inCollision(z);
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
			if(!projectiles.get(i).living)projectiles.remove(projectiles.get(i)); //on retire les projectiles qui sont "mort".
		}

		checkFinie();

		sortCharacters();
	}

	/**
	 * Indique si la salle a au moins une porte dans la direction indiqu�e en param�tre
	 * @param o orientation de la porte � d�tecter dans la salle
	 * @return true si il y a une porte de l'orientation indiqu�e dans cette salle, false sinon.
	 */
	public boolean hasDoorInOrientation(Orientation o){
		for(CasePorte c : this.portes){
			if(c.collisionPorte.lien.getOrientation()==o){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Permet d'ajouter un ennemis � la salle
	 * @param e l'ennemi � ajouter
	 */
	public void addEnemy(Ennemis e) {
		personnages.add(e);
	}

	/**
	 * Méthode de tri
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
	 * On v�rifie si le nombre d'ennemis est nul et que seul le h�ro control� par le joueur reste dans la salle.
	 * Si c'est le cas on rends le passage vers les autres salles possible
	 * Certaines salles peuvent override cette m�thode au besoin
	 */
	public void checkFinie(){
		if(personnages.size() == 1 && !finie){
			finie = true;
			activerLesPortes(true);
		}
	}
	
	/**
	 * Accesseur
	 * @return la valeur du bool�en finie de la salle
	 */
	public boolean estFinie(){
		return finie;
	}
	
	/**
	 * Cette m�thode v�rifie que toutes les portes ont une destination, c�d une case dans une autre salle
	 * @return true si toutes les portes de la salle ont une destination, false sinon
	 */
	public boolean allDoorsHaveDestination() {
		for(CasePorte c : portes){
			if(!c.collisionPorte.lien.hasDestination()){
				return false;
			}
		}
		return true;
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
	public void attaque(Vecteur v) {
		this.hero.attaquer(personnages, projectiles, v);
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
	 * @see fr.donjon.utils.EcouteurClavier#toggleMap()
	 */
	@Override
	public void toggleMap() {
		// Do nothing.
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
