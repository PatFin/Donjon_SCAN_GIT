/**
 * 
 */
package fr.donjon.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

/**
 * 
 * Cette classe permet d'adapter les evenements du clavier au jeu
 * On retranscrit les touches en methodes attaquer, marcher, etc... 
 * 
 * @author Baptiste
 *
 */
public class JeuKeyAdapter implements KeyListener {

	private EcouteurClavier ecouteur;	//La classe qui attend un retour sur evenement

	boolean attaque;
	boolean deplacement;

	LinkedList<Integer> tattk;
	LinkedList<Integer> tdepl;
	LinkedList<Integer> tobj;
	LinkedList<Integer> pressed;
	

	/**
	 * @param ecouteur La classe implementant l'ecouteur
	 */
	public JeuKeyAdapter(EcouteurClavier ecouteur) {
		// TODO Auto-generated constructor stub
		this.ecouteur = ecouteur;
		this.attaque = false;
		this.deplacement = false;

		this.tattk = new LinkedList<Integer>();
		this.tdepl = new LinkedList<Integer>();
		this.tobj = new LinkedList<Integer>();
		this.pressed  = new LinkedList<Integer>();
		
		tattk.add(38);
		tattk.add(40);
		tattk.add(39);
		tattk.add(37);

		tdepl.add(90);
		tdepl.add(83);
		tdepl.add(68);
		tdepl.add(81);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(!pressed.contains(code))pressed.add(code);
		handleEvent();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Integer code = e.getKeyCode();
		if(pressed.contains(code))pressed.remove(code);
		handleEvent();
	}
	
	private void handleEvent(){
		
		Vecteur vit = new Vecteur(0,0);
		boolean arret = true;
		
		for( Integer i : pressed ){
			
			if(tattk.contains(i)){
				if(tattk.get(0) == i)ecouteur.attaque(Orientation.NORD);
				else if(tattk.get(1) == i)ecouteur.attaque(Orientation.SUD);
				else if(tattk.get(2) == i)ecouteur.attaque(Orientation.EST);
				else if(tattk.get(3) == i)ecouteur.attaque(Orientation.OUEST);
			}
			
			else if(tdepl.contains(i)){
				
				if(tdepl.get(0) == i)vit = vit.ajoute(Vecteur.vNord);
				if(tdepl.get(1) == i)vit = vit.ajoute(Vecteur.vSud);
				if(tdepl.get(2) == i)vit = vit.ajoute(Vecteur.vEst);
				if(tdepl.get(3) == i)vit = vit.ajoute(Vecteur.vOuest);
				arret = false;
				ecouteur.deplacement(vit.normalise());
			}
			
			else if (tobj.contains(i)){
				
			}
			
		}
		
		if(arret)ecouteur.stopDeplacement();
		
	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}




}
