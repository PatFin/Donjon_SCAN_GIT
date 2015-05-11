/**
 * 
 */
package fr.donjon.testblac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.donjon.classes.Objet;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.Case;

/**
 * @author Baptiste
 *
 */
public class DessinateurSalle {


	Salle salle;

	Rectangle ecran;

	BufferedImage imageSol;
	Graphics bufferSol;

	BufferedImage image;
	Graphics buffer;

	Vecteur centrage;

	/**
	 * 
	 */
	public DessinateurSalle(Rectangle ecran, Salle s) {

		this.salle = s;
		
		this.ecran = ecran;

		
		image = new BufferedImage(salle.cases.length * Case.TAILLE, salle.cases[0].length * Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();

		creerImageSol();
	}

	public BufferedImage getImage(Graphics g, long t){

		//buffer.drawImage(imageSol,0,0,null);

		for(int y=0;y<salle.cases[0].length;y++){
			for(int x=0;x<salle.cases.length;x++){
				
				salle.cases[x][y].draw(buffer, t, x, y);
				
				
			}
		}
		
		//We add the room number at the top left hand corner
		//This is not necessary for the game to work properly
		buffer.setColor(Color.white);
		buffer.drawString("Salle "+salle.roomNumber, 20, 20);

		for( Projectile p : salle.projectiles){
			
			p.draw(t, buffer);
			
		}

		//We display the characters
		for(int i=0;i<salle.personnages.size();i++){
			Objet z=salle.personnages.get(i);
			if(z!=null){
				z.draw(t,buffer);
			}
		}

		return image;

	}
	
	public void changerSalle(Salle s){
		
		this.salle = s;
		
		image = new BufferedImage(salle.cases.length * Case.TAILLE, salle.cases[0].length * Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();
		
		creerImageSol();
		
	}

	private void creerImageSol(){

		for(int y=0;y<salle.cases[0].length;y++){
			for(int x=0;x<salle.cases.length;x++){
				
				this.salle.cases[x][y].setCollisionBoxLocation(x, y);
			}
		}
		
		
		/*
		imageSol = new BufferedImage(salle.cases.length * Case.TAILLE, salle.cases[0].length * Case.TAILLE, BufferedImage.TYPE_INT_ARGB);
		bufferSol = imageSol.createGraphics();

		//Creating the image
		for(int y=0;y<salle.cases[0].length;y++){
			for(int x=0;x<salle.cases.length;x++){
				if(salle.cases[x][y] !=null){
					//The image is being created
					bufferSol.drawImage(salle.cases[x][y].image, x*Case.TAILLE,y*Case.TAILLE, Case.TAILLE, Case.TAILLE, null);

					this.salle.cases[x][y].setCollisionBoxLocation(x, y);
				}
			}
		}
		
		*/
	}
	
	public Vecteur getSize(){
		return new Vecteur(salle.cases.length*Case.TAILLE,salle.cases[0].length*Case.TAILLE);
	}
}
