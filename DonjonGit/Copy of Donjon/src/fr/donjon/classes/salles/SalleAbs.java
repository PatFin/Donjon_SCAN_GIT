package fr.donjon.classes.salles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;

import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Objet;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.Squelette;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_Obstacle;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.editor.SalleDescription;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public abstract class SalleAbs implements EcouteurClavier {

	/**
	 * Attributes
	 */
	public static Rectangle ecran;							//Contains the available space to put the room.
	public static int numberOfRooms=0;						//The total number of rooms.

	public int roomNumber;									//The unique number associated with a room.
	public EnumMap<Orientation,Link> link;					//The list of links to other rooms.
	public EnumMap<Orientation,Vecteur> destination;		//The list of the spawn places in the room when coming from another room.
	public EnumMap<Orientation,Vecteur> porte;				//the list of the positions of the potential doors of the room.

	public Case[][] cases; 									//2D array containing the tyles composing the room.
	public ArrayList <Personnage> personnage; 				//Contains the character of the room (Ennemis and Heros).
	public Heros hero;										//The character the player controls.

	public BufferedImage imageSalle;  						//Contains the image of the room without the objects.
	public Graphics bufferImageSalle;            			//Graphical space associated to imageSalle.

	public BufferedImage buffer1;							//Buffer used in the draw method.
	public Graphics monG;									//Graphical space associated to buffer1.


	/**
	 * Constructor
	 * @param ecran the space available for the game
	 * @param h the character the player can control
	 * @throws CustomException 
	 */
	public SalleAbs(Rectangle ecran, Heros h) throws CustomException {
		this.roomNumber=numberOfRooms;
		SalleAbs.numberOfRooms++;

		SalleAbs.ecran=ecran;
		this.hero=h;
		this.personnage = new ArrayList <Personnage> ();
		this.personnage.add(hero);


		this.link = new EnumMap<Orientation, Link>(Orientation.class);

		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_ARGB);
		monG = buffer1.getGraphics();
	}

	/**
	 * Empty constructor
	 * Used in the "gestionnaire" to have a list of rooms to be played.
	 */
	public SalleAbs(){

	}
	

	protected abstract void generateRoom() throws CustomException;
	protected abstract void setDoorPlaces() throws CustomException;

	/**
	 * The general method to add enemis to a room.
	 * It distributes a number of "points" that depend on the room number.
	 * So you can have few but strong ennemis or pletora of weak ennemis in the room.
	 * Specific rooms can override this method for more specific needs.
	 */
	protected void generateEnnemis(){
		//First we get a list of the tiles that can welcome an enemy.
		//The loop excludes the sides of the room since the hero can be at these locations.
		LinkedList<Vecteur> l = new LinkedList<Vecteur>();

		for(int x=2; x<cases.length-2; x++){
			for(int y=2; y<cases[0].length-2; y++){
				if(!(cases[x][y] instanceof Case_Obstacle)){
					l.add(new Vecteur(x,y));
				}
			}
		}

		//Then we choose how many enemies we will create.
		//It shouldn't exceed 1/8 of the available tiles.
		//It should be at least 1.
		int r=(int)(Math.random()*l.size()/8);
		if(r<1 && l.size()!=0){
			r=1;
		}

		System.out.println("This room (Room "+roomNumber+") will have "+r+" ennemis !");
		//We create the enemies and place each of them at an available tile chosen randomly.
		int a;
		Vecteur v;

		for(int i=0;i<r;i++){
			a=(int)(Math.random()*l.size()/8);			//selecting a random index in the list of available tiles.
			v = l.get(a);

			//TODO implement a level selection system
			Ennemis e = new Squelette(0,0, hero, 1);
			e.setLocation(v);
			addEnemy(e);
			l.remove(a);
		}


	}

	/**
	 * Gives the instance of Case located at the position given by v as a parameter.
	 * @param v Position vector of the tile in the room.
	 * @return the tile at position v in the room.
	 */
	public Case getCase(Vecteur v){
		return cases[(int)v.x][(int)v.y];
	}



	/**
	 * Returns a new Room 
	 * @param ecran the space available for the game.
	 * @param h the hero the player controls.
	 * @param o the orientation of the door to the next room.
	 * @return a new room.
	 * @throws CustomException 
	 */
	public abstract SalleAbs clone(Rectangle ecran, Heros h, Orientation o) throws CustomException;

	/**
	 * Returns a new Room
	 * @param h the hero controlled by the character.
	 * @param l the link from the previous room to this one.
	 * @param o the orientation of the link to the next one.
	 * @return a new room.
	 * @throws CustomException 
	 */
	public abstract SalleAbs clone(Heros h, Link l) throws CustomException;

	/**
	 * This method creates a link to another room in the
	 * direction required by the parameter.
	 * @param o the position of the door to be created in the room
	 */
	public void addDoor(Orientation o,boolean enabled){
		//We create a link to a future room.
		//The room will be created later when the player steps on the door.
		this.link.put(o, new Link(this, this.porte.get(o), o, enabled));
	}

	/**
	 * This method creates a link to the previous room
	 * @param l the link from the previous room to the current one
	 */
	public void addDoorToPrevRoom(Link l){
		//We create the link.
		Orientation a = Orientation.opposite(l.orientation);

		this.link.put(a, new Link(l.origineSalle, l.origineSalle.destination.get(l.orientation), this, this.porte.get(a), a, true));
	}

	/**
	 * Updates all the objects.
	 * @param temps
	 */
	public void update(long temps){

		Personnage z;

		//Parcours des personnages
		for(int i=0;i<personnage.size();i++){
			z=personnage.get(i);

			if(z!=null){

				z.update(temps);	//Le personnage agit.

				//Collisions inter-personnages
				for(int j = i+1 ; j < personnage.size(); j ++){
					Personnage p2 = personnage.get(j);
					if(z.collisionDecor.intersects(p2.collisionDecor)){
						z.setLocation((int)z.lPos.x, (int) z.lPos.y);
						p2.setLocation((int)p2.lPos.x, (int) p2.lPos.y);
					}
				}

				//Collisions avec les cases
				for(int x=0; x<cases.length;x++){
					for(int y=0; y<cases[0].length; y++){ 
						if(z.collisionDecor.intersects(cases[x][y].collision))cases[x][y].inCollision(z);
					}
				}

			}//Fin de la boucle sur les personnages

			if(!z.living)personnage.remove(z); //(on enterre les morts)
		}

		//We sort the list of characters such that they superimpose correctly in the room
		sortCharacters();
	}

	/**
	 * This method generates the image of the room.
	 * It takes the image of each tyle contained in the 
	 * cases[][] array and stiches them together.
	 */
	public void generateImage(){

		//Initializing the graphic tools 
		imageSalle = new BufferedImage(ecran.width,ecran.height, BufferedImage.TYPE_INT_RGB);
		bufferImageSalle = imageSalle.createGraphics();

		//Creating the image
		for(int y=0;y<cases[0].length;y++){
			for(int x=0;x<cases.length;x++){
				if(cases[x][y] !=null){
					//The image is being created
					bufferImageSalle.drawImage(cases[x][y].image, x*Case.TAILLE,y*Case.TAILLE, Case.TAILLE, Case.TAILLE, null);

					//One places the collision box of the Case at the correct position

					this.cases[x][y].setCollisionBoxLocation(x, y);
				}
			}
		}
	}

	/**
	 * This method draws the room.
	 * @param t time parameter used by the characters to get the corresponding image.
	 * @param g the graphical space where the room will be drawn.
	 */
	public void draw(long t, Graphics g){

		//We put the room image first
		monG.drawImage(imageSalle,0,0,null);

		//We add the room number at the top left hand corner
		//This is not necessary for the game to work properly
		monG.setColor(Color.white);
		monG.drawString("Salle "+this.roomNumber, 20, 20);



		//We display the characters
		for(int i=0;i<personnage.size();i++){
			Objet z=personnage.get(i);
			if(z!=null){
				z.draw(t,monG);
			}
		}

		//Finally send everything at the destination
		g.drawImage(buffer1, ecran.x, ecran.y, null);
	}

	/**
	 * Adds the enemy given as a parameter to the room.
	 * @param e the enemy to be added.
	 */
	public void addEnemy(Ennemis e) {
		personnage.add(e);
	}

	/**
	 * This method sorts the characters in increasing y coordinate.
	 * It is used in the paint method such that the characters at 
	 * the bottom of the room are painted on top of those which are 
	 * above them and not the contrary.
	 */
	private void sortCharacters(){
		//Putting the elements in an array
		Personnage [] a = new Personnage[personnage.size()];
		
		for(int i=0; i<a.length;i++){
			a[i] = personnage.get(i);
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
		this.personnage = new ArrayList<Personnage>();
		for(int i=0;i<a.length;i++){
			this.personnage.add(a[i]);
		}

	}

	/**
	 * This method fills the empty parts of the cases
	 * array with Case_void(). It comes in handy when
	 * declaring the array of the room.
	 */
	protected void fillEmptyWithVoid(){
		for(int x=0; x<cases.length;x++){
			for(int y=0;y<cases[0].length;y++){

				if(cases[x][y]==null){
					cases[x][y]=new Case_void();
					cases[x][y].setCollisionBoxLocation(y, x);
				}
			}
		}
	}

	/**
	 * This method gives the intersection point of the doors.
	 * @return Vecteur where the paths should intersect.
	 */
	protected Vecteur getCenter(){
		double xIntersect = porte.get(Orientation.NORD).x;
		double yIntersect = porte.get(Orientation.EST).y;
		return new Vecteur(xIntersect, yIntersect);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void attaque(Orientation o) {
		this.hero.attaquer(personnage, null, o);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void stopAttaque() {

	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void deplacement(Vecteur v) {
		this.hero.marcher(v);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void utiliseObjet(int reference) {
		this.hero.utiliserObjet(reference);
	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void togglePause() {

	}

	/**
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void stopDeplacement() {
		this.hero.stop();
	}

}