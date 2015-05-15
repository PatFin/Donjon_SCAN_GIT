/**
 * 
 */
package fr.donjon.testblac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.donjon.utils.Vecteur;
import fr.donjon.zpoubelle.Case;

/**
 * @author Baptiste
 *
 */
public class DessinateurGestionnaire {


	GestionnaireSalle gest;

	Rectangle ecran;

	BufferedImage imageSol;
	Graphics bufferSol;

	BufferedImage image;
	Graphics buffer;

	Vecteur centrage;

	int salleW;
	int salleH;
	
	

	public DessinateurGestionnaire(Rectangle ecran, GestionnaireSalle g) {

		this.gest = g;
		
		salleW = (Salle.W+2) * Case.TAILLE;
		salleH = (Salle.H+2) * Case.TAILLE;
		
		this.ecran = ecran;
		
		image = new BufferedImage(gest.smap.length * salleW, gest.smap[0].length * salleH, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();

		creerImageSol();
	}

	public BufferedImage getImage(Graphics g, long t){

		buffer.drawImage(imageSol,0,0,null);

		return image;
	}
	
	public void updateImage(){
		creerImageSol();
	}
	

	private void creerImageSol(){

		imageSol = new BufferedImage(gest.smap.length * salleW, gest.smap[0].length * salleH, BufferedImage.TYPE_INT_ARGB);
		bufferSol = imageSol.createGraphics();

		BufferedImage imT;
		Graphics bufT;
		
		//Creating the image
		for(int y=0; y < gest.smap[0].length ;y++){
			for(int x=0; x < gest.smap.length ; x++){
				
				if(gest.smap[x][y] != null){
					
					Salle salle = gest.smap[x][y];
					
					imT = new BufferedImage(salleW, salleH, BufferedImage.TYPE_INT_ARGB);
					bufT = imT.createGraphics();
					
					
					for(int j=0 ; j < salle.cases[0].length ; j++){
						for(int i=0 ; i < salle.cases.length ; i++){
							if(salle.cases[i][j] !=null){
								
								bufT.drawImage(salle.cases[i][j].image, i*Case.TAILLE,j*Case.TAILLE, Case.TAILLE, Case.TAILLE, null);
								
							}
						}
					}
					
					
					bufferSol.drawImage(imT, (int) (x * salleW), (int) (y*salleH), null);
					
				}
			}
		}
	}
	
	public Vecteur getSize(){
		return new Vecteur(gest.smap.length * salleW, gest.smap[0].length * salleH);
	}
}
