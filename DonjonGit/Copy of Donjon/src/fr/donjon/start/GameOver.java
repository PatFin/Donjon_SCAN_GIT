package fr.donjon.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.donjon.utils.GameOverListener;
import fr.donjon.utils.ImageManager;

public class GameOver extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	
	Image hero;
	
	final static String alive = "Player_Up.png";
	final static String dead = "Player_Dead_Alternative.png";
	
	JButton BQuit;	
	JButton BRetour;
	
	GameOverListener listener;
	
	public GameOver(boolean win){
		super();
		
		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		if(win){
			this.hero = ImageManager.getImage(alive, this.getClass().getSimpleName());
		}else{
			this.hero = ImageManager.getImage(dead, this.getClass().getSimpleName());
		}
		
		Dimension d = new Dimension(180,60);
		BQuit = new JButton("Quit");
		BRetour = new JButton("Retour");
		BQuit.setPreferredSize(d);
		BRetour.setPreferredSize(d);
		BQuit.addActionListener(new ActionListener(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.quit();
			}
		});
		BRetour.addActionListener(new ActionListener(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e){
				listener.retourMenu();
			}
		});
		
		this.add(BRetour);
		this.add(BQuit);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (hero!=null) {
			g.drawImage (hero, 0, 0, this);	
		}
	}
}
