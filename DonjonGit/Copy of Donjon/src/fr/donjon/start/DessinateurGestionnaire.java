/**
 * 
 */
package fr.donjon.start;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import fr.donjon.Donjons.GestionnaireSalle;
import fr.donjon.salles.Salle;

/**
 * @author Baptiste
 *
 */
public class DessinateurGestionnaire implements ActionListener {
	
	private int temps;
	Graphics buffer;

	Rectangle ecran;

	GestionnaireSalle gest;
	BufferedImage image;
	private Image hero;
	
	
	public DessinateurGestionnaire(Rectangle ecran, GestionnaireSalle g) {

		
		this.gest = g;
		
		this.ecran = ecran;
		
		try{
			hero = ImageIO.read(new File("Ressources/Images/Player_UP.png"));
		}catch(Exception e){
			
		}
		
		image = new BufferedImage(ecran.width, ecran.height, BufferedImage.TYPE_INT_ARGB);
		buffer = image.createGraphics();

	}

	/**
	 * Cette methode dessine une image du donjon à partir du tableau de salles fourni.
	 * Une image du hero clignotante sera presente dans la salle current fourni en parametre.
	 * @param sMap le tableau de salle a dessiner
	 * @param current la salle où l'on souhaite voir une image du hero. Dans le panelJeu, bien s'assurer qu'on envoi bien la salle actuelle, envoyer une autre salle n'a pas de sens.
	 * @param tempsTimer 
	 * @return Une image du donjon.
	 */
	public BufferedImage getImage(Salle[][] sMap, Salle current){

		int sw = 200;
		int sh = 200;

		int w = sw*sMap.length;
		int h = sh*sMap[0].length;
		
		int d = w >= h ? w : h;
		
		BufferedImage image = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
		Graphics buffer = image.createGraphics();
		
		BufferedImage imT;
		Graphics bufT;
		
		//Parcours du tableau de salles
		for(int y=0; y < sMap[0].length ;y++){
			for(int x=0; x < sMap.length ; x++){
				
				if(sMap[x][y] != null){
					
					Salle salle = sMap[x][y];
					
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
					
					
					
					//Ajout de l'image du hero s'il s'agit de la salle actuelle.
					if(salle == current){
						bufT.drawImage(hero,0,0,imT.getHeight(), imT.getWidth(), null);
					}
					buffer.drawImage(imT, x*sw, y*sh, sw, sh, null);
					
				}
			}
		}
		
		return image;
		
	}
	
	/**
	 * Methode appelee par le timer private. On incremente juste le compteur qui sert à faire clignoter l'image
	 * du hero dans la salle actuelle. 
	 * @param arg0
	 * @see fr.donjon.start.DessinateurGestionnaire#getImage(Salle[][], Salle, long)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.temps++;
	}
	
}
