/**
 * 
 */
package fr.donjon.start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.donjon.Donjons.GestionnaireSalle;
import fr.donjon.salles.Salle;

/**
 * @author Baptiste
 *
 */
public class DessinateurGestionnaire {


	Graphics buffer;

	Rectangle ecran;

	GestionnaireSalle gest;
	BufferedImage image;

	
	
	public DessinateurGestionnaire(Rectangle ecran, GestionnaireSalle g) {

		this.gest = g;
		
		this.ecran = ecran;
		
		image = new BufferedImage(ecran.width, ecran.height, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();

	}

	public BufferedImage getImage(){

		int sw = 200;
		int sh = 200;

		int w = sw*gest.smap.length;
		int h = sh*gest.smap[0].length;
		
		int d = w >= h ? w : h;
		
		image = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();
		
		BufferedImage imT;
		Graphics bufT;
		
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, d, d);
		
		
		//Parcours du gestionnaire
		for(int y=0; y < gest.smap[0].length ;y++){
			for(int x=0; x < gest.smap.length ; x++){
				
				if(gest.smap[x][y] != null){
					
					Salle salle = gest.smap[x][y];
					
					imT = new BufferedImage( (int) salle.getPixelSize().x, (int) salle.getPixelSize().y,
							BufferedImage.TYPE_INT_ARGB);
					
					
					bufT = imT.createGraphics();
					
					//Parcours des cases de la salle
					for(int j=0 ; j < salle.cases[0].length ; j++){
						for(int i=0 ; i < salle.cases.length ; i++){
							if(salle.cases[i][j] !=null){
								
								salle.cases[i][j].draw(bufT,0, i, j);
								
							}
						}
					}
					
					
					buffer.drawImage(imT, x*sw, y*sh, sw, sh, null);
					
				}
			}
		}
		
		return image;
		
	}
	
}
