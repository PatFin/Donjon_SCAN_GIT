package fr.donjon.classes.salles;

import java.awt.Rectangle;
import java.util.EnumMap;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_Obstacle;
import fr.donjon.classes.cases.Case_porte;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.editor.SalleDescription;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

public class SalleEditeur extends SalleAbs {

	SalleDescription s;
	EnumMap<Orientation, Case_porte> porte_case;
	
	
	/**
	 * Constructeur quand on veut créer une nouvelle instance de ce genre de salle
	 * @param h le héros controlé par le joueur
	 * @param l le lien de la salle précédente vers cette nouvelle salle
	 * @throws CustomException 
	 */
	public SalleEditeur(Heros h, Link l) throws CustomException {
		super(ecran, h);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Constructeur vide utilisé lors de la création de la liste des salles disponibles dans le gestionnaire.
	 * Cette méthode convertit la description de la salle créé dans l'éditeur en une salle jouable.
	 * @param s la description de la salle. 
	 */
	public SalleEditeur(SalleDescription s) {
		// TODO Auto-generated constructor stub
		this.s = s;
		
		
	}

	@Override
	protected void generateRoom() throws CustomException {
		// TODO Auto-generated method stub
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
			throw new CustomException("4 doors were not found in this room. It can't work with the rest of the donjon. Too bad :D");
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
						throw new CustomException("Aucune case disponible n'a été trouvée autour d'une porte.");
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
			throw new CustomException("Toutes les portes n'ont pas été trouvées. Il doit y en avoir en double!");
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
	public SalleAbs clone(Rectangle ecran, Heros h, Orientation o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalleAbs clone(Heros h, Link l) {
		// TODO Auto-generated method stub
		return null;
	}

}
