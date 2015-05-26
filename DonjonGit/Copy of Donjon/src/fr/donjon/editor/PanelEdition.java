package fr.donjon.editor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.donjon.cases.Case;
import fr.donjon.cases.CaseDalle;
import fr.donjon.cases.CaseVide;
import fr.donjon.utils.Vecteur;

/**
 * 
 */

/**
 * JPanel personnalis� permettant l'affichage de la zone de dessin de l'�diteur 
 * 
 * @author Baptiste
 *
 */
public class PanelEdition extends JPanel implements MouseMotionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int panelHeight = 600;
	final static int panelWidth = 900;

	Graphics buffer;
	Graphics bufferBrosse;
	Graphics bufferGrid;

	Case[][] cases;					//La matrice contenant le dessin
	Case caseT;						//La case actuellement desin�e

	int dim;						//Dimension d'affichage d'une case en pixels
	int height;		//Nombre de cases en hauteur
	BufferedImage image;			//Image principale
	BufferedImage imageBrosse;		//Image de la brosse
	BufferedImage imageGrid;		//Image de la grille
	boolean initialized = false;	//Fenetre initialisée ou pas
	int thickness;	//Epaisseur de la brosse 
	int width;		//Nombre de cases en largeur

	private Vecteur mouse = new Vecteur(0,0);	//Position de la souris

	/**
	 * Initialise un panel d'edition
	 */
	public PanelEdition(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.thickness = 1;
		this.initialized = false;
		this.caseT = new CaseDalle(); //Case par defaut

		image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
		imageGrid = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);

		bufferGrid = imageGrid.createGraphics();
		buffer = image.getGraphics();

	}

	public PanelEdition(Case[][] cases){
		this();
		this.width = cases.length;
		this.height = cases[0].length;
		this.initialized = true;
		this.cases = cases;
		
		int espX = panelWidth / this.width ;	//Taille d'une case sur X
		int espY = panelHeight / this.height;	//Taille d'une case sur Y
		dim = espX >= espY ? espY : espX;		//Calcul taille de la case carré( min(expX,expY) )
		
		imageBrosse = new BufferedImage(6*dim, 6*dim, BufferedImage.TYPE_INT_ARGB);
		bufferBrosse = imageBrosse.createGraphics();

		setThickness(1);
		generateGrid();
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	/**
	 * Initialisation complète
	 * 
	 * @param width		Nombre de cases en largeur
	 * @param height	Nombre de cases en hauteur
	 */
	public PanelEdition(int width, int height){
		this(new Case[width][height]);
		fill(new CaseVide());
	}

	/**
	 * Ajoute des cases en prenant en compte la taille de la brosse
	 */
	private void addCases(){
		
		Vecteur c = new Vecteur(0,0);
		int offset = (dim*thickness/2);

		for(int y = (int) ((mouse.y-offset)+dim/2) ; y < (mouse.y+offset) ; y+=dim){
			for(int x = (int) ((mouse.x-offset)+dim/2) ; x < (mouse.x+offset) ; x+=dim){
				c = getCoordinates(x, y); //On simule le d�placement de la souris
				if( c.x >= 0 && c.x < width && c.y >= 0 && c.y < height){
					cases[(int) c.x][(int) c.y] = caseT.clone();
				}
				
			}
		}
		
	}

/**
 * Generates the image of the grid (needs to do this just once)
 */
private void generateGrid(){
	
	bufferGrid.setColor(Color.cyan);

	for(int y = 0 ; y <= height ; y++){
		bufferGrid.drawLine(0, y*dim, width*dim, y*dim);
	}
	for(int x = 0 ; x <= width ; x++){

		bufferGrid.drawLine(x*dim, 0, x*dim, height*dim);
	}

}
	
	/**
	 * Returns the coordinates on the grid cooresponding to that of the mouse on the screen
	 */
	private Vecteur getCoordinates(int x, int y){
		return new Vecteur(x/dim, y/dim);
	}

	/**
	 * Draw the brosse image on the buffer
	 * @param g
	 */
	private void paintBrosse(Graphics g){

		int offSet = (dim*thickness)/2;
	
		g.drawImage(imageBrosse, (int)(mouse.x-offSet), (int)(mouse.y-offSet), this);

	}

	/**
	 * Draw the cases on the buffer
	 * @param g
	 */
	private void paintCases(Graphics g){
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ;  x++){
				if(cases[x][y] != null)g.drawImage(cases[x][y].image, x*dim, y*dim, dim, dim, null);
			}
		}

	}

	public void changeCase(Case c){
		this.caseT = c;
		setThickness(this.thickness);
		repaint();
	}

	/**
	 * Permet de remplir la grille avec une case
	 * @param c	Case de remplissage
	 */
	public void fill(Case c){
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				cases[x][y] = c.clone();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { //On ajoute des cases si la souris est cliqu�e
		mouse.setLocation(e.getX(), e.getY());	
		addCases();
		repaint();
	}

	////////INTERFACE DE SUIVI DE LA SOURIS//////////////////////
	@Override
	public void mouseDragged(MouseEvent e) { //Souris cliqu�e et d�place en meme temps -> ajout de cases
		mouse.setLocation(e.getX(), e.getY());	
		addCases();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {	//On suit juste la souris pour que la brosse se d�place
		mouse.setLocation(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//Draw the background of the Panel in gray
		buffer.setColor(Color.LIGHT_GRAY);
		buffer.fillRect(0, 0, panelWidth, panelHeight);
		
		//Draw the cases on the buffer
		paintCases(buffer);
		//Draw the brosse on the buffer
		paintBrosse(buffer);
		//Draw the grid on the buffer
		buffer.drawImage(imageGrid, 0, 0, this);
		
		//Draw the total image on the screen
		g.drawImage(image, 0, 0, this);

	}

	public void reinitialize(Case[][] cases){
			
			this.caseT = new CaseDalle();
	
			image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
			imageGrid = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
	
			bufferGrid = imageGrid.createGraphics();
			buffer = image.getGraphics();
			
			this.width = cases.length;
			this.height = cases[0].length;
			this.initialized = true;
			this.cases = cases;
			
			int espX = panelWidth / this.width ;
			int espY = panelHeight / this.height;
			dim = espX >= espY ? espY : espX;
			
			imageBrosse = new BufferedImage(6*dim, 6*dim, BufferedImage.TYPE_INT_ARGB);
			bufferBrosse = imageBrosse.createGraphics();
	
			setThickness(1);
			
			generateGrid();
		}

	/**
	 * 
	 * Remet a zéro la fenètre
	 * 
	 * @param w	Taille x
	 * @param h	Taille y
	 */
	public void reinitialize(int w, int h){
		reinitialize(new Case[w][h]);
		this.fill(new CaseVide());
	}

	/**
	 * Sets the thickness and generates the image of the brosse
	 * @param t
	 */
	public void setThickness(int t){

		this.thickness = t;
		
		imageBrosse = new BufferedImage(dim*6, dim*6, BufferedImage.TYPE_INT_ARGB);
		bufferBrosse = imageBrosse.createGraphics();
		
		for(int j = 0 ; j < thickness ; j++){
			for(int i = 0 ; i < thickness ; i++){
				bufferBrosse.drawImage(caseT.image, i*dim, j*dim, dim,dim, this);
			}
		}
		repaint();
	}




}
