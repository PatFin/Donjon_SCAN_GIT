package fr.donjon.classes.salles;

import java.awt.Rectangle;

import fr.donjon.classes.Heros;
import fr.donjon.classes.cases.Case;
import fr.donjon.classes.cases.Case_porte;
import fr.donjon.classes.cases.Case_void;
import fr.donjon.editor.SalleDescription;
import fr.donjon.utils.CustomException;
import fr.donjon.utils.Link;
import fr.donjon.utils.Orientation;

public class SalleEditeur extends SalleAbs {

	SalleDescription s;
	
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
			throw new CustomException("4 doors were not found in this room. It can't work with the rest of the donjon");
		}

	}

	@Override
	protected void setDoorPlaces() {
		//We go through the array of description and detect a door
		Case[][] description = s.getMatrix();
		
		for(int i=0; i<description.length; i++){
			for(int j=0; j<description[0].length; j++){
				
				//We have a door
				if(description[i][j] instanceof Case_porte){
					
					//We look around this door looking for the landing place of the character
					
					
					
				}
				
				
			}
		}
			
		
		
		

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
