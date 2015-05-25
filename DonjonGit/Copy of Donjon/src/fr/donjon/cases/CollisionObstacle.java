/**
 * 
 */
package fr.donjon.cases;

import java.awt.Rectangle;

import fr.donjon.classes.Personnage;
import fr.donjon.classes.Projectile;
import fr.donjon.utils.Type;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class CollisionObstacle implements CollisionPattern {

	Rectangle l;
	
	

	public CollisionObstacle(Rectangle l) {
		this.l = l;
	}

	@Override
	public void persoCollision(Personnage p) {
		
		if(p.type == Type.ENNEMI){

			Vecteur v = new Vecteur( p.image.x + p.image.width/2 - (l.x + l.width/2),
					p.image.y + p.image.height/2 - (l.y + l.height/2));
			
			v = v.normalise();
			
			p.vvitesse = v;
		} 
		else {
			p.setLocation(p.lPos);
		}

	}

	@Override
	public void persoEnterCase(Personnage p) {
	}


	@Override
	public void persoLeaveCase(Personnage p) {
	}


	@Override
	public void projEnterCase(Projectile p) {
		p.living = false;
	}


	@Override
	public void projLeaveCase(Projectile p) {

	}


	@Override
	public String toString(){
		return "Obstacle";
	}

}
