package fr.donjon.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.LinkedList;

import fr.donjon.classes.Ennemis;
import fr.donjon.classes.Heros;
import fr.donjon.classes.Link;
import fr.donjon.classes.Objet;
import fr.donjon.classes.Personnage;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.test.EcouteurChangementSalle;
import fr.donjon.utils.EcouteurClavier;
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
	public LinkedList <Personnage> personnage; 				//Contains the character of the room (Ennemis and Heros).
	public Heros hero;										//The character the player controls.

	public BufferedImage imageSalle;  						//Contains the image of the room without the objects.
	public Graphics bufferImageSalle;            			//Graphical space associated to imageSalle.

	public BufferedImage buffer1;							//Buffer used in the draw method.
	public Graphics monG;									//Graphical space associated to buffer1.


	/**
	 * Constructor
	 * @param ecran the space available for the game
	 * @param h the character the player can control
	 */
	public SalleAbs(Rectangle ecran, Heros h) {
		this.roomNumber=numberOfRooms;
		SalleAbs.numberOfRooms++;

		SalleAbs.ecran=ecran;
		this.hero=h;
		this.personnage = new LinkedList <Personnage> ();
		this.personnage.add(hero);

		this.link = new EnumMap<Orientation, Link>(Orientation.class);

		buffer1 =new BufferedImage(ecran.width,ecran.height,BufferedImage.TYPE_INT_ARGB);
		monG = buffer1.getGraphics();

		generateRoom();
		setDestinationPlaces();
		setDoorPlaces();
	}


	protected abstract void generateRoom();
	protected abstract void setDestinationPlaces();
	protected abstract void setDoorPlaces();

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

		this.link.put(a, new Link(l.origineSalle, l.origineSalle.destination.get(l.orientation), this, this.porte.get(a), a, true)); //crer une methode reciprocal dans la classe Link.
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
						//If the character is in collision with a tile, apply method inCollision of the corresponding tile. 

						if(z.collisionDecor.intersects(cases[x][y].collision)){	
							cases[x][y].inCollision(z);
						}

					}
				}
			}
		}
	}


	/**
	 * This method generates the image of the room.
	 * It takes the image of each tyle contained in the 
	 * cases[][] array and stiches them together.
	 */
	protected void generateImage(){

		//Initialysing the graphic tools 
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
	 * This method fills the empty parts of the cases
	 * array with Case_voi(). It comes in handy when
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
	 * Method inherited from EcouteurClavier
	 */
	@Override
	public void attaque(Orientation o) {
		this.hero.attaquer(null, null, o);
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
		// TODO implement the method
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