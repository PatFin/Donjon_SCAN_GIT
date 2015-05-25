/**
 * 
 */
package fr.donjon.cases;

import java.awt.Graphics;

import fr.donjon.utils.ImageManager;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CaseSource extends Case {

	CaseMap map = ImageManager.map1;
	Vecteur positionInMap;
	
	/**
	 * @param src
	 */
	public CaseSource(Vecteur pos) {
		super("case_map_1.png");
		this.positionInMap = pos;
	}

	@Override
	public Case clone() {
		return new CaseSource(this.positionInMap);
	}
	
	 @Override
	public void draw(Graphics g, long t, int x, int y) {
		map.drawPosition(g, x, y, positionInMap);
	}

}
