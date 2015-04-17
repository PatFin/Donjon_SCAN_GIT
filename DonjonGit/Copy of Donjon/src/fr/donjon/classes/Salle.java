package fr.donjon.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.LinkedList;

import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_dalle_sol;
import fr.donjon.classes.cases.Case_fendue_sol;
import fr.donjon.classes.cases.Case_mur;
import fr.donjon.classes.cases.Case_rocher;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class Salle implements EcouteurClavier{
	
	public static int numberOfRooms=0;				//The total number of rooms.
	public int roomNumber;							//The unique number associated with a room.
	public LinkedList <Link> liens;					//The list of links to other rooms.
	public EnumMap<Orientation,Vecteur> portes;		//The list of the potential doors to other rooms.
	
	
	public int difficulte; 							//Pour initialiser les ennemis. Pourra s'avérer utile.
	public Case[][] cases; 							//Tableau de cases qui composent la salle.
	public LinkedList <Personnage> personnage; 		//Contient les personnages de la salle (ennemis et héros).
	public Heros hero;								//Le héros que le joueur contrôle.
	public Rectangle ecran;							//Contient l'espace de jeu disponible dans la fenêtre.
	
	public BufferedImage imageSalle;  				//Contient l'image de la salle. Les objets n'y sont pas dessinés.
	public Graphics bufferImageSalle;            	//espace graphique associe a l'arriere plan.
	public BufferedImage buffer1;					//buffer utilisé pour générer l'image de la salle avec les personnage dedans.
	public Graphics monG;							//espace graphique associé à buffer1.
    
    /**
     * Constructeur de la salle
	 * @param p le personnage du joueur
	 * @param casesSalle contient un tableau de cases préfait, au cas ou on définit des salle prédéfinies.
	 * @param ecran contient l'espace de jeu disponible dans la fenêtre
	 */
    public Salle(Heros p, Case[][] casesSalle, Rectangle ecran, EnumMap<Orientation,Vecteur> portes){
    	super();
    	this.liens = new LinkedList<Link>();
    	
    	//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		this.hero = p;
		this.personnage.add(p);
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = casesSalle; 
		refreshRoomCases(cases);
		
		//On initialise la position des téléporteurs potentiels de la salle
		this.portes = portes;
		
		//On génère une image de la salle qui sera utilisée après dans la méthode draw
		generateImage();
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_ARGB);
        monG = buffer1.getGraphics();
        
        //On stocke le numéro de la salle et on incrémente le nombre de salle:
        this.roomNumber=Salle.numberOfRooms;
        Salle.numberOfRooms++;
        
	}
    
    public Salle(Heros hero, Rectangle ecran){
    	super();
    	this.liens = new LinkedList<Link>();
    	
    	//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		this.hero = hero;
		this.personnage.add(hero);
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_ARGB);
        monG = buffer1.getGraphics();
        
        //GEN SALLE
        
        Case[][] casesSalle;
		casesSalle = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE];

		for(int x=0; x<casesSalle.length;x++){					
			for(int y=0; y<casesSalle[0].length; y++){
				casesSalle[x][y]  = new Case_void();
			}
		}

		for(int x=1;x<casesSalle.length-1;x++){
			casesSalle[x][1]=new Case_mur();
		}

		//Le reste du tableau est rempli aléatoirement de dalles (fendue / rocher / normale)
		for(int y=2;y<casesSalle[0].length-1;y++){
			for(int x=1;x<casesSalle.length-1;x++){
				int random = (int)(Math.round(6*Math.random()));

				if(random == 0){
					casesSalle[x][y]=new Case_rocher();	
				}else if(random == 1){
					casesSalle[x][y]=new Case_fendue_sol();
				}else {						
					casesSalle[x][y]=new Case_dalle_sol();
				}
			}
		}
		
		refreshRoomCases(casesSalle);
        
        
        //On stocke le numéro de la salle et on incrémente le nombre de salle:
        this.roomNumber=Salle.numberOfRooms;
        Salle.numberOfRooms++;
    	
    }
	
    /**
     * Empty constructor.
     */
	public Salle(){
		
	}
    
	/**
	 * Constructor to create a room without initializing the cases.
	 * It requires the use of refreshRoomCases afterwards to make sure the room isn't just empty.
	 * @param ecran L'ecran de jeu.
	 * @param h le hero controlé par le joueur.
	 */
	public Salle(Rectangle ecran, Heros h, EnumMap<Orientation,Vecteur> portes){
		super();
		
		//On indique le numéro de salle et on incrément le nombre de salle.
		this.roomNumber=Salle.numberOfRooms;
		Salle.numberOfRooms++;
		this.liens = new LinkedList<Link>();
		
		//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		personnage.add(h);
		
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//On initialise la position des téléporteurs potentiels de la salle
		this.portes = portes;
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	/**
	 * This method is used if one needs to modify the tyles in the room.  
	 * @param caseSalle the new array of cases.
	 */
	protected void refreshRoomCases(Case[][] caseSalle){
		this.cases = caseSalle;
		for(int i=0;i<cases[0].length;i++){
			for(int j=0;j<cases.length;j++){
				try{
					this.cases[j][i].setCollisionBoxLocation(i, j);
				}catch(Exception e){
					System.out.println("Case ["+i+","+j+"] non définie.");
				}
				
			}
		}
		generateImage();
	}
	
	/**
	 * Updates all the objects.
	 * @param temps
	 */
	public void update(long temps){
		
		Personnage z;
		
		for(int i=0;i<personnage.size();i++){
		
			z=personnage.get(i);
			
			if(z!=null){
				
				z.update(temps);	//Le personnage agit.
				
				/**
				 * On applique les comportements à effectuer en fonction des cases 
				 * sur lesquelles le personnage marche.
				 */
				for(int x=0; x<cases.length;x++){
					
					for(int y=0; y<cases[0].length; y++){
						
						if(z.collisionDecor.intersects(cases[x][y].collision)){
							
							cases[x][y].inCollision(z);
							
						}
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode génère une image de la salle à partir du tableau 2D de cases.
	 */
	private void generateImage(){
		
		//Initialysing the graphic tools 
		imageSalle = new BufferedImage(ecran.width,ecran.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferImageSalle = imageSalle.createGraphics();
		
		for(int y=0;y<cases[0].length;y++){
			for(int x=0;x<cases.length;x++){
				if(cases[x][y] !=null){
					bufferImageSalle.drawImage(cases[x][y].image, x*Case.TAILLE,y*Case.TAILLE, Case.TAILLE, Case.TAILLE, null);
				}
			}
		}
	}
	
	/**
	 * Cette méthode est appelé dans la boucle principale du jeu et permet d'afficher tous les objets contenus dans la salle.
	 * @param t le parametre de temps utile à l'animations des objets
	 * @param g le param graphisue
	 */
	public void draw(long t, Graphics g){
		
		//On met l'image de la salle d'abord
		monG.drawImage(imageSalle,0,0,null);
		//On ajoute le numéro de salle en haut à gauche
		monG.setColor(Color.white);
		monG.drawString("Salle "+this.roomNumber, 20, 20);
		
		//On affiche les objets comme il faut
		for(int i=0;i<personnage.size();i++){
			Objet z=personnage.get(i);
			if(z!=null){
				z.draw(t,monG);
			}
		}
		
		//On envoie tout le buffer dans la fenêtre
		g.drawImage(buffer1, ecran.x, ecran.y, null);
	}
	
	/**
	 * Créé un lien vers une autre salle et l'ajoute à la liste des liens de la salle.
	 * @param destination la salle de destination. null si non déja créé.
	 * @param dx case destination horizontale
	 * @param dy case destination verticale
	 * @param x position dans la salle horizontale du téléporteur
	 * @param y position verticale dans la salle du téléporteur
	 * @param o orientation dans la salle du téléporteur
	 * @param enabled boolean qui autorise ou pas le passage
	 */
	public void ajouterLien(Salle destination, int dx, int dy, int x, int y, Orientation o, boolean enabled){
		Link l;
		if(destination == null){
			l = new Link(this, x,y,o,enabled);
		}else{
			l = new Link(destination, dx,dy, this, x,y, o, true);
		}
		this.liens.add(l);
	}
	
	
	public void ajouterEnnemi(Ennemis e) {
		personnage.add(e);
	}

	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		this.hero.attaquer(null, null, o);
	}

	@Override
	public void stopAttaque() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacement(Vecteur v) {
		// TODO Auto-generated method stub
		this.hero.marcher(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDeplacement() {
		// TODO Auto-generated method stub
		this.hero.stop();
	}
}