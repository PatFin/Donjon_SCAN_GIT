package fr.donjon.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class GameOverDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3023311581966527573L;

	JButton BQuit;	
	JButton BRetour;
	
	Box content; //Le contenu graphique du JDialog
	
	GameOverListener ecouteur;
	
	/**
	 * Constructeur
	 * @param w true si le jeu a été remporté, false si le héro a été tué
	 */
	public GameOverDialog(JFrame parent, boolean modal, GameOverListener l){
		super(parent, "GAME OVER", true);
		
		ecouteur = l;
		
		//Centrage
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		Dimension d = new Dimension(60,40);
		
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
				ecouteur.quit();
				setVisible(false);
			}
		});
		
		BRetour.addActionListener(new ActionListener(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e){
				ecouteur.retourMenu();
				setVisible(false);
			}
		});
		
		Box box1 = Box.createHorizontalBox();
		box1.add(BRetour);
		box1.add(BQuit);
		
		this.getContentPane().add(box1, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	
}
