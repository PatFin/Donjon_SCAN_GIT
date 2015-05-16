package fr.donjon.zpoubelle;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.cases.Case;
import fr.donjon.classes.Heros;
import fr.donjon.editor.SalleDescription;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;



public class SalleEditeur extends SalleAbs {

	SalleDescription s;
	EnumMap<Orientation, Case_porte> porte_case;
	
	
	/**
	 * Constructeur de la premi�re salle qui n'a qu'une seule porte vers une autre salle
	 * NB : cette m�thode est appel�e par le cl�ne. Il faut toujours avoir essay� d'initialis�
	 * cette salle  
	 * @param ecran l'espace de jeu
	 * @param h le h�ros control� par le joueur 
	 * @param o l'orientation de la porte qui doit �tre cr��
	 * @param s la description de la salle.
	 * @throws CustomException
	 */
	public SalleEditeur(Rectangle ecran, Heros h, Orientation o, SalleDescription s) throws CustomException {
		super(ecran,h);
		
		this.s=s;
		
		generateRoom();
		setDoorPlaces();
		
		//Adding the doors to the next room
		this.addDoor(o,true);
		
		//generating the image of the room after all the tiles have been set
		this.generateImage();
		
		//Placing the character in the middle of the room.
		Vecteur v = this.getCenter();
		hero.setLocation((int)v.x*Case.TAILLE, (int)v.y*Case.TAILLE);
	}
	
	/**
	 * Constructeur quand on veut cr�er une nouvelle instance de ce genre de salle
	 * @param h le h�ros control� par le joueur
	 * @param l le lien de la salle pr�c�dente vers cette nouvelle salle
	 * @throws CustomException 
	 */
	public SalleEditeur(Heros h, Link l, SalleDescription s) throws CustomException {
		super(ecran, h);
		this.s = s;

		generateRoom();
		setDoorPlaces();
		
		this.addDoorToPrevRoom(l);
		
		this.generateEnnemis();
	}

	
	/**
	 * Constructeur vide utilis� lors de la cr�ation de la liste des salles disponibles dans le gestionnaire.
	 * Cette m�thode convertit la description de la salle cr�� dans l'�diteur en une salle jouable.
	 * @param s la description de la salle. 
	 */
	public SalleEditeur(SalleDescription s)throws CustomException {
		this.s = s;
		
		//We try to generate the room
		//At this point the process might fail in the gestionnaire initialisation
		//The Exception is caught by a try/catch block.
		generateRoom();
		setDoorPlaces();
		
	}

	@Override
	protected void generateRoom() throws CustomException {
		int nbPortes=0;
		
		Case[][] description = s.getMatrix(); 
		
		this.cases = new Case[s.getWidth()][s.getHeight()];
		
		for(int i=0; i<description.length; i++){
			for(int j=0; j<description[0].length; j++){
				//Si la case n'est pas du genre porte, c'est une case standard
				//On l'ajoute donc dans notre tableau de case
				if(!(description[i][j] instanceof Case_porte)){
					this.cases[i][j] = description[i][j].clone();
				}else{
					//Si c'est une case porte, alors on la remplace par une case void
					this.cases[i][j] = new Case_void();
					nbPortes++;
				}
				
			}
		}
		if(nbPortes!=4){
			//Send an error
			throw new CustomException(nbPortes+" doors were not found in this room. It can't work with the rest of the donjon. Too bad :D");
		}

	}

	@Override
	protected void setDoorPlaces() throws CustomException {
		//We go through the array of description and detect a door
		Case[][] description = s.getMatrix();
		
		//Initialisation of the enumMaps
		this.porte=new EnumMap<Orientation, Vecteur>(Orientation.class);
		this.destination=new EnumMap<Orientation, Vecteur>(Orientation.class);
		this.porte_case = new EnumMap<Orientation, Case_porte>(Orientation.class);
		
		for(int i=0; i<description.length; i++){
			for(int j=0; j<description[0].length; j++){
				
				//We have a door
				if(description[i][j] instanceof Case_porte){
					
					
					
					//We look around this door looking for the landing place of the character
					Vecteur porte = new Vecteur(i,j);
					Vecteur destination = getAvailableCaseNear(porte);
					
					//If no appropriate tile was found, we send an exception.
					if(destination.x == -1){
						throw new CustomException("Aucune case disponible n'a �t� trouv�e autour d'une porte.");
					}
					
					//We set the appropriate door in the correct direction:
					Orientation o = Vecteur.projectMainDirection(destination, porte);
					
					//We store the information in the enumMaps.
					this.porte.put(o, porte);
					this.destination.put(o, destination);
					porte_case.put(o, (Case_porte) description[i][j].clone());
				}
				
				
			}
		}
		
		//We check if all the doors have been found in the room
		if(!(this.porte.containsKey(Orientation.NORD) && this.porte.containsKey(Orientation.SUD) && porte.containsKey(Orientation.OUEST) && porte.containsKey(Orientation.EST))){
			throw new CustomException("Toutes les portes n'ont pas �t� trouv�es. Il doit y en avoir en double!");
		}
		
		
	}
	
	private Vecteur getAvailableCaseNear(Vecteur v){
		Vecteur a = new Vecteur(-1,-1);
		for(int i=-1; i<2;i=i+2){
			try{
				if(!(cases[(int)v.x][(int)v.y+i] instanceof Case_Obstacle)){
					a.setLocation((int)v.x,(int)v.y +i);
					break;
				}
				
				
			}catch(Exception e){
				//catches a potential out of bound exception
			}
			
			try{
				if(!(cases[(int)v.x+i][(int)v.y] instanceof Case_Obstacle)){
					a.setLocation((int)v.x+i,(int)v.y);
					break;
				}
				
			}catch(Exception e){
				//catches a potential out of bound exception
			}
		}
		return a;
	}
	
	@Override
	public void addDoor(Orientation o, boolean enabled){
		Vecteur v = porte.get(o);
		this.cases[(int)v.x][(int)v.y] = porte_case.get(o).clone();
		
		super.addDoor(o, enabled);
	}
	
	@Override
	public void addDoorToPrevRoom(Link l){
		Orientation a= Orientation.opposite(l.orientation);
		Vecteur v = porte.get(a);
		this.cases[(int)v.x][(int)v.y] = porte_case.get(a).clone();
		
		super.addDoorToPrevRoom(l);
	}
	

	@Override
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) throws CustomException {
		return new SalleEditeur(ecran, h, o,this.s);
	}

	@Override
	public SalleAbs clone(Heros h, Link l) throws CustomException {
		return new SalleEditeur(h,l, this.s);
	}

}
