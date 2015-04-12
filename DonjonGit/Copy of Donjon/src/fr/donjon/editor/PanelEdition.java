package fr.donjon.editor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.donjon.classes.Case;
import fr.donjon.classes.Case_dalle_sol;
import fr.donjon.classes.Case_void;
import fr.donjon.utils.Vecteur;

/**
 * 
 */

/**
 * @author Baptiste
 *
 */
public class PanelEdition extends JPanel implements MouseMotionListener, MouseListener{

	final static int panelWidth = 900;
	final static int panelHeight = 600;

	int width;
	int height;
	int thickness;

	boolean initialized = false;
	int dim;

	Case caseT;
	Case[][] cases;
	BufferedImage imageGrid;
	BufferedImage imageBrosse;
	BufferedImage image;
	Graphics bufferGrid;
	Graphics bufferBrosse;
	Graphics buffer;

	private Vecteur mouse = new Vecteur(0,0);

	public PanelEdition(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.thickness = 1;
		this.initialized = false;
		this.caseT = new Case_dalle_sol();

		image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
		imageGrid = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);

		bufferGrid = imageGrid.createGraphics();
		buffer = image.getGraphics();

	}

	public PanelEdition(int width, int height){
		this();
		this.width = width;
		this.height = height;
		this.initialized = true;
		this.cases = new Case[width][height];
		this.fill(new Case_void());
		
		int espX = panelWidth / this.width ;
		int espY = panelHeight / this.height;
		dim = espX >= espY ? espY : espX;
		
		imageBrosse = new BufferedImage(6*dim, 6*dim, BufferedImage.TYPE_INT_ARGB);
		bufferBrosse = imageBrosse.createGraphics();

		setThickness(1); //To modify

		generateGrid();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	public void reinitialize(int w, int h){
		
		this.caseT = new Case_dalle_sol();

		image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
		imageGrid = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);

		bufferGrid = imageGrid.createGraphics();
		buffer = image.getGraphics();
		
		this.width = w;
		this.height = h;
		this.initialized = true;
		this.cases = new Case[width][height];
		this.fill(new Case_void());
		
		int espX = panelWidth / this.width ;
		int espY = panelHeight / this.height;
		dim = espX >= espY ? espY : espX;
		
		imageBrosse = new BufferedImage(6*dim, 6*dim, BufferedImage.TYPE_INT_ARGB);
		bufferBrosse = imageBrosse.createGraphics();

		setThickness(1);
		
		generateGrid();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		//Drawing the background of the Panel in blue
		buffer.setColor(Color.LIGHT_GRAY);
		buffer.fillRect(0, 0, panelWidth, panelHeight);
		
		//Draw the cases on the buffer
		paintCases(buffer);
		//Draw the brosse on the buffer
		paintBrosse(buffer);
		//Draw the grid on the buffer
		buffer.drawImage(imageGrid, 0, 0, this);
		
		//Draw the image on the screen
		g.drawImage(image, 0, 0, this);

	}

	/**
	 * Draw the cases on the buffer
	 * @param g
	 */
	private void paintCases(Graphics g){
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ;  x++){
				if(cases[x][y] != null)g.drawImage(cases[x][y].image, x*dim, y*dim,dim,dim, null);
			}
		}

	}

	/**
	 * Draw the brosse image on the buffer
	 * @param g
	 */
	private void paintBrosse(Graphics g){

		int offSet = (dim*thickness)/2;
	
		g.drawImage(imageBrosse, mouse.x-offSet, mouse.y-offSet, this);

	}

	/**
	 * Generates the image of the grid
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

	/**
	 * Returns the coordinates of the grid cooresponding to that of the mouse
	 */
	private Vecteur getCoordinates(int x, int y){
		return new Vecteur(x/dim, y/dim);
	}

	/**
	 * Ajoute des cases en prenant en compte la brosse
	 */
	private void addCases(){
		
		Vecteur c = new Vecteur(0,0);
		int offset = (dim*thickness/2);

		for(int y = (mouse.y-offset)+dim/2 ; y < (mouse.y+offset) ; y+=dim){
			for(int x = (mouse.x-offset)+dim/2 ; x < (mouse.x+offset) ; x+=dim){
				c = getCoordinates(x, y);
				if( c.x >= 0 && c.x < width && c.y >= 0 && c.y < height){
					cases[c.x][c.y] = caseT;
				}
				
			}
		}
		
	}

	public void changeCase(Case c){
		this.caseT = c;
		setThickness(this.thickness);
		repaint();
	}

	public void fill(Case c){
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				cases[x][y] = c;
			}
		}
		
	}

	//INTERFACE//////////////////////
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		mouse.setLocation(e.getX(), e.getY());	
		addCases();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouse.setLocation(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mouse.setLocation(e.getX(), e.getY());	
		addCases();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}




}
