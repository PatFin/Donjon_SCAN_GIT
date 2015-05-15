package fr.donjon.cases2;

import java.awt.Color;
import java.awt.Graphics;

public class CaseSwitch extends Case {

	final static String image = "Case_dalle.png";
	CollisionSwitch colliSwitch;
	
	public CaseSwitch() {
		super(image);
		this.colliSwitch = new CollisionSwitch();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.donjon.cases2.Case#draw(java.awt.Graphics, long, int, int)
	 */
	@Override
	public void draw(Graphics g, long t, int x, int y){
		if(colliSwitch.on){
			g.setColor(Color.green);
		}else{
			g.setColor(Color.red);
		}
		g.fillRect(x*TAILLE, y*TAILLE, TAILLE, TAILLE);
	}
	
	
	@Override
	public Case clone(){
		return new CaseSwitch();
	}
	
}
