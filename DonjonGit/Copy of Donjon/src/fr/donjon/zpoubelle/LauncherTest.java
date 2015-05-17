/**
 * 
 */
package fr.donjon.zpoubelle;

import javax.swing.JFrame;

import fr.donjon.utils.EcouteurClavier;
import fr.donjon.utils.JeuKeyAdapter;
import fr.donjon.utils.Orientation;
import fr.donjon.utils.Vecteur;

/**
 * @author Baptiste
 *
 */
public class LauncherTest extends JFrame implements EcouteurClavier{

	/**
	 * 
	 */
	PanelJeu panel;
	
	public LauncherTest() {
		super("TEST CONFIG");
		
		panel = new PanelJeu();
		
		add(panel);
		
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(new JeuKeyAdapter(this));
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}


	

	public static void main(String[] args) {
		
		LauncherTest t = new LauncherTest();

	}


	///////////////////////////////////////////////////////////////
	///INTERFACE D'ECOUTE//////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	
	//On fait passer les evenements au jeu
	
	@Override
	public void attaque(Orientation o) {
		panel.attaque(o);
	}

	@Override
	public void stopAttaque() {
		panel.stopAttaque();
	}

	@Override
	public void deplacement(Vecteur v) {
		panel.deplacement(v);
	}

	@Override
	public void utiliseObjet(int reference) {
		panel.utiliseObjet(reference);
	}

	@Override
	public void togglePause() {
		panel.togglePause();
	}

	@Override
	public void stopDeplacement() {
		panel.stopDeplacement();
	}




	@Override
	public void toggleMap() {
		// TODO Auto-generated method stub
		
	}
	
	

}
