package fr.donjon.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Orientation;

public class Salle implements EcouteurClavier{
	
	public static int numberOfRooms=0;				//The total number of rooms
	public int roomNumber;							//The unique number associated with a room.
	public Linear_Castle castle;
	
	public int difficulte; 						//Pour initialiser les ennemis. Pourra s'avérer utile
	public Case[][] cases; 						//Tableau de cases qui composent la salle
	public LinkedList <Personnage> personnage; 	//Contient tous les personnages de la salle (héros et ennemis)
	public Heros hero;
	public Rectangle ecran;						//Contient l'espace de jeu disponible dans la fenêtre.
	
    
	public BufferedImage imageSalle;  				//Contient l'image de la salle. Les objets n'y sont pas dessinés.
	public Graphics bufferImageSalle;            	//espace graphique associe a l'arriere plan
	public BufferedImage buffer1;					//buffer utilisé pour générer l'image de la salle avec les personnage dedans.
	public Graphics monG;								//espace graphique associé à buffer1
    
    /**
     * Constructeur de la salle
	 * @param p le personnage du joueur
	 * @param casesSalle contient un tableau de cases préfait, au cas ou on définit des salle prédéfinies.
	 * @param ecran contient l'espace de jeu disponible dans la fenêtre
	 */
    public Salle(Heros p, Case[][] casesSalle, Rectangle ecran, Linear_Castle castle){
    	super();
    	this.castle = castle;
    	
    	
    	//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		this.hero = p;
		this.personnage.add(p);
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = casesSalle; 
				
				//new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//On met les éléments de casesSalle dans cases.
		//Attention, casesSalle doit être au moins aussi grand que cases qui est adapté à la taille de l'écran
		//Amelioration si casesSalle<case, centrer la salle?
		
		/*if(casesSalle != null){
			for(int i=2;i<cases[0].length;i++){
				for(int j=0;j<cases.length;j++){
					if(casesSalle[j][i]!=null){
						this.cases[j][i]= casesSalle[j][i];
						this.cases[j][i].setCollisionBoxLocation(i, j);
					}
				}
			}
		}
		*/
		
		//On génère une image de la salle qui sera utilisée après dans la méthode draw
		generateImage();
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
        
        //On stocke le numéro de la salle et on incrémente le nombre de salle:
        this.roomNumber=Salle.numberOfRooms;
        Salle.numberOfRooms++;
        
	}
	
	/**
	 * Constructeur pour mes tests personnels
	 * @param ecran
	 */
	public Salle(Rectangle ecran){
		super();
		//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//Les deux premières lignes sont remplie de rocher et de mur
		for(int x=0;x<cases.length;x++){
			cases[x][0]=new Case_rocher();
			cases[x][1]=new Case_mur();
		}
		
		//Le reste du tableau est rempli aléatoirement de dalles (fendue ou non)
		for(int y=2;y<cases[0].length;y++){
			for(int x=0;x<cases.length;x++){
				int random = (int)Math.round(Math.random());
				
				if(random == 0){
					cases[x][y]=new Case_fendue_sol();	
				}else{
					cases[x][y]=new Case_dalle_sol();
				}
				
				
			}
		}
		//On génère une image de la salle qui sera utilisée après dans la méthode draw
		generateImage();
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	/**
	 * Constructor to crreate a room without initializing the cases.
	 * It requires the use of refreshRoomCases afterwards to make sure the room isn't just empty.
	 * @param ecran L'ecran de jeu.
	 * @param h le hero controlé par le joueur.
	 */
	public Salle(Rectangle ecran, Heros h){
		super();
		
		//On indique le numéro de salle et on incrément le nombre de salle.
		this.roomNumber=Salle.numberOfRooms;
		Salle.numberOfRooms++;
		
		//On créé les objets contenus dans la salle
		this.difficulte=0;
		this.personnage = new LinkedList <Personnage> ();
		personnage.add(h);
		
		
		//On créé le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		
		//On créé le buffer qui sera rempli par l'image de la salle et des objets dans la méthode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	/**
	 * This method is used if one needs to modify the tyles in the room.  
	 * @param caseSalle the new arrayof cases.
	 */
	protected void refreshRoomCases(Case[][] caseSalle){
		this.cases = caseSalle;
		for(int i=0;i<cases[0].length;i++){
			for(int j=0;j<cases.length;j++){
				this.cases[j][i].setCollisionBoxLocation(i, j);
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
	
	public void ajouterEnnemi(Ennemis e) {
		
		personnage.add(e);
	}

	@Override
	public void attaque(Orientation o) {
		// TODO Auto-generated method stub
		this.hero.attaquer(null, null, o);
	}

	@Override
	public void stopAttaque(Orientation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacement(Orientation o) {
		// TODO Auto-generated method stub
		this.hero.marcher(o);
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
	public void stopDeplacement(Orientation o) {
		// TODO Auto-generated method stub
		this.hero.stop(o);
	}
}