package fr.donjon.classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Salle {
	
	int difficulte; 						//Pour initialiser les ennemis. Pourra s'av�rer utile
	Case[][] cases; 						//Tableau de cases qui composent la salle
	LinkedList <Objet> objets; 				//Contient tousles objets de la salle (h�ros, ennemis et projectiles)
    Rectangle ecran;						//Contient l'espace de jeu disponible dans la fen�tre.
	
	BufferedImage imageSalle;  				//Contient l'image de la salle. Les objets n'y sont pas dessin�s.
    Graphics2D bufferImageSalle;            	//espace graphique associe a l'arriere plan
	BufferedImage buffer1;					//buffer utilis� pour g�n�rer l'image de la salle avec les personnage dedans.
	Graphics monG;								//espace graphique associ� � buffer1
    
    /**
     * Constructeur de la salle
	 * @param p le personnage du joueur
	 * @param casesSalle contient un tableau de cases pr�fait, au cas ou on d�finit des salle pr�d�finies.
	 * @param ecran contient l'espace de jeu disponible dans la fen�tre
	 */
    public Salle(Heros p, Case[][] casesSalle, Rectangle ecran){
    	super();
		
    	//On cr�� les objets contenus dans la salle
		this.difficulte=0;
		this.objets = new LinkedList <Objet> ();
		
		//On cr�� le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//On met les �l�ments de casesSalle dans cases.
		//Attention, casesSalle doit �tre au moins aussi grand que cases qui est adapt� � la taille de l'�cran
		//Amelioration si casesSalle<case, centrer la salle?
		if(cases != null){
			for(int i=2;i<cases[0].length;i++){
				for(int j=0;j<cases.length;j++){
					if(casesSalle[j][i]!=null){
						this.cases[j][i]= casesSalle[j][i];	
					}
				}
			}
		}
		
		//On g�n�re une image de la salle qui sera utilis�e apr�s dans la m�thode draw
		generateImage();
		
		//On cr�� le buffer qui sera rempli par l'image de la salle et des objets dans la m�thode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	/**
	 * Constructeur pour mes tests personnels
	 * @param ecran
	 */
	public Salle(Rectangle ecran){
		super();
		//On cr�� les objets contenus dans la salle
		this.difficulte=0;
		this.objets = new LinkedList <Objet> ();
		
		//On cr�� le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//Les deux premi�res lignes sont remplie de rocher et de mur
		for(int x=0;x<cases.length;x++){
			cases[x][0]=new Case_rocher();
			cases[x][1]=new Case_mur();
		}
		
		//Le reste du tableau est rempli al�atoirement de dalles (fendue ou non)
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
		//On g�n�re une image de la salle qui sera utilis�e apr�s dans la m�thode draw
		generateImage();
		
		//On cr�� le buffer qui sera rempli par l'image de la salle et des objets dans la m�thode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	public Salle(Rectangle ecran, Heros h){
		super();
		//On cr�� les objets contenus dans la salle
		this.difficulte=0;
		this.objets = new LinkedList <Objet> ();
		objets.add(h);
		
		//On cr�� le tableau de cases contenues de la salle
		this.ecran=ecran;
		this.cases = new Case[ecran.width/Case.TAILLE][ecran.height/Case.TAILLE]; 
		
		//Les deux premi�res lignes sont remplie de rocher et de mur
		for(int x=0;x<cases.length;x++){
			cases[x][0]=new Case_rocher();
			cases[x][1]=new Case_mur();
		}
		
		//Le reste du tableau est rempli al�atoirement de dalles (fendue ou non)
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
		cases[cases.length/2][0]=new Case_escalier();
		cases[cases.length/2][1]=new Case_escalier();
		cases[cases.length/2][cases[0].length-1]=new Case_escalier();
		
		
		//On g�n�re une image de la salle qui sera utilis�e apr�s dans la m�thode draw
		generateImage();
		
		//On cr�� le buffer qui sera rempli par l'image de la salle et des objets dans la m�thode draw
		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_RGB);
        monG = buffer1.getGraphics();
	}
	
	/**
	 * This method is used if one needs to modify the cases in the room.  
	 * @param caseSalle the new arrayof cases.
	 */
	protected void refreshRoomCases(Case[][] caseSalle){
		this.cases = caseSalle;
		generateImage();
	}

	public void update(long temps){
		Objet z;
		for(int i=0;i<objets.size();i++){
			z=objets.get(i);
			if(z!=null){
				z.update(temps);
			}
		}
	}
	
	/**
	 * Cette m�thode g�n�re une image de la salle � partir du tableau 2D de cases.
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
	 * Cette m�thode est appel� dans la boucle principale du jeu et permet d'afficher tous les objets contenus dans la salle.
	 * @param t le parametre de temps utile � l'animations des objets
	 * @param g le param graphisue
	 */
	public void draw(long t, Graphics g){
		
		//On met l'image de la salle d'abord
		monG.drawImage(imageSalle,0,0,null);
		
		//On affiche les objets comme il faut
		for(int i=0;i<objets.size();i++){
			Objet z=objets.get(i);
			if(z!=null){
				z.draw(t,monG);
			}
		}
		
		g.drawImage(buffer1, ecran.x, ecran.y, null);
	}
}