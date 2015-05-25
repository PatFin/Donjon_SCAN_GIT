package fr.donjon.start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.donjon.salles.Salle;
import fr.donjon.utils.GameOverListener;
import fr.donjon.utils.ImageManager;

public class GameOver extends JPanel {

	final static String alive = "Player_Up.png";
	
	final static String dead = "Player_Dead.png";
	static String looseMessage = "Oops! Looks like you died. You made it to room "+Salle.instances+" though!";
	
	static String winMessage = "Congratulations! You went through "+Salle.instances+" rooms without a single scratch!";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton BQuit;
	
	JButton BRetour;
	Image hero;
	
	GameOverListener listener;	
	String message;
	
	final int SCREENX=800; // LONGUEUR FENETRE
	final int SCREENY=600; // LARGEUR FENETRE
	
	public GameOver(boolean win){
		super();
		
		this.setPreferredSize(new Dimension(SCREENX,SCREENY));
		
		if(win){
			this.hero = ImageManager.getImage(alive, this.getClass().getSimpleName());
			message = winMessage;
		}else{
			this.hero = ImageManager.getImage(dead, this.getClass().getSimpleName());
			message = looseMessage;
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
		
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if (hero!=null) {
			g.drawImage (hero, getWidth()/2-(hero.getWidth(null)/2), getHeight()/2-(hero.getHeight(null)/2), this);	
		}
		g.setColor(Color.white);
		g.drawString(message,(getWidth() - message.length()*5)/2,getHeight()/2 + 10 + hero.getHeight(null));
	}
}
