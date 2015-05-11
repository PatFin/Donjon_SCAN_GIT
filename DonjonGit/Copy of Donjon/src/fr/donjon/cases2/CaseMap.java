/**
 * 
 */
package fr.donjon.cases2;

import java.awt.Graphics;
import java.awt.Image;

import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Vecteur;


/**
 * @author Baptiste
 *
 */
public class CaseMap {

	public int w;
	public int h;

	Image source ;
	int mapCut ;
	
	/**
	 * 
	 */
	public CaseMap(String src, int cut) {
		this.source = ImageManager.getImage(src, this.getClass().getSimpleName());
		this.mapCut = cut;
		
		w = (int) source.getWidth(null)/mapCut;
		h = (int) source.getHeight(null)/mapCut;
	}
	
	public void drawPosition(Graphics g, int x, int y, Vecteur position){
		
		g.drawImage(source, Case.TAILLE*x,Case.TAILLE*y , Case.TAILLE*(x+1), Case.TAILLE*(y+1),
				(int)position.x*mapCut, (int)position.y*mapCut, (int)(position.x+1)*mapCut, (int)(position.y+1)*mapCut
				,null );
	}
	
	
	
	

}
