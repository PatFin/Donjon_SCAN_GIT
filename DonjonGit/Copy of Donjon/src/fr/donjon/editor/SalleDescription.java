/**
 * 
 */
package fr.donjon.editor;

import fr.donjon.classes.Case;

/**
 * @author Baptiste
 *
 */
public class SalleDescription {

	
	Case[][] matrix;
	int width;
	int height;
	int index;
	String name;
	
	
	public SalleDescription(String[] matrix, String width, String height,
			String name, String index) {
		super();
		
		this.width = Integer.parseInt(width);
		this.height = Integer.parseInt(height);
		this.name = name;
		this.index = Integer.parseInt(index);
		this.matrix = getMatrixFromString(matrix);
	}
	
	

	public SalleDescription(Case[][] matrix, int width, int height, int index,
			String name) {
		super();
		this.matrix = matrix;
		this.width = width;
		this.height = height;
		this.index = index;
		this.name = name;
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				matrix[x][y].setCollisionBoxLocation(y, x);
				index++;
			}
		}
	}



	public Case[][] getMatrixFromString(String[] str){
		
		Case[][] tab = new Case[width][height];
		int index = 0;
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				tab[x][y] = MapFileHandler.getCaseFromIndex(Integer.parseInt(str[index]));
				tab[x][y].setCollisionBoxLocation(y, x);
				index++;
			}
		}
		
		return tab;
	}
	
	public Case[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Case[][] matrix) {
		this.matrix = matrix;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	public String toString(){
		
		return "Name: "+name + "\n" +
				"Index: "+index + "\n" +
				"Width: "+width + "\n" +
				"Height: "+height + "\n";
	}
	
	
	

}
